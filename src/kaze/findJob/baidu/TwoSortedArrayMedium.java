package kaze.findJob.baidu;

/**
 * Created by kaze on 2016/9/25.
 */
public class TwoSortedArrayMedium {

    public double helper(int[] nums1, int start1, int[] nums2, int start2, int topK) {
        if (start1 > nums1.length - 1) return nums2[start2 + topK - 1];
        if (start2 > nums2.length - 1) return nums1[start1 + topK - 1];
        if (topK == 1) return Math.min(nums1[start1], nums2[start2]);

        if (start2 + topK / 2 - 1 > nums2.length - 1)
            return helper(nums1, start1 + topK / 2, nums2, start2, topK - topK / 2);
        if (start1 + topK / 2 - 1 > nums1.length - 1)
            return helper(nums1, start1, nums2, start2 + topK / 2, topK - topK / 2);

        if (nums1[start1 + topK / 2 - 1] < nums2[start2 + topK / 2 - 1])
            return helper(nums1, start1 + topK / 2, nums2, start2, topK - topK / 2);
        else return helper(nums1, start1, nums2, start2 + topK / 2, topK - topK / 2);
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int leftMedian = (nums1.length + nums2.length + 1) / 2;
        int rightMedian = (nums1.length + nums2.length + 2) / 2;
        return (helper(nums1, 0, nums2, 0, leftMedian) + helper(nums1, 0, nums2, 0, rightMedian)) / 2.0;
    }

}
