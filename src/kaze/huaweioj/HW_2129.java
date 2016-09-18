package kaze.huaweioj;

import java.util.Scanner;

/**
 * Created by kaze on 2016/9/15.
 */
public class HW_2129 {

    static int _max_ = Integer.MIN_VALUE;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        rendenr(arr, 0);
        System.out.println(_max_);
    }

    private static void rendenr(int[] arr, int start) {
        if(_max_ < arr.length / 2) {
            if(arr.length == start) {
                int count = 0;
                for (int i = 0; i < start - 1; i+=2) {
                    if (isPrime(arr[i] + arr[i + 1])) {
                        ++count;
                    }
                    if (count > _max_) _max_ = count;
                }
                return;
            }
            for (int i = start; i < arr.length; i++) {
                int temp = arr[i];
                arr[i] = arr[start];
                arr[start] = temp;
                rendenr(arr, start + 1);
                arr[start] = arr[i];
                arr[i] = temp;
            }
        }
    }

    private static boolean isPrime(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if(n % i == 0) return false;
        }
        return true;
    }
}
