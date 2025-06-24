class Solution {
    public int longestOnes(int[] nums, int k) {
        int max = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                count++;
            }
            if (nums[i] == 0) {
                if (k > 0) {
                    k--;
                    count++;
                }
                else {
                    while (k == 0) {
                        if (nums[i - count] == 1) {
                            count--;
                        }
                        if (nums[i - count] == 0) {
                            break;
                        }
                    }
                }
            }
            max = Math.max(max, count);
        }
        return max;
    }
}
