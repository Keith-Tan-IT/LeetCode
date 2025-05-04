class Solution {
    public int nearestExit(char[][] maze, int[] entrance) {
        int row = maze.length;
        int column = maze[0].length;
        int[][] directions = new int[][] {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(entrance);
        boolean[][] visited = new boolean[row][column];
        visited[entrance[0]][entrance[1]] = true;
        int steps = 0;
        while (!queue.isEmpty()) {
            steps++;
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                int[] curr = queue.poll();
                for (int[] direction : directions) {
                    int newRow = curr[0] + direction[0];
                    int newColumn = curr[1] + direction[1];
                    if (newRow >= 0 && newRow < row && newColumn >= 0 && newColumn < column && (!visited[newRow][newColumn] && maze[newRow][newColumn] == '.')) {
                        if (newRow == 0 || newRow == row - 1 || newColumn == 0 || newColumn == column - 1) {
                            return steps;
                        }
                        else {
                            queue.offer(new int[] {newRow,newColumn});
                            visited[newRow][newColumn] = true;
                        }
                    }
                }
            }
        }
        return -1;
    }
}
