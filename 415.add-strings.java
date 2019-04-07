/*
 * @lc app=leetcode id=415 lang=java
 *
 * [415] Add Strings
 *
 * https://leetcode.com/problems/add-strings/description/
 *
 * algorithms
 * Easy (43.38%)
 * Total Accepted:    89.2K
 * Total Submissions: 205.7K
 * Testcase Example:  '"0"\n"0"'
 *
 * Given two non-negative integers num1 and num2 represented as string, return
 * the sum of num1 and num2.
 * 
 * Note:
 * 
 * The length of both num1 and num2 is < 5100.
 * Both num1 and num2 contains only digits 0-9.
 * Both num1 and num2 does not contain any leading zero.
 * You must not use any built-in BigInteger library or convert the inputs to
 * integer directly.
 * 
 * 
 */
class Solution {
    public String addStrings(String num1, String num2) {
        char[] numOne = num1.toCharArray();
        char[] numTwo = num2.toCharArray();
        int maxLength = numOne.length > numTwo.length ? numOne.length : numTwo.length;
        int carryOver = 0;
        String result = "";

        for (int i = 0; i < maxLength; i++) {
            int sum;
            int idx1 = numOne.length - 1 - i;
            int idx2 = numTwo.length - 1 - i;

            if (idx1 < 0) {
                sum = Character.getNumericValue(numTwo[idx2]);
            } else if (idx2 < 0) {
                sum = Character.getNumericValue(numOne[idx1]);
            } else {
                sum = Character.getNumericValue(numOne[idx1]) + Character.getNumericValue(numTwo[idx2]);
            }
            sum += carryOver;
            carryOver = 0;

            if (sum >= 10) {
                carryOver = 1;
                sum -= 10;
            }
            result = String.valueOf(sum) + result;
        }

        if (carryOver != 0) {
            result = "1" + result;
        }

        return result;
    }
}
