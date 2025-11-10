class Solution {
    public int minimumCost(int[] cost) {
        int total = 0, result = 0;
        Arrays.sort(cost);
        for (int i = 0; i < cost.length; i++) {
            if (i % 3 != cost.length % 3) {
                result += cost[i];
            }
        }
        return result;
    }
}
