package kaze.socket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by 枫叶 on 2016/4/29.
 */
public class TcpClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8888);
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF("yo man~");
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
