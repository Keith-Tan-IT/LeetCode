class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backTracking(result, new ArrayList<Integer>(), target, 0, candidates);
        return result;
    }

    public void backTracking(List<List<Integer>> result, List<Integer> combination, int remainTarget, int startIndex, int[] candidates) {
        if(remainTarget == 0) {
            List<Integer> list = new ArrayList <> (combination);
            result.add(list);
        }
        for (int i = startIndex; i < candidates.length && remainTarget > 1; i++) {
            combination.add(candidates[i]);
            backTracking(result, combination, remainTarget - candidates[i], i, candidates);
            combination.remove(combination.size() - 1);
        }
    }
}