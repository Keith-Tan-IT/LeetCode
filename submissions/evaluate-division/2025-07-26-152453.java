class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> graph = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            String u = equations.get(i).get(0);
            String v = equations.get(i).get(1);
            graph.putIfAbsent(u, new HashMap<>());
            graph.putIfAbsent(v, new HashMap<>());
            graph.get(u).put(v, values[i]);
            graph.get(v).put(u, 1.0 / values[i]);
        }
        double[] result = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            if (!graph.containsKey(queries.get(i).get(0)) || !graph.containsKey(queries.get(i).get(1))) {
                result[i] = -1.0;
            }
            else {
                result[i] = helper(new HashSet<>(), graph, queries.get(i).get(0), queries.get(i).get(1));
            }
        }
        return result;

    }
    public double helper (Set<String> visited, Map<String, Map<String, Double>> graph, String start, String end) {
        if (graph.get(start).containsKey(end)) {
            return graph.get(start).get(end);
        }
        else {
            visited.add(start);
            for (Map.Entry<String, Double> neighbour : graph.get(start).entrySet()) {
                String u = neighbour.getKey();
                if(!visited.contains(u)) {
                    double result = helper(visited, graph, u, end);
                    if (result != -1.0) {
                        return result * neighbour.getValue();
                    }
                }
            }
            return -1.0;
        }
    }
}