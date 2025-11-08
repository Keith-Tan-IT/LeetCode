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

