class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        int[] output = new int[n];
        for (int i = 0; i < k; i++) {
            output[i] = nums[n - k + i];
        }
        for (int i = 0; i < n - k; i++) {
            output[i + k] = nums[i];
        }
        for (int i = 0; i < n; i++) {
            nums[i] = output[i];
        }

    }
}