package kaze.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 枫叶 on 2016/4/4.
 */
public class ThreadPoolTest implements Runnable {



    public static ExecutorService threadPool = Executors.newFixedThreadPool(50);


    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            threadPool.execute(new ThreadPoolTest(i));
        }
        threadPool.shutdown();
    }

    public String name;
    public int id;

    @Override
    public void run() {
//        System.out.println(id);
    }

    public ThreadPoolTest(int id) {
        this.id = id;
    }
}
