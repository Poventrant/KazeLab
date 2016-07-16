package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import static nio.Poller.poller;

/**
 * Created by kaze on 2016/7/16.
 */
public class Acceptor implements Runnable {

    private final ServerSocketChannel serverSocketChannel;

    public Acceptor() throws IOException {
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress("127.0.0.1", 80));
        serverSocketChannel.socket().setSoTimeout(20000);
        serverSocketChannel.configureBlocking(true);
    }

    @Override
    public void run() {
        while (true) {
            try {
                SocketChannel socket = serverSocketChannel.accept();
                poller.register(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
