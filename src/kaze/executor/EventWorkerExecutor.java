package kaze.executor;

import org.apache.log4j.Logger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by kaze on 16-7-14.
 */
public class EventWorkerExecutor {

    private final static Logger logger = Logger.getLogger(EventWorkerExecutor.class);

    private int maxPoolSize = 20;
    protected int maxAvailabelThreads = 40;
    protected int minSpareThreads = 25;
    protected int maxIdleTime = 60000;
    protected BlockingQueue<EventWorkerProcessor> availabelQueue = new LinkedBlockingQueue<>(maxAvailabelThreads);
    protected BlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<>(maxAvailabelThreads / 2);

    private final ThreadPoolExecutor executor = new ThreadPoolExecutor(minSpareThreads, maxPoolSize, maxIdleTime, TimeUnit.MILLISECONDS, blockingQueue);

    public void process(EventWorker eventWorker) {
        try {
            EventWorkerProcessor eventWorkerProcessor = availabelQueue.take();
            if(eventWorker == null && executor.getTaskCount() <  maxAvailabelThreads) {
                eventWorkerProcessor = new EventWorkerProcessor();
                availabelQueue.put(eventWorkerProcessor);
            } else {
                logger.warn("availabel is out of max pool size.");
                return;
            }
            eventWorkerProcessor.setEventWorker(eventWorker);
            executor.execute(eventWorkerProcessor);
        } catch (InterruptedException e) {
            logger.error("error", e);
        }
    }

}
