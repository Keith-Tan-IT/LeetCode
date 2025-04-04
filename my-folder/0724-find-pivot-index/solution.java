class Solution {
    public int pivotIndex(int[] nums) {
        int sum = 0;
        int sumleft = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum - nums[0] == 0) {
            return 0;
        }
        sum -= nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum -= nums[i];
            sumleft += nums[i-1];
            System.out.println(sum-sumleft);
            if (sum == sumleft) {
                return i;
            }
        }
        return -1;
    }
}
