import java.util.ArrayDeque;
import java.util.Deque;

/*
 * @lc app=leetcode id=402 lang=java
 *
 * [402] Remove K Digits
 *
 * https://leetcode.com/problems/remove-k-digits/description/
 *
 * algorithms
 * Medium (26.33%)
 * Total Accepted:    56.1K
 * Total Submissions: 213.2K
 * Testcase Example:  '"1432219"\n3'
 *
 * Given a non-negative integer num represented as a string, remove k digits
 * from the number so that the new number is the smallest possible.
 * 
 * 
 * Note:
 * 
 * The length of num is less than 10002 and will be ≥ k.
 * The given num does not contain any leading zero.
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: num = "1432219", k = 3
 * Output: "1219"
 * Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219
 * which is the smallest.
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: num = "10200", k = 1
 * Output: "200"
 * Explanation: Remove the leading 1 and the number is 200. Note that the
 * output must not contain leading zeroes.
 * 
 * 
 * 
 * Example 3:
 * 
 * Input: num = "10", k = 2
 * Output: "0"
 * Explanation: Remove all the digits from the number and it is left with
 * nothing which is 0.
 * 
 * 987987
 * 787
 * 
 * 987543456987
 * 345
 * 
 */
class Solution {
    public String removeKdigits(String num, int k) {
        // Default trivia cases
        if (k == 0) {
            return num;
        }

        if (k >= num.length()) {
            return "0";
        }

        Deque<Character> stack = new ArrayDeque<>();

        for(char c : num.toCharArray()) {
            // peekLast() removeLast() addLast()
            // used to compare with latest inserted element
            // and replace it when we found a smaller one
            while (k > 0 && !stack.isEmpty() && stack.peekLast() > c) {
                stack.removeLast();
                k--;
            }

            stack.addLast(c);
        }

        // Could be left over k caused by duplicated close digits
        while (k > 0) {
            stack.removeLast();
            k--;
        }

        // Use Queue api, instead of stack, to restore the result string

        // Remove leading zeroes
        while (!stack.isEmpty() && stack.peek() == '0') {
            stack.pop();
        }

        // Trivia cases
        if (stack.isEmpty()) {
            return "0";
        } 

        // Build result
        StringBuilder builder = new StringBuilder();
        while(!stack.isEmpty()) {
            builder.append(stack.pop());
        }
        return builder.toString();
    }
}

