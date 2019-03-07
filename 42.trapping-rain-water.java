/*
 * @lc app=leetcode id=42 lang=java
 *
 * [42] Trapping Rain Water
 *
 * https://leetcode.com/problems/trapping-rain-water/description/
 *
 * algorithms
 * Hard (41.96%)
 * Total Accepted:    259.2K
 * Total Submissions: 617.7K
 * Testcase Example:  '[0,1,0,2,1,0,1,3,2,1,2,1]'
 *
 * Given n non-negative integers representing an elevation map where the width
 * of each bar is 1, compute how much water it is able to trap after raining.
 * 
 * 
 * The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
 * In this case, 6 units of rain water (blue section) are being trapped. Thanks
 * Marcos for contributing this image!
 * 
 * Example:
 * 
 * 
 * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 * 
 */
class Solution {
    public int trap(int[] height) {
        if (height.length <= 2) {
            return 0;
        }

        int[] left  = new int[height.length];
        int[] right = new int[height.length];

        int maxLeft = height[0];
        left[0] = maxLeft;
        for(int i = 1; i < left.length; i++) {
            if (height[i] > maxLeft) {
                maxLeft = height[i];
                left[i] = height[i];
            } else {
                left[i] = maxLeft;
            }
        }

        int maxRight = height[height.length - 1];
        right[right.length - 1] = maxRight; 
        for(int i = right.length - 2; i >= 0; i--) {
            if (height[i] > maxRight) {
                maxRight = height[i];
                right[i] = height[i];
            } else {
                right[i] = maxRight;
            }
        }

        int result = 0;
        for(int i = 0; i < height.length; i ++) {
            result += Math.min(left[i], right[i]) - height[i];
        }

        return result;
    }
}

