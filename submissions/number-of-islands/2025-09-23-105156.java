/**
 * TIP
 * Problem context: LeetCode 200 — Number of Islands
 *
 * Summary:
 * - You are given a 2D grid of '1's (land) and '0's (water).
 * - An island is a group of connected '1's (horizontal or vertical).
 * - Count the number of distinct islands.
 *
 * Implementation Strategy:
 * 1. Traverse each cell in the grid.
 * 2. When a '1' is found:
 *    - Increment island count.
 *    - Perform BFS or DFS to mark all connected '1's as visited (set to '0').
 * 3. Use a queue (BFS) or recursion/stack (DFS) to explore neighbors.
 *
 * BFS Implementation Notes:
 * - Use a queue to store coordinates.
 * - Explore in 4 directions: up, right, down, left.
 * - Mark visited land cells as '0' immediately after enqueuing to avoid re-visiting.
 *
 * Implementation Variants:
 *
 * | Method     | Traversal | Space | Notes                          |
 * |------------|-----------|-------|--------------------------------|
 * | BFS (queue)| O(m*n)    | O(min(m,n)) | Avoids recursion stack overflow |
 * | DFS (rec)  | O(m*n)    | O(m*n) | Simpler, but can overflow stack |
 * | Union-Find | O(m*n*α(n)) | O(m*n) | Advanced; useful for large grids |
 *
 * Pitfalls:
 * - Do NOT use fixed queue size or early termination (`size`); BFS runs until the queue is empty.
 * - Always mark a cell as visited (`grid[i][j] = '0'`) before enqueuing or recursing.
 * - Handle edge cases: empty grid, single cell, all land or all water.
 *
 * Example:
 * grid = {
 *   {'1','1','0','0','0'},
 *   {'1','1','0','0','0'},
 *   {'0','0','1','0','0'},
 *   {'0','0','0','1','1'}
 * }
 * 
 * Steps:
 * - First island → (0,0) triggers BFS, marks connected (0,1), (1,0), (1,1)
 * - Second island → (2,2)
 * - Third island → (3,3) and (3,4)
 * Result: 3 islands
 *
 * Key points:
 * - Use BFS/DFS to traverse each island once.
 * - Mark cells as visited to avoid cycles.
 * - Time: O(m * n) — each cell visited once.
 * - Space: O(min(m, n)) — for BFS queue (or recursion depth).
 */

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