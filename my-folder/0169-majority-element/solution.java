class Solution {
    public int majorityElement(int[] nums) {
        int counter = 0, candidate = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if(counter == 0) {
                candidate = nums[i];
            }
            if (nums[i] == candidate) {
                counter++;
            }
            else {
                counter--;
            }
        }
        return candidate;
    }
}
