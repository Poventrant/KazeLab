package kaze.thread;

/**
 * Created by kaze on 2016/7/24.
 */
public class ThreadJoinTest implements Runnable{

    private int sleepTime;

    public ThreadJoinTest(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    public static void main(String[] args) throws InterruptedException {
        int len = 3;
        Thread[] ts = new Thread[len];
        for (int i = 0; i < len; i++) {
            ts[i] = new Thread(new ThreadJoinTest(i * 3000 + 3000));
            ts[i].start();
        }
        for (int i = len-1; i >= 0 ; i--) {
            ts[i].join();
            System.out.println("????");
        }
    }

    @Override
    public void run() {
        try {
            Thread.sleep(sleepTime);
            System.out.println(Thread.currentThread().getId());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
