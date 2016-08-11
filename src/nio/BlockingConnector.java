package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;

/**
 * Created by kaze on 2016/7/27.
 */
public class BlockingConnector {
    public static void main(String[] args) {
        try {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.bind(new InetSocketAddress(8877));
            ssc.socket().setSoTimeout(20000);
            ssc.configureBlocking(true);

            while (true) {
                System.out.println("accept" + ssc.accept().getRemoteAddress());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
