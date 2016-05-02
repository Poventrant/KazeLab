package kaze.other;

/**
 * Created by 枫叶 on 2016/4/16.
 */
public class AliTestClass {
    private static void testMethod() {
        System.out.println("test");
    }



    public static void main(String[] args) {
        ((AliTestClass) null).testMethod();
        System.out.println(((AliTestClass) null));
    }
}
