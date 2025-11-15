class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, 1);
        int curr = 1;
        for (int i = 0; i < n; i++) {
            result[i] *= curr;
            curr *= nums[i];
        }
        curr = 1;
        for (int j = n - 1; j >= 0; j--) {
            result[j] *= curr;
            curr *= nums[j];
        }
        return result;
    }
}