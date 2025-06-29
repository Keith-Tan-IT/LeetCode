class Solution {
    public int equalPairs(int[][] grid) {
        int n = grid.length;
        Map<String, Integer> map = new HashMap<>();
        int count = 0;
        //for row
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb.append(grid[i][j]).append(",");
            }
            String key = sb.toString();
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        //for column
        for (int j = 0; j < n; j++) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                sb.append(grid[i][j]).append(",");
            }
            String key = sb.toString();
            count += map.getOrDefault(key, 0);
            System.out.println(count);
        }
        return count;
    }
}
