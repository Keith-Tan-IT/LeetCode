class Solution {
    int[] memo;
    public int rob(int[] nums) {
        memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return maxRob(nums, nums.length - 1);
    }
    public int maxRob(int[] nums, int i) {
        if (i < 0) {
            return 0;
        }
        if (memo[i] > -1) {
            return memo[i];
        }
        memo[i] = Math.max(maxRob(nums, i - 2) + nums[i], maxRob(nums,i - 1));
        return memo[i];
    }
}