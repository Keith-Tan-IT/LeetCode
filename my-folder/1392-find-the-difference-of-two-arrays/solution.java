class Solution {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        HashSet <Integer> set1= new HashSet<Integer>();
        HashSet <Integer> set2 = new HashSet<Integer>();
        for (int i = 0; i < nums1.length; i++) {
            set1.add(nums1[i]);
        }
        for (int num : nums2) {
            set2.add(num);
        }

        List<List<Integer>> ans = new ArrayList <List<Integer>>();
        List<Integer> ans1 = new ArrayList<Integer>();
        List<Integer> ans2 = new ArrayList<Integer>();

        for (Integer nums : set1) {
            if(!set2.contains(nums)) {
                ans1.add(nums);
            }
        }

        for (int num : set2) {
            if (!set1.contains(num)) {
                ans2.add(num);
            }
        }

        ans.add(ans1);
        ans.add(ans2);

        return ans; 
    }
}
