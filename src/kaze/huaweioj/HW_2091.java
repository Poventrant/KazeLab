package kaze.huaweioj;

import java.util.Scanner;

/**
 * Created by kaze on 2016/9/17.
 */
public class HW_2091 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), maxWeight = 0;
        int w[] = new int[n], t[] = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            t[i] = in.nextInt();
            maxWeight += t[i] * w[i];
        }
        boolean map[] = new boolean[n * 10 * 10];

        map[0] = true;
        for (int i = 0; i < w.length; i++) {
            for (int j = 0; j < t[i]; j++) {
                for (int k = maxWeight; k >= w[i]; k--) {
                    if (map[k - w[i]]) map[k] = true;
                }
            }
        }

        int count = 0;
        for (int i = 0; i < map.length; i++) {
            if (map[i]) ++count;
        }
        System.out.println(count);
    }
}
