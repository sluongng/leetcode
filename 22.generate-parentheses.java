import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/*
 * @lc app=leetcode id=22 lang=java
 *
 * [22] Generate Parentheses
 *
 * https://leetcode.com/problems/generate-parentheses/description/
 *
 * algorithms
 * Medium (53.16%)
 * Total Accepted:    302.1K
 * Total Submissions: 568.3K
 * Testcase Example:  '3'
 *
 * 
 * Given n pairs of parentheses, write a function to generate all combinations
 * of well-formed parentheses.
 * 
 * 
 * 
 * For example, given n = 3, a solution set is:
 * 
 * 
 * [
 * ⁠ "((()))",
 * ⁠ "(()())",
 * ⁠ "(())()",
 * ⁠ "()(())",
 * ⁠ "()()()"
 * ]
 * 
 */
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new LinkedList<>();

        while (n > 0) {
            if (result.isEmpty()) {
                result.add("()");
            } else {
                result = result.stream()
                    .map(this::addBracketToString)
                    .flatMap(List::stream)
                    .distinct()
                    .collect(Collectors.toList());
            }

            n--;
        }

        return result;
    }

    private List<String> addBracketToString(String stringOfBracket) {
        List<String> result = new LinkedList<>();

        for (int i = 0; i < stringOfBracket.length(); i++) {
            if (stringOfBracket.charAt(i) == ')') {
                String newCombination = stringOfBracket.substring(0, i) + "()" + stringOfBracket.substring(i);
                result.add(newCombination);
            }
        }
        result.add(stringOfBracket + "()");

        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.generateParenthesis(2));
        System.out.println(solution.generateParenthesis(3));
        System.out.println(solution.generateParenthesis(4));
    }
}

