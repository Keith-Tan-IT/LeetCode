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

class Solution {
    public String reorderSpaces(String text) {
        List<String> words = new ArrayList<>();
        int spaceCount = 0;
        StringBuilder eachWord = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == ' ') {
                spaceCount++;
                if (eachWord.length() > 0) {
                    words.add(eachWord.toString());
                    eachWord.setLength(0);
                }
            }
            else {
                eachWord.append(c);
            }
        }
        if (eachWord.length() > 0) {
            words.add(eachWord.toString());
        }

        if (words.size() == 1) {
            return words.get(0) + createSpace(spaceCount).toString();
        }
        int spaceBetween = spaceCount / (words.size() - 1);
        int lastSpace = spaceCount % (words.size() - 1);

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < words.size(); i++) {
            result.append(words.get(i));
            if (i < words.size() - 1) {
                result.append(createSpace(spaceBetween));
            }
        }
        result.append(createSpace(lastSpace));
        return result.toString();
    }

    public String createSpace (int n) {
        StringBuilder space = new StringBuilder();
        for (int i = 0; i < n; i++) {
            space.append(' ');
        }
        return space.toString();
    }
}