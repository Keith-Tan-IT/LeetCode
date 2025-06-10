class Solution {
    public int maxProfit(int[] prices) {
        int max = 0;
        if (prices.length == 1) {
            return 0;
        }
        int cheapest = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            cheapest = Math.min(prices[i], cheapest);
            if (i > 0 && prices[i] > cheapest) {
                max = Math.max(max, prices[i] - cheapest);
            }
        }
        return max;
    }
}
