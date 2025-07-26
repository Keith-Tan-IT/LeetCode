class Solution {
    public int jump(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 10001);
        return dfs(nums, 0, dp);
    }
    public int dfs (int[] nums, int position, int[] dp) {
        if (position > nums.length - 2) {
            return 0;
        }
        if (dp[position] != 10001) {
            return dp[position];
        }
        int minJump = Integer.MAX_VALUE;;
        for (int i = 1; i <= nums[position]; i++) {
            dp[position] = Math.min(dp[position], 1 + dfs(nums, position + i, dp));
        }
        return dp[position];
    }
}
