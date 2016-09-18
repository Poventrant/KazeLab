package kaze.algorithm;


import java.util.Scanner;

/**
 * Created by kaze on 2016/9/6.
 */
public class JosephCircle {
    public static void main(String[] args) {
        int n, step = 13;
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            n = in.nextInt();
            int a[] = new int[n + 1];
            a[1] = 3;
            for (int i = 2; i <= n; i++) {
                a[i] = (a[i - 1] + step) % i;
            }
            System.out.println(a[n]);
        }
    }
}
