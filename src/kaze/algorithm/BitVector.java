package kaze.algorithm;

/**
 * Created by kaze on 2016/9/10.
 */
public class BitVector {

    final static int N = 10000000, BIT_PER_WORD = 32, MASK =  0x1F;
    static int[] bitMap = new int[(N-1)/BIT_PER_WORD + 1];

    static void set(int i) {
        bitMap[i >> 5] |= (1 << (i&MASK));
    }

    static boolean test(int i) {
        return (bitMap[i >> 5] & (1 << (i & MASK))) != 0;
    }

    static void clr(int i) {
        bitMap[i >> 5] &= ~(1 << (i&MASK));
    }

    public static void main(String[] args) {
        set(100);
        System.out.println(test(100));
        System.out.println(test(101));
        set(101);
        System.out.println(test(101));
        clr(101);
        System.out.println(test(101));
    }
}
