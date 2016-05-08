package kaze.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by 枫叶 on 2016/5/5.
 */
public class ReadWriteLockTest {
    static int i = 0;
    final static ReadWriteLock lock = new ReentrantReadWriteLock();
    final static Condition condition = lock.writeLock().newCondition();

    public static void main(String[] args) {
        Thread writer1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.writeLock().lockInterruptibly();
                    i ++;
                    condition.await();
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.writeLock().unlock();
                }
            }
        }, "writer1");


        Thread reader1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.readLock().lockInterruptibly();
                    i--;
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.readLock().unlock();
                }
            }
        }, "reader1");


        Thread reader2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.readLock().lockInterruptibly();
                    i *= 2;
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.readLock().unlock();
                }
            }
        }, "reader2");

        Thread reader3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.readLock().lockInterruptibly();
                    i *= 2;
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.readLock().unlock();
                }
            }
        }, "reader3");

        Thread reader4 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.readLock().lockInterruptibly();
                    i *= 2;
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.readLock().unlock();
                }
            }
        }, "reader4");

        Thread writer2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.writeLock().lockInterruptibly();
                    i ++;
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.writeLock().unlock();
                }
            }
        }, "writer2");

        writer1.start();
        reader1.start();
        reader2.start();
        reader3.start();
        reader4.start();
//        writer2.start();
    }
}
