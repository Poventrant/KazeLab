package kaze.other;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

/**
 http://www.nowcoder.com/practice/62cdf520b9d94616b6644ac03a0306ff?tpId=49&tqId=29309&rp=2&ru=/ta/2016test&qru=/ta/2016test/question-ranking
 */
public class NOWCODER_LIGHT_MAX_COVER {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        DecimalFormat format = new DecimalFormat("#0.00");
        while (in.hasNextInt()) {
            int n = in.nextInt();
            int l = in.nextInt();
            int _max = -1;
            int p[] = new int[n];
            for (int i = 0; i < n; i++) {
                p[i] = in.nextInt();
            }
            Arrays.sort(p);
            for (int i = 0; i < n - 1; i++) {
                if (_max < p[i + 1] - p[i]) {
                    _max = p[i + 1] - p[i];
                }
            }
            double res = _max/2.0;
            int left = p[0], right = l-p[n-1];
            System.out.println(format.format(Math.max(res, Math.max(left, right))));
        }
    }
}
