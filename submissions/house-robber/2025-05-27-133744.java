class Solution {
    public int rob(int[] nums) {
        if (nums.length < 2) {
            return nums[0];
        }
        int prevPrev = nums[0];
        int prev = Math.max(nums[1], nums[0]);
        for (int i = 2; i < nums.length; i++) {
            int temp = Math.max(prevPrev + nums[i], prev);
            prevPrev = prev;
            prev = temp;
        }
        return Math.max(prevPrev, prev);
    }
}