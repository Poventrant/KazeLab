package kaze.algorithm;

/**
 * Created by kaze on 2016/9/24.
 */
public class TwoBitVector {
    //00 不存在 01存在一次  11存在多次
    private final static int N = Integer.MAX_VALUE, BIT_PER_WORD = 4, MASK = 15;

    private static int[] bitMap = new int[(N - 1) / BIT_PER_WORD + 1];

    final static int NOT_CONTAIN = 0, CONTAIN_ONCE = 1, CONTAIN_MULTIPLE = 3;

    private static void set(int i) {
        int count = times(i);
        if (count == NOT_CONTAIN) {
            bitMap[i >> BIT_PER_WORD] |= (1 << ((i & MASK) << 1));
        } else if (count == CONTAIN_ONCE) {
            bitMap[i >> BIT_PER_WORD] |= (3 << ((i & MASK) << 1));
        }
    }

    private static int times(int i) {
        int step = (i & MASK) << 1;
        int temp = bitMap[i >> BIT_PER_WORD] & (3 << step);
        return temp >> step;
    }

    private static void clr(int i) {
        bitMap[i >> BIT_PER_WORD] &= ~(3 << ((i & MASK) << 1));
    }

    public static void main(String[] args) {
        set(100);
        set(100);
        set(100);

        set(101);
        set(101);
        clr(101);
        set(101);
        set(101);
        set(101);
        set(101);
    }

}
