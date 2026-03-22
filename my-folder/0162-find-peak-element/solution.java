/**
 * TIP
 * Problem: 162. Find Peak Element
 *
 * Goal:
 * - Find any peak element (strictly greater than neighbors).
 * - Must run in O(log n).
 *
 * -------------------------------------------------------------
 * Key Idea:
 * Use Binary Search with explicit boundary handling.
 *
 * -------------------------------------------------------------
 * Core Trick:
 * - Handle edge cases first (index 0 and n-1).
 * - Then safely check both neighbors for middle elements.
 *
 * Why?
 * - Avoids out-of-bounds when accessing nums[mid - 1] and nums[mid + 1].
 *
 * -------------------------------------------------------------
 * Boundary Handling:
 * - If nums[0] > nums[1] → index 0 is a peak
 * - If nums[n-1] > nums[n-2] → index n-1 is a peak
 *
 * After this:
 * - We only search in range [1 ... n-2]
 * - So mid-1 and mid+1 are ALWAYS valid
 *
 * -------------------------------------------------------------
 * Binary Search Logic:
 *
 * If nums[mid] > nums[mid - 1] AND nums[mid] > nums[mid + 1]:
 *      → Found peak → return mid
 *
 * Else if nums[mid] < nums[mid + 1]:
 *      → Increasing slope → move RIGHT
 *
 * Else if nums[mid] < nums[mid - 1]:
 *      → Decreasing slope → move LEFT
 *
 * -------------------------------------------------------------
 * Why this works:
 * - A peak must exist in the array.
 * - If slope is increasing → peak lies to the right
 * - If slope is decreasing → peak lies to the left
 *
 * -------------------------------------------------------------
 * Algorithm:
 * 1) Handle edge peaks (0 and n-1)
 * 2) Set left = 1, right = n - 2
 * 3) While left <= right:
 *      - mid = (left + right) / 2
 *      - Check if mid is peak
 *      - Else move toward increasing direction
 * 4) Return peak index
 *
 * -------------------------------------------------------------
 * Example:
 * nums = [1,2,3,1]
 *
 * Step 1:
 * mid = 1 → 2 < 3 → move right
 *
 * Step 2:
 * mid = 2 → 3 > 2 AND 3 > 1 → peak found
 *
 * Answer = index 2
 *
 * -------------------------------------------------------------
 * Complexity:
 * Time  = O(log n)
 * Space = O(1)
 *
 * -------------------------------------------------------------
 * Takeaways:
 * - Always handle boundaries when accessing mid-1 and mid+1.
 * - Binary search can be applied using "slope direction".
 * - Two valid approaches:
 *      1) Compare mid and mid+1 (cleaner, no boundary checks)
 *      2) Compare both sides with boundary guards (this approach)
 * - Both achieve O(log n), but mid vs mid+1 is more elegant.
 */
 
 class Solution {
    public int findPeakElement(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }
        if (nums[0] > nums[1]) {
            return 0;
        }
        if (nums[n - 1] > nums[n - 2]) {
            return n - 1;
        }
        int left = 1, right = n - 2;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
                return mid;
            }
            else if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            }
            else if (nums[mid] < nums[mid-1]){
                right = mid - 1;
            }
        }
        return - 1;
    }
}
