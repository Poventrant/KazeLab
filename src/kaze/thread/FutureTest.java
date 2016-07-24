package kaze.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by kaze on 2016/7/23.
 */
public class FutureTest implements Callable<String> {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Future<String>> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Future<String> future = executorService.submit(new FutureTest());
            list.add(future);
        }
        for (Future<String> future : list) {
            System.out.println(future.get());
        }
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(5000);
        return "test";
    }
}
