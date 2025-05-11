class Solution {
    public int findPeakElement(int[] nums) {
        int currentIndex = nums.length / 2;
        if (nums.length == 1) {
            return 0;
        }
        while (true) {
            // Handle boundaries
            if (currentIndex == 0) {
                if (nums[0] > nums[1]) return 0;
            } else if (currentIndex == nums.length - 1) {
                if (nums[currentIndex] > nums[currentIndex - 1]) return currentIndex;
                currentIndex--;
            }
            // Middle cases
            else if (nums[currentIndex] > nums[currentIndex - 1] && nums[currentIndex] > nums[currentIndex + 1]) {
                return currentIndex;
            } else if (nums[currentIndex] < nums[currentIndex - 1]) {
                currentIndex--;
            } else {
                currentIndex++;
            }
        }
        
    }
}
