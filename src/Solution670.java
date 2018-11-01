/**
 * 670. Maximum Swap
 * Created by man on 2018/11/1.
 */
public class Solution670 {
    /*
    描述:
    Given a non-negative integer, you could swap two digits at most
    once to get the maximum valued number. Return the maximum valued number you could get.

    测试用例:
    Example1
    Input: 2736
    Output: 7236
    Explanation: Swap the number 2 and the number 7.

    Example2
    Input: 9973
    Output: 9973
    Explanation: No swap.

    Note:
    + The given number is in the range [0, 10^8]
     */

    public int maximumSwap(int num) {
        /*
        找到一个后一个数字大于前一个数字的情况，此位置为需要调整的位置，
        由此位置开始，向后找，找到最大的数字，与它的位置，然后交换该位置的数字，
        与开始位置前一位的数字

        115不能过输出了151，应该为511
         */
        String strNum = Integer.toString(num);
        StringBuilder strBuilder = new StringBuilder(strNum);
        char max = strBuilder.charAt(0);
        int pos = 0;
        int posMax = 0;
        for (int i = 1; i < strNum.length(); i++) {
            if(strBuilder.charAt(i-1) < strBuilder.charAt(i)) { // 前一个数字小于后一个数字
                pos = i - 1;
                max = strBuilder.charAt(i);
                posMax = i;
                for (int j = i; j < strNum.length(); j++) {
                    if (max <= strBuilder.charAt(j)) {
                        max = strBuilder.charAt(j);
                        posMax = j;
                    }
                }
                break;
            }
        }

        // 1993不能过

        // 找到前一个位置比最值大，或者为初始位置
        while (pos-1 >= 0 && strBuilder.charAt(pos-1) < max) pos--;

        strBuilder.setCharAt(posMax, strBuilder.charAt(pos));
        strBuilder.setCharAt(pos, max);
        return Integer.valueOf(strBuilder.toString());
    }

    // https://leetcode.com/problems/maximum-swap/solution/
    public int maximumSwap2(int num) {
        char[] A = Integer.toString(num).toCharArray();
        int[] last = new int[10];
        // 出现位数d的最大的索引
        for (int i = 0; i < A.length; i++) {
            last[A[i] - '0'] = i;
        }

        for (int i = 0; i < A.length; i++) {
            // 从每一位置进行判定，该位置从位数9到当前位数之间的位数出现的索引是否有在i之后的
            // 如果有，那么表示这是需要交换的位置
            for (int d = 9; d > A[i] - '0'; d--) {
                if (last[d] > i) {
                    char tmp = A[i];
                    A[i] = A[last[d]];
                    A[last[d]] = tmp;
                    return Integer.valueOf(new String(A));
                }
            }
        }
        return num;
    }

    public static void main(String[] args) {
        System.out.println(new Solution670().maximumSwap(98368));
    }
}
