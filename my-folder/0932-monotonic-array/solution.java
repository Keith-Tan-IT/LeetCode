class Solution {
    public boolean isMonotonic(int[] nums) {
        boolean isInc = true, isDec = true;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                isInc = false;
            }
            if (nums[i] > nums[i - 1]) {
                isDec = false;
            }
        }
        return isInc || isDec; 
    }
}
