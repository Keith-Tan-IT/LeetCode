class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        List<int[]> zero = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    zero.add(new int[] {i,j});
                }
            }
        }
        for (int[] element : zero) {
            int row = element[0], column = element[1];
            for (int i = 0; i < m; i++) {
                matrix[i][column] = 0;
            }
            for (int j = 0; j < n; j++) {
                matrix[row][j] = 0;
            }
        }
    }
}