/**
 * A simple class that monitors the data and existence of a ZooKeeper
 * node. It uses asynchronous ZooKeeper APIs.
 */

import org.apache.zookeeper.*;
import org.apache.zookeeper.AsyncCallback.StatCallback;
import org.apache.zookeeper.KeeperException.Code;
import org.apache.zookeeper.data.Stat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataMonitor implements Watcher, StatCallback, AsyncCallback.ChildrenCallback {

    private final ZooKeeper zk;

    private final String znode;

    private final Watcher chainedWatcher;

    private final DataMonitorListener listener;

    private boolean dead;

    private byte prevData[];

    private List<String> children = new ArrayList<>();

    DataMonitor(ZooKeeper zk, String znode, Watcher chainedWatcher,
                DataMonitorListener listener) {
        this.zk = zk;
        this.znode = znode;
        this.chainedWatcher = chainedWatcher;
        this.listener = listener;
        // Get things started by checking if the node exists. We are going
        // to be completely event driven
        zk.exists(znode, true, this, null);
        zk.getChildren(znode, true, this, null);
    }

    boolean isDead() {
        return dead;
    }

    @Override
    public void processResult(int rc, String path, Object ctx, List<String> children) {
        if (path.equals(znode) && children != null) {
            this.children = children;
            System.out.println(String.format("Current number of children of %s : (direct - %d, non-direct - %d)", znode, children.size(), getChildrenCount(znode)));
        }
    }

    private int getChildrenCount(String node) {
        try {
            List<String> children = zk.getChildren(node, false);
            return children.size() + children.stream().map(c -> node + '/' + c).mapToInt(this::getChildrenCount).sum();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return 0;
    }

    /**
     * Other classes use the DataMonitor by implementing this method
     */
    public interface DataMonitorListener {
        void exists(byte data[]);

        void closing(int rc);
    }

    void printTree(String parent, int depth) {
        try {
            String tabPrefix = new String(new char[depth]).replace("\0", "\t");
            String paths[] = parent.split("/");
            String name = paths[paths.length - 1];
            System.out.println(tabPrefix + "/" + name);
            zk.getChildren(parent, false).forEach(child -> printTree(parent + "/" + child, depth + 1));
        } catch (Exception e) {
        }
    }

    public void process(WatchedEvent event) {
        String path = event.getPath();
        if (event.getType() == Event.EventType.None) {
            // We are are being told that the state of the
            // connection has changed
            switch (event.getState()) {
                case SyncConnected:
                    // In this particular example we don't need to do anything
                    // here - watches are automatically re-registered with
                    // server and any watches triggered while the client was
                    // disconnected will be delivered (in order of course)
                    break;
                case Expired:
                    // It's all over
                    dead = true;
                    listener.closing(KeeperException.Code.SessionExpired);
                    break;
            }
        } else if (path != null && path.equals(znode)) {
            zk.exists(znode, true, this, null);
            zk.getChildren(znode, true, this, null);
        }
        if (chainedWatcher != null) {
            chainedWatcher.process(event);
        }
    }

    public void processResult(int rc, String path, Object ctx, Stat stat) {
        boolean exists;
        switch (rc) {
            case Code.Ok:
                exists = true;
                break;
            case Code.NoNode:
                exists = false;
                break;
            case Code.SessionExpired:
            case Code.NoAuth:
                dead = true;
                listener.closing(rc);
                return;
            default:
                // Retry errors
                zk.exists(znode, true, this, null);
                return;
        }

        byte b[] = null;
        if (exists) {
            try {
                b = zk.getData(znode, false, null);
            } catch (KeeperException e) {
                // We don't need to worry about recovering now. The watch
                // callbacks will kick off any exception handling
                e.printStackTrace();
            } catch (InterruptedException e) {
                return;
            }
        }
        if ((b == null && b != prevData)
                || (b != null && !Arrays.equals(prevData, b))) {
            listener.exists(b);
            prevData = b;
        }
    }
}