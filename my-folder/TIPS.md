# 📘 LeetCode Tips Cheat Sheet
_Newest tips first_


---
# Tip — 1592. Rearrange Spaces Between Words
# Tip — 1592. Rearrange Spaces Between Words

/**
 * TIP
 * Problem: 1592. Rearrange Spaces Between Words
 *
 * Summary:
 * - Redistribute all spaces evenly between words.
 * - Append leftover spaces at the end.
 * - Maintain total space count and word order.
 *
 * ----------------------------------------------------------------
 * Steps:
 * 1️⃣ Count total spaces & extract words manually.
 * 2️⃣ Handle one-word edge case (all spaces go to end).
 * 3️⃣ Compute:
 *      - spacesBetween = totalSpaces / (numWords - 1)
 *      - spacesLeft    = totalSpaces % (numWords - 1)
 * 4️⃣ Build result using helper `createSpace()`.
 *
 * ----------------------------------------------------------------
 * Edge Cases:
 * - Single word → all spaces at the end.
 * - Handles leading/trailing/multiple spaces gracefully.
 *
 * ----------------------------------------------------------------
 * Example:
 * Input:  "  this   is  a sentence "
 * Output: "this   is   a   sentence"
 *
 * ----------------------------------------------------------------
 * Complexity:
 * Time  = O(n)
 * Space = O(n)
 */



---
# Tip — 1071. Greatest Common Divisor of Strings
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
# Tip —  * Problem context: LeetCode 989 — Add to Array-Form of Integer
# Tip —  * Problem context: LeetCode 989 — Add to Array-Form of Integer

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



---
# Tip — 872. Leaf-Similar Trees
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
# Tip —  * Problem context: Graph traversal problems using adjacency matrices or lists
# Tip —  * Problem context: Graph traversal problems using adjacency matrices or lists

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
# Tip — 435. Non-overlapping Intervals
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



---
# Tip — Understanding 1 << k (Bitwise Left Shift)
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
# Tip —  * Problem context: LeetCode 200 — Number of Islands
# Tip —  * Problem context: LeetCode 200 — Number of Islands

/**
 * TIP
 * Problem context: LeetCode 200 — Number of Islands
 *
 * Summary:
 * - You are given a 2D grid of '1's (land) and '0's (water).
 * - An island is a group of connected '1's (horizontal or vertical).
 * - Count the number of distinct islands.
 *
 * Implementation Strategy:
 * 1. Traverse each cell in the grid.
 * 2. When a '1' is found:
 *    - Increment island count.
 *    - Perform BFS or DFS to mark all connected '1's as visited (set to '0').
 * 3. Use a queue (BFS) or recursion/stack (DFS) to explore neighbors.
 *
 * BFS Implementation Notes:
 * - Use a queue to store coordinates.
 * - Explore in 4 directions: up, right, down, left.
 * - Mark visited land cells as '0' immediately after enqueuing to avoid re-visiting.
 *
 * Implementation Variants:
 *
 * | Method     | Traversal | Space | Notes                          |
 * |------------|-----------|-------|--------------------------------|
 * | BFS (queue)| O(m*n)    | O(min(m,n)) | Avoids recursion stack overflow |
 * | DFS (rec)  | O(m*n)    | O(m*n) | Simpler, but can overflow stack |
 * | Union-Find | O(m*n*α(n)) | O(m*n) | Advanced; useful for large grids |
 *
 * Pitfalls:
 * - Do NOT use fixed queue size or early termination (`size`); BFS runs until the queue is empty.
 * - Always mark a cell as visited (`grid[i][j] = '0'`) before enqueuing or recursing.
 * - Handle edge cases: empty grid, single cell, all land or all water.
 *
 * Example:
 * grid = {
 *   {'1','1','0','0','0'},
 *   {'1','1','0','0','0'},
 *   {'0','0','1','0','0'},
 *   {'0','0','0','1','1'}
 * }
 * 
 * Steps:
 * - First island → (0,0) triggers BFS, marks connected (0,1), (1,0), (1,1)
 * - Second island → (2,2)
 * - Third island → (3,3) and (3,4)
 * Result: 3 islands
 *
 * Key points:
 * - Use BFS/DFS to traverse each island once.
 * - Mark cells as visited to avoid cycles.
 * - Time: O(m * n) — each cell visited once.
 * - Space: O(min(m, n)) — for BFS queue (or recursion depth).
 */



---
# Tip —  * Problem context: LeetCode 91 — Decode Ways
# Tip —  * Problem context: LeetCode 91 — Decode Ways

/**
 * TIP
 * Problem context: LeetCode 91 — Decode Ways
 *
 * Summary:
 * - Each digit maps to a letter: '1' → 'A', ..., '26' → 'Z'.
 * - Count the number of ways to decode a numeric string.
 * - Dynamic Programming (DP) problem with sequential dependency.
 *
 * Implementation Strategy:
 * 1. Think in terms of DP: dp[i] = number of ways to decode substring s[i..].
 * 2. Base cases:
 *    - dp[n] = 1 (empty string is valid).
 *    - dp[i] = 0 if s[i] == '0'.
 * 3. Transition:
 *    - Single digit: if s[i] != '0' → add dp[i+1].
 *    - Double digit: if s[i..i+1] ∈ [10..26] → add dp[i+2].
 * 4. Optimize space:
 *    - Only need dp[i+1] and dp[i+2] → use two variables (prev, curr).
 *
 * Implementation Variants:
 *
 * | Method                | Complexity | Space |
 * | --------------------- | ---------- | ----- |
 * | DP Array              | O(n)       | O(n)  |
 * | Two Variables (prev,curr) ✅ | O(n)       | O(1)  |
 * | Recursion + Memo      | O(n)       | O(n)  |
 *
 * Pitfalls:
 * - Using `else if` instead of two separate checks → misses valid double-decoding cases.
 * - Forgetting that '0' is invalid unless part of '10' or '20'.
 * - Wrong shifting order of variables (prev vs curr).
 * - Returning `curr` instead of `prev` at the end./**
 * TIP
 * Problem context: LeetCode 91 — Decode Ways
 *
 * Summary:
 * - Each digit maps to a letter: '1' → 'A', ..., '26' → 'Z'.
 * - Count the number of ways to decode a numeric string.
 * - Dynamic Programming (DP) problem with sequential dependency.
 *
 * Implementation Strategy:
 * 1. Think DP: dp[i] = number of ways to decode substring s[i..].
 * 2. Base cases:
 *    - dp[n] = 1 (empty string).
 *    - dp[i] = 0 if s[i] == '0'.
 * 3. Transition:
 *    - Single digit valid? add dp[i+1].
 *    - Double digit valid (10–26)? add dp[i+2].
 * 4. Optimize space with 2 variables:
 *    - waysNext = dp[i+1]
 *    - waysNextNext = dp[i+2]
 *    - waysCurrent = dp[i]
 *
 * Implementation Variants:
 *
 * | Method                | Complexity | Space |
 * | --------------------- | ---------- | ----- |
 * | DP Array              | O(n)       | O(n)  |
 * | Two Variables (✅)    | O(n)       | O(1)  |
 * | Recursion + Memo      | O(n)       | O(n)  |
 *
 * Pitfalls:
 * - Using `else if` instead of two checks (misses valid double-decoding).
 * - Forgetting that '0' is invalid alone (must be part of 10 or 20).
 * - Wrong variable shifting order → overwrites values incorrectly.
 * - Returning wrong variable at the end (should return waysNext).
 *
 * Example:
 * s = "226"
 * i=2: '6' → ways=1
 * i=1: '2' → single=1, double="26"=1 → total=2
 * i=0: '2' → single=2, double="22"=1 → total=3
 * Result = 3 ("BZ", "VF", "BBF")
 *
 * Key points:
 * - Use meaningful names: 
 *   waysNext = dp[i+1], waysNextNext = dp[i+2], waysCurrent = dp[i].
 * - Always check both single and double digits.
 * - Standard O(n) solution with O(1) space.
 */



---
# Tip —  * Problem context: LeetCode 57 — Insert Interval
# Tip —  * Problem context: LeetCode 57 — Insert Interval

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



---
# Tip —  * - For most LeetCode problems, reference is fine.
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



---
# Tip —  * Problem context: LeetCode 54 — Spiral Matrix
# Tip —  * Problem context: LeetCode 54 — Spiral Matrix

/**
 * TIP
 * Problem context: LeetCode 54 — Spiral Matrix
 *
 * Summary:
 * - Traverse an m×n matrix in clockwise spiral order.
 * - Example:
 *   Input: [[1,2,3],[4,5,6],[7,8,9]]
 *   Output: [1,2,3,6,9,8,7,4,5]
 *
 * ----------------------------------------------------------------
 * Implementation Strategy:
 * 
 * Maintain 4 boundaries:
 *   top = 0, bottom = m-1
 *   left = 0, right = n-1
 *
 * Repeat while (top <= bottom && left <= right):
 *   1️⃣ Traverse Left → Right (top row), then top++
 *   2️⃣ Traverse Top → Bottom (right column), then right--
 *   3️⃣ Traverse Right → Left (bottom row, if top ≤ bottom), then bottom--
 *   4️⃣ Traverse Bottom → Top (left column, if left ≤ right), then left++
 *
 * ----------------------------------------------------------------
 * Complexity:
 * | Operation | Time | Space |
 * |------------|------|-------|
 * | Spiral traversal | O(m*n) | O(1) |
 *
 * ----------------------------------------------------------------
 * Pitfalls:
 * - ❌ Forgetting to check bounds (top ≤ bottom, left ≤ right).
 * - ❌ Increment/decrement boundaries at wrong place → duplicates.
 * - ⚠️ Edge case: single row or single column.
 *
 * ----------------------------------------------------------------
 * Example Walkthrough:
 * matrix = [
 *   [1, 2, 3],
 *   [4, 5, 6],
 *   [7, 8, 9]
 * ]
 * → Traverse 1→2→3→6→9→8→7→4→5
 *
 * ----------------------------------------------------------------
 * Clean Implementation:
 *
 * class Solution {
 *     public List<Integer> spiralOrder(int[][] matrix) {
 *         List<Integer> result = new ArrayList<>();
 *         if (matrix == null || matrix.length == 0) return result;
 *         int top = 0, bottom = matrix.length - 1;
 *         int left = 0, right = matrix[0].length - 1;
 *         while (top <= bottom && left <= right) {
 *             for (int j = left; j <= right; j++) result.add(matrix[top][j]);
 *             top++;
 *             for (int i = top; i <= bottom; i++) result.add(matrix[i][right]);
 *             right--;
 *             if (top <= bottom)
 *                 for (int j = right; j >= left; j--) result.add(matrix[bottom][j]);
 *             bottom--;
 *             if (left <= right)
 *                 for (int i = bottom; i >= top; i--) result.add(matrix[i][left]);
 *             left++;
 *         }
 *         return result;
 *     }
 * }
 */



---
# Tip —  * Problem context: LeetCode 53 — Maximum Subarray
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



---
# Tip —  * Problem context: LeetCode 49 — Group Anagrams
# Tip —  * Problem context: LeetCode 49 — Group Anagrams

/**
 * TIP
 * Problem context: LeetCode 49 — Group Anagrams
 *
 * Summary:
 * - Group words that are anagrams (same letters in different order).
 * - Example: ["eat","tea","tan","ate","nat","bat"]
 *   → [["eat","tea","ate"], ["tan","nat"], ["bat"]]
 *
 * ----------------------------------------------------------------
 * Implementation Strategies:
 *
 * ① Brute Force (Compare every string) ❌
 *    - For each string, check all others if they are anagrams.
 *    - Requires character frequency or sorting per comparison.
 *    - Complexity: O(n² * k log k)
 *
 * ② Sort-based HashMap Key ✅
 *    - Sort each string alphabetically (e.g. "eat" → "aet").
 *    - Use the sorted string as a key in a HashMap.
 *    - Group original strings by identical sorted key.
 *    - Complexity: O(n * k log k)
 *      (n = number of strings, k = average string length)
 *
 * ③ Frequency-count Key (Optimized) ⚡
 *    - Build 26-length frequency array for each word.
 *    - Convert to string key like "1#0#0#..." (no sorting needed).
 *    - Complexity: O(n * k)
 *    - Avoids O(k log k) sorting cost.
 *
 * ----------------------------------------------------------------
 * Common Pitfalls:
 * - ❌ Using char[] as key → invalid, since arrays don’t override equals()/hashCode().
 * - ⚠️ Forgetting to wrap `map.values()` → must convert to `new ArrayList<>(...)`.
 * - ⚠️ Misusing `map.containsKey(charArray)` instead of `map.containsKey(key)`.
 *
 * ----------------------------------------------------------------
 * Clean & Correct Implementation:
 *
 * class Solution {
 *     public List<List<String>> groupAnagrams(String[] strs) {
 *         Map<String, List<String>> map = new HashMap<>();
 *         for (String s : strs) {
 *             char[] ca = s.toCharArray();
 *             Arrays.sort(ca);
 *             String key = new String(ca);
 *             map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
 *         }
 *         return new ArrayList<>(map.values());
 *     }
 * }
 *
 * ----------------------------------------------------------------
 * Complexity Comparison:
 * | Method              | Time       | Space | Notes                      |
 * |----------------------|------------|--------|----------------------------|
 * | Brute Force          | O(n² * k)  | O(1)  | Too slow                   |
 * | Sorting Key ✅        | O(n * k log k) | O(n * k) | Simple & clean          |
 * | Frequency Key ⚡      | O(n * k)  | O(n * k) | Fastest, avoids sorting   |
 *
 * ----------------------------------------------------------------
 * Key Takeaways:
 * - Always convert sorted char[] → String before using as map key.
 * - `computeIfAbsent()` is a concise, safer alternative to if-else checks.
 * - Use `map.values()` + `new ArrayList<>(...)` to return grouped results.
 */



---
# Tip —  * Problem context: LeetCode 15 — 3Sum
# Tip —  * Problem context: LeetCode 15 — 3Sum

/**
 * TIP
 * Problem context: LeetCode 15 — 3Sum
 *
 * Summary:
 * - Find all unique triplets [a, b, c] such that a + b + c = 0.
 * - Extension of the "2Sum" problem with an outer loop.
 * - Key approach: Sort the array and use the two-pointer method.
 *
 * Implementation Strategy:
 * 1. Sort the array.
 * 2. Loop with index `i` as the first element.
 * 3. Use two pointers (`left = i+1`, `right = n-1`) to find pairs
 *    that sum with nums[i] to 0.
 * 4. Skip duplicates at all levels (`i`, `left`, `right`).
 *
 * Implementation Variants:
 *
 * | Method                  | Complexity | Space |
 * | ----------------------- | ---------- | ----- |
 * | Sort + Two Pointers     | O(n²)      | O(1)  |
 * | Brute Force (3 loops)   | O(n³) ❌    | O(1)  |
 * | HashSet (2Sum variant)  | O(n²)      | O(n)  |
 *
 * Pitfalls:
 * - Forgetting to skip duplicates → repeated triplets in result.
 * - Wrong left pointer setup → must be `i+1` (not nums[i]+1).
 * - Returning duplicate references → always create fresh triplets.
 * - Outer loop must go only until `nums.length - 2`.
 *
 * Example:
 * nums = [-1,0,1,2,-1,-4]
 * Sorted → [-4, -1, -1, 0, 1, 2]
 *
 * i=0 → -4 → no triplet
 * i=1 → -1 → found [-1,-1,2], [-1,0,1]
 * i=2 → -1 (duplicate) → skip
 * Done.
 * Result = [[-1,-1,2], [-1,0,1]]
 *
 * Key points:
 * - Standard solution is sort + two pointers (O(n²)).
 * - Must skip duplicates at 3 places: i, left, right.
 * - Build triplets with `Arrays.asList()`, `List.of()`, or manual new ArrayList.
 */



---
# Tip — 3. Longest Substring Without Repeating Characters
# Tip — 3. Longest Substring Without Repeating Characters

/**
 * TIP:
 * Problem: 3. Longest Substring Without Repeating Characters
 * 
 * \U0001f539 Approach: Sliding Window + HashSet
 * - Use two pointers `left` and `i` to form a window [left, i].
 * - Add characters to a HashSet to ensure all are unique.
 * - If a duplicate is found, shrink window from the left until the duplicate is removed.
 * - Update `max` at each step as `i - left + 1`.
 * 
 * \U0001f539 Example:
 * s = "pwwkew"
 * - Window expands and contracts as duplicates are found.
 * - Longest substring without repeats: "wke" → length = 3
 * 
 * ⏱️ Time Complexity: O(n)
 * \U0001f4be Space Complexity: O(min(n, charset))
 * 
 * This is the optimal solution using a dynamic sliding window.
 */



---
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


---
