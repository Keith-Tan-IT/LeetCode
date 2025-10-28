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
