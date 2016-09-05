package kaze.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by 枫叶 on 2016/5/8.
 */
public class ReentrantLockTest {

    static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    System.out.println("thread 1");
                    lock.lock();
                } finally {
                    lock.unlock();
                    lock.unlock();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    System.out.println("thread 2");
                } finally {
                    lock.unlock();
                }
            }
        }).start();

    }
}
