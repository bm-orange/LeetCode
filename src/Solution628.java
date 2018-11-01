import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 628. Maximum Product of Three Numbers
 * Created by man on 2018/10/31.
 */
public class Solution628 {
    /*
    描述:
    Given an integer array, find three numbers whose product is maximum and output the maximum product.

    测试用例:
    Example1
    Input: [1,2,3]
    Output: 6

    Example2
    Input: [1,2,3,4]
    Output: 24

    Note:
    + The length of the given array will be in range [3,10^4]
    and all elements are in the range [-1000, 1000].
    + Multiplication of any three numbers in the input won't
    exceed the range of 32-bit signed integer.
     */

    public int maximumProduct(int[] nums) {
        // 暴力法
        //
        int[] max = new int[nums.length];
        int[] min = new int[nums.length];
        max[0] = Integer.MIN_VALUE;
        min[0] = Integer.MAX_VALUE;
        // 在第i个元素之前的所有元素中的最大值与最小值
        for (int i = 1; i < nums.length; i++) {
            max[i] = Math.max(max[i - 1], nums[i - 1]);
            min[i] = Math.min(min[i - 1], nums[i - 1]);
        }

        // 第i个元素与之前的元素的乘积的最大值与最小值
        for (int i = nums.length - 1; i > 0; i--) {
            int maxT = max[i];
            int minT = min[i];
            max[i] = Math.max(nums[i] * maxT, nums[i] * minT);
            min[i] = Math.min(nums[i] * maxT, nums[i] * minT);
        }

        int res = Integer.MIN_VALUE;
        for (int i = 2; i < nums.length; i++) {
            // 当第i个元素确定时，比较它与之前i-2个元素之间的三个数乘积
            for (int j = i - 1; j > 0; j--) {
                res = Math.max(res, Math.max(max[j] * nums[i], min[j] * nums[i]));
            }
        }

        return res;
    }

    /*
    本题关键为：找到最大的三个数，与最小的两个数
    可以线性查找，也可以使用堆的方式
    source:https://leetcode.com/problems/maximum-product-of-three-numbers/discuss/104729/Java-O(1)-space-O(n)-time-solution-beat-100
     */
    public int maximumProduct2(int[] nums) {
        PriorityQueue<Integer> poheap = new PriorityQueue<>();  // 小顶堆
        PriorityQueue<Integer> neheap = new PriorityQueue<>(Collections.reverseOrder());  // 大顶堆
        for (int n : nums) {
            poheap.offer(n);
            neheap.offer(n);
            if (poheap.size() > 3) {
                poheap.poll();  // 将小顶堆的顶去掉，去掉的当前最小值，余下较大的值
            }
            if (neheap.size() > 2) {
                neheap.poll(); // 将大顶堆的顶去掉，去掉的当前的最大值,余下较小的值
            }
        }

        // 注意堆的流的顺序，并不是出队顺序，而是整个堆的顺序
        // poheap.stream().forEach(c -> System.out.print(c + " "));
        // System.out.println("\n");

        int max1 = poheap.stream().reduce((a, b) -> a * b).get();  // 无关顺序
        int max2 = neheap.stream().reduce((a, b) -> a * b).get();  // 无关顺序
        // Math.max(max1 * max2 * max3, max1 * min1 * min2);
        return Math.max(max1, poheap.stream().max(Integer::compareTo).get() * max2);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4};
        int[] nums2 = new int[]{-135, -73, 496, -515, -519, -773, -40, -411, -991,
                -260, 427, -67, 77, 452, 279, 556, -799, 656, -482, 144, -354, 439, -284,
                865, -917, 813, 223, -342, -779, -892, 536, -896, -141, -180, 279, -666,
                -133, -667, -533, 770, 599, 464, 408, -243, 33, -812, -398, 33, -516, 528,
                -209, 861, 637, -514, -18, 642, -33, -46, 243, 710, 778, -902, 299, -213,
                817, -344, -425, 892, 837, -320, -956, -267, 625, -114, 350, -722, 835, 825,
                82, -741, -419, -664, 135, -260, -320, -259, -513, -595, 161, -663, -389,
                402, -233, 40, -829, 695, -271, -663, 457, -294};
        System.out.println(new Solution628().maximumProduct2(nums2));
    }
}
