package kaze.algorithm;

import java.util.Random;

/**
 * Created by kaze on 2016/10/1.
 */
public class ShellSort {
    public static void main(String[] args) {
        Random ran = new Random();
        int[] sort = new int[20];
        for (int i = 0; i < sort.length; ++i) {
            sort[i] = ran.nextInt(20);
        }
        System.out.print("beforeSorting:");
        for (int s : sort) {
            System.out.print(s + " ");
        }
        shellSort(sort);
        System.out.print("\nafterSorting:");
        for (int s : sort) {
            System.out.print(s + " ");
        }
    }

    private static void shellSort(int[] sort) {
        if (sort == null) return;
        for (int step = sort.length >> 1; step > 0; step >>= 1) {
//            for (int k = step; k < sort.length; k++) {
//                for (int t = k - step; t >= 0; t -= step) {
//                    if (sort[t] > sort[t + step]) {
//                        int temp = sort[t];
//                        sort[t] = sort[t + step];
//                        sort[t + step] = temp;
//                    }
//                }
//            }
            for (int k = 0; k < step; ++k) {
                for (int i = k; i < sort.length; i += step) {
                    int j = i - step, key = sort[i];
                    while (j >= 0 && sort[j] > key) {
                        sort[j + step] = sort[j];
                        j -= step;
                    }
                    sort[j + step] = key;
                }
            }
        }
    }
}
