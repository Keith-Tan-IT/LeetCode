class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        long reverse = 0;
        int ori = x;
        while (x > 0) {
            reverse = reverse * 10 + x % 10;
            x /= 10;
        }
        return reverse == ori;
    }
}