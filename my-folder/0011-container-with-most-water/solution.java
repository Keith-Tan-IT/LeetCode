class Solution {
    public int maxArea(int[] height) {
        int n = height.length;
        int left = 0;
        int maxArea = 0;
        int right = n-1;
        while (right > left) {
            if (height[right] > height[left]) {
                maxArea = Math.max((height[left] * (right - left)), maxArea);
                left++;
            }
            else {
                maxArea = Math.max((height[right] * (right - left)), maxArea);
                right--;
            }
        }
        return maxArea;
    }
}
