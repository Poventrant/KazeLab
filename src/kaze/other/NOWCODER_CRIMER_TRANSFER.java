package kaze.other;

import java.util.Scanner;

/*
http://www.nowcoder.com/practice/b7b1ad820f0a493aa128ed6c9e0af448?tpId=49&tqId=29287&rp=1&ru=/ta/2016test&qru=/ta/2016test/question-ranking
 */
public class NOWCODER_CRIMER_TRANSFER {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int n, t, c, a[];
            n = in.nextInt();
            t = in.nextInt();
            c = in.nextInt();
            a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
            }
            if (n < c) {
                System.out.println(0);
                continue;
            }
            int l = 0, h = c - 1;
            int sum = 0;
            for (int i = l; i <= h; i++) {
                sum += a[i];
            }
            int cases = 0;
            while (true) {
                if (sum <= t) {
                    ++cases;
                }
                sum -= a[l];
                ++l;
                ++h;
                if (h >= n) break;
                sum += a[h];
            }
            System.out.println(cases);
        }
    }
}
