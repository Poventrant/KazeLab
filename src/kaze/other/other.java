package kaze.other;

/**
 * Created by 枫叶 on 2016/4/10.
 */
public class other {
    public static void main(String[] args) {
        Integer i1 = 127;
        Integer i2 = 127;
        Integer i3 = 128;
        Integer i4 = 128;
        System.out.println(i1 == i2);
        System.out.println(i3 == i4);
        System.out.println(i3.equals(i4));
    }
}