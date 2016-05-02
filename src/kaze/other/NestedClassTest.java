package kaze.other;

/**
 * Created by 枫叶 on 2016/4/30.
 */
public class NestedClassTest {
    public static void main(String[] args) {
        NestedClassTest nc = new NestedClassTest();
        new NestedClassTest.Inner();
        nc.new Inner0();
    }

    static class Inner {
        Inner() {
            System.out.printf("Inner");
        }
    }

    class Inner0 {
        Inner0() {
            System.out.printf("Inner");
        }
    }

}
