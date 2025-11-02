/**
 * TIP
 * Problem: 1592. Rearrange Spaces Between Words
 *
 * Summary:
 * - Redistribute all spaces in a text evenly between words.
 * - If leftover spaces exist, append them at the end.
 * - Maintain total space count and word order.
 *
 * ----------------------------------------------------------------
 * Step-by-step:
 * 1️⃣ Count total spaces.
 * 2️⃣ Split words using `split("\\s+")`.
 * 3️⃣ Compute:
 *      - spacesBetween = totalSpaces / (numWords - 1)
 *      - spacesLeft = totalSpaces % (numWords - 1)
 * 4️⃣ Join with " ".repeat(spacesBetween)
 * 5️⃣ Append leftover spaces.
 *
 * ----------------------------------------------------------------
 * Edge Cases:
 * - Only one word → all spaces go to the end.
 * - Leading/trailing/multiple spaces are all preserved correctly.
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
 *
 * ----------------------------------------------------------------
 * Key Takeaways:
 * - Use `split("\\s+")` to handle variable spaces.
 * - Use `" ".repeat(n)` and `String.join()` for clean concatenation.
 */

class Solution {
    public String reorderSpaces(String text) {
        String[] words = text.trim().split("\\s+");
        int spaceNo = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                spaceNo++;
            }
        }
        if (words.length == 1) {
            return words[0] + " ".repeat(spaceNo);
        }
        int spaceBetween = spaceNo / (words.length - 1);
        int spaceLeft = spaceNo % (words.length - 1);
        String spaceString = " ".repeat(spaceBetween);
        String result = String.join(spaceString, words);
        return result + " ".repeat(spaceLeft);
    }
}