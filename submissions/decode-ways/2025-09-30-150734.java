class Solution {
    public int numDecodings(String s) {
        int n = s.length();
        int curr = 1, prev = 0;
        for (int i = n - 1; i >= 0; i--) {
            int temp = 0;
            if (s.charAt(i) != '0') {
                temp = curr;
            }
            if (i < n - 1 && (s.charAt(i) == '1' || s.charAt(i) == '2' && s.charAt(i + 1) < '7')) {
                temp += prev;
            }
            prev = curr;
            curr = temp;
        }
        return curr;
    }
}