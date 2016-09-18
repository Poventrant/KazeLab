package kaze.huaweioj;

import java.util.Scanner;

/**
 * Created by kaze on 2016/9/17.
 */
public class HW_2168 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        StringBuffer line = new StringBuffer(in.nextLine());
        StringBuffer o = new StringBuffer();
        for (int i = 0; i < line.length(); i++) {
            if(isLegal(line.charAt(i))) {
                o.append(line.charAt(i));
            }
        }
        for (int i = 0; i < o.length(); i++) {
            for (int j = 0; j < o.length() - i - 1; j++) {
                char a = Character.toLowerCase(o.charAt(j));
                char b = Character.toLowerCase(o.charAt(j+1));
                if(a > b) {
                    char temp = o.charAt(j);
                    o.setCharAt(j, o.charAt(j+1));
                    o.setCharAt(j+1, temp);
                }
            }
        }
        for (int i = 0, c = 0; i < line.length(); i++) {
            if(isLegal(line.charAt(i))) {
               line.setCharAt(i, o.charAt(c++));
            }
        }
        System.out.println(line);

//        for (int i = 0; i < line.length(); i++) {
//            int len = line.length() - i - 1, j = 0;
//            while(j < len && !isLegal( line.charAt(j) ) ) {
//                ++j;
//            }
//            while (j < len) {
//                int idx0 = j;
//                int idx1 = idx0+1;
//                while(idx1 <= len && !isLegal( line.charAt(idx1) ) ) {
//                    ++idx1;
//                }
//                if(idx1 > len) break;
//
//                char a = Character.toLowerCase(line.charAt(idx0));
//                char b = Character.toLowerCase(line.charAt(idx1));
//                if(a > b) {
//                    char temp = line.charAt(idx0);
//                    line.setCharAt(idx0, line.charAt(idx1));
//                    line.setCharAt(idx1, temp);
//                }
//                j = idx1;
//            }
//        }
//        System.out.println(line);
    }

    private static boolean isLegal(char c) {
        return (c >= 'A' && c <= 'Z')
                || (c >= 'a' && c <= 'z');
    }
}
