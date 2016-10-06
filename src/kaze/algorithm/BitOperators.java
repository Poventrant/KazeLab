package kaze.algorithm;

/**
 * Created by kaze on 2016/9/28.
 */
public class BitOperators {

    private static int add(int a, int b) {
        int base, carry;
        do {
            base = (a ^ b);
            carry = (a & b) << 1;
            a = base;
            b = carry;
        } while (carry != 0);
        return base;
    }

    private static int sub(int a, int b) {
        return add(a, add(~(b), 1));
    }

    private static int multiply(int a, int b) {
        int res = 0;
        for (int i = 0; i < b; i = add(i, 1)) {
            res = add(res, a);
        }
        return res;
    }

    private static int divide(int a, int b) {
        int res = 0, left = a;
        while (true) {
            left = sub(left, b);
            if (left < 0) {
                return res;
            } else {
                res = add(res, 1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(add(12, 89));
        System.out.println(sub(12, 89));
        System.out.println(multiply(12, 89));
        System.out.println(divide(89, 12));
    }
}
