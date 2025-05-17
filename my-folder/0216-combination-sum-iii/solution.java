class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        backTracking(k, n, result, new ArrayList<>(), 1);
        return result;
    }
    public void backTracking(int k, int n, List<List<Integer>> result, List<Integer> combination, int start) {
        if (combination.size() > k) {
            return;
        }
        if (combination.size() == k && n == 0) {
            List<Integer> newList = new ArrayList<>(combination); 
            result.add(newList);
        }
        for (int i = start; i <= 9 && n >= 0; i++) {
            combination.add(i);
            backTracking(k, n - i, result, combination, i + 1);
            combination.remove(combination.size() - 1);
        }
    }
}
