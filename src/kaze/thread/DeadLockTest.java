package kaze.thread;

/**
 * Created by kaze on 2016/8/31.
 */
public class DeadLockTest {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        new Thread(() -> {
            try {
                lock1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                lock2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static synchronized void lock1() throws InterruptedException {
        Thread.sleep(3000);
        lock2();
    }

    public static synchronized void lock2() throws InterruptedException {
        Thread.sleep(5000);
        lock1();
    }

}
