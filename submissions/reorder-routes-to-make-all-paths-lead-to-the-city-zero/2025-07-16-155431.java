class Solution {
    int change = 0;
    List<int[]>[] graph;
    public int minReorder(int n, int[][] connections) {
        graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] c : connections) {
            graph[c[0]].add(new int[] {c[1], 1});
            graph[c[1]].add(new int[] {c[0], 0});
        }
        dfs(0, -1);
        return change;
    }
    public void dfs (int node, int parent) {
        for (int[] edge : graph[node]) {
            int neighbour = edge[0];
            int isOutgoing = edge[1];
            
            if (neighbour == parent) {
            continue;
            }

            change += isOutgoing;
            dfs(neighbour, node);
        }

        
    }
}