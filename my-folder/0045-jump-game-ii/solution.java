class Solution {
    public int jump(int[] nums) {
        int n = nums.length;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 10001);
        dp[n - 1] = 0;
        for (int i = n - 2; i >= 0; i--) {
            for (int jumpLen = 1; jumpLen <= nums[i]; jumpLen++) {
                dp[i] = Math.min(dp[i], 1 + dp[Math.min(n-1, i + jumpLen)]);
            }
        }
        return dp[0];
    }
}

/*
class Solution {
    public int jump(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 10001);
        return dfs(nums, 0, dp);
    }
    public int dfs (int[] nums, int position, int[] dp) {
        if (position >= nums.length - 1) {
            return 0;
        }
        if (dp[position] != 10001) {
            return dp[position];
        }
        for (int i = 1; i <= nums[position]; i++) {
            dp[position] = Math.min(dp[position], 1 + dfs(nums, position + i, dp));
        }
        return dp[position];
    }
}
*/
