/**
 * 665. Non-decreasing Array
 * Created by man on 2018/11/1.
 */
public class Solution665 {
    /*
    描述:
    Given an array with n integers, your task is to check if
    it could become non-decreasing by modifying at most 1 element.
    We define an array is non-decreasing if array[i] <= array[i + 1]
    holds for every i (1 <= i < n).

    测试用例:
    Example1
    Input: [4,2,3]
    Output: True
    Explanation: You could modify the first 4 to 1 to get a non-decreasing array.

    Example2
    Input: [4,2,1]
    Output: False
    Explanation: You can't get a non-decreasing array by modify at most one element.

    Note: The n belongs to [1, 10,000].
     */

    // 错误方法
    // [3,4,2,3] false 输出了true
    public boolean checkPossibility(int[] nums) {
        int cnt = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] >= nums[i - 1]) continue;
            else {
                cnt++;
                if (cnt > 1) return false;
            }
        }

        return true;
    }

    // source:https://leetcode.com/problems/non-decreasing-array/discuss/106835/Very-easy-to-understand-C++
    public boolean checkPossibility3(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                int temp = nums[i];
                // 改变nums[i]判定是否有序
                nums[i] = nums[i - 1];
                int j = 1;
                for (; j < nums.length; j++) {
                    if (nums[j] < nums[j - 1]) break;
                }
                if (j >= nums.length) return true;  // 有序

                // 改变nums[i-1]
                nums[i] = nums[i - 1] = temp;
                for (j = 1; j < nums.length; j++) {
                    if (nums[j] < nums[j - 1]) break;
                }
                if (j >= nums.length) return true;
                return false;
            }
        }

        return true;
    }

    public boolean checkPossibility2(int[] nums) {
        // 逆向思考，从后往前考虑
        // 3, 3, 2
        int cnt = 0;
        int min = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] <= min) continue;
            else {
                cnt++;
                if (cnt > 1) return false;
            }
            min = Math.min(nums[i], min);
        }
        return true;
    }
}
