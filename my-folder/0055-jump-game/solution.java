class Solution {
    public boolean canJump(int[] nums) {
        int fast = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > fast) {
                return false;
            }
            fast = Math.max(fast, nums[i] + i);
        }
        return true;
    }
}
