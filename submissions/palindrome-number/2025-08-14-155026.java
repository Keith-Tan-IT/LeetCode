class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        long reverse = 0;
        int ori = x;
        int temp;
        while (x > 0) {
            temp = x % 10;
            reverse += temp;
            reverse *= 10;
            x /= 10;
        }
        return reverse / 10 == ori;
    }
}