package kaze.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class TcpServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8081);
            Socket socket = null;
            while(true) {
                socket = serverSocket.accept();
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                System.out.println(dis.readUTF());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                dos.writeUTF("helloworld");
                //close_time handle things...
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
