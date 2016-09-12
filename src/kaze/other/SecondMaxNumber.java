package kaze.other;

import java.util.Arrays;
import java.util.Random;

/**
 * 查找数组第二大的数
 */
public class SecondMaxNumber {
    public static void main(String[] args) {
        int len = 50;
        int sort[] = new int[len];
        Random rand = new Random();
        System.out.print("before sorting: ");
        for (int i = 0; i < sort.length; ++i) {
            sort[i] = rand.nextInt(len*10);
            System.out.print(sort[i] + " ");
        }
        System.out.println();

        int heap0, heap1;
        heap0 = Math.min(sort[0], sort[1]);
        heap1 = Math.max(sort[0], sort[1]);
        for (int i = 2; i < sort.length; i++) {
            // sort[i] != heap1 防止最大值有两个一样的情况下，堆进行替换
            if(sort[i] > heap0 && sort[i] != heap1) {
                heap0 = sort[i];
                if(heap0 > heap1) {
                    int tmp = heap0;
                    heap0 = heap1;
                    heap1 = tmp;
                }
            }
        }

        System.out.println("second maxumium is " + heap0);

        Arrays.sort(sort);
        System.out.print("after sorting: ");
        for (int i = 0; i < sort.length; ++i) {
            System.out.print(sort[i] + " ");
        }
        System.out.println();

    }
}
