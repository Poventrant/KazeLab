package kaze.other;

import java.util.Scanner;

/**
 * Created by kaze on 2016/9/18.
 */
public class HW_ZHISHU_FACTOR {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLong()) {
            long n = in.nextInt();
            long a = n;
            while (a > 1) {
                long i = 2;
                while (i <= a) {
                    if (a % i == 0 && isPrime(i)) {
                        a /= i;
                        System.out.print(i + " ");
                    } else {
                        ++i;
                    }
                }
            }
        }
    }

    private static boolean isPrime(long a) {
        for (int i = 2; i * i <= a; i++) {
            if (a % i == 0) return false;
        }
        return true;
    }
}
