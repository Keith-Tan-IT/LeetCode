/**
 * TIP
 * Problem context: LeetCode 56 — Merge Intervals
 *
 * Summary:
 * - You are given an array of intervals [start, end].
 * - Merge all overlapping intervals into one.
 * - Overlap check: newInterval.start <= currentMergedEnd.
 *
 * Implementation Strategy:
 * 1. Sort intervals by start time.
 * 2. Use an output array (or list) to track merged intervals.
 * 3. For each interval:
 *    - If overlap with last merged → extend last merged interval’s end.
 *    - Else → push a new interval into output.
 *
 * Implementation Variants:
 *
 * | Method                        | Sorting   | Merging Pass | Space |
 * | ----------------------------- | --------- | ------------ | ----- |
 * | ArrayList result              | O(n log n)| O(n)         | O(n)  |
 * | In-place with 2D array buffer | O(n log n)| O(n)         | O(n)  |
 * | LinkedList (push & update)    | O(n log n)| O(n)         | O(n)  |
 *
 * Pitfalls:
 * - Do NOT compare with `intervals[i-1]`; always compare with `output[last].end`.
 * - Watch edge case: single interval, fully nested intervals, touching intervals (e.g. [1,4] and [4,5]).
 * - After merging, return only the filled portion of output (`Arrays.copyOf(result, size)`).
 *
 * Example:
 * intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Sorted → [[1,3],[2,6],[8,10],[15,18]]
 * Merge:
 *   - [1,3] + [2,6] → [1,6]
 *   - [8,10] no overlap → keep
 *   - [15,18] no overlap → keep
 * Result = [[1,6],[8,10],[15,18]]
 *
 * Key points:
 * - Sort by start first.
 * - Track mergedEnd properly with the last element of result.
 * - Time: O(n log n) (sort dominates), Space: O(n) for output.


    Tip — Why prev[1] Updates result
 *
 * Summary:
 * - Arrays in Java are *reference types*.
 * - When you `add(prev)` into `result`, you add a reference to the same array object.
 * - Any future modification (`prev[1] = …`) updates the same object, so changes are visible in `result`.
 *
 * Example:
 * int[] arr = {1,2};
 * result.add(arr);
 * arr[1] = 99; → result.get(0)[1] == 99
 *
 * If you want an independent copy:
 *   result.add(new int[]{interval[0], interval[1]});
 *
 * Key point:
 * - Reference semantics save memory and time.
 * - Use explicit copying only if you need immutability or independence.


    Tip — toArray(new int[size][]) vs toArray(new int[0][])
/**
 * Problem context: Converting List<int[]> → int[][]
 *
 * Summary:
 * - Java’s `List.toArray(T[] a)` behavior:
 *   - If `a` is large enough → fills it in and returns the same array.
 *   - If `a` is too small → allocates a new array internally.
 *
 * Variants:
 * - result.toArray(new int[result.size()][]):
 *     • Pre-allocates exact size.
 *     • Slightly more efficient.
 * - result.toArray(new int[0][]):
 *     • Idiomatic shorthand.
 *     • Java allocates the right-sized array automatically.
 *
 * Both are correct. Difference is negligible in coding interviews.
 *
 * Key point:
 * - Prefer `new int[0][]` for readability.
 * - Use `new int[result.size()][]` in performance-critical code.



    Tip — prev vs newInterval Style
 * Summary:
 * - Two common coding styles:
 *   1. Use `prev`:
 *        int[] prev = intervals[0];
 *        result.add(prev);
 *        // merge into prev directly
 *
 *   2. Use `newInterval`:
 *        int[] newInterval = intervals[0];
 *        result.add(newInterval);
 *        // reassign when disjoint interval starts
 *
 * Why both work:
 * - Arrays are reference types.
 * - Updating `prev[1]` or `newInterval[1]` directly updates the array inside `result`.
 *
 * Key point:
 * - Style choice only — both produce the same merged output.
 * - Consistency is more important than style here.


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


class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a,b) -> a[0] - b[0]);
        List<int[]> result = new ArrayList<>();
        result.add(intervals[0]);
        for (int[] interval : intervals) {
            if (interval[0] <= result.get(result.size() - 1)[1]) {
                result.get(result.size() - 1)[1] = Math.max(interval[1], result.get(result.size() - 1)[1]);
            }
            else {
                result.add(interval);
            }
        }
        return result.toArray(new int [0][]);
    }
}