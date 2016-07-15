package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by kaze on 16-7-15.
 */
public class ServerSocketChannelServer {
    public static void main(String[] args) {
        try {
            ServerSocketChannel serverSock = ServerSocketChannel.open();
            InetSocketAddress addr = new InetSocketAddress("localhost", 5454);
            serverSock.socket().bind(addr);
            serverSock.configureBlocking(true); //mimic APR behavior
            serverSock.socket().setSoTimeout(20000);

            SocketChannel socket = serverSock.accept();

            while (true) {
                ByteBuffer buffer = ByteBuffer.allocate(256);
                socket.read(buffer);
                String output = new String(buffer.array()).trim();

                System.out.println("Message read from client: " + output);

                if(output.endsWith("Bye.")) {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
