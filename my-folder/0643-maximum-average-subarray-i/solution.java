class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int sum = 0;
        int max = Integer.MIN_VALUE;
        System.out.println(sum);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if(i > k-1) {
                sum -= nums[i-k];
            }
            if(i >= k-1) {
                max = Math.max(sum,max);
            }
        }
        return (double) max/k;
    }
}
