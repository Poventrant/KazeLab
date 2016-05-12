package kaze.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Created by 枫叶 on 2016/5/1.
 */
public class AQSTest implements Runnable{

    static class Sync extends AbstractQueuedSynchronizer{

        Sync(int count) {
            setState(count);
        }

        void lock() {
            if(compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
            } else {
                acquire(1);
            }
        }

        @Override
        protected boolean tryAcquire(int acquires) {
            final Thread current = Thread.currentThread();
            int c = getState();
            if (c == 0) {
                if (compareAndSetState(0, acquires)) {
                    setExclusiveOwnerThread(current);
                    return true;
                }
            }
            else if (current == getExclusiveOwnerThread()) {
                int nextc = c + acquires;
                if (nextc < 0) // overflow
                    throw new Error("Maximum lock count exceeded");
                setState(nextc);
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int releases) {
            int c = getState() - releases;
            if (Thread.currentThread() != getExclusiveOwnerThread())
                throw new IllegalMonitorStateException();
            boolean free = false;
            if (c == 0) {
                free = true;
                setExclusiveOwnerThread(null);
            }
            setState(c);
            return free;
        }
    }

    public void lock() {
        sync.lock();
    }

    public void unlock() {
        sync.release(1);
    }


    static int index = 0;
    final static Sync sync = new Sync(0);

    @Override
    public void run() {
        lock();
        try {
            index ++;
        } finally {
            unlock();
        }
    }


    public static void main(String[] args) {
        int len = 10;
        ExecutorService executor = Executors.newFixedThreadPool(len);
        for (int i = 0; i < len; i++) {
            executor.execute(new AQSTest());
        }
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(index);

    }

}
