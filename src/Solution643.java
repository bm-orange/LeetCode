/**
 * 643. Maximum Average Subarray I
 * Created by man on 2018/11/1.
 */
public class Solution643 {
    /*
    描述:
    Given an array consisting of n integers, find the
    contiguous subarray of given length k that has the
    maximum average value. And you need to output the
    maximum average value.

    测试用例:
    Example1:
    Input: [1,12,-5,-6,50,3], k = 4
    Output: 12.75
    Explanation: Maximum average is (12-5-6+50)/4 = 51/4 = 12.75

    Note:
    + 1 <= k <= n <= 30,000.
    + Elements of the given array will be in the range [-10,000, 10,000].
     */

    public double findMaxAverage(int[] nums, int k) {
        /*
        以k为窗口，不断向后一步一步的滑动，并同时记录最大的和
         */
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        max = sum;
        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - nums[i - k];
            max = Math.max(max, sum);
        }

        return (double) max / (double) k;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 12, -5, -6, 50, 3};
        System.out.println(new Solution643().findMaxAverage(nums, 4));
    }
}

