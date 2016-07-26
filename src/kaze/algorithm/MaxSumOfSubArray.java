package kaze.algorithm;

/**
 * Created by kaze on 2016/7/26.
 */
public class MaxSumOfSubArray {

    public static int maxSum(int [] arr) {
        int max = Integer.MIN_VALUE, sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if(sum > max) {
                max = sum;
            }
            if(sum < 0) {
                sum = 0;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(maxSum(new int[] {
                1,-2,3,5,-3,2
        }));
        System.out.println(maxSum(new int[] {
                0,-2,3,5,-1,2
        }));
        System.out.println(maxSum(new int[] {
                -9,-2,-3,-5,-3
        }));
    }
}
