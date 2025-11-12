/**
 * TIP
 * Problem: 1200. Minimum Absolute Difference
 *
 * Summary:
 * - Given an array of distinct integers, find all pairs with the minimum absolute difference.
 * - Output pairs in ascending order.
 *
 * ----------------------------------------------------------------
 * Approaches:
 * ① Brute Force (O(n²)): Compare all pairs, track smallest |a - b|.
 * ② Optimized (O(n log n)): Sort first, then check only adjacent pairs.
 *
 * ----------------------------------------------------------------
 * Example:
 * Input:  [4,2,1,3]
 * Sorted: [1,2,3,4]
 * Min diff = 1
 * Output: [[1,2],[2,3],[3,4]]
 *
 * ----------------------------------------------------------------
 * Complexity:
 * Time  = O(n log n)  // sorting dominates
 * Space = O(1)
 *
 * ----------------------------------------------------------------
 * Key Takeaways:
 * - In a sorted array, the minimum difference must appear between adjacent numbers.
 * - Use Arrays.sort() → then single linear scan.
 * - Always handle distinct integers → no duplicate handling needed.
 */

class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        List<List<Integer>> result = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < arr.length; i++) {
            int diff = arr[i] - arr[i - 1];
            if (diff < min) {
                min = diff;
                result.clear();
                result.add(Arrays.asList(arr[i - 1], arr[i]));
            }
            else if (diff == min) {
                result.add(Arrays.asList(arr[i - 1], arr[i]));
            }
        }
        return result;
    }
}