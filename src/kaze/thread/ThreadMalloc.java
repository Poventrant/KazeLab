package kaze.thread;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by kaze on 2016/7/26.
 */
public class ThreadMalloc {
    public static void main(String[] args) {
        long start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            new Thread();
        }
        System.out.println(System.nanoTime() - start);
        start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            new ConcurrentHashMap<>();
        }
        System.out.println(System.nanoTime() - start);
    }
}
