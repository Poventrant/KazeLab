package kaze.algorithm;

import java.util.Random;

/**
 * Created by kaze on 2016/9/16.
 */
public class InsertSort {
    public static void main(String[] args) {
        Random ran = new Random();
        int[] sort = new int[20];
        for (int i = 0; i < sort.length; ++i) {
            sort[i] = ran.nextInt(20);
        }
        System.out.print("beforeSorting:");
        for (int i = 0; i < sort.length; ++i) {
            System.out.print(sort[i] + " ");
        }
        insertSort(sort);
        System.out.print("\nafterSorting:");
        for (int i = 0; i < sort.length; ++i) {
            System.out.print(sort[i] + " ");
        }
    }

    private static void insertSort(int[] sort) {
        for (int i = 0; i < sort.length; i++) {
            int temp = sort[i], j = i - 1;
            while (j >= 0 && sort[j] > temp) {
                sort[j + 1] = sort[j--];
            }
            sort[j + 1] = temp;
        }
    }
}
