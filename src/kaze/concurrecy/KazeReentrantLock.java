package kaze.concurrecy;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;

/**
 * Created by 枫叶 on 2016/8/22.
 */
public class KazeReentrantLock {

    private Sync sync;

    public KazeReentrantLock(boolean fair) {
        if(fair) sync = new FairSync();
        else sync = new NonFairSync();
    }

    public KazeReentrantLock() {
        sync = new NonFairSync();
    }

    @SuppressWarnings("all")
    private static   abstract class Sync
            extends AbstractQueuedSynchronizer {

        protected boolean nonFairTryAcquire(int arg) {
            final Thread current = Thread.currentThread();
            int c = getState();
            if(c == 0) {
                if(compareAndSetState(0, 1)) {
                    setExclusiveOwnerThread(Thread.currentThread());
                    return true;
                }
            } else if(current == getExclusiveOwnerThread()){
                int nextc = c + arg;
                if(nextc < 0) throw new Error("Maximum lock count exceeded");
                setState(nextc);
                return true;
            }
            return false;
        }

        @Override
        protected final boolean tryRelease(int arg) {
            if(Thread.currentThread() != getExclusiveOwnerThread()) {
                throw new IllegalMonitorStateException();
            } else {
                int c = getState();
                int nextc = c - arg;
                if(nextc == 0) {
                    setExclusiveOwnerThread(null);
                    setState(0);
                    return true;
                } else {
                    setState(nextc);
                    return false;
                }
            }
        }

        final ConditionObject newCondition() {
            return new ConditionObject();
        }

        protected abstract void lock();

        private boolean isLocked() {
            return getState() != 0;
        }
    }

    private static class FairSync extends Sync {

        @Override
        protected void lock() {
            acquire(1);
        }

        @Override
        protected boolean tryAcquire(int arg) {
            return !hasQueuedPredecessors() && nonFairTryAcquire(arg);
        }
    }

    private static class NonFairSync extends Sync {

        @Override
        protected final void lock() {
            if(!compareAndSetState(0, 1)) {
                acquire(1);
            } else {
                setExclusiveOwnerThread(Thread.currentThread());
            }
        }

        @Override
        protected final boolean tryAcquire(int arg) {
            return nonFairTryAcquire(arg);
        }
    }

    public void lock() {
        sync.lock();
    }

    public void unlock() {
        sync.release(1);
    }

    public boolean tryLock() {
        return sync.nonFairTryAcquire(1);
    }

    public Condition newCondition() {
        return sync.newCondition();
    }

    public boolean isLocked() {
        return sync.isLocked();
    }
}
