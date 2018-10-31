import java.util.Arrays;
import java.util.function.Predicate;

/**
 * 611. Valid Triangle Number
 * Created by man on 2018/10/31.
 */
public class Solution611 {
    /*
    描述:
    Given an array consists of non-negative integers,
    your task is to count the number of triplets chosen
    from the array that can make triangles if we take them as side lengths of a triangle.

    Example 1:
    Input: [2,2,3,4]
    Output: 3
    Explanation:
    Valid combinations are:
    2,3,4 (using the first 2)
    2,3,4 (using the second 2)
    2,2,3

    Note:
    + The length of the given array won't exceed 1000.
    + The integers in the given array are in the range of [0, 1000].
     */

    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int cnt = 0;

        for (int i = 0; i < nums.length-2; i++) {
            for (int j = i+1; j < nums.length-1; j++) {
                for (int k = j+1; k < nums.length; k++) {
                    cnt += nums[i] + nums[j] > nums[k] ? 1 : 0;
                }
            }
        }

        return cnt;
    }

    public int triangleNumber2(int[] nums) {
        Arrays.sort(nums);
        int cnt = 0;
        // 当一个值确定时，变成一个窗口问题，使用双指针
        for (int i = nums.length-1; i > 1; i--) {
            // 当确定其中一个数为nums[i]时
            // 从左侧0开始，右侧nums[i-1]结束的区间中选择
            int l = 0;
            int r = i-1;
            while (l < r) {
                if (nums[l] + nums[r] > nums[i]) {
                    // 那么l-r的区间中的所有值都满足构成一个三角形
                    cnt += r-l;
                    r--;
                } else {
                    l++;
                }
            }
        }
        return cnt;
    }

    public boolean canBeTriangle(int a, int b, int c) {
        // a,b,c有序 a<=b<=c
        return a + b > c;
    }

    public static void main(String[] args) {
        System.out.println(new Solution611().triangleNumber(new int[]{2, 2, 3, 4}));
    }
}
