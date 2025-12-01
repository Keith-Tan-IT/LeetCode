/**
 * TIP
 * Problem: 128. Longest Consecutive Sequence
 *
 * Goal:
 * - Given an unsorted array, find the length of the longest consecutive sequence.
 * - Must run in O(n).
 *
 * -------------------------------------------------------------
 * Key Idea:
 * Use a HashSet for O(1) existence checks.
 *
 * Trick:
 * - Only start counting when the current number is the *start* of a sequence
 *   (i.e., num - 1 is NOT in the set).
 *
 * Why this works:
 * - Each sequence is counted once.
 * - Avoids O(n^2) repeated scanning.
 *
 * -------------------------------------------------------------
 * Algorithm:
 * 1) Insert all numbers into a HashSet.
 * 2) For each number:
 *      - If (num - 1) not in set → num is the start.
 *      - Expand forward: count num, num+1, num+2...
 * 3) Track the longest count.
 *
 * -------------------------------------------------------------
 * Example:
 * nums = [100, 4, 200, 1, 3, 2]
 *
 * Set = {1,2,3,4,100,200}
 *
 * Start sequences:
 * - 1 → 1,2,3,4 → length 4
 * - 100 → length 1
 * - 200 → length 1
 *
 * Answer = 4
 *
 * -------------------------------------------------------------
 * Complexity:
 * Time  = O(n)
 * Space = O(n) for the HashSet
 *
 * -------------------------------------------------------------
 * Takeaways:
 * - Always detect "sequence starts" using (num - 1) not in set.
 * - HashSet gives O(1) expansion.
 * - Avoid sorting (O(n log n)) to meet the required O(n).
 */
class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        for (int num : set) {
            if (!set.contains(num - 1)) {
                int length = 1;
                int current = num;
                while (set.contains(current + 1)) {
                    length++;
                    current++;
                }
                max = Math.max(max, length);
            }
        }
        return max;
    }
}