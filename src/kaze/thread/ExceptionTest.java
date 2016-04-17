package kaze.thread;

/**
 * Created by 枫叶 on 2016/4/16.
 */
public class ExceptionTest implements Runnable{

    public static Object lock = new Object();

    public static void main(String[] args) {

        Thread t = new Thread(new ExceptionTest(2));
        t.start();
        t.interrupt();
        new Thread(new ExceptionTest(1)).start();
    }

    ExceptionTest(int base) {
        this.base = base;
    }

    private int base;

    @Override
    public void run() {
        synchronized (lock) {
            int t = 1 / base;
            try {
                Thread.sleep(50000);
                System.out.println("???????????");
            } catch (InterruptedException e) {
                System.out.println("interrupt~!!!");
            }
        }
    }
}
