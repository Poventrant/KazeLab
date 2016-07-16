package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class BlockSelectorExample {

    protected static NioBufferHandler bufHandler = new NioBufferHandler(256, 256, false);

    public static void main(String[] args)
            throws IOException {
        Selector selector = Selector.open();
        System.out.println("Selector open: " + selector.isOpen());
        ServerSocketChannel ssc = ServerSocketChannel.open();
        InetSocketAddress hostAddress = new InetSocketAddress("localhost", 5454);
        ssc.socket().bind(hostAddress, 100);
        ssc.socket().setSoTimeout(20000);
        ssc.configureBlocking(true);

        System.out.println("Waiting for select...");
        SocketChannel sc = ssc.accept();
        sc.configureBlocking(false);
        sc.register(selector, SelectionKey.OP_READ);

        for (; ; ) {
            int keyCount = selector.select();
            if(keyCount > 0) {
                Set selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> iter = selectedKeys.iterator();
                while (iter.hasNext()) {
                    SelectionKey ky = iter.next();
                    if (ky.isReadable()) {
                        SocketChannel client = (SocketChannel) ky.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(256);
                        client.read(buffer);
                        String output = new String(buffer.array()).trim();
                        System.out.println("Message read from client: " + output);
                        if (output.endsWith("Bye.")) {
                            client.close();
                            System.out.println("Client messages are complete; close.");
                        }
                    } // end if (ky...)
                    iter.remove();
                } // end while loop
            }
        } // end for loop
    }
}