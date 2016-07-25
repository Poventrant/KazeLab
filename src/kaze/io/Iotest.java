package kaze.io;

import java.io.*;

public class Iotest {
    public static void main(String[] args) {
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream("C:\\Users\\kaze\\Desktop\\hrmall_test.sql"));
            byte[] res = new byte[256];
            while (bis.read(res) != -1) {
                System.out.println(new String(res));
            }
            bis.close();

            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\kaze\\Desktop\\hrmall_test.sql"));
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
