package kaze.algorithm;


import java.util.Scanner;

/**
 * Created by kaze on 2016/9/6.
 */
public class JosephCircle {
    public static void main(String[] args) {
        int n, step = 3;
        Scanner in = new Scanner(System.in);
        while(in.hasNextInt()) {
            n = in.nextInt();
            if(n<=1) {
                System.out.println(0);
                continue;
            }
            int a[] = new int[n+1];
            for (int i = 2; i <= n; i++) {
                a[i] = (a[i-1] + step) % i;
            }
            System.out.println(a[n]);
        }
    }
}
