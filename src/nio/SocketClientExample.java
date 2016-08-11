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

        ByteBuffer buffer = ByteBuffer.allocate(256);

        System.out.println("Client sending messages to server...");

        // Send messages to server

        String [] messages = new String [] {"Time goes fast.", "What now?", "Bye."};

        for (int i = 0; i < messages.length; i++) {

            buffer.clear();
            byte [] message = messages[i].getBytes();
            buffer.put(message);
            buffer.flip();
            client.write(buffer);
//
//            buffer.clear();
//            int flag = client.read(buffer);
//            client.configureBlocking(false);
//            while (flag != -1) {
//                byte[] dst = new byte[buffer.position()];
//                buffer.flip();
//                buffer.get(dst);
//                String data = new String(dst);
//                System.out.print(data);
//                buffer.clear();
//                if("done!".equals(data)) break;
//                flag = client.read(buffer);
//            }
//            buffer.clear();
//            client.configureBlocking(true);
//            Thread.sleep(250);
        }

        client.close();				
    }
}