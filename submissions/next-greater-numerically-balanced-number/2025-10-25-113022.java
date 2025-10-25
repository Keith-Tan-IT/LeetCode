class Solution {
    public int nextBeautifulNumber(int n) {
        for (int i = n + 1; ; i++) {
            if (isBalanced(i)) {
                return i;
            }
        }
    }
    public boolean isBalanced(int num) {
        int[] count = new int[10];
        int temp = num;
        while (temp > 0) {
            int d = temp % 10;
            if (d == 0) {
                return false;
            }
            count[d]++;
            temp /= 10;
        }
        //check count
        for (int d = 1; d <= 9; d++) {
            if (count[d] > 0 && count[d] != d) {
                return false;
            }
        }
        return true;
    }
}