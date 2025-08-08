class Solution {
    public void moveZeroes(int[] nums) {
        int counter = 0, current = 0;
        while (current < nums.length) {
            if (nums[current] == 0) {
                counter += 1;
            } else {
                nums[current - counter] = nums[current];
            } 
            current++;
        }
        int zero_num = nums.length - counter;
        for (int j = zero_num; j < nums.length; j++) {
            nums[j] = 0;
        }
    }
}