package kaze.juc;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by 枫叶 on 2016/5/6.
 */
public class LockSupportTest {
    public static void main(String[] args) {
        final Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                LockSupport.park(Thread.currentThread());
                System.out.println("unpark");
            }
        });
        t.start();
        final Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    System.out.println("wake up");
                    LockSupport.unpark(t);
                }
            }
        });
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                if(!t1.isInterrupted()) t1.interrupt();
                System.out.println("interrupt");
            }
        });

        try {
            Thread.sleep(1000);
            t2.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                LockSupport.park(Thread.currentThread());
            }
        }, "PARK_TEST_THREAD").start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "SLEEP_TEST_THREAD").start();

        final Object waitLock = new Object();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (waitLock) {
                    try {
                        waitLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "WAIT_TEST_THREAD").start();
    }
}
