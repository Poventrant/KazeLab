package nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class SelectorExample {

    protected static NioBufferHandler bufHandler = new NioBufferHandler(256, 256, false);

    public static void main(String[] args)
            throws IOException {

        String filePath = SelectorExample.class.getClassLoader().getResource("oscache.properties").getFile();
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
                    client.socket().setSoTimeout(20000);
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
                        buffer.rewind(); //or flip
                        buffer.get(bytes);
                        String output = new String(bytes);
                        System.out.println("Message read from client: " + output);

                        buffer.clear();

                        RandomAccessFile out = new RandomAccessFile(filePath, "rw");
                        FileChannel fc = out.getChannel();
                        int bytesread = fc.read(buffer);
                        while (bytesread != -1) {
                            buffer.flip();
                            client.write(buffer);
                            buffer.compact();
                            bytesread = fc.read(buffer);
                        }
                        // 为了让客户端有时间将上面的数据都从channel中读出来，使得之后能单独的读取到"done!"
                        Thread.sleep(1000);
                        buffer.clear();
                        buffer.put("done!".getBytes());
                        buffer.flip();
                        client.write(buffer);

                        if (output.endsWith("Bye.")) {
                            client.close();
                            System.out.println("Client messages are complete; close.");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
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