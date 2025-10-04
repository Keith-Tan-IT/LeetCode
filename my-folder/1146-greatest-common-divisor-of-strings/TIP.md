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

