package kaze.executor;

/**
 * Created by kaze on 16-7-14.
 */
public class EventWorkerProcessor implements Runnable{

    protected String uuid;

    protected int retryTime = 1;
    protected Long delay = null;
    protected Long timeout = null;
    protected boolean paused = false;
    protected boolean running = true;
    protected EventWorker eventWorker;

    public void setEventWorker(EventWorker eventWorker) {
        this.eventWorker = eventWorker;
    }

    @Override
    public void run() {
        if(eventWorker != null) {
            eventWorker.doRun();
        }
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }


    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setRetryTime(int retryTime) {
        this.retryTime = retryTime;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public String getUuid() {
        return uuid;
    }

    public int getRetryTime() {
        return retryTime;
    }

    public Long getTimeout() {
        return timeout;
    }

    public boolean isPaused() {
        return paused;
    }

    public boolean isRunning() {
        return running;
    }

    public Long getDelay() {
        return delay;
    }

    public void setDelay(Long delay) {
        this.delay = delay;
    }
}
