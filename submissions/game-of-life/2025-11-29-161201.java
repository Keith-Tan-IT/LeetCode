class Solution {
    public void gameOfLife(int[][] board) {
        int m = board.length, n = board[0].length;
        int [][] copy = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int liveNeighbors = count(board, i, j);
                if (board[i][j] == 1) {
                    if (liveNeighbors == 2 || liveNeighbors == 3) {
                        copy[i][j] = 1;
                    }
                    else {
                        copy[i][j] = 0;
                    }
                }
                else {
                    copy[i][j] = (liveNeighbors == 3) ? 1 : 0;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = copy[i][j];
            }
        }
    }
    public int count(int[][] board, int r, int c) {
        int m = board.length, n = board[0].length;
        int liveCount = 0;
        int[][] direction = {
            {1,0}, {-1,0}, {0,1}, {0,-1},
            {1,1}, {-1,1}, {1,-1}, {-1,-1}
        };
        for (int[] d : direction) {
            int nr = r + d[0], nc = c + d[1];
            if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                liveCount += board[nr][nc];
            }
        }
        return liveCount;
    }
}