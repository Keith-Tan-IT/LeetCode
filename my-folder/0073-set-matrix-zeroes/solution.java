/**
 * TIP
 * Problem: 73. Set Matrix Zeroes
 *
 * Goal:
 * - If a cell is 0 → set its entire row and column to 0.
 * - Must be done IN-PLACE.
 *
 * -------------------------------------------------------------
 * Approaches:
 * 1) O(m*n) space   → store full copy (bad)
 * 2) O(m + n) space → track zero rows & columns separately
 * 3) O(1) space     → best approach using first row & column as markers
 *
 * -------------------------------------------------------------
 * O(1) Space Trick:
 * - Use matrix[i][0] to mark "row i should be zero"
 * - Use matrix[0][j] to mark "column j should be zero"
 * - BUT first row & first column need separate flags:
 *      boolean firstRowZero
 *      boolean firstColZero
 *   Because they are used as markers and cannot mark themselves.
 *
 * -------------------------------------------------------------
 * Example:
 * Input:
 * [1,1,1]
 * [1,0,1]
 * [1,1,1]
 *
 * Markers become:
 * First row marker:  row 1 has zero → matrix[1][0] = 0
 * First col marker:  col 1 has zero → matrix[0][1] = 0
 *
 * After applying markers:
 * [1,0,1]
 * [0,0,0]
 * [1,0,1]
 *
 * -------------------------------------------------------------
 * Complexity:
 * Time  = O(m*n)
 * Space = O(1) extra (optimal)
 *
 * -------------------------------------------------------------
 * Takeaways:
 * - First row & column act as storage for zero markers.
 * - Must track whether first row/col originally contained zero.
 * - Never create extra arrays when matrix itself can store flags.
 */
class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean firstRowZero = false;
        boolean firstColumnZero = false;
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                firstColumnZero = true;
                break;
            } 
        }
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                firstRowZero = true;
                break;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (firstColumnZero) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
        if (firstRowZero) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }
    }
}
