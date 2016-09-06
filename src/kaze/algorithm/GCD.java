package kaze.algorithm;

/**
 * Created by kaze on 2016/7/26.
 */
public class GCD {

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static void main(String[] args) {
        System.out.println(gcd(598443985, 815));
    }
}
