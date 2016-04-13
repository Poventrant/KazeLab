package nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelTest {
    public static void main(String[] args) {
        try {
            RandomAccessFile aFile = new RandomAccessFile("C:\\Users\\lenovo\\Desktop\\NOTE.txt", "rw");
            FileChannel inChannel = aFile.getChannel();

            ByteBuffer buf = ByteBuffer.allocate(1024);

            int bytesRead = inChannel.read(buf);
            while (bytesRead != -1) {

                System.out.println("Read " + bytesRead);
                buf.flip();

                /*while (buf.hasRemaining()) {
                    System.out.print((char) buf.get());
                }*/
                System.out.println(new String(buf.array(), "UTF-8"));

                buf.clear();
                bytesRead = inChannel.read(buf);
            }
            aFile.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
