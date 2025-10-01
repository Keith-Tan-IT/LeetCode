/**
 * TIP
 * Problem context: LeetCode 57 — Insert Interval
 *
 * Summary:
 * - You are given a set of non-overlapping intervals sorted by start.
 * - Insert a new interval and merge overlaps if necessary.
 * - Output must remain sorted and non-overlapping.
 *
 * Implementation Strategy:
 * 1. Add all intervals that end before newInterval starts.
 * 2. Merge all intervals that overlap with newInterval.
 * 3. Add all intervals that start after newInterval ends.
 *
 * Implementation Variants:
 *
 * | Method                    | Steps (logic)                    | Space | Notes |
 * | ------------------------- | -------------------------------- | ----- | ----- |
 * | Three-pass `while` loops  | Before → Merge → After           | O(n)  | Easiest to reason about |
 * | Single `for` loop         | Check case-by-case while iterating| O(n) | Compact but trickier |
 * | Build new array manually  | Copy + insert + merge pass        | O(n)  | More manual indexing |
 *
 * Pitfalls:
 * - Forgetting to increment `i` in last while → infinite loop.
 * - Accidentally skipping `newInterval` if it goes at the end.
 * - Not updating `newInterval[0]`/`[1]` when merging → only partially merged.
 * - Edge cases: empty intervals, inserting at front/back, full overlap.
 *
 * Example:
 * intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 
 * Step 1: Add intervals ending before 2 → []
 * Step 2: Merge overlaps:
 *   - [1,3] with [2,5] → [1,5]
 * Step 3: Add remaining [6,9]
 * Result = [[1,5],[6,9]]
 *
 * Key points:
 * - Always handle in three phases (before, merge, after).
 * - The merge phase grows `newInterval` by taking min(start), max(end).
 * - Return `result.toArray(new int[0][])` for conversion.
 * - Time: O(n) (linear scan), Space: O(n) for output.
 */

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            if (newInterval[0] > intervals[i][1]) {
                result.add(intervals[i]);
            }
            else if (newInterval[1] >= intervals[i][0]) {
                newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
                newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            }
            else {
                result.add(newInterval);
                newInterval = intervals[i];
            }
        }
        result.add(newInterval);
        return result.toArray(new int[0][]);
    }
}