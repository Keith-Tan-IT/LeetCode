class Solution {
    int MOD = 1000000007;
    long[][] dp = new long[1001][2];
    public int numTilings(int n) {
        return (int)(helper(0, n, 0) % MOD);
    }
    long helper(int i, int n, int previousGap) {
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return previousGap == 0 ? 1 : 0;
        }
        if (dp[i][previousGap] != 0) {
            return dp[i][previousGap];
        }
        if (previousGap == 1) {
            dp[i][previousGap] = helper(i + 1, n, 0) + helper(i + 1, n, 1);
        }
        else {
            dp[i][previousGap] = helper(i + 1, n, 0) + helper(i + 2, n, 0) + 2 * helper(i + 2, n, 1);
        }
        return dp[i][previousGap] %= MOD;
    }
}
