/**
 * TIP
 * Problem context: LeetCode 15 — 3Sum
 *
 * Summary:
 * - Find all unique triplets [a, b, c] such that a + b + c = 0.
 * - Classic variation of "2Sum" extended with an outer loop.
 * - Key: Sort the array + use two pointers.
 *
 * Implementation Strategy:
 * 1. Sort the array.
 * 2. Loop with index `i` (the first number).
 * 3. Use two pointers (`left = i+1`, `right = n-1`) to find pairs
 *    that complete the triplet.
 * 4. Skip duplicates for both `i`, `left`, and `right`.
 *
 * Implementation Variants:
 *
 * | Method                  | Complexity | Space |
 * | ----------------------- | ---------- | ----- |
 * | Sort + Two Pointers     | O(n²)      | O(1)  |
 * | Brute Force (3 loops)   | O(n³) ❌    | O(1)  |
 * | HashSet (2Sum variant)  | O(n²)      | O(n)  |
 *
 * Pitfalls:
 * - Forgetting to skip duplicates → leads to repeated triplets.
 * - Using wrong starting index: `left = i + 1` (NOT `nums[i] + 1`).
 * - Returning duplicate references instead of a fresh list.
 * - Loop should stop at `nums.length - 2` (need at least 3 numbers).
 *
 * Example:
 * nums = [-1,0,1,2,-1,-4]
 * Sorted → [-4,-1,-1,0,1,2]
 * 
 * i=0: -4 → no triplet
 * i=1: -1 → pairs = (0,2), (1,2) → [-1,-1,2], [-1,0,1]
 * Skip duplicates for i=2
 * Done.
 * Result = [[-1,-1,2], [-1,0,1]]
 *
 * Key points:
 * - Sort + two pointers is the standard O(n²) solution.
 * - Skip duplicates at 3 levels: i, left, right.
 * - Use `Arrays.asList(...)`, `List.of(...)`, or new ArrayList to build triplets.
 */

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new LinkedList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (right > left) {
                int total = nums[i] + nums[left] + nums[right];
                if (total > 0) {
                    right--;
                }
                else if (total < 0) {
                    left++;
                }
                else {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                }
            }
        }
        return result;
    }
}