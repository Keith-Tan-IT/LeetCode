class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] ans = new int[nums.length];

        ans[0] = 1;

        for (int i = 1; i < nums.length; i++) {
            ans[i] = ans[i-1] * nums[i-1] ;
        }
        System.out.println(Arrays.toString(ans));

        int suffix = 1; 
        for (int j = nums.length - 1; j >= 0; j--) {
            ans[j] *= suffix;
            suffix *= nums[j];
        }

        System.out.println(Arrays.toString(ans));;
        return ans;
    }
}
