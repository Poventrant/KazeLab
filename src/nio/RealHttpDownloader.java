package nio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

public class RealHttpDownloader {

    public static File download(URI uri, String fileName) throws IOException {
        Path path = Paths.get(fileName);
        long totalBytesRead = 0L;
        HttpURLConnection con = (HttpURLConnection) uri.resolve(fileName).toURL().openConnection();
        con.setReadTimeout(10000);
        con.setConnectTimeout(10000);
        try (ReadableByteChannel rbc = Channels.newChannel(con.getInputStream());
             FileChannel fileChannel = FileChannel.open(path, EnumSet.of(StandardOpenOption.CREATE, StandardOpenOption.WRITE));) {
            totalBytesRead = fileChannel.transferFrom(rbc, 0, 1 << 24); // download file with max size 16MB
            System.out.println("totalBytesRead = " + totalBytesRead);
            fileChannel.close();
            rbc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return path.toFile();
    }

    public static void main(String[] args) {
        try {
            File file = download(new URI("http://s.cn.bing.net/az/hprichbg/rb/"), "BlackSea_ZH-CN9772885358_1920x1080.jpg");
            System.out.printf(file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
