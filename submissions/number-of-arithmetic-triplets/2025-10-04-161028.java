class Solution {
    public int arithmeticTriplets(int[] nums, int diff) {
        Map <Integer, Integer> hashMap = new HashMap<>();
        int count = 0, i = 0;
        for (int num : nums) {
            hashMap.put(i++, num);
        }
        for (int num : nums) {
            if (hashMap.containsValue(num + diff) && hashMap.containsValue(num + 2 * diff)) {
                count++;
            }
        }
        return count;
    }
}