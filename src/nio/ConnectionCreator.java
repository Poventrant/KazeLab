package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * Created by kaze on 2016/7/27.
 */
public class ConnectionCreator {
    public static void main(String[] args) {
        while(true) {
            try {
                SocketChannel sc = SocketChannel.open(new InetSocketAddress("localhost", 8877));
                sc.close();
                Thread.sleep(1);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
