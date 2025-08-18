class Solution {
    public int orangesRotting(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> rotten = new LinkedList<>();
        int freshOrange = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    rotten.offer(new int[] {i, j});
                }
                else if (grid[i][j] == 1) {
                    freshOrange++;
                }
            }
        }
        if (freshOrange == 0) {
            return 0;
        }
        int minutes = 0;
        int[][] directions = new int[][] {{0,1}, {0, -1}, {1, 0}, {-1, 0}};
        while (!rotten.isEmpty() && freshOrange > 0) {
            minutes++;
            int size = rotten.size();
            for (int i = 0; i < size; i++) {
                int[] position = rotten.poll();
                for (int[] dir : directions) {
                    int x = dir[0] + position[0];
                    int y = dir[1] + position[1];
                    if (x < 0 || y < 0 || x >= rows || y >= cols || grid[x][y] == 0 || grid[x][y] == 2) {
                        continue;
                    }
                    else {
                        grid[x][y] = 2;
                        rotten.offer(new int[] {x, y});
                        freshOrange--;
                    }
                }
            }
        }
        return freshOrange == 0 ? minutes : -1;
    }
}
