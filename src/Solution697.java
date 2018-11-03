import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 697. Degree of an Array
 * Created by man on 2018/11/3.
 */
public class Solution697 {
    /*
    描述:
    Given a non-empty array of non-negative integers nums,
    the degree of this array is defined as the maximum frequency
    of any one of its elements.

    Your task is to find the smallest possible length of a
    (contiguous) subarray of nums, that has the same degree as nums.

    测试用例:
    Example1
    Input: [1, 2, 2, 3, 1]
    Output: 2
    Explanation:
    The input array has a degree of 2 because both elements 1 and 2 appear twice.
    Of the subarrays that have the same degree:
    [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
    The shortest length is 2. So return 2.

    Example2
    Input: [1,2,2,3,1,4,2]
    Output: 6

    Note:
    + nums.length will be between 1 and 50,000.
    + nums[i] will be an integer between 0 and 49,999.
     */

    public int findShortestSubArray(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, int[]> mapIndex = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            int[] idx = mapIndex.get(nums[i]);
            if (idx == null) {
                idx = new int[2];
                idx[0] = i;
                idx[1] = i;  // 当度为1时
                mapIndex.put(nums[i], idx);
            } else {
                idx[1] = i;
            }
        }

        int degree = 0;
        int len = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (degree < entry.getValue()) {
                degree = entry.getValue();
                int[] idx = mapIndex.get(entry.getKey());
                len = idx[1] - idx[0] + 1;
            } else if (degree == entry.getValue()) {
                int[] idx = mapIndex.get(entry.getKey());
                len = Math.min(len, idx[1] - idx[0] + 1);
            }
        }

        return len;
    }
}
