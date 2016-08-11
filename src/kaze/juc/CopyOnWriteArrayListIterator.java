package kaze.juc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by kaze on 2016/7/31.
 */
public class CopyOnWriteArrayListIterator {
    public static void main(String[] args) throws InterruptedException {
        final List<Integer> list = Collections.synchronizedList(new ArrayList<Integer>());
        for (int i = 0; i < 50; i++) {
            list.add(i);
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    list.add(i+100);
                }
            }
        }).start();
        itra(list);
    }

    private static void itra(List<Integer> list) {
        List<Integer> l = list;
        synchronized (l) {
            for (Integer i : l) {
                System.out.println(i);
            }
        }
    }
}
