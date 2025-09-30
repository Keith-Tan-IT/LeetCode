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


class Solution {
    public int numDecodings(String s) {
        int n = s.length();
        int waysNext = 1;       // dp[i+1], base case: empty string has 1 way
        int waysNextNext = 0;   // dp[i+2], initially 0
        for (int i = n - 1; i >= 0; i--) {
            int waysCurrent = 0;
            if (s.charAt(i) != '0') {
                waysCurrent = waysNext; // take single digit
            }
            if (i < n - 1 && (s.charAt(i) == '1' || 
                (s.charAt(i) == '2' && s.charAt(i + 1) < '7'))) {
                waysCurrent += waysNextNext; // take double digit
            }
            // shift for next iteration
            waysNextNext = waysNext;
            waysNext = waysCurrent;
        }
        return waysNext;
    }
}

