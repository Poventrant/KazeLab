package kaze.algorithm;

/**
 * Created by kaze on 2016/7/26.
 */
public class GCD {

    private static int gcd(int a, int b) {
        if(a<b) return gcd(b,a);
        if(b==0) return a;
        else {
            return gcd(a-b, b);
        }
    }

    public static void main(String[] args) {
        System.out.println(gcd(81, 995));
    }
}
