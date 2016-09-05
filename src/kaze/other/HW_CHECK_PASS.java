package kaze.other;

import java.util.Scanner;

/**
 * Created by kaze on 2016/9/4.
 */
public class HW_CHECK_PASS {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        loop:
        while (scanner.hasNext()) {
            String pass = scanner.next();
            int len = pass.length();
            if(len <= 8) {
                System.out.println("NG");
                continue;
            }
            int flag[] = new int[] {0, 0, 0, 0};
            for (int i = 0; i < len; i++) {
                char letter = pass.charAt(i);
                if(letter <= '9' && letter >= '0') {
                    flag[0] = 1;
                } else if(letter <= 'z' && letter >= 'a') {
                    flag[1] = 1;
                } else if(letter <= 'Z' && letter >= 'A') {
                    flag[2] = 1;
                } else {
                    flag[3] = 1;
                }
            }
            if(flag[0] + flag[1] + flag[2] + flag[3] < 3) {
                System.out.println("NG");
                continue;
            }
            for (int i = 3; i <= len; i++) {
                String temp = pass.substring(i-3, i);
                String sub = pass.substring(i);
                if(sub.contains(temp)) {
                    System.out.println("NG");
                    continue loop;
                }
            }
            System.out.println("OK");
        }
    }
}
