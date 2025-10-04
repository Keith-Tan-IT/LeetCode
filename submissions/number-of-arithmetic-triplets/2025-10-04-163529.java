class Solution {
    public int arithmeticTriplets(int[] nums, int diff) {
        Map <Integer, Integer> hashMap = new HashMap<>();
        int count = 0, i = 0;
        for (int num : nums) {
            hashMap.put(num, i++);
        }
        for (int num : nums) {
            if (hashMap.containsKey(num + diff) && hashMap.containsKey(num + 2 * diff)) {
                count++;
            }
        }
        return count;
    }
}