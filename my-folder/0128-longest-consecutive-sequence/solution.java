/**
 * TIP
 * Problem: 128. Longest Consecutive Sequence
 *
 * Goal:
 * - Given an unsorted array, find the length of the longest consecutive sequence.
 * - Must run in O(n).
 *
 * -------------------------------------------------------------
 * Key Idea:
 * Use a HashSet for O(1) existence checks.
 *
 * Trick:
 * - Only start counting when the current number is the *start* of a sequence
 *   (i.e., num - 1 is NOT in the set).
 *
 * Why this works:
 * - Each sequence is counted once.
 * - Avoids O(n^2) repeated scanning.
 *
 * -------------------------------------------------------------
 * Algorithm:
 * 1) Insert all numbers into a HashSet.
 * 2) For each number:
 *      - If (num - 1) not in set → num is the start.
 *      - Expand forward: count num, num+1, num+2...
 * 3) Track the longest count.
 *
 * -------------------------------------------------------------
 * Example:
 * nums = [100, 4, 200, 1, 3, 2]
 *
 * Set = {1,2,3,4,100,200}
 *
 * Start sequences:
 * - 1 → 1,2,3,4 → length 4
 * - 100 → length 1
 * - 200 → length 1
 *
 * Answer = 4
 *
 * -------------------------------------------------------------
 * Complexity:
 * Time  = O(n)
 * Space = O(n) for the HashSet
 *
 * -------------------------------------------------------------
 * Takeaways:
 * - Always detect "sequence starts" using (num - 1) not in set.
 * - HashSet gives O(1) expansion.
 * - Avoid sorting (O(n log n)) to meet the required O(n).

 | Pattern                                               | Legal?          | Reason                                         |
| ----------------------------------------------------- | --------------- | ---------------------------------------------- |
| `for (int num : set)` + `set.remove()`                | ❌ Runtime Error | Modifying the same collection you're iterating |
| `for (int num : new HashSet<>(set))` + `set.remove()` | ✅ Safe          | Iterator sees a copy; original can change      |
| `for (int num : set)` + `set.contains()`              | ✅ Safe          | No mutation                                    |

 */
class Solution {
    public int longestConsecutive(int[] nums) {
        int max = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num: nums) {
            if (!map.containsKey(num)) {
                int left = map.containsKey(num - 1) ? map.get(num - 1) : 0;
                int right = map.containsKey(num + 1) ? map.get(num + 1) : 0;
                int sum = left + right + 1;
                map.put(num, sum);
                max = Math.max(max, sum);

                map.put(num - left, sum);
                map.put(num + right, sum);
            }
            else {
                continue;
            }
        }
        return max;
    }
}
