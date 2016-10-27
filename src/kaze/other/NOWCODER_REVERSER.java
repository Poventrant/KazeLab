package kaze.other;

import java.util.Scanner;

/**
 http://www.nowcoder.com/practice/655a43d702cd466093022383c24a38bf?tpId=49&tqId=29295&rp=2&ru=/ta/2016test&qru=/ta/2016test/question-ranking
 */
public class NOWCODER_REVERSER {

    final static int[] mask = new int[2];

    static boolean isReverser(StringBuilder str) {
        mask[0] = 0;
        mask[1] = str.length() - 1;
        while (mask[0] <= mask[1]) {
            if (str.charAt(mask[0]) != str.charAt(mask[1])) {
                return false;
            }
            ++mask[0];
            --mask[1];
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            StringBuilder line = new StringBuilder(in.next());
            if (isReverser(line)) {
                System.out.println("YES");
                continue;
            }
            line.insert(mask[0], line.charAt(mask[1]));
            int o0 = mask[0], o1 = mask[1];
            if (isReverser(line)) {
                System.out.println("YES");
                continue;
            }
            line.deleteCharAt(o0);

            line.insert(o1 + 1, line.charAt(o0));
            if (isReverser(line)) {
                System.out.println("YES");
                continue;
            }
            line.deleteCharAt(o1 + 1);
            System.out.println("NO");
        }
    }
}
