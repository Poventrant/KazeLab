package kaze.thread;

import java.util.Iterator;
import java.util.Queue;
import java.util.TreeMap;
import java.util.concurrent.*;

/**
 * 枫叶
 */
public class ThreadPoolLab {

    final static int POOL_SIZE = Runtime.getRuntime().availableProcessors();

    public static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(5);

    public static final WorkerQueue WORK_QUEUE = new WorkerQueue(POOL_SIZE);

    static class Worker extends Thread {

        private String url = "";

        private CountDownLatch counter;

        @Override
        public void run() {
            try {
                System.out.println("Thread " + Thread.currentThread().getId() + ": " + url);
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

        for (int i = 0; i < 100; i++) {
           new AuthorWorker(i+".com", "13349086", i).start();
        }

        /*EXECUTOR.shutdown();
        EXECUTOR.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        System.out.println("All task finished!!!");*/
    }

}
