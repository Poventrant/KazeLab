package nio;

import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.Selector;
import java.nio.channels.SelectionKey;
import java.nio.ByteBuffer;
import java.io.IOException;
import java.util.Set;
import java.util.Iterator;
import java.net.InetSocketAddress;

public class SelectorExample {

    protected static NioBufferHandler bufHandler = new NioBufferHandler(256, 256, false);

    public static void main(String[] args)
            throws IOException {

        // Get selector
        Selector selector = Selector.open();

        System.out.println("Selector open: " + selector.isOpen());

        // Get server socket channel and register with selector
        ServerSocketChannel ssc = ServerSocketChannel.open();
        InetSocketAddress hostAddress = new InetSocketAddress("localhost", 5454);
        ssc.socket().bind(hostAddress, 100);
        ssc.socket().setSoTimeout(20000);
        ssc.configureBlocking(false);
        int ops = ssc.validOps();
        SelectionKey selectKy = ssc.register(selector, ops, null);

        ByteBuffer buffer = ByteBuffer.allocate(256);

        for (; ; ) {

            System.out.println("Waiting for select...");
            int noOfKeys = selector.select();

            System.out.println("Number of selected keys: " + noOfKeys);

            Set selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iter = selectedKeys.iterator();

            while (iter.hasNext()) {

                SelectionKey ky = iter.next();

                if (ky.isAcceptable()) {

                    // Accept the new client connection
                    SocketChannel client = ssc.accept();
                    client.configureBlocking(false);

                    // Add the new connection to the selector
                    client.register(selector, SelectionKey.OP_READ, new KeyAttachment());

                    System.out.println("Accepted new connection from client: " + client);
                } else if (ky.isReadable()) {
                    SocketChannel client = null;
                    try {
                        // Read the data from client
                        KeyAttachment keyAttachment = (KeyAttachment) ky.attachment();
                        System.out.println(keyAttachment.name);
                        client = (SocketChannel) ky.channel();

                        buffer.clear();
                        client.read(buffer);
                        byte [] bytes = new byte[buffer.position()];
                        buffer.flip();
                        buffer.get(bytes);
                        String output = new String(bytes);
                        System.out.println("Message read from client: " + output);

                        buffer.clear();
                        buffer.put("got it".getBytes());
                        buffer.flip();
                        client.write(buffer);

                        if (output.endsWith("Bye.")) {
                            client.close();
                            System.out.println("Client messages are complete; close.");
                        }
                    } catch (Exception e) {
                        if(client != null) {
                            client.close();
                        }
                    }


                } // end if (ky...)

                iter.remove();

            } // end while loop

        } // end for loop
    }

    static class KeyAttachment {
        String name = "kaze";
    }
}