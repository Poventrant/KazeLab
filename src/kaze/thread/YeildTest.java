package kaze.thread;

public class YeildTest implements Runnable {

    private int sleepTime;

    public YeildTest(int sleepTime) {
        this.sleepTime = sleepTime;
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

    public static void main(String[] args) {
        int len = 10;
        Thread[] ts = new Thread[len];
        int oriCount = Thread.activeCount();
        for (int i = 0; i < len; i++) {
            ts[i] = new Thread(new YeildTest(i * 300 + 300));
            ts[i].start();
        }
        while (Thread.activeCount() > oriCount) {
            Thread.yield();
        }
        System.out.println("all done");
    }
}
