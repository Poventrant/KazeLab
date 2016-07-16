package kaze.juc;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Created by 枫叶 on 2016/4/29.
 */
public class JucTest extends AbstractQueuedSynchronizer {

    public static volatile List<Integer> list = new ArrayList<Integer>();
    public static AtomicInteger ai = new AtomicInteger(0);

    public static void main(String[] args) {
//        ConcurrentHashMap
//        LockSupport
//        ReentrantReadWriteLock
//        LinkedBlockingQueue
//        CountDownLatch
//        TreeMap
//        ConcurrentHashMap
//        HashMap
//        Hashtable
//        ReentrantLock
    }

    @Test
    public void ConcurrentHashMapTest() {
        final int len = 100;
/*        ConcurrentMap<Integer, Integer> map = new ConcurrentHashMap<Integer, Integer>();
        map.put(1,1);
        map.put(4,1);
        map.put(1,1);*/
        final List<Integer> list0 = list;
        ExecutorService executor = Executors.newFixedThreadPool(len);
        for (int i = 0; i < len; i++) {
            final int finalI = i;
            executor.execute(new Thread(new Runnable() {
                @Override
                public void run() {
                    list0.add(finalI);
                }
            }));
        }
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(list0.size());
    }

}
