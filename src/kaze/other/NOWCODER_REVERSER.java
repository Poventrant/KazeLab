package kaze.other;

import java.util.Scanner;

/**
 * Created by kaze on 2016/9/8.
 */
public class NOWCODER_REVERSER {

    static int isReverser(StringBuilder str) {
        int l = 0, h = str.length()-1;
        while(l<h) {
            if(str.charAt(l) != str.charAt(h)) {
                return l;
            }
            ++l;
            --h;
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            StringBuilder line = new StringBuilder(in.next());
            if(isReverser(line) == -1) {
                System.out.println("YES");
                continue;
            }
            for (int i = 0; i < line.length(); i++) {
//                String str = line.setCharAt(i, );
            }
        }
    }
}
