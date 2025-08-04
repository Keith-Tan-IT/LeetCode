/* memoization to avoid StackOverflow
class Solution {
    public boolean canJump(int[] nums) {
        Boolean[] memo = new Boolean[nums.length];
        return dfs(nums, 0, memo);
    }
    public boolean dfs (int[] nums, int furthest, Boolean[] memo) {
        if (memo[furthest] != null) {
            return memo[furthest];
        }
        if (furthest >= nums.length - 1) {
            return true;
        }
        for (int i = 1; i <= nums[furthest]; i++) {
            if (dfs(nums, furthest + i, memo) == true) {
                memo[furthest] = true;
                return memo[furthest];
            }
        }
        memo[furthest] = false;
        return memo[furthest];
    }
}
*/

class Solution {
    public boolean canJump (int[] nums) {
        int reach = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > reach) {
                return false;
            }
            reach = Math.max(reach, i + nums[i]);
        }
        return true;
    }
}
