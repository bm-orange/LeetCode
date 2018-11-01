/**
 * 674. Longest Continuous Increasing Subsequence
 * Created by man on 2018/11/1.
 */
public class Solution674 {
    /*
    描述:
    Given an unsorted array of integers, find the length of
    longest continuous increasing subsequence (subarray).

    测试用例:
    Example1
    Input: [1,3,5,4,7]
    Output: 3
    Explanation: The longest continuous increasing subsequence is [1,3,5], its length is 3.
    Even though [1,3,5,7] is also an increasing subsequence,
    it's not a continuous one where 5 and 7 are separated by 4.

    Example2
    Input: [2,2,2,2,2]
    Output: 1
    Explanation: The longest continuous increasing subsequence is [2], its length is 1.

    Note: Length of the array will not exceed 10,000.
     */

    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int lenMax = 1;
        int lenCur = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i-1]) {
                lenCur++;
                lenMax = Math.max(lenCur, lenMax);
            } else {
                lenCur = 1;
            }
        }

        return lenMax;
    }
}
