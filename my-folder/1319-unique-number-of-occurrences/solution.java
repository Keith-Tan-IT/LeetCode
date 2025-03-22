class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        HashMap <Integer, Integer> count = new HashMap <>();
        for (int i = 0; i < arr.length; i++) {
            if(!count.containsKey(arr[i]))
                count.put(arr[i], 1);
            else {
                count.put(arr[i],count.get(arr[i]) + 1);
            }
        }
        HashSet <Integer> set = new HashSet<>();
        for (int nums : count.values()) {
            set.add(nums);
        }

        return count.size() == set.size();

        
    }
}
