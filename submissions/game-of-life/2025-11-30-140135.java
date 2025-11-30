class Solution {
    public void gameOfLife(int[][] board) {
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int liveNeighbors = count(board, i, j);
                if (board[i][j] == 1) {
                    if (liveNeighbors != 2 && liveNeighbors != 3) {
                        board[i][j] = -1;
                    }
                }
                else {
                    if (liveNeighbors == 3) {
                        board[i][j] = 2;
                    }
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == -1) {
                    board[i][j] = 0;
                }
                else if (board[i][j] == 2) {
                    board[i][j] = 1;
                }
            }
        }
    }
    public int count(int[][] board, int r, int c) {
        int[][] directions = {
            {0,1}, {0,-1}, {1,0}, {-1,0}, {-1,1}, {1,1}, {1,-1}, {-1,-1}
        };
        int live = 0;
        for (int[] d : directions) {
            int nr = r + d[0], nc = c + d[1];
            if (nr >= 0 && nr < board.length && nc >= 0 && nc < board[0].length) {
                if (board[nr][nc] == 1 || board[nr][nc] == -1) {
                live++;
                }
            } 
        }
        return live;
    }
}