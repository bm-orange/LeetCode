/**
 * 714. Best Time to Buy and Sell Stock with Transaction Fee
 * Created by man on 2018/11/3.
 */
public class Solution714 {
    /*
    描述:
    Your are given an array of integers prices, for which the i-th element
    is the price of a given stock on day i; and a non-negative integer fee
    representing a transaction fee.

    You may complete as many transactions as you like, but you need to pay
    the transaction fee for each transaction. You may not buy more than 1
    share of a stock at a time (ie. you must sell the stock share before you
    buy again.)

    Return the maximum profit you can make.

    测试用例:
    Example1
    Input: prices = [1, 3, 2, 8, 4, 9], fee = 2
    Output: 8
    Explanation: The maximum profit can be achieved by:
    Buying at prices[0] = 1
    Selling at prices[3] = 8
    Buying at prices[4] = 4
    Selling at prices[5] = 9
    The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.

    Note:
    + 0 < prices.length <= 50000.
    + 0 < prices[i] < 50000.
    + 0 <= fee < 50000.

     */

    public int maxProfit(int[] prices, int fee) {
        // [1,3,7,5,10,3]
        // 3
        // 不能得到正确答案
        int maxProfit = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] - min > fee) {
                maxProfit += prices[i] - min - fee;
                min = prices[i];
            }
            min = Math.min(min, prices[i]);
        }

        return maxProfit;
    }

    // source: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/108870/Most-consistent-ways-of-dealing-with-the-series-of-stock-problems
    public int maxProfit2(int[] prices, int fee) {
        /*
        通用递推式
        T[i][k][0] = max(T[i-1][k][0], T[i-1][k][1] + prices[i])
        T[i][k][1] = max(T[i-1][k][1], T[i-1][k][0] - prices[i])
         */
        int T_ik0 = 0, T_ik1 = Integer.MIN_VALUE;
        for (int i = 0; i < prices.length; i++) {
            int T_ik0_old = T_ik0;
            T_ik0 = Math.max(T_ik0, T_ik1 + prices[i]);  // 这里数值溢出了Integer.MIN_VALUE + 1 - 2
            T_ik1 = Math.max(T_ik1, T_ik0_old - prices[i] - fee);
        }

        return T_ik0;
    }

    public static void main(String[] args) {
        int[] prices = new int[]{1, 3, 2, 8, 4, 9};

        System.out.println(new Solution714().maxProfit2(prices, 2));
    }
}
