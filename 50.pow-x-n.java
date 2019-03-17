/*
 * @lc app=leetcode id=50 lang=java
 *
 * [50] Pow(x, n)
 *
 * https://leetcode.com/problems/powx-n/description/
 *
 * algorithms
 * Medium (27.63%)
 * Total Accepted:    296.1K
 * Total Submissions: 1.1M
 * Testcase Example:  '2.00000\n10'
 *
 * Implement pow(x, n), which calculates x raised to the power n (x^n).
 * 
 * Example 1:
 * 
 * 
 * Input: 2.00000, 10
 * Output: 1024.00000
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: 2.10000, 3
 * Output: 9.26100
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: 2.00000, -2
 * Output: 0.25000
 * Explanation: 2^-2 = 1/2^2 = 1/4 = 0.25
 * 
 * 
 * Note:
 * 
 * 
 * -100.0 < x < 100.0
 * n is a 32-bit signed integer, within the range [−2^31, 2^31 − 1]
 * 
 * 
 */
class Solution {
    public double myPow(double x, int n) {
        if (x == 0 || x == 1) {
            return x;
        } else if (n == Integer.MIN_VALUE) {
            return myPow(x * x, n / 2);
        } else if (n < 0) {
            return 1 / myPow(x, (-1 * n));
        } else if (n == 0) {
            return 1D;
        } else if (n == 1) {
            return x;
        } else if (n == 2) {
            return x * x;
        } else if (n % 2 == 0) {
            return myPow(x * x, n / 2);
        } else {
            return myPow(x * x, n / 2) * x;
        }
    }

    public static void main(String[] args) {
        Solution mySolution = new Solution();

        System.out.println(mySolution.myPow(2.0, -2147483648));
    }
}
