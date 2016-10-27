package kaze.algorithm;

import java.util.Scanner;
import java.util.Random;

import static kaze.algorithm.QuickSortKthMaxNumber.qSort;

public class BinarySearch {

    private static int binarySearch(int[] sort, int num) {
        if (sort == null) return -1;
        int mid, low = 0, high = sort.length - 1;
        while (low <= high) {
            mid = (high + low) >> 1;
            if (num > sort[mid]) {
                low = mid + 1;
            } else if (num < sort[mid]) {
                high = mid - 1;
            } else
                return mid + 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        Random ran = new Random();
        int[] sort = new int[10];
        for (int i = 0; i < sort.length; ++i) {
            sort[i] = ran.nextInt(1000);
        }
        System.out.print("beforeSorting:");
        for (int i = 0; i < sort.length; ++i) {
            System.out.print(sort[i] + " ");
        }
        qSort(sort, 0, sort.length - 1);
        System.out.print("\nafterSorting:");
        for (int i = 0; i < sort.length; ++i) {
            System.out.print(sort[i] + " ");
        }
        System.out.print("\n");
        Scanner input = new Scanner(System.in);
        int res = input.nextInt();
        int index = binarySearch(sort, res);
        System.out.println(index);
    }
}