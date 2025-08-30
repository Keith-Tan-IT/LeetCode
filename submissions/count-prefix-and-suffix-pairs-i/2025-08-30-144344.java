class Solution {
    public int countPrefixSuffixPairs(String[] words) {
        int count = 0;
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (isPrefixAndSuffix(words[i], words[j]) == true) {
                    count++;
                }
            }
        }
        return count;
    }

    public boolean isPrefixAndSuffix (String s1, String s2) {
        int n = s1.length(), m = s2.length();
        if (s1.length() > s2.length()) {
            return false;
        }
        if (s1.equals(s2.substring(0, n)) && s1.equals(s2.substring(m - n, m))) {
            return true;
        }
        else {
            return false;
        }
    }
}