class Solution {
    public String removeStars(String s) {
        StringBuilder newString = new StringBuilder();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '*') {
                newString.append(s.charAt(i));
                count += 1;
            }
            else {
                newString.deleteCharAt(--count);
            }
        }
        return newString.toString();
    }
}
