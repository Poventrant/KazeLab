package kaze.other;

/**
 * Created by 枫叶 on 2016/4/28.
 */
public class MemAllocTest {
    static int x = 0;
    public static void main(String[] args) {
        int a = 3;
        int b = 3;
        System.out.println(a == b);
        Integer c = 127;
        Integer d = 127;
        System.out.println(c == d);
        Integer e = 128;
        Integer f = 128;
        System.out.println(e == f);

    }
}
