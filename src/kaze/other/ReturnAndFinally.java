package kaze.other;

/**
 * Created by kaze on 16-8-18.
 */
public class ReturnAndFinally {
    public static void main(String[] args) {
        System.out.println(test());
    }

    private static int test() {
        int a = 0;
        try {
            return (a  = 1);
        } finally {
            a = 2;
            System.out.println(a);
        }

    }
}
