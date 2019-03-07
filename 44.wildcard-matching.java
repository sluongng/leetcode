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

    // Previous solution using recursion was working
    // But leetcode test cases seem to be test for memory/running time also
    // Recursion has residual on the system stack and resulted a longer running time

    // This is a solution that is a lot harder to follow
    // but has O(n) run time and O(1) mem spc complx

    public boolean isMatch(String s, String p) {
        // Index to track current position in s and p
        int sIndex = 0;
        int pIndex = 0;

        // Temp store position of sIndex and pIndex to revisit
        int sStore = -1;
        int pStore = -1;

        // Goal here is to reach the end of S in any mean possible
        while (sIndex < s.length() ) {

            boolean pIsInRange = pIndex < p.length();

            if (pIsInRange && (p.charAt(pIndex) == '?' || p.charAt(pIndex) == s.charAt(sIndex))) {

                // Very straight forward
                sIndex++;
                pIndex++;

            } else if (pIsInRange && p.charAt(pIndex) == '*') {

                // Store index as mark
                sStore = sIndex;
                pStore = pIndex;

                // Proceed the loop with the starchar being skipped
                pIndex++;

            } else if (sStore != -1) {

                // if previously there were something stored
                // take those value out and use them as indexes
                sIndex = sStore + 1;
                pIndex = pStore + 1;

                // We also increase sStore so that we dont repeat infinite loop
                // This helps the while loop proceed and terminate eventually
                sStore++;

            } else {

                // If no stored and easy case :D
                return false;
            }
        }

        // Handle the dangling *** at the end of pattern
        while (pIndex < p.length() && p.charAt(pIndex) == '*') {
            pIndex++;
        }

        // Now that sIndex has reached its length and pIndex has reached the last none-star char
        // Check if pattern has any left over
        return pIndex == p.length();
    }
}
