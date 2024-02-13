class Solution {
    public boolean isAnagram(String s, String t) {
        int [] count = new int[26];
        for (char x : s.toCharArray()) {
            count[x - 'a']++;
        }
        for (char y : t.toCharArray()) {
            count[y - 'a']--;
        }

        for (int i : count) {
            if (i != 0)
            return false;
        }

    return true;
    }
}
