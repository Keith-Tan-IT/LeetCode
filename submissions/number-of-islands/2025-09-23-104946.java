class Solution {
    public int numIslands(char[][] grid) {
        int num = 0;
        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int[][] directions = {
            {-1, 0},
            {0, 1},
            {1, 0},
            {0, -1}
        };

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    grid[i][j] = '0';
                    num++;
                    queue.offer(new int[] {i, j});
                    while (!queue.isEmpty()) {
                        int[] curr = queue.poll();
                        for (int[] dir : directions) {
                            int row = curr[0] + dir[0];
                            int col = curr[1] + dir[1];
                            if (row < m && row >= 0 && col < n && col >= 0) {
                                if (grid[row][col] == '1') {
                                    queue.offer(new int[] {row, col});
                                    grid[row][col] = '0';
                                }
                            }

                        }
                    }
                }
            }
        }
        return num;
    }
}