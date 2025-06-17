class Solution {
    Integer[][][] memo;
    public int maxProfit(int[] prices) {
        int n = prices.length;
        memo = new Integer[n][2][3];
        return dfs(prices, 0, 1, 2);
    }
    public int dfs(int[] prices, int index, int canBuy, int transactionLeft) {
        if (index == prices.length || transactionLeft == 0) {
            return 0;
        }
        if (memo[index][canBuy][transactionLeft] != null) {
            return memo[index][canBuy][transactionLeft];
        }
        if (canBuy == 1) {
            int buy = -prices[index] + dfs(prices, index + 1, 0, transactionLeft);
            int skip = dfs(prices, index + 1, 1, transactionLeft);
            memo[index][canBuy][transactionLeft] = Math.max(buy, skip);
        }
        else {
            int sell = prices[index] + dfs(prices, index + 1, 1, transactionLeft - 1);
            int skip = dfs(prices, index + 1, 0, transactionLeft);
            memo[index][canBuy][transactionLeft] = Math.max(sell, skip);
        }
        return memo[index][canBuy][transactionLeft];
    }
}
