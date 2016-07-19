package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketClientExample {

    public static void main (String [] args)
            throws IOException, InterruptedException {

        InetSocketAddress hostAddress = new InetSocketAddress("localhost", 5454);
        SocketChannel client = SocketChannel.open(hostAddress);

        NioBufferHandler bufhandler = new NioBufferHandler(256, 256, false);

        System.out.println("Client sending messages to server...");

        // Send messages to server

        String [] messages = new String [] {"Time goes fast.", "What now?", "Bye."};

        for (int i = 0; i < messages.length; i++) {

            byte [] message = messages[i].getBytes();
            ByteBuffer buffer = bufhandler.getWriteBuffer();
            buffer.put(message);
            buffer.rewind();    //or flip
            client.write(buffer);

            ByteBuffer buffer1 = bufhandler.getReadBuffer();
            client.read(buffer1);
            System.out.println(new String(buffer1.array()).trim());

            bufhandler.reset();
            Thread.sleep(1000);
        }

        client.close();				
    }
}