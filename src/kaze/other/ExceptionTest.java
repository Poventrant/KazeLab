package kaze.other;

/**
 * Created by kaze on 16-4-24.
 */
public class ExceptionTest {

    @Override
    public String toString() {
        return "ExceptionTest.toString() " + this;
    }

    public static int method1() {
        try {
            System.out.println(1);
            throw new Exception();
        } catch (Exception e) {
            System.out.println(2);
            e.printStackTrace();
            System.out.println(3);
            return 11;
        } finally {
            System.out.println(4);
            return 12;
        }
    }

    private static void test(int[]arr) {
        for (int i = 0; i < arr.length; i++) {
            try {
                if (arr[i] % 2 == 0) {
                    throw new NullPointerException();
                } else {
                    System.out.print(i);
                }
            } finally {
                System.out.print("e");
            }
        }
    }


    private static void ignoreException() {
        try {
            try {
                throw new NullPointerException();
            } finally {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("NullPointerException");
        }
    }

    public static void main(String[]args) {
//        try {
//            test(new int[] {0, 1, 2, 3, 4, 5});
//        } catch (ExceptionLab e) {
//            System.out.print("E");
//        }
//        ignoreException();
        System.out.println(new ExceptionTest());
    }

//    public static void main(String[] args) {
//        System.out.println(method1());
//        System.out.println(6);
//    }
}
