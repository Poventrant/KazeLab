package kaze.other;

import java.util.Scanner;

/**
 * Created by kaze on 2016/8/29.
 */
public class HW_LEAST_CHAR {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()) {
            String line = scanner.next();
            byte[] mask = new byte[128];
            for (int i = 0; i < line.length(); i++) {
                ++ mask[line.charAt(i) - 'a'];
            }
            int _min_ = Byte.MAX_VALUE;
            for (int i = 0; i < mask.length; i++) {
                if(mask[i] != 0) _min_ = Math.min(_min_, mask[i]);
            }
            for (int i = 0; i < line.length(); i++) {
                byte temp = mask[line.charAt(i) - 'a'];
                if(temp != _min_ && temp != 0) {
                    System.out.print(line.charAt(i));
                }
            }
            System.out.println();
        }
    }
}
