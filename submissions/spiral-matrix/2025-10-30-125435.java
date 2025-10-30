/**
 * TIP
 * Problem context: LeetCode 54 — Spiral Matrix
 *
 * Summary:
 * - Traverse an m×n matrix in clockwise spiral order.
 * - Example:
 *   Input: [[1,2,3],[4,5,6],[7,8,9]]
 *   Output: [1,2,3,6,9,8,7,4,5]
 *
 * ----------------------------------------------------------------
 * Implementation Strategy:
 * 
 * Maintain 4 boundaries:
 *   top = 0, bottom = m-1
 *   left = 0, right = n-1
 *
 * Repeat while (top <= bottom && left <= right):
 *   1️⃣ Traverse Left → Right (top row), then top++
 *   2️⃣ Traverse Top → Bottom (right column), then right--
 *   3️⃣ Traverse Right → Left (bottom row, if top ≤ bottom), then bottom--
 *   4️⃣ Traverse Bottom → Top (left column, if left ≤ right), then left++
 *
 * ----------------------------------------------------------------
 * Complexity:
 * | Operation | Time | Space |
 * |------------|------|-------|
 * | Spiral traversal | O(m*n) | O(1) |
 *
 * ----------------------------------------------------------------
 * Pitfalls:
 * - ❌ Forgetting to check bounds (top ≤ bottom, left ≤ right).
 * - ❌ Increment/decrement boundaries at wrong place → duplicates.
 * - ⚠️ Edge case: single row or single column.
 *
 * ----------------------------------------------------------------
 * Example Walkthrough:
 * matrix = [
 *   [1, 2, 3],
 *   [4, 5, 6],
 *   [7, 8, 9]
 * ]
 * → Traverse 1→2→3→6→9→8→7→4→5
 *
 * ----------------------------------------------------------------
 * Clean Implementation:
 *
 * class Solution {
 *     public List<Integer> spiralOrder(int[][] matrix) {
 *         List<Integer> result = new ArrayList<>();
 *         if (matrix == null || matrix.length == 0) return result;
 *         int top = 0, bottom = matrix.length - 1;
 *         int left = 0, right = matrix[0].length - 1;
 *         while (top <= bottom && left <= right) {
 *             for (int j = left; j <= right; j++) result.add(matrix[top][j]);
 *             top++;
 *             for (int i = top; i <= bottom; i++) result.add(matrix[i][right]);
 *             right--;
 *             if (top <= bottom)
 *                 for (int j = right; j >= left; j--) result.add(matrix[bottom][j]);
 *             bottom--;
 *             if (left <= right)
 *                 for (int i = bottom; i >= top; i--) result.add(matrix[i][left]);
 *             left++;
 *         }
 *         return result;
 *     }
 * }
 */

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int top = 0, bottom = matrix.length - 1;
        int left = 0, right = matrix[0].length - 1;
        while (bottom >= top && right >= left) {
            for (int i = left; i <= right; i++) {
                result.add(matrix[top][i]);
            }
            top++;

            for (int j = top; j <= bottom; j++) {
                result.add(matrix[j][right]);
            }
            right--;

            if (bottom >= top) {
                for (int i = right; i >= left; i--) {
                    result.add(matrix[bottom][i]);
                }
                bottom--;
            }

            if (right >= left) {
                for (int j = bottom; j >= top; j--) {
                    result.add(matrix[j][left]);
                }
                left++;
            }
        }
        return result;
    }
}