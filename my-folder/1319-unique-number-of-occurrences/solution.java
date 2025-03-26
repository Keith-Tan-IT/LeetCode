class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        HashMap <Integer, Integer> count = new HashMap <>();
        for (int i = 0; i < arr.length; i++) {
            count.put(arr[i], count.getOrDefault(arr[i], 0)+ 1);
        }
        HashSet <Integer> set = new HashSet<>();
        for (int nums : count.values()) {
            set.add(nums);
        }

        return count.size() == set.size();
        
    }
}
