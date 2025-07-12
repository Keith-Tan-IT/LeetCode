class Solution {
    public int removeDuplicates(int[] nums) {
        int slow = 0;
        for (int i = 0; i < nums.length; i++) {
            if (slow < 2 || nums[i] > nums[slow - 2]) {
                nums[slow] = nums[i];
                slow++;
            }
        }
        return slow;
    }
}