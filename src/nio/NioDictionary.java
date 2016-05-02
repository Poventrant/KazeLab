package nio;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * Created by 枫叶 on 2016/4/23.
 */
public class NioDictionary {
    public static void main(String[] args) {
        try {
            Path path= Paths.get("F:\\Cadence\\SPB_Data\\pixiv\\public\\images\\pixiv\\1337356");
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
                for (Path entry : stream) {
                    if (!Files.isDirectory(entry)) {
                        System.out.println(entry.getFileName());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
