package kaze.other;

/**
 * Created by kaze on 2016/9/5.
 */
public class FindGreatestSumOfSubArray {
    public static void main(String[] args) {
        int[] arr = {6, -3, -2, 7, -15, 1, 2, 2};
        int a = arr[0], b = Integer.MIN_VALUE;
        for (int i = 1; i < arr.length; i++) {
            a = a < 0 ? arr[i] : a+ arr[i];
            b = Math.max(b, a);
        }
        System.out.println(b);
    }
}
