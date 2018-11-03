import java.util.Arrays;

/**
 * 689. Maximum Sum of 3 Non-Overlapping Subarrays
 * Created by man on 2018/11/1.
 */
public class Solution689 {
    /*
    描述:
    In a given array nums of positive integers, find three non-overlapping
    subarrays with maximum sum.
    Each subarray will be of size k, and we want to maximize the sum of
    all 3*k entries.
    Return the result as a list of indices representing the starting
    position of each interval (0-indexed). If there are multiple answers,
    return the lexicographically smallest one.

    测试用例:
    Example
    Input: [1,2,1,2,6,7,5,1], 2
    Output: [0, 3, 5]
    Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the
    starting indices [0, 3, 5].
    We could have also taken [2, 1], but an answer of [1, 3, 5] would
    be lexicographically larger.

    Note:
    + nums.length will be between 1 and 20000.
    + nums[i] will be between 1 and 65535.
    + k will be between 1 and floor(nums.length / 3).
     */

    // https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/discuss/108231/C++Java-DP-with-explanation-O(n)
    // 可以通过dp改进，想法还是分为三个窗口，但是通过数组保存之前窗口的结果
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        /*
        每次固定一个长度为k的窗口，然后滑动第二个窗口，然后相应的滑动第三个窗口
        求出每个窗口中的和，然后三个和相加判断是否是当前最大，若是则更新索引。
         */
        int[] indexedArray = new int[3];
        int[] sumArray = new int[nums.length];
        sumArray[0] = nums[0];
        int sum1 = 0, sum2 = 0, sum3 = 0;
        for (int i = 1; i < nums.length; i++) {
            sumArray[i] = sumArray[i - 1] + nums[i];
        }
        int maxSum = sumArray[3 * k - 1];
        indexedArray[0] = 0;
        indexedArray[1] = k;
        indexedArray[2] = 2 * k;

        for (int i = k - 1; i < nums.length - 2 * k; i++) {
            sum1 = sumArray[i] - (i - k >= 0 ? sumArray[i - k] : 0);
            for (int j = i + k; j < nums.length - k; j++) {
                sum2 = sumArray[j] - sumArray[j - k];
                for (int m = j + k; m < nums.length; m++) {
                    sum3 = sumArray[m] - sumArray[m - k];
                    if (maxSum < sum1 + sum2 + sum3) {
                        indexedArray[0] = i - k + 1;  // 注意这里的索引加1
                        indexedArray[1] = j - k + 1;
                        indexedArray[2] = m - k + 1;
                        maxSum = sum1 + sum2 + sum3;
                    }
                }
            }
        }

        return indexedArray;
    }

    // source: https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/discuss/108230/Clean-Java-DP-O(n)-Solution.-Easy-extend-to-Sum-of-K-Non-Overlapping-SubArrays.
    public int[] maxSumOfThreeSubarrays2(int[] nums, int k) {
        /*
        动态规划:
        dp[t][i] 表示在0-i-1中有t组长度为k的子数组的最大和
        dp[t][i] = max(dp[t-1][i-k]+sum(nums[i-k] 到 nums[i]), dp[t][i-1])
        indexedArray 存储索引
         */
        int[][] dp = new int[3 + 1][nums.length + 1];  // dp数组
        int[][] indexedArray = new int[3 + 1][nums.length + 1];  // 索引数组，存放子数组的最后索引
        int[] sumArray = new int[nums.length + 1];  // 累加和数组
        sumArray[1] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sumArray[i + 1] = sumArray[i] + nums[i];
        }

        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= nums.length; j++) {
                if (j < i * k) continue;  // 不够形成子数组的数字
                int max1 = dp[i - 1][j - k] + sumArray[j] - sumArray[j - k];
                if (max1 > dp[i][j - 1]) {
                    dp[i][j] = max1;
                    indexedArray[i][j] = j;
                } else {
                    dp[i][j] = dp[i][j - 1];
                    indexedArray[i][j] = indexedArray[i][j-1];
                }
            }
        }

        int[] result = new int[3];
        result[2] = indexedArray[3][nums.length];
        result[1] = indexedArray[2][result[2]-k];
        result[0] = indexedArray[1][result[1]-k];  // 得到结尾的索引
        result[0] -= k;  // 换算为开始索引
        result[1] -= k;
        result[2] -= k;
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 1, 2, 6, 7, 5, 1};
        System.out.println(Arrays.toString(new Solution689().maxSumOfThreeSubarrays2(nums, 2)));
    }
}
