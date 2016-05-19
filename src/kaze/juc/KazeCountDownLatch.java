package kaze.juc;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Created by kaze on 16-5-18.
 */
public class KazeCountDownLatch {

    private static class Sync extends AbstractQueuedSynchronizer {
        Sync(int acq) {
            setState(acq);
        }

        @Override
        protected boolean tryReleaseShared(int acq) {
            for (;;) {
                int c = getState();
                if(c == 0) return false;
                int nextc = c - 1;
                if(compareAndSetState(c, nextc)) {
                    return true;
                }
            }
        }

        @Override
        protected int tryAcquireShared(int acq) {
            return getState() == 0 ? 1 : -1 ;
        }
    }

    final Sync sync;

    KazeCountDownLatch(int count) throws Exception {
        if(count < 0) {
            throw new Exception("count cant not less than 0.");
        }
        sync = new Sync(10);
    }

    public static void main(String[] args) {

    }
}
