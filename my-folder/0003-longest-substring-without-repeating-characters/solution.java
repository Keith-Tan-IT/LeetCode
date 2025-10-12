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


class Solution {
    public int lengthOfLongestSubstring(String s) {
        int max = 0, left = 0;
        Set<Character> charSet = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            while (charSet.contains(s.charAt(i))) {
                charSet.remove(s.charAt(left++));
            }
            charSet.add(s.charAt(i));
            max = Math.max(max, i - left + 1);
        }
        return max;
    }
}
