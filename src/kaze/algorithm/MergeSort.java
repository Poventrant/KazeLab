package kaze.algorithm;

import java.util.Random;

/**
 * Created by 枫叶 on 2016/5/9.
 */
public class MergeSort {
    public static void mergeSort(int a[], int start, int end, int temp[]) {
        if (start < end) {
            int mid = (start + end) >> 1;
            if (mid != start) {
                mergeSort(a, start, mid, temp);
                mergeSort(a, mid + 1, end, temp);
            }

            for (int i = 0, l = start, r = mid + 1; i < end + 1 - start; ++i) {
                if (r > end || l <= mid && a[l] < a[r]) {
                    temp[i] = a[l++];
                } else {
                    temp[i] = a[r++];
                }
            }
            System.arraycopy(temp, 0, a, start, end + 1 - start);
        }
    }

    public static void main(String[] args) {
        int len = 500;
        int sort[] = new int[len];
        Random rand = new Random();
        System.out.print("before sorting: ");
        for (int i = 0; i < sort.length; ++i) {
            sort[i] = rand.nextInt(len);
            System.out.print(sort[i] + " ");
        }
        int temp[] = new int[sort.length];
        long starttime = System.currentTimeMillis();
        mergeSort(sort, 0, sort.length - 1, temp);
        System.out.println("\n" + (System.currentTimeMillis() - starttime));
        System.out.print("\nafter sorting: ");
        for (int e : sort) {
            System.out.print(e + " ");
        }

    }
}
