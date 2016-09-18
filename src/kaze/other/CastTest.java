package kaze.other;

/**
 * Created by kaze on 2016/8/9.
 */
public class CastTest {

    static {
        System.out.println("static constructor");
    }

    static void test() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100000);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().isInterrupted());
                    Thread.currentThread().interrupt();
                    System.out.println("interrupt");
                    System.out.println(Thread.currentThread().isInterrupted());
                }
            }
        });
        t.start();
        System.out.println(t.isInterrupted());
        t.interrupt();
//        System.out.println(t.isInterrupted());
//        t.interrupt();
    }


    public static void main(String[] args) {
        int a = 511;
        System.out.println(Byte.MAX_VALUE);
        test();
    }
}
