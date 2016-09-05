package kaze.concurrecy;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Created by 枫叶 on 2016/8/24.
 */
public class KazeReadWriteLock {

    static class ReadSync extends AbstractQueuedSynchronizer {

    }

    static class WriteSync extends AbstractQueuedSynchronizer {

    }

}
