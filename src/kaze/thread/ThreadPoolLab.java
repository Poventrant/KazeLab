package kaze.thread;

import java.util.Iterator;
import java.util.Queue;
import java.util.TreeMap;
import java.util.concurrent.*;

/**
 * 枫叶
 */
public class ThreadPoolLab {

    final static int POOL_SIZE = 10;

    public static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(5);

    public static final WorkerQueue WORK_QUEUE = new WorkerQueue(POOL_SIZE);

    static class Worker extends Thread {

        private String url = "";

        private CountDownLatch counter;

        Worker() {
        }

        @Override
        public void run() {
            try {
                System.out.println("Thread " + Thread.currentThread().getId() + ": " + url);
             /*   Thread.sleep(2000);
            } catch (InterruptedException e) {*/
            } finally {
                counter.countDown();
                //空闲当前线程
                WORK_QUEUE.rest(this);
            }
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public CountDownLatch getCounter() {
            return counter;
        }

        public void setCounter(CountDownLatch counter) {
            this.counter = counter;
        }

    }

    static class WorkerQueue {
        BlockingQueue<Worker> availableQueue;
        final Object moniter = new Object();

        WorkerQueue(Worker[] workers) {
            availableQueue = new ArrayBlockingQueue<Worker>(POOL_SIZE);
            try {
                for (Worker worker : workers) {
                    availableQueue.put(worker);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        WorkerQueue(int num) {
            availableQueue = new ArrayBlockingQueue<Worker>(POOL_SIZE);
            try {
                for (int i = 0; i < num; i++) {
                    availableQueue.put(new Worker());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public Worker work() {
            try {
                return availableQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        public void rest(Worker worker) {
            try {
                availableQueue.put(worker);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    static class AuthorWorker extends Thread {

        private String url;

        private String authorId;

        private int pages;

        private CountDownLatch counter;

        AuthorWorker(String url, String authorId, int pages) {
            if(pages < 0) throw new IllegalArgumentException("pages cant smaller than 0 neither can executor.");
            this.pages = pages;
            this.url = url;
            this.authorId = authorId;
            this.counter = new CountDownLatch(pages);
        }

        @Override
        public void run() {
            //申请pages个空闲的线程, 重定义这些线程的内容
            for(int i = 0; i < pages; ++i) {
                Worker worker = WORK_QUEUE.work();
                worker.setUrl(url+"/"+authorId);
                worker.setCounter(counter);
                EXECUTOR.execute(worker);
            }
            try {
                counter.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(url + " finished!!");
            }
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public CountDownLatch getCounter() {
            return counter;
        }

        public void setCounter(CountDownLatch counter) {
            this.counter = counter;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public String getAuthorId() {
            return authorId;
        }

        public void setAuthorId(String authorId) {
            this.authorId = authorId;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new AuthorWorker("1.com", "13349086", 7).start();
        new AuthorWorker("2.com", "13349086", 7).start();
        new AuthorWorker("3.com", "13349086", 7).start();
        new AuthorWorker("4.com", "13349086", 7).start();
        new AuthorWorker("5.com", "13349086", 7).start();
        new AuthorWorker("6.com", "13349086", 7).start();
        new AuthorWorker("7.com", "13349086", 7).start();
        new AuthorWorker("8.com", "13349086", 7).start();
        new AuthorWorker("9.com", "13349086", 7).start();
        new AuthorWorker("10.com", "13349086", 7).start();
        new AuthorWorker("11.com", "13349086", 7).start();

        /*EXECUTOR.shutdown();
        EXECUTOR.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        System.out.println("All task finished!!!");*/
    }

//   static Worker[] WORKER = new Worker[POOL_SIZE];
//
//    static {
//        for (int i = 0; i < POOL_SIZE; i ++) {
//            WORKER[i] = new Worker();
//        }
//    }
//    public static void main(String[] args) throws InterruptedException {
//
//        // 固定工作线程数量的线程池
//        ExecutorService executorService1 = Executors.newFixedThreadPool(20);
//
//
//        CountDownLatch latch = new CountDownLatch(POOL_SIZE);
//        for (int i = 0; i < POOL_SIZE; i ++) {
//            WORKER[i] = new Worker();
//            WORKER[i].setCounter(latch);
//            executorService1.execute(WORKER[i]);
//        }
//        try {
//            latch.await();
//        } finally {
//            System.out.println("terminate!!");
//        }
//
//        latch = new CountDownLatch(POOL_SIZE - 4);
//        for (int i = 0; i < POOL_SIZE - 4; i ++) {
//            WORKER[i].setUrl("test" + i);
//            WORKER[i].setCounter(latch);
//            executorService1.execute(WORKER[i]);
//        }
//        try {
//            latch.await();
//        } finally {
//            System.out.println("terminate!!");
//        }
//
//        latch = new CountDownLatch(POOL_SIZE - 8);
//        for (int i = 0; i < POOL_SIZE - 8; i ++) {
//            WORKER[i].setUrl("excuse me ? " + i);
//            WORKER[i].setCounter(latch);
//            executorService1.execute(WORKER[i]);
//        }
//        try {
//            latch.await();
//        } finally {
//            System.out.println("terminate!!");
//        }
//
//        executorService1.shutdown();
//        executorService1.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
//        System.out.println("Test finished!!!");
//    }
}
