class Solution {
    public int maxProfit(int[] prices, int fee) {
        int sell = 0;
        int hold = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            hold = Math.max(hold, sell - prices[i]);
            sell = Math.max(sell, hold + prices[i] - fee);
        }
        return sell;
    }
}