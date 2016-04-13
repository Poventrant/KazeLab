package kaze.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by uc on 2016/3/24.
 */
public class ThreadPoolLab {
    public static void main(String[] args) {

        // 固定工作线程数量的线程池
        ExecutorService executorService1 = Executors.newFixedThreadPool(10);

        // 一个可缓存的线程池
        ExecutorService executorService2 = Executors.newCachedThreadPool();

        // 单线程化的Executor
        ExecutorService executorService3 = Executors.newSingleThreadExecutor();

        // 支持定时的以及周期性的任务执行
        ExecutorService executorService4 = Executors.newScheduledThreadPool(3);

        for (int i = 0; i < 100; i ++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getId());
                }
            });
            executorService1.execute(t);
        }

    }
}
