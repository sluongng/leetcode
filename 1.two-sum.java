import java.util.Map;

/*
 * @lc app=leetcode id=1 lang=java
 *
 * [1] Two Sum
 *
 * https://leetcode.com/problems/two-sum/description/
 *
 * algorithms
 * Easy (41.92%)
 * Total Accepted:    1.5M
 * Total Submissions: 3.6M
 * Testcase Example:  '[2,7,11,15]\n9'
 *
 * Given an array of integers, return indices of the two numbers such that they
 * add up to a specific target.
 * 
 * You may assume that each input would have exactly one solution, and you may
 * not use the same element twice.
 * 
 * Example:
 * 
 * 
 * Given nums = [2, 7, 11, 15], target = 9,
 * 
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 * 
 * 
 * 
 * 
 */
class Solution {
    public int[] twoSum(int[] nums, int target) {

        // Potential maximum is nums.length, 
        // we use nums.length + 1 to avoid additional mem allocation
        Map<Integer, Integer> remainIndexMap = new HashMap<>(nums.length + 1);

        int remain;
        for (int i = 0; i < nums.length; i++) {
            remain = target - nums[i];

            if(remainIndexMap.containsKey(remain)) {
                return new int[]{remainIndexMap.get(remain), i};
            } else {
                remainIndexMap.put(nums[i], i);
            }
        }

        // input should have exactly one solution
        // execution should never reach this part
        return new int[]{};
    }
}

