package kaze.thread;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by 枫叶 on 2016/5/29.
 */
@SuppressWarnings("all")
public class ThreadCoreTest implements Runnable{

    static final long MAX_BORDER = 999999999990L;
    static long start;
    static long end;
    public AtomicLong ai = null;
    static public int index = 0;

    ThreadCoreTest(AtomicLong ai) {
        this.ai = ai;
    }

    public static void main(String[] args) {

//        start = System.currentTimeMillis();
//        for (long i = 0; i <= MAX_BORDER; i++) {
//        }
//        end = System.currentTimeMillis();
//        System.out.println("single thread : " + (end - start));

        start = System.currentTimeMillis();
        AtomicLong tai = new AtomicLong(0);
        for (int i = 0; i < 4; i++) {
            new Thread(new ThreadCoreTest(tai)).start();
        }

    }

    public void run() {
        while(ai.incrementAndGet() <= MAX_BORDER) {
        }
        System.out.println("mutiple threads : " + (start - end));
    }


}
