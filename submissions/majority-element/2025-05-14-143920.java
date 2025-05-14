class Solution {
    public int majorityElement(int[] nums) {
        HashMap <Integer, Integer> counter = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            counter.put(nums[i], counter.getOrDefault(nums[i], 0) + 1);
            if (counter.get(nums[i]) > nums.length / 2) {
                return nums[i];
            }
        }
        return -1;
    }
}