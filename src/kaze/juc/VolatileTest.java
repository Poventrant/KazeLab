package kaze.juc;

/**
 * Created by kaze on 2016/7/19.
 */
public class VolatileTest implements Runnable{

    //加上volatile，就回立即终止 while(running)
    static volatile boolean runnning = true;

    public static void main(String[] args) {
        VolatileTest volatileTest = new VolatileTest();
        new Thread(volatileTest).start();
        try {
            Thread.sleep(5000);
            runnning = false;
            System.out.println("stop");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (runnning) {
        }
    }
}
