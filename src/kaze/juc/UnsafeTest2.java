package kaze.juc;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;

/**
 * Created by kaze on 2016/8/16.
 */
public class UnsafeTest2 {
    volatile int LOCK = -1;
    volatile int counter;

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InterruptedException {
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);
        long lockOffset = unsafe.objectFieldOffset(UnsafeTest2.class.getDeclaredField("LOCK"));
        final UnsafeTest2 test = new UnsafeTest2();
        int threshold = 20;
        final CountDownLatch countDownLatch = new CountDownLatch(threshold);
        for (int t = 0; t < threshold; t++) {
            new Thread(() -> {
                for (int i = 0; i < 1000; i++) {
                    for (;;){
                        int o = test.LOCK;
                        if(o == -1 && unsafe.compareAndSwapInt(test, lockOffset, o, 1)) {
                            try {
                                ++ test.counter;
                            } finally {
                                test.LOCK = -1;
                            }
                            break;
                        }
                    }
                }
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        System.out.println(test.counter);
    }
}
