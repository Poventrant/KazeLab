package kaze.algorithm;

/**
 * Created by 枫叶 on 2016/5/9.
 */
public class MergeSort {
    //将有二个有序数列a[first...mid]和a[mid...last]合并。
    public static void mergearray(int a[], int first, int mid, int last, int temp[]) {
        int i = first, j = mid + 1;
        int m = mid, n = last;
        int k = 0;

        while (i <= m && j <= n) {
            if (a[i] <= a[j])
                temp[k++] = a[i++];
            else
                temp[k++] = a[j++];
        }

        while (i <= m)
            temp[k++] = a[i++];

        while (j <= n)
            temp[k++] = a[j++];

        for (i = 0; i < k; i++)
            a[first + i] = temp[i];
    }

    public static void mergesort0(int a[], int first, int last, int temp[]) {
        if (first < last) {
            int mid = (first + last) / 2;
            mergesort0(a, first, mid, temp);    //左边有序
            mergesort0(a, mid + 1, last, temp); //右边有序
            mergearray(a, first, mid, last, temp); //再将二个有序数列合并
        }
    }

    public static boolean mergeSort(int a[], int n) {
        if(n <= 0 ) return false;
        int[] p = new int[n];
        mergesort0(a, 0, n - 1, p);
        p = null;
        return true;
    }

    public static void main(String[] args) {
        int [] a = new int[] {2,5,9,7,4,6,12,4,6,7,65,45,78,98,12,12,32,45,78,21,65,46,78,98,10,30,154};
        mergeSort(a, a.length);
        for (int i : a) {
            System.out.printf("%d ", i);
        }
    }
}
