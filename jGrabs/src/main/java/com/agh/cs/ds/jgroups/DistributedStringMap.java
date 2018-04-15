package com.agh.cs.ds.jgroups;

import com.agh.cs.ds.jgroups.DistributedMapOperationProtos.DistributedMapOperation;
import com.google.protobuf.InvalidProtocolBufferException;
import org.jgroups.*;
import org.jgroups.protocols.*;
import org.jgroups.protocols.pbcast.*;
import org.jgroups.stack.ProtocolStack;
import org.jgroups.util.Util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import static com.agh.cs.ds.jgroups.DistributedStringMap.UserOperations.*;

public final class DistributedStringMap extends ReceiverAdapter implements SimpleStringMap {
    private static final String CHANNEL_NAME = "ROZPROSZONY_GRABEK";
    static final long STATE_ACQUISITION_TIMEOUT = 10000;

    interface UserOperations {
        String PUT = "put";
        String GET = "get";
        String CONTAINS_KEY = "ck";
        String REMOVE = "rm";
        String ALL = "all";
        String EXIT = "exit";
    }

    private final HashMap<String, String> state = new HashMap<>();
    private final JChannel channel = new JChannel(false);

    DistributedStringMap() {
    }

    void start() throws Exception {
        initStack(channel);
        channel.setReceiver(this);
        channel.connect(CHANNEL_NAME);
        channel.getState(null, STATE_ACQUISITION_TIMEOUT);
        readLoop();
        channel.close();
    }

    private void readLoop() {
        boolean isRunning = true;
        Scanner scanner = new Scanner(System.in);
        while (isRunning) {
            String[] commandParts = scanner.nextLine().split(" ");
            String operation = commandParts[0];
            switch (operation) {
                case PUT:
                    if (commandParts.length == 3)
                        System.out.println(String.format("old value under %s : %s", commandParts[1], put(commandParts[1], commandParts[2])));
                    break;
                case GET:
                    if (commandParts.length == 2)
                        System.out.println(String.format("%s : %s", commandParts[1], get(commandParts[1])));
                    break;
                case CONTAINS_KEY:
                    if (commandParts.length == 2)
                        System.out.println(String.format("%s is present: %s", commandParts[1], containsKey(commandParts[1])));
                    break;
                case REMOVE:
                    if (commandParts.length == 2)
                        System.out.println(String.format("removing '%s': %s", commandParts[1], remove(commandParts[1])));
                    break;
                case ALL:
                    System.out.println("Printing map contents");
                    state.forEach((key, value) -> System.out.println(String.format("%s : %s", key, value)));
                    break;
                case EXIT:
                    isRunning = false;
                    break;
                default:
                    System.out.println(String.format("Invalid command '%s'", Arrays.toString(commandParts)));
            }
        }
    }

    private void initStack(JChannel channel) throws Exception {
        ProtocolStack stack = new ProtocolStack();
        channel.setProtocolStack(stack);
        stack.addProtocol(new UDP().setValue("mcast_group_addr", InetAddress.getByName("224.19.9.10")))
                .addProtocol(new PING())
                .addProtocol(new MERGE3())
                .addProtocol(new FD_SOCK())
                .addProtocol(new FD_ALL().setValue("timeout", 12000).setValue("interval", 3000))
                .addProtocol(new VERIFY_SUSPECT())
                .addProtocol(new BARRIER())
                .addProtocol(new NAKACK2())
                .addProtocol(new UNICAST3())
                .addProtocol(new STABLE())
                .addProtocol(new GMS())
                .addProtocol(new UFC())
                .addProtocol(new MFC())
                .addProtocol(new FRAG2())
                .addProtocol(new STATE_TRANSFER())
//                .addProtocol(new SEQUENCER())
                .addProtocol(new FLUSH());
        stack.init();
    }

    // ReceiverAdapter interface
    @Override
    public void viewAccepted(View view) {
        if (view instanceof MergeView) {
            System.out.println("Trying to perform merge");
            MergeViewHandler handler = new MergeViewHandler(channel, (MergeView) view);
            handler.start();
        } else {
            super.viewAccepted(view);
        }
    }

    @Override
    public void receive(Message msg) {
        try {
            DistributedMapOperation operation = (DistributedMapOperation) msg.getObject();
            switch (operation.getType()) {
                case UPDATE:
                    handlePutOperation(operation.getKey(), operation.getValue());
                    break;
                case REMOVE:
                    handleRemoveOperation(operation.getKey());
                    break;
                default:
                    throw new InvalidProtocolBufferException("Unknown message type");
            }
        } catch (InvalidProtocolBufferException e) {
            System.err.println(String.format("Couldn't parse incoming message %s", e));
        }
    }


    @Override
    public void getState(OutputStream output) throws Exception {
        synchronized (state) {
            Util.objectToStream(state, new DataOutputStream(output));
        }
    }

    // since protobuf can't handle generics, there's no fast and elegant way of making DistributedStringMap a generic one
    @Override
    public void setState(InputStream input) throws Exception {
        HashMap<String, String> newState = (HashMap<String, String>) Util.objectFromStream(new DataInputStream(input));
        synchronized (state) {
            state.clear();
            state.putAll(newState);
        }
        System.out.println(String.format("%s: new state acquired", LocalDateTime.now()));
    }
    //e ReceiverAdapter

    // SimpleStringMap interface
    @Override
    public boolean containsKey(String key) {
        return state.containsKey(key);
    }

    @Override
    public String get(String key) {
        return state.get(key);
    }


    @Override
    public String put(String key, String value) {
        DistributedMapOperation putOperation = DistributedMapOperation.newBuilder()
                .setType(DistributedMapOperation.OperationType.UPDATE)
                .setKey(key)
                .setValue(value)
                .build();
        String oldValue = null;
        try {
            oldValue = handlePutOperation(key, value);
            channel.send(new Message().setObject(putOperation));
        } catch (Exception e) {
            System.err.println(String.format("Couldn't perform PUT %s", e));
        }

        return oldValue;
    }

    @Override
    public String remove(String key) {
        DistributedMapOperation removeOperation = DistributedMapOperation.newBuilder()
                .setType(DistributedMapOperation.OperationType.REMOVE)
                .setKey(key)
                .build();

        String oldValue = null;
        try {
            oldValue = handleRemoveOperation(key);
            channel.send(new Message().setObject(removeOperation));
        } catch (Exception e) {
            System.err.println(String.format("Couldn't perform REMOVE %s", e));
        }
        return oldValue;
    }
    //e SimpleStringMap interface

    private String handlePutOperation(String key, String value) {
        String oldValue;
        synchronized (state) {
            oldValue = state.put(key, value);
        }
        return oldValue;
    }

    private String handleRemoveOperation(String key) {
        String oldValue;
        synchronized (state) {
            oldValue = state.remove(key);
        }
        return oldValue;
    }
}
