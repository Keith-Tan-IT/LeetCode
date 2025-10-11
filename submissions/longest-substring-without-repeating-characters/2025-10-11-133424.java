class Solution {
    public int lengthOfLongestSubstring(String s) {
        int max = 0, left = 0;
        Set<Character> charSet = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (charSet.contains(s.charAt(i))) {
                while (charSet.contains(s.charAt(i))) {
                    charSet.remove(s.charAt(left++));
                }
            }
            charSet.add(s.charAt(i));
            max = Math.max(max, i - left + 1);
        }
        return max;
    }
}