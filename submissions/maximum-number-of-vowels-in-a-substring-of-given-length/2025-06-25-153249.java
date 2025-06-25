class Solution {
    public int maxVowels(String s, int k) {
        int max = 0;
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (isVowel(s.charAt(i))) {
                count++;
            }
            if (i >= k && isVowel(s.charAt(i - k))) {
                count--;
            }
            max = Math.max(max, count);
        }
        return Math.min(max, k);
    }

    public boolean isVowel (char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u'; 
    }
}