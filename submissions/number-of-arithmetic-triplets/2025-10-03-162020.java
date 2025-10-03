class Solution {
    public int arithmeticTriplets(int[] nums, int diff) {
        int counter = 0;
        for (int i = 0; i < nums.length; i++) {
            if (contains(nums, nums[i] + diff) && contains(nums, nums[i] + 2 * diff)) {
                counter++;
            }
        }
        return counter;
    }

    public boolean contains(int[] nums, int target) {
        for (int num : nums) {
            if (num == target) {
                return true;
            }
        }
        return false;
    }
}