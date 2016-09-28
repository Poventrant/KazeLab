package kaze.algorithm;

/**
 * Created by kaze on 2016/9/28.
 */
public class BitOperators {

    static int add(int a, int b) {
        int base, carry;
        do {
            base = (a ^ b);
            carry = (a & b) << 1;
            a = base;
            b = carry;
        } while (carry != 0);
        return base;
    }

    public static void main(String[] args) {
        System.out.println(add(12, 89));
    }
}
