/**
 * TIP
 * Problem context: LeetCode 15 — 3Sum
 *
 * Summary:
 * - Find all unique triplets [a, b, c] such that a + b + c = 0.
 * - Extension of the "2Sum" problem with an outer loop.
 * - Key approach: Sort the array and use the two-pointer method.
 *
 * Implementation Strategy:
 * 1. Sort the array.
 * 2. Loop with index `i` as the first element.
 * 3. Use two pointers (`left = i+1`, `right = n-1`) to find pairs
 *    that sum with nums[i] to 0.
 * 4. Skip duplicates at all levels (`i`, `left`, `right`).
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
 * - Forgetting to skip duplicates → repeated triplets in result.
 * - Wrong left pointer setup → must be `i+1` (not nums[i]+1).
 * - Returning duplicate references → always create fresh triplets.
 * - Outer loop must go only until `nums.length - 2`.
 *
 * Example:
 * nums = [-1,0,1,2,-1,-4]
 * Sorted → [-4, -1, -1, 0, 1, 2]
 *
 * i=0 → -4 → no triplet
 * i=1 → -1 → found [-1,-1,2], [-1,0,1]
 * i=2 → -1 (duplicate) → skip
 * Done.
 * Result = [[-1,-1,2], [-1,0,1]]
 *
 * Key points:
 * - Standard solution is sort + two pointers (O(n²)).
 * - Must skip duplicates at 3 places: i, left, right.
 * - Build triplets with `Arrays.asList()`, `List.of()`, or manual new ArrayList.
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
                    right--;
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                }
            }
        }
        return result;
    }
}
