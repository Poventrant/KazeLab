package kaze.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by 枫叶 on 2016/4/28.
 */
public class TcpServer {
    public static void main(String[] args) {
        File file = new File("C:/Users/lenovo/Desktop/socket.txt");
        if(!file.exists()) file.mkdirs();
        byte[] buf = new byte[1024];
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            Socket socket = serverSocket.accept();
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            System.out.println(dis.readUTF());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF("hellow");
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
