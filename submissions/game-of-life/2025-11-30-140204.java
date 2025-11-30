/**
 * TIP
 * Problem: 289. Game of Life
 *
 * Goal:
 * - Apply Game of Life rules to every cell simultaneously.
 * - Update the board IN PLACE (no extra m*n array).
 *
 * -------------------------------------------------------------
 * Key Idea:
 * Use in-place ENCODING to store both old + new states:
 *   1 -> 0  encoded as -1
 *   0 -> 1  encoded as  2
 *
 * Old state check:
 *   old alive = (cell == 1 || cell == -1)
 *
 * After scanning:
 *   -1 → 0   (was live, now dead)
 *    2 → 1   (was dead, now live)
 *
 * -------------------------------------------------------------
 * Rules Recap:
 * - Live cell survives with 2 or 3 neighbors.
 * - Dead cell becomes live with exactly 3 neighbors.
 * - Everything else dies/stays dead.
 *
 * -------------------------------------------------------------
 * Complexity:
 * Time  = O(m*n)
 * Space = O(1)
 *
 * -------------------------------------------------------------
 * Takeaways:
 * - Use encoding to avoid extra memory.
 * - Always count neighbors using OLD state (1 or -1).
 * - This is a classic in-place transformation pattern.
 */

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