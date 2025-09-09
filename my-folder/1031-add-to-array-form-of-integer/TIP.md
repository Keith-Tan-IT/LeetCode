# Tip — Unknown. Unknown
/**
 * TIP
 * Problem context: LeetCode 989 — Add to Array-Form of Integer
 *
 * Summary:
 * - You need to add an integer `k` to an array-form number `num[]`.
 * - Iterate from the end (least significant digit) and propagate the carry (`k`).
 * - Insert each new digit into the result from the front, or build in reverse and flip later.
 *
 * Implementation Variants:
 *
 * | Method                         | Insert at Front          | Build `n`-size Result | Space |
 * | ------------------------------ | ------------------------ | --------------------- | ----- |
 * | `ArrayList.add(0, …)`          | O(n) each                | O(n²) total ❌         | O(n)  |
 * | `LinkedList.addFirst(…)`       | O(1) each                | O(n) total ✅          | O(n)  |
 * | `ArrayList.add(…) + reverse()` | O(1) each + O(n) reverse | O(n) total ✅          | O(n)  |
 * | `LinkedList.add(0, …)`         | O(1) each                | O(n) total ✅          | O(n)  |
 *
 * Pitfalls:
 * - `ArrayList.add(0, …)` looks simple but is O(n²) because shifting elements costs O(n).
 * - For competitive coding, prefer:
 *   - `LinkedList.addFirst()` (direct front insertion), or
 *   - Append with `ArrayList.add()` then `Collections.reverse()` once.
 * - Make sure to continue processing while `k > 0` after finishing array traversal.
 *
 * Example:
 * num = [2,7,4], k = 181
 *  4 + 181 = 185 → digit=5, carry=18
 *  7 + 18 = 25 → digit=5, carry=2
 *  2 + 2 = 4 → digit=4, carry=0
 *  Result = [4,5,5]
 *
 * Key points:
 * - Always handle leftover carry after loop.
 * - Use `LinkedList.addFirst()` for efficient front insertions.
 * - For `ArrayList`, append and reverse at the end.
 */
