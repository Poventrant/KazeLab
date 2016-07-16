package nio;

import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * Created by kaze on 2016/7/15.
 */
public class PollerEvent implements Runnable{

    private final Selector selector;
    SocketChannel socketChannel;

    public PollerEvent(SocketChannel socket, Selector selector) {
        this.socketChannel = socket;
        this.selector = selector;
    }

    public void doRun() {
        try {
            socketChannel.register(selector, SelectionKey.OP_READ);
        } catch (ClosedChannelException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        doRun();
    }
}
