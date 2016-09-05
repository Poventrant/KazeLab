package kaze.juc;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by 枫叶 on 2016/5/11.
 */
public class ConcurrentHashMapTest {
    public static void main(String[] args) {
        final ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<Integer, Integer>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                map.put(1,1);
            }
        }, "put").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                map.get(1);
                map.get(1);
            }
        }, "get").start();

    }
}
