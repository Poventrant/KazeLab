package kaze.other;

import java.util.Random;

/**
 * Created by kaze on 2016/9/1.
 */
public class RANDOM_7_TO_10 {
    public static void main(String[] args) {
        int count = 100;
        while(count >= 0) {
            Random random = new Random(System.currentTimeMillis());
            int temp = 48;
            while(temp > 40) {
                temp = Math.abs(random.nextInt() % 7) * 7
                        + Math.abs(random.nextInt() % 7);
            }
            System.out.println(temp%10+1);
            --count;
        }

    }
}
