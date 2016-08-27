package kaze.huaweioj;

import java.util.Scanner;

public class HW_2288 {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        while(in.hasNextInt()) {
            int n = in.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = in.nextInt();

            int[] l = new int[n], r = new int[n];
            int max = 0;

            for (int i = 0; i < n; i++) {
                l[i] = 1;
                for (int j = 0; j < i; j++) {
                    if (a[j] < a[i]) {
                        l[i] = Math.max(l[i], l[j] + 1);
                    }
                }
            }

            for (int i = n - 1; i >= 0; i--) {
                r[i] = 1;
                for (int j = n - 1; j > i; j--) {
                    if (a[j] < a[i]) {
                        r[i] = Math.max(r[i], r[j] + 1);
                    }
                }
                max = Math.max(max, l[i] + r[i] - 1);
            }

            System.out.println(n - max);
        }
        in.close();
    }

}
