package kaze.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RejectedExecutionExceptionExample {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executor = new ThreadPoolExecutor(3, 3, 0L,
                TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(15));

        Worker tasks[] = new Worker[20];
        for (int i = 0; i < 10; i++) {
            tasks[i] = new Worker(i);
            executor.execute(tasks[i]);
        }
        Thread.sleep(3000);
        for (int i = 10; i < 20; i++) {
            tasks[i] = new Worker(i);
            executor.execute(tasks[i]);
        }
        executor.shutdown();
    }


    static class Worker implements Runnable {

        private int ID;

        public Worker(int id) {
            this.ID = id;
        }

        @Override
        public void run() {
            try {
                Thread curThread = Thread.currentThread();
                System.out.println(curThread.getName() + " currently executing the task " + ID);
                Thread.sleep(500);
                System.out.println(curThread.getName() + " just completed the task " + ID);
            } catch (Exception e) {
                System.out.println(e);
            }

        }

        public int getID() {
            return ID;
        }

        public void setID(int iD) {
            ID = iD;
        }
    }
}