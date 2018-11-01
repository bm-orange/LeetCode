import java.util.Arrays;

/**
 * 667. Beautiful Arrangement II
 * Created by man on 2018/11/1.
 */
public class Solution667 {
    /*
    描述:
    Given two integers n and k, you need to construct a list
    which contains n different positive integers ranging from
    1 to n and obeys the following requirement:
    Suppose this list is [a1, a2, a3, ... , an], then the list
    [|a1 - a2|, |a2 - a3|, |a3 - a4|, ... , |an-1 - an|] has exactly k distinct integers.

    If there are multiple answers, print any of them.

    测试用例:
    Example1
    Input: n = 3, k = 1
    Output: [1, 2, 3]
    Explanation: The [1, 2, 3] has three different positive integers ranging
    from 1 to 3, and the [1, 1] has exactly 1 distinct integer: 1.

    Example2
    Input: n = 3, k = 2
    Output: [1, 3, 2]
    Explanation: The [1, 3, 2] has three different positive integers ranging from
    1 to 3, and the [2, 1] has exactly 2 distinct integers: 1 and 2.

    Note:
    + The n and k are in the range 1 <= k < n <= 10^4.
     */

    // source:https://leetcode.com/problems/beautiful-arrangement-ii/discuss/106948/C++-Java-Clean-Code-4-liner
    public int[] constructArray(int n, int k) {
        // 通过从首尾交叉取值
        int l = n - k;
        int r = n;
        int[] result = new int[n];
        for (int i = 0; i < l - 1; i++) {
            result[i] = i + 1;
        }

        int i = l - 1;
        while (l <= r) {  // 注意奇偶，比如n=3， k=2
            if (l != r) {
                result[i++] = l;
                result[i++] = r;
            } else {
                result[i++] = l;
            }
            l++;
            r--;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution667().constructArray(9, 5)));
    }
}
