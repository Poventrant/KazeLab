package kaze.tcpip;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by kaze on 16-5-22.
 */
public class TcpServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9000);
            Socket socket = serverSocket.accept();
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            System.out.println(dis.readUTF());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF("???");
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
