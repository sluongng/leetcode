/*
 * @lc app=leetcode id=44 lang=java
 *
 * [44] Wildcard Matching
 *
 * https://leetcode.com/problems/wildcard-matching/description/
 *
 * algorithms
 * Hard (22.37%)
 * Total Accepted:    163.3K
 * Total Submissions: 729.8K
 * Testcase Example:  '"aa"\n"a"'
 *
 * Given an input string (s) and a pattern (p), implement wildcard pattern
 * matching with support for '?' and '*'.
 * 
 * 
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 * 
 * 
 * The matching should cover the entire input string (not partial).
 * 
 * Note:
 * 
 * 
 * s could be empty and contains only lowercase letters a-z.
 * p could be empty and contains only lowercase letters a-z, and characters
 * like ? or *.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input:
 * s = "aa"
 * p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 * 
 * 
 * Example 2:
 * 
 * 
 * Input:
 * s = "aa"
 * p = "*"
 * Output: true
 * Explanation: '*' matches any sequence.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input:
 * s = "cb"
 * p = "?a"
 * Output: false
 * Explanation: '?' matches 'c', but the second letter is 'a', which does not
 * match 'b'.
 * 
 * 
 * Example 4:
 * 
 * 
 * Input:
 * s = "adceb"
 * p = "*a*b"
 * Output: true
 * Explanation: The first '*' matches the empty sequence, while the second '*'
 * matches the substring "dce".
 * 
 * 
 * Example 5:
 * 
 * 
 * Input:
 * s = "acdcb"
 * p = "a*c?b"
 * Output: false
 * 
 * 
 */
class Solution {
    public boolean isMatch(String s, String p) {

        if (p.length() == 0) {
            return s.length() == 0;
        }
        if (s.length() == 0) {
            for (int i = 0; i < p.length(); i++) {
                if (p.charAt(i) != '*') {
                    return false;
                }
            }
            return true;
        }

        char[] pattern = p.toCharArray();
        char[] stringArr = s.toCharArray();

        for (int j = 0; j < pattern.length; j++) {
            switch (pattern[j]) {
                case '*':
                    if (j == pattern.length - 1) {
                        return true;
                    }

                    int nextNonStarIndex = j + 1;
                    for (int i = j + 1; i < pattern.length; i++) {
                        if (pattern[i] == '*') {
                            nextNonStarIndex++;
                        } else {
                            break;
                        }
                    }
                    if (nextNonStarIndex == pattern.length) {
                        return true;
                    }

                    for (int i = j; i < stringArr.length; i++) {
                        if (isMatch(s.substring(i), p.substring(nextNonStarIndex))) {
                            return true;
                        }
                    }
                    return false;
                case '?':
                    return isMatch(s.substring(j + 1), p.substring(j + 1));
                default:
                    if (j >= stringArr.length || pattern[j] != stringArr[j]) {
                        return false;
                    }
                    break;
            }
        }

        return true;
    }
}
