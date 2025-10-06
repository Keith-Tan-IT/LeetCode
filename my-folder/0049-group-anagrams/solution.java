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

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map <String, List<String>> map = new HashMap<>();
        List<List<String>> result = new ArrayList<>();
        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String key = new String(charArray);
            if (map.containsKey(key)) {
                map.get(key).add(str);
            }
            else {
                map.put(key, new ArrayList<>());
                map.get(key).add(str);
            }
        }
        result = new ArrayList<>(map.values());
        return result;
    }
}
