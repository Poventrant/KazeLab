package nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by kaze on 2016/7/15.
 */
public class Poller implements Runnable {

    static Poller poller = new Poller();

    private Selector selector = null;
    private BlockingQueue<PollerEvent> events = null;

    private boolean running;

    public void init() {
        try {
            synchronized (Selector.class) {
                selector = Selector.open();
            }
            events = new ArrayBlockingQueue<>(50);
            running = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addEvent(PollerEvent event) {
        events.offer(event);
        selector.wakeup();
    }

    @Override
    public void run() {
        while (running) {
            try {
                int keyCount = selector.select();
                System.out.println("wake");
                if(keyCount == 0) events();
                Iterator<SelectionKey> iterator = keyCount > 0 ? selector.selectedKeys().iterator() : null;
                while (iterator != null && iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    if(selectionKey.isReadable()) {
                        SocketChannel sc = (SocketChannel) selectionKey.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(256);
                        sc.read(byteBuffer);
                        System.out.println(new String(byteBuffer.array()));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void events() {
        PollerEvent pe = null;
        while ( (pe = events.poll()) != null ) {
            try {
                pe.run();
            } catch ( Throwable x ) {
            }
        }
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public static void main(String[] args) throws InterruptedException {
        poller.init();
        new Thread(poller).start();
        Thread.sleep(5000);
    }

    public void register(SocketChannel socket) {
        try {
            socket.configureBlocking(false);
            PollerEvent pollerEvent = new PollerEvent(socket, selector);
            poller.addEvent(pollerEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
