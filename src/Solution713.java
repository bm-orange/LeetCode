/**
 * 713. Subarray Product Less Than K
 * Created by man on 2018/11/5.
 */
public class Solution713 {
    /*
    描述:
    Your are given an array of positive integers nums.
    Count and print the number of (contiguous) subarrays
    where the product of all the elements in the subarray
    is less than k.

    测试用例:
    Example
    Input: nums = [10, 5, 2, 6], k = 100
    Output: 8
    Explanation: The 8 subarrays that have product less than
    100 are: [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
    Note that [10, 5, 2] is not included as the product of 100
    is not strictly less than k.

    Note:
    + 0 < nums.length <= 50000.
    + 0 < nums[i] < 1000.
    + 0 <= k < 10^6.
     */
    // 超时
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            cnt += dfs(nums, i, k, nums[i]);
        }
        return cnt;
    }

    public int dfs(int[] nums, int start, int k, int product) {
        if (product >= k) return 0;
        if (start+1 >= nums.length) return 1;
        return 1 + dfs(nums, start + 1, k, product * nums[start+1]);
//        for (int i = start; i < nums.length; i++) {
//            if (product * nums[i] < k) {
//                cnt[0] += 1;
//                dfs(nums, i+1, k, product*nums[i], cnt);
//            }
//        }
    }

    // source:https://leetcode.com/problems/subarray-product-less-than-k/discuss/108830/C++-concise-solution-O(n)
    public int numSubarrayProductLessThanK2(int[] nums, int k) {
        /*
        考虑范围[l, r]的积，如果积小于k，继续增大r，否则将l右移一个减小窗口
        每次增加的子数组个数为r-l+1
        比如[0, 3]范围的积都小于k，则数组[0],[0,1], [0, 1, 2], [0,1,2,3]都满足条件
         */
        int cnt = 0;
        if (k < 2) return cnt;
        int l = 0, product = 1;
        for (int r = 0; r < nums.length && product < k; r++) {
            product *= nums[r];
            while (product >= k) product /= nums[l++];
            cnt += r - l + 1;
        }
        return cnt;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{10, 5, 2, 6};
        System.out.println(new Solution713().numSubarrayProductLessThanK(nums, 100));
    }
}
