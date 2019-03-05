import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/*
 * @lc app=leetcode id=31 lang=java
 *
 * [31] Next Permutation
 *
 * https://leetcode.com/problems/next-permutation/description/
 *
 * algorithms
 * Medium (30.05%)
 * Total Accepted:    214.7K
 * Total Submissions: 714.4K
 * Testcase Example:  '[1,2,3]'
 *
 * Implement next permutation, which rearranges numbers into the
 * lexicographically next greater permutation of numbers.
 * 
 * If such arrangement is not possible, it must rearrange it as the lowest
 * possible order (ie, sorted in ascending order).
 * 
 * The replacement must be in-place and use only constant extra memory.
 * 
 * Here are some examples. Inputs are in the left-hand column and its
 * corresponding outputs are in the right-hand column.
 * 
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 * 
 */
class Solution {
    public void nextPermutation(int[] nums) {

        boolean uglyCase = false;

        for(int i = nums.length - 1; i >= 0; i--) {

            if(i == 0) {
                int left = 0;
                int right = nums.length - 1;
                while(left < right) {
                    swap2Index(nums, left, right);
                    left++;
                    right--;
                }
                return;
            }

            if(nums[i] > nums[i-1]) {
                if (uglyCase) {

                    // Traverse from right to left
                    // all elements are in increasing orders
                    // nums[i-1] is the first drop in value

                    // Here we take nums[i-1] and swap it
                    // with the minimum value (n) in nums[i -> nums.length]
                    // such that n > nums[i-1]

                    // accomplish that by traversing i-> end
                    // taking the differences(distant) between current value and nums[i-1]
                    // discard all distants less than 0
                    // collect the min distant and min index

                    // nums[i] > nums[i-1] so the default index here is `i`

                    int closestBiggerIndex = i;
                    for (int j = i; j < nums.length; j++) {

                        int minDistant = nums[closestBiggerIndex] - nums[i-1];
                        int currentDistant = nums[j] - nums[i-1];

                        if ( currentDistant > 0 && currentDistant < minDistant) {
                            minDistant = currentDistant;
                            closestBiggerIndex = j;
                        }
                    }
                    swap2Index(nums, closestBiggerIndex, i-1);

                    // sort nums[i -> nums.length - 1] ascending
                    Arrays.sort(nums, i, nums.length);
                } else {
                    swap2Index(nums, i, i-1);
                }
                return;
            }

            if(nums[i] < nums[i-1] && !uglyCase) {
                uglyCase = true;
            }
        }
    }

    private void swap2Index(int[] arr, int idx1, int idx2) {
        arr[idx1] = arr[idx1] ^ arr[idx2];
        arr[idx2] = arr[idx2] ^ arr[idx1];
        arr[idx1] = arr[idx1] ^ arr[idx2];
    }

    // Run with javac 31.next-permutation.java && java -cp . Solution
    public static void main(String[] args) {
        // Test case -> Expected result map
        Map<int[], int[]> testAndResultMap = new LinkedHashMap<>();

        // Base Case
        testAndResultMap.put(new int[]{1, 1, 1},  new int[]{1, 1, 1});

        // Sorting order cases
        testAndResultMap.put(new int[]{1, 2, 3},  new int[]{1, 3, 2});
        testAndResultMap.put(new int[]{1, 3, 2},  new int[]{2, 1, 3});
        testAndResultMap.put(new int[]{1, 8, 2, 7, 3},  new int[]{1, 8, 3, 2, 7});
        testAndResultMap.put(new int[]{1, 2, 8, 7, 3},  new int[]{1, 3, 2, 7, 8});
        testAndResultMap.put(new int[]{1, 2, 8, 7, 3, 3},  new int[]{1, 3, 2, 3, 7, 8});
        testAndResultMap.put(new int[]{1, 2, 8, 7, 3, 4},  new int[]{1, 2, 8, 7, 4, 3});

        // Some more case with peak dropping(from right to left) at 0 index
        testAndResultMap.put(new int[]{2, 3, 1},  new int[]{3, 1, 2});
        testAndResultMap.put(new int[]{3, 9, 8, 2, 1},  new int[]{8, 1, 2, 3, 9});

        // Reseting order cases
        testAndResultMap.put(new int[]{3, 2, 1},  new int[]{1, 2, 3});
        testAndResultMap.put(new int[]{5, 3, 2, 1},  new int[]{1, 2, 3, 5});

        // Duplicated number cases
        testAndResultMap.put(new int[]{1, 1, 5},  new int[]{1, 5, 1});
        testAndResultMap.put(new int[]{1, 1, 5, 5, 5},  new int[]{1, 5, 1, 5, 5});

        Solution test = new Solution();
        testAndResultMap.entrySet().stream()
            .forEach(entry -> {
                try {
                    test.nextPermutation(entry.getKey());
                    boolean result = Arrays.equals(entry.getKey(), entry.getValue());
                    System.out.println(result);
                    if (!result) {
                        System.out.println("Expected:");
                        printArr(entry.getValue());
                        System.out.println("Result:");
                        printArr(entry.getKey());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
    }

    private static void printArr(int[] arr) {
        for (int i = 0; i<arr.length; i++) {
            System.out.print(arr[i]);
            if (i != arr.length - 1) System.out.print(", ");
        }
        System.out.println("");
    }
}

