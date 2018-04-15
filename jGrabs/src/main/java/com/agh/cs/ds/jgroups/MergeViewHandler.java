package com.agh.cs.ds.jgroups;

import org.jgroups.Address;
import org.jgroups.JChannel;
import org.jgroups.MergeView;
import org.jgroups.View;

import java.util.List;

public class MergeViewHandler extends Thread {
    private final JChannel channel;
    private final MergeView view;

    MergeViewHandler(JChannel channel, MergeView view) {
        this.channel = channel;
        this.view = view;
    }

    @Override
    public void run() {
        List<View> subgroups = view.getSubgroups();
        View firstSubgroup = subgroups.get(0);
        if (isChannelMemberOfSubgroup(firstSubgroup, channel)) {
            System.out.println("Member of primary partition, omitting merge changes");
        } else {
            try {
                channel.getState(firstSubgroup.getCoord(), DistributedStringMap.STATE_ACQUISITION_TIMEOUT);
            } catch (Exception e) {
                System.err.println(String.format("Couldn't acquire state of primary partition: %s", e));
            }
        }
    }

    private boolean isChannelMemberOfSubgroup(View subgroup, JChannel channel) {
        Address localAddress = channel.getAddress();
        return subgroup.getMembers().contains(localAddress);
    }
}
