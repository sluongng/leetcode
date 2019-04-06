/*
 * @lc app=leetcode id=231 lang=java
 *
 * [231] Power of Two
 *
 * https://leetcode.com/problems/power-of-two/description/
 *
 * algorithms
 * Easy (41.76%)
 * Total Accepted:    219.1K
 * Total Submissions: 524.8K
 * Testcase Example:  '1'
 *
 * Given an integer, write a function to determine if it is a power of two.
 * 
 * Example 1:
 * 
 * 
 * Input: 1
 * Output: true 
 * Explanation: 2^0 = 1
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: 16
 * Output: true
 * Explanation: 2^4 = 16
 * 
 * Example 3:
 * 
 * 
 * Input: 218
 * Output: false
 * 
 */
class Solution {
    public boolean isPowerOfTwo(int n) {
        switch (n) {
        case 0:
            return false;
        case 1:
            return true;
        default:
            if (n % 2 == 1) {
                return false;
            }
            return isPowerOfTwo(n / 2);
        }
    }
}
