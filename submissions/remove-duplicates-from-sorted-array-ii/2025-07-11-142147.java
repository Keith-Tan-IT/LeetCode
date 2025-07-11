class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int k = 1, counter = 1;
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] == nums[i - 1] && counter < 2) {
                counter ++;
                nums[k] = nums[i];
                k++;
            }
            else if (nums[i] == nums[i - 1] && counter >= 2){
                continue;
            }
            else {
                counter = 1;
                nums[k] = nums[i];
                k++; 
            }
        }
        return k;
    }
}