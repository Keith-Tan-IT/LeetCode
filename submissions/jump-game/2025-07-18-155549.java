class Solution {
    public boolean canJump(int[] nums) {
        int fast = 0;
        if (nums.length == 1) return true;
        for (int i = 0; i <= fast; i++) {
            fast = Math.max(fast, i + nums[i]);
            if (fast >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }
}