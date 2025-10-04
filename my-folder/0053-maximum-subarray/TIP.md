# Tip —  * Problem context: LeetCode 53 — Maximum Subarray

/**
 * TIP
 * Problem context: LeetCode 53 — Maximum Subarray
 *
 * Summary:
 * - Classic DP problem solved with Kadane’s Algorithm in O(n).
 * - Idea: At each index, decide whether to:
 *   1. Extend the current subarray (`currSum + nums[i]`)
 *   2. Start fresh from current element (`nums[i]`)
 * - Transition:
 *   currSum = max(nums[i], currSum + nums[i])
 *   maxSum = max(maxSum, currSum)
 *
 * Pitfalls:
 * - Don’t confuse subarray with subsequence → must be contiguous.
 * - Works even if all numbers are negative → picks the least negative number.
 * - Don’t reset `currSum` to 0 (that’s a common mistake!) — instead restart from `nums[i]`.
 *
 * Example:
 * nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Kadane’s steps:
 *   currSum = max(-2, -2) = -2 → maxSum = -2
 *   currSum = max(1, -2+1) = 1 → maxSum = 1
 *   currSum = max(-3, 1-3) = -2 → maxSum = 1
 *   currSum = max(4, -2+4) = 4 → maxSum = 4
 *   currSum = max(-1, 4-1) = 3 → maxSum = 4
 *   currSum = max(2, 3+2) = 5 → maxSum = 5
 *   currSum = max(1, 5+1) = 6 → maxSum = 6
 *   currSum = max(-5, 6-5) = 1 → maxSum = 6
 *   currSum = max(4, 1+4) = 5 → maxSum = 6
 *
 * Key points:
 * - O(n) time, O(1) space.
 * - Divide & Conquer approach also works but slower (O(n log n)).
 * - Extensions:
 *   - Return subarray indices (track start/end).
 *   - 2D max subarray → apply Kadane row-wise.
 *   - Circular max subarray (LeetCode 918).
 */

