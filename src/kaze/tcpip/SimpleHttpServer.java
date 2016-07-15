package kaze.tcpip;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleHttpServer {

//    public static void main(String[] args) throws ExceptionLab {
//        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
//        server.createContext("/test", new MyHandler());
//        server.setExecutor(null); // creates a default executor
//        server.start();
//    }
//
//    static class MyHandler implements HttpHandler {
//        @Override
//        public void handle(HttpExchange t) throws IOException {
//            String response = "This is the response";
//            t.sendResponseHeaders(200, response.length());
//            OutputStream os = t.getResponseBody();
//            os.write(response.getBytes());
//            os.close();
//        }
//    }

    public static void main(String[] args) throws Exception {

        int port = 7777;
        ServerSocket serverSocket = new ServerSocket(port);
        System.err.println("Server listen on port : " + port);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.err.println("client connected!");

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            String s;
            while ((s = in.readLine()) != null) {
                System.out.println(s);
                if (s.isEmpty()) {
                    break;
                }
            }

            out.write("HTTP/1.0 200 OK\r\n");
            out.write("Date: Fri, 31 Dec 1999 23:59:59 GMT\r\n");
            out.write("Server: Apache/0.8.4\r\n");
            out.write("Content-Type: text/html\r\n");
            out.write("Expires: Sat, 01 Jan 2000 00:59:59 GMT\r\n");
            out.write("Last-modified: Fri, 09 Aug 1996 14:21:40 GMT\r\n");
            out.write("\r\n");
            out.write("<TITLE>Exemple</TITLE>");
            out.write("<P>Ceci est une page d'exemple.</P>");

            System.err.println("Connexion avec le client terminée");
            out.close();
        }
    }

//    public static void main(String args[]) throws IOException {
//        ServerSocket server = new ServerSocket(7777);
//        System.out.println("Listening for connection on port 7777 ....");
//        while (true) {
//            try (Socket socket = server.accept()) {
//                Date today = new Date();
//                String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + today;
//                socket.getOutputStream().write(httpResponse.getBytes("UTF-8"));
//            }
//        }
//    }

}