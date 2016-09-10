package kaze.other;

import java.util.Scanner;

/**
 http://www.nowcoder.com/practice/3a571cdc72264d76820396770a151f90?tpId=49&tqId=29292&rp=2&ru=/ta/2016test&qru=/ta/2016test/question-ranking
 */
public class NOWCODER_MAX_INTERVAL {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int n = in.nextInt();
            if(n<=2) {
                System.out.println(0);
            } else {
                int d[] = new int[n - 1];
                int max = Integer.MIN_VALUE,
                pre_max = Integer.MIN_VALUE,
                post_max = Integer.MIN_VALUE;
                int last = in.nextInt();
                for (int i = 1; i < n; i++) {
                    int temp = in.nextInt();
                    d[i - 1] = temp - last;
                    last = temp;
                    if (max < d[i - 1]) {
                        max = d[i - 1];
                    }
                    if(i>1 && pre_max < d[i - 1]) {
                        pre_max = d[i - 1];
                    }
                    if(i<n-1 && post_max < d[i - 1]) {
                        post_max = d[i - 1];
                    }
                }
                int _min = max;
                for (int i = 0; i < d.length - 1; i++) {
                    int temp = d[i + 1] + d[i];
                    if (temp > max) {
                        _min = Math.min(_min, temp);
                    }
                }
                System.out.println(Math.min(_min, Math.min(post_max, pre_max)));
            }
        }
    }
}
