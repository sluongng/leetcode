import java.util.*;

/*
 * @lc app=leetcode id=20 lang=java
 *
 * [20] Valid Parentheses
 *
 * https://leetcode.com/problems/valid-parentheses/description/
 *
 * algorithms
 * Easy (35.88%)
 * Total Accepted:    519.6K
 * Total Submissions: 1.4M
 * Testcase Example:  '"()"'
 *
 * Given a string containing just the characters '(', ')', '{', '}', '[' and
 * ']', determine if the input string is valid.
 * 
 * An input string is valid if:
 * 
 * 
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * 
 * 
 * Note that an empty string is also considered valid.
 * 
 * Example 1:
 * 
 * 
 * Input: "()"
 * Output: true
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: "()[]{}"
 * Output: true
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: "(]"
 * Output: false
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: "([)]"
 * Output: false
 * 
 * 
 * Example 5:
 * 
 * 
 * Input: "{[]}"
 * Output: true
 * 
 * 
 */
class Solution {
    public boolean isValid(String s) {
        if ("".equals(s)) {
            return true;
        }

        Stack<Character> bracketStack = new Stack<>();

        for(int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case '(':
                    bracketStack.push(')');
                    break;
                case '[':
                    bracketStack.push(']');
                    break;
                case '{':
                    bracketStack.push('}');
                    break;
                case ')':
                case ']':
                case '}':
                default:
                    if (bracketStack.empty()) {
                        return false;
                    }

                    char nextCloseBracket = bracketStack.pop();

                    if (s.charAt(i) != nextCloseBracket) {
                        return false;
                    }
            }
        }

        return bracketStack.empty();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isValid("([])[]{}{([])}"));
    }
}

