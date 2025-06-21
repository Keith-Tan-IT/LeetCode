class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n + 1][2][3];
        for (int day = n - 1; day >= 0; day--) {
            for (int canBuy = 0; canBuy <= 1; canBuy++) {
                for (int transactionLeft = 1; transactionLeft <= 2; transactionLeft++) {
                    if (canBuy == 1) {
                        dp[day][canBuy][transactionLeft] = Math.max(
                            -prices[day] + dp[day + 1][0][transactionLeft], //buy
                            dp[day + 1][1][transactionLeft] //skip
                        );
                    }
                    else {
                        dp[day][canBuy][transactionLeft] = Math.max(
                            prices[day] + dp[day + 1][1][transactionLeft - 1], 
                            dp[day + 1][0][transactionLeft]
                        );
                    }
                }
            }
        }
    return dp[0][1][2];
    }
}