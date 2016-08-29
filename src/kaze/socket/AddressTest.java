package kaze.socket;

import java.io.IOException;
import java.net.InetAddress;

/**
 * Created by kaze on 2016/8/28.
 */
public class AddressTest {
    public static void main(String[] args) throws IOException {
        InetAddress addr = InetAddress.getByName("www.baidu.com");
        System.out.println(addr.getHostAddress());
        System.out.println(addr.isReachable(1000));
    }
}
