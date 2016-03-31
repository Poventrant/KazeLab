package kaze.thread;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest extends Thread {

    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(2);

        new CountDownLatchTest(latch).start();

        new CountDownLatchTest(latch).start();

        try {
            System.out.println("等待2个子线程执行完毕...");
            latch.await();
            System.out.println("2个子线程已经执行完毕");
            System.out.println("继续执行主线程");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private CountDownLatch latch = null;

    CountDownLatchTest(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
            Thread.sleep(3000);
            System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕");
            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}