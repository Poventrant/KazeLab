package kaze.io;

import java.io.*;

/**
 * Created by 枫叶 on 2016/3/31.
 */
public class FileReader {
    public static void main(String[] args) {
       /* try {
            java.io.FileReader fr = new java.io.FileReader("C:\\Users\\lenovo\\Desktop\\NOTE.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = null;
            while((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream("C:\\Users\\lenovo\\Desktop\\NOTE.txt"), "UTF-8" ));
            String line = null;
            while((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
