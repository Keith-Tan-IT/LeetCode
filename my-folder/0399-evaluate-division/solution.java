class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> graph = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            graph.computeIfAbsent(equations.get(i).get(0), k -> new HashMap<>()).put(equations.get(i).get(1), values[i]);
            graph.computeIfAbsent(equations.get(i).get(1), k -> new HashMap<>()).put(equations.get(i).get(0), 1.0 / values[i]);
        }
        
        double[] output = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String curr = queries.get(i).get(0);
            String target = queries.get(i).get(1);
            Set<String> visited = new HashSet<>();
            output[i] = dfs(graph, curr, target, 1.0, visited);
        }
        return output;
    }
    public double dfs (Map<String, Map<String, Double>> graph, String curr, String target, double product, Set<String> visited) {
        if (!graph.containsKey(curr) || !graph.containsKey(target)) {
            return -1.0;
        }
        if (curr.equals(target)) {
            return product;
        }
        visited.add(curr);
        for (Map.Entry<String, Double> neighbor : graph.get(curr).entrySet()) {
            if (!visited.contains(neighbor.getKey())) {
                double result = dfs(graph, neighbor.getKey(), target, product * neighbor.getValue(), visited);
                if (result != -1.0) return result;
            }
        }
        return -1.0;
    }
}
