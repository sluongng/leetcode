import java.util.Collections;

/*
 * @lc app=leetcode id=54 lang=java
 *
 * [54] Spiral Matrix
 *
 * https://leetcode.com/problems/spiral-matrix/description/
 *
 * algorithms
 * Medium (29.97%)
 * Total Accepted:    219.9K
 * Total Submissions: 733.7K
 * Testcase Example:  '[[1,2,3],[4,5,6],[7,8,9]]'
 *
 * Given a matrix of m x n elements (m rows, n columns), return all elements of
 * the matrix in spiral order.
 * 
 * Example 1:
 * 
 * 
 * Input:
 * [
 * ⁠[ 1, 2, 3 ],
 * ⁠[ 4, 5, 6 ],
 * ⁠[ 7, 8, 9 ]
 * ]
 * Output: [1,2,3,6,9,8,7,4,5]
 * 
 * 
 * Example 2:
 * 
 * Input:
 * [
 * ⁠ [1, 2, 3, 4],
 * ⁠ [5, 6, 7, 8],
 * ⁠ [9,10,11,12]
 * ]
 * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 * 
 */
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {

        if (matrix.length == 0) {
            return Collections.EMPTY_LIST;
        }

        /**
         * direction of movement
         * - Left to Right: 0
         * - Top to Bottom: 1
         * - Right to Left: 2
         * - Bottom to Top: 3
         */
        int direction = 0;

        /**
         * size of output array should be equal to (row * column) of input array
         */
        int height = matrix.length;
        int width = matrix[0].length;
        int resultLength = height * width;

        int upperBound = 0;
        int lowerBound = height-1;
        int rightBound = width-1;
        int leftBound = 0;

        // index of current element
        int i = 0;
        int j = 0;

        List<Integer> result = new LinkedList();
        int index = 0;
        while (index < resultLength) {
            result.add(matrix[i][j]);

            switch (direction) {
                case 0:
                    if (j + 1 > rightBound) {
                        upperBound++;
                        direction = changeDirection(direction);
                        i++;
                    } else {
                        j++;
                    }
                    break;
                case 1:
                    if (i + 1 > lowerBound) {
                        rightBound--;
                        direction = changeDirection(direction);
                        j--;
                    } else {
                        i++;
                    }
                    break;
                case 2:
                    if (j - 1 < leftBound) {
                        lowerBound--;
                        direction = changeDirection(direction);
                        i--;
                    } else {
                        j--;
                    }
                    break;
                case 3:
                    if (i - 1 < upperBound) {
                        leftBound++;
                        direction = changeDirection(direction);
                        j++;
                    } else {
                        i--;
                    }
                    break;
                default:
                    break;
            }

            index++;
        }

        return result;
    }

    /**
     * used to change current direction
     * @param currDirection current direction represented by integer 0 <= i <= 3
     * @return new direction
     */
    private static int changeDirection(int currDirection) {
        if (currDirection >= 3) {
            return 0;
        }
        return currDirection+1;
    }
}

