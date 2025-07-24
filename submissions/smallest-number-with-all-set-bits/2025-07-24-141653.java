class Solution {
    public int smallestNumber(int n) {
        int res = 2;
        while (n >= res) {
            res *= 2;
        }
        return res - 1;

    }
}