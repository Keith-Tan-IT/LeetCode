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
 * Use Binary Search based on slope direction.
 *
 * -------------------------------------------------------------
 * Core Trick:
 * - Compare nums[mid] with nums[mid + 1]
 *
 * If nums[mid] < nums[mid + 1]:
 *      → Peak is on the RIGHT
 * If nums[mid] > nums[mid + 1]:
 *      → Peak is on the LEFT (including mid)
 *
 * -------------------------------------------------------------
 * Why this works:
 * - The array always has at least one peak.
 * - Moving in the direction of increase guarantees a peak.
 *
 * -------------------------------------------------------------
 * Algorithm:
 * 1) Initialize left = 0, right = n - 1
 * 2) While left < right:
 *      - mid = (left + right) / 2
 *      - If nums[mid] < nums[mid + 1]:
 *            left = mid + 1
 *      - Else:
 *            right = mid
 * 3) Return left
 *
 * -------------------------------------------------------------
 * Example:
 * nums = [1,2,3,1]
 *
 * mid=1 → 2 < 3 → move right
 * mid=2 → 3 > 1 → move left
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
 * - Binary search is not just for sorted arrays.
 * - Use "slope direction" to guide search.
 * - Always prefer comparing mid and mid+1 to avoid boundary issues.
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