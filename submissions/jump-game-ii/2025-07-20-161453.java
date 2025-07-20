class Solution {
    public int jump(int[] nums) {
        int currReach = 0, nextReach = 0, jump = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            nextReach = Math.max(nextReach, i + nums[i]);
            if (i == currReach) {
                jump++;
                currReach = nextReach;
            }
        }
        return jump;
    }
}