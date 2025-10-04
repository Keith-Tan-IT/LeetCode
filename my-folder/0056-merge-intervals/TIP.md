# Tip —  * - For most LeetCode problems, reference is fine.

/**
Tip — Adding a Copy Instead of a Reference *
 * Summary:
 * - By default, `result.add(interval)` stores a *reference* to the same array.
 * - If you later update `interval[1]`, it also changes the value stored inside `result`.
 *
 * To prevent shared references, explicitly create a copy:
 *
 *   result.add(new int[]{interval[0], interval[1]});
 *
 * Example:
 * int[] arr = {1,2};
 * result.add(arr);          // adds reference
 * arr[1] = 99;
 * result.get(0)[1] == 99    // ❌ unintended mutation
 *
 * Using copy:
 * result.add(new int[]{arr[0], arr[1]});
 * arr[1] = 99;
 * result.get(0)[1] == 2     // ✅ independent copy
 *
 * When to use:
 * - If you want immutability (no later changes affect stored intervals).
 * - If intervals may be reused or modified outside of `result`.
 *
 * Key point:
 * - Copying adds safety but costs extra memory.
 * - For most LeetCode problems, reference is fine.
 * - Use copying in production when data integrity matters.
 */

