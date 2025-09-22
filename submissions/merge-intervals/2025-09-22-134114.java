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
 */

class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a,b) -> a[0] - b[0]);
        List<int[]> result = new ArrayList<>();
        int[] prev = intervals[0];
        result.add(prev);
        for (int[] interval : intervals) {
            if (interval[0] <= prev[1]) {
                prev[1] = Math.max(interval[1], prev[1]);
            }
            else {
                result.add(interval);
                prev = interval;
            }
        }
        return result.toArray(new int [0][]);
    }
}