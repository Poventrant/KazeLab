package kaze.thread;

/**
 * Created by kaze on 2016/8/1.
 */
public class WaiNotifyTest {

    public static void main(String[] args) {
        final Object o = new Object();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o) {
                    try {
                        o.wait();
                        System.out.println("after wait");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (this) {
                    try {
                        wait();
                        System.out.println("after wait!!!");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }synchronized (o) {
                    o.notify();
                    System.out.println("after notify");
                }
            }
        }).start();

    }
}
