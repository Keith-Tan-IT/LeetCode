class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        StringBuilder s = new StringBuilder();
        while (x > 0) {
            s.append(x % 10);
            x /= 10;
        }
        for (int i = 0; i < (s.length() + 1) / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }
}