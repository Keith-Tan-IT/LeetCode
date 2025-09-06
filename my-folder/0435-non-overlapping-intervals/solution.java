/**
 * TIP
 * Problem: 435. Non-overlapping Intervals
 *
 * Summary:
 * To find the minimum number of intervals to remove so that the rest are non-overlapping:
 * - Sort intervals by their **end time** (ascending).
 * - Use a greedy approach: always keep the interval with the earliest end time.
 * - Compare the start of the current interval with the end of the last kept interval.
 * - If overlapping, skip/remove; otherwise, update the end tracker.
 *
 * Example:
 * intervals = [[1,2],[2,3],[3,4],[1,3]]
 * After sorting by end time: [[1,2],[1,3],[2,3],[3,4]]
 * Greedy selection keeps: [1,2],[2,3],[3,4]
 * Remove interval [1,3] → answer = 1
 *
 * Pitfall:
 * - Sorting incorrectly by start time instead of end time can lead to wrong greedy results.
 * - Make sure to use `Integer.compare(a[1], b[1])` or `(a, b) -> a[1] - b[1]` in Java.
 *
 * Key points:
 * - Greedy works because selecting earliest ending interval leaves more space for next intervals.
 * - Time complexity: O(n log n) due to sort.
 * - Space complexity: O(1) extra (if in-place sort used).
 */
 class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a,b) -> Integer.compare(a[1], b[1]));
        int prevEnd = intervals[0][1];
        int count = 1;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= prevEnd) {
                count++;
                prevEnd = intervals[i][1];
            }
        }
        return intervals.length - count;
    }
}
