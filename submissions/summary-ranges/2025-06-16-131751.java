class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> summary = new ArrayList<>();
        if (nums.length == 0) {
            return summary;
        }
        if (nums.length == 1) {
            summary.add(Integer.toString(nums[0]));
        }
        int start = nums[0];
        for(int i = 1; i < nums.length; i++) {
            if (nums[i] - 1 != nums[i - 1]) {
                if (i == 1) {
                    summary.add(Integer.toString(start));
                }
                start = nums[i];
                if (i == nums.length - 1 || nums[i] + 1 != nums[i + 1]) {
                    summary.add(Integer.toString(start));
                }
            }
            else {
                if (i == nums.length - 1 || nums[i] + 1 != nums[i + 1]) {
                    summary.add(start + "->" + nums[i]);
                }
            }
        }
        return summary;
    }
}