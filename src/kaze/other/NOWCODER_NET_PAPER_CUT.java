package kaze.other;

import java.util.Scanner;

/**
 http://www.nowcoder.com/practice/65865c6644154bb4acca764b1480ecbb?tpId=49&tqId=29288&rp=2&ru=/ta/2016test&qru=/ta/2016test/question-ranking
 */
public class NOWCODER_NET_PAPER_CUT {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int n = in.nextInt();
            int maxX = Integer.MIN_VALUE,
                    maxY = Integer.MIN_VALUE,
                    minX = Integer.MAX_VALUE,
                    minY = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                int x = in.nextInt();
                int y = in.nextInt();
                if (x > maxX) maxX = x;
                if (x < minX) minX = x;
                if (y > maxY) maxY = y;
                if (y < minY) minY = y;
            }
            int len = Math.max((maxX - minX),(maxY - minY));
            System.out.println(len*len);
        }
    }
}
