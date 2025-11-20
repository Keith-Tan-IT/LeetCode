/**
 * TIP
 * Problem: 12. Integer to Roman
 *
 * Summary:
 * - Convert an integer (1–3999) into its Roman numeral representation.
 * - Roman numerals use symbols: 
 *     I=1, V=5, X=10, L=50, C=100, D=500, M=1000
 * - Subtractive notation: 
 *     4=IV, 9=IX, 40=XL, 90=XC, 400=CD, 900=CM
 *
 * ----------------------------------------------------------------
 * Step-by-step approach:
 * 1️⃣ Define two arrays:
 *      - `values[]`  → descending integer values
 *      - `symbols[]` → corresponding Roman symbols
 * 2️⃣ Iterate through the arrays:
 *      while (num >= values[i]):
 *          append(symbols[i])
 *          subtract(values[i]) from num
 * 3️⃣ Continue until num = 0
 *
 * ----------------------------------------------------------------
 * Example:
 * Input: num = 58
 * Process:
 *   58 ≥ 50 → "L", num = 8
 *   8 ≥ 5 → "V", num = 3
 *   3 ≥ 1 → "III"
 * Output: "LVIII"
 *
 * ----------------------------------------------------------------
 * Implementation:
 *
 * class Solution {
 *     public String intToRoman(int num) {
 *         int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
 *         String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
 *
 *         StringBuilder roman = new StringBuilder();
 *         for (int i = 0; i < values.length; i++) {
 *             while (num >= values[i]) {
 *                 roman.append(symbols[i]);
 *                 num -= values[i];
 *             }
 *         }
 *         return roman.toString();
 *     }
 * }
 *
 * ----------------------------------------------------------------
 * Edge Cases:
 * - num = 3  → "III"
 * - num = 4  → "IV"
 * - num = 9  → "IX"
 * - num = 58 → "LVIII"
 * - num = 1994 → "MCMXCIV"
 *
 * ----------------------------------------------------------------
 * Complexity:
 * Time  = O(1) (13 fixed Roman numerals)
 * Space = O(1)
 *
 * ----------------------------------------------------------------
 * Key Takeaways:
 * - Use greedy approach: always subtract the largest possible value.
 * - Avoid maps or dynamic logic; a static array is clean and constant-time.
 * - StringBuilder is faster than using string concatenation (`+=`).
 */

class Solution {
    public String intToRoman(int num) {
        String[] symbol = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] value = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        StringBuilder roman = new StringBuilder();
        for (int i = 0; i < symbol.length; i++) {
            while (num >= value[i]) {
                roman.append(symbol[i]);
                num -= value[i];
            }
        }
        return roman.toString();
    }
}