package kaze.other;

/**
 * Created by kaze on 2016/9/11.
 */
public class LEETCODE_221 {

    private static int rob(int[] num, int lo, int hi) {
        int include = 0, exclude = 0;
        for (int j = lo; j <= hi; j++) {
            int i = include, e = exclude;
            include = e + num[j];
            exclude = Math.max(e, i);
        }
        return Math.max(include, exclude);
    }

    public static int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        return Math.max(rob(nums, 0, nums.length - 2), rob(nums, 1, nums.length - 1));
    }

    public static void main(String[] args) {
        System.out.println(rob(new int[] {
                4,1,1,5,1,1
        }));
    }
}
