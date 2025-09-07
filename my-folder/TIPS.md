# 📘 LeetCode Tips Cheat Sheet
_Newest tips first_

---
# Tip — Unknown. Unknown
/**
 * TIP
 * Problem context: Graph traversal problems using adjacency matrices or lists
 *
 * Summary:
 * - `int[][] isConnected` → 2D array; use `isConnected.length` to get number of rows.
 * - `List<List<Integer>> rooms` → list of lists; use `rooms.size()` to get number of rooms.
 * - For 1D array: `int[] array` → `array.length` gives size.
 * - For a list: `List<Integer> list` → `list.size()` gives number of elements.
 *
 * Pitfalls:
 * - `.length` for arrays, `.size()` for lists — don't confuse them.
 * - Accessing out-of-bound indices will throw exceptions.
 * - Initializing 2D arrays vs list-of-lists differs:
 *   - `new int[n][m]` → rows and columns fixed
 *   - `List<List<Integer>> rooms = new ArrayList<>()` → flexible size, must init inner lists
 *
 * Example:
 * int[][] isConnected = {{1,1,0},{1,1,0},{0,0,1}};
 * System.out.println(isConnected.length); // 3
 * List<List<Integer>> rooms = new ArrayList<>();
 * rooms.add(Arrays.asList(1,0));
 * System.out.println(rooms.size()); // 1
 *
 * Key points:
 * - Arrays: fixed size, use `.length`.
 * - Lists: dynamic size, use `.size()`.
 * - Common in graph problems: adjacency matrix (`isConnected`) or adjacency list (`rooms`).
 */


---
# Tip — Understanding 1 << k (Bitwise Left Shift)
/**
 * TIP
 * Problem: Understanding 1 << k (Bitwise Left Shift)
 *
 * Summary:
 * - `<<` is the bitwise left shift operator in Java.
 * - `1 << k` shifts the number 1 left by `k` bits, which is equivalent to 2^k.
 * - This is often used in problems involving powers of 2, complete binary trees, or bitmasking.
 *
 * Example:
 * 1 << 3 = 8   // 1000 in binary
 * 1 << 4 = 16  // 10000 in binary
 *
 * Usage in LeetCode:
 * - Common in problems like "Count Complete Tree Nodes" where you calculate nodes in a full binary subtree:
 *   int nodes = 1 << leftHeight;  // computes number of nodes in a perfect binary subtree
 *
 * Pitfall:
 * - Remember operator precedence: `(1 << k)` is safe, avoid writing `1 << k + 1` without parentheses.
 * - `<<` only works with integer types; results may overflow if k is too large.
 *
 * Key points:
 * - Efficient way to compute powers of 2.
 * - Often used in combination with recursion or tree traversal to calculate counts.
 */


---
# Tip — 1071. Greatest Common Divisor of Strings
/**
 * TIP
 * Problem: 1071. Greatest Common Divisor of Strings
 *
 * Summary:
 * In Java, you must use `.equals()` to compare string values, not `==` or `!=`.
 * `==` and `!=` check reference equality (whether both strings point to the same object).
 * `.equals()` checks value equality (whether both strings contain the same characters in order).
 *
 * Example:
 * For two strings `str1` and `str2`, we say "t divides s" if and only if
 * s = t + t + ... + t (concatenated one or more times)
 *
 * Given:
 * str1 = "ABCABC", str2 = "ABC"
 * Return "ABC" because it divides both strings.
 *
 * Java implementation snippet:
 * public String gcdOfStrings(String str1, String str2) {
 *     // If concatenating in both orders doesn’t match, they have no common base
 *     if (!(str1 + str2).equals(str2 + str1)) return "";
 *     // Otherwise, find gcd of lengths and return substring
 *     int gcdLen = gcd(str1.length(), str2.length());
 *     return str1.substring(0, gcdLen);
 * }
 *
 * Pitfall:
 * Never use `==` for string value comparisons in Java; always use `.equals()`.
 */


---
# Tip — 872. Leaf-Similar Trees
/**
 * TIP
 * Problem: 872. Leaf-Similar Trees
 *
 * Summary:
 * When comparing boxed Integer values in Java, `==` is a reference comparison.
 * Values between -128 and 127 are cached (so `==` might appear to work), but
 * for general correctness use `Objects.equals(a, b)` or compare primitives:
 *   a.intValue() == b.intValue()
 *
 * Example:
 * Integer a = 200, b = 200;
 * System.out.println(a == b); // false
 * System.out.println(Objects.equals(a, b)); // true
 *
 * Pitfall:
 * If you `new Integer(200)` references differ too. Avoid relying on interning.
 */


---
# Tip — 435. Non-overlapping Intervals
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


