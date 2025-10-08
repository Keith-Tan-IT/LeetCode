class Solution {
    public String convert(String s, int numRows) {
        char[] c = s.toCharArray();
        StringBuilder[] sb = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            sb[i] = new StringBuilder();
        }
        int i = 0;
        while (i < s.length()) {
            for (int idx = 0; idx < numRows && i < s.length(); idx++) {
                sb[idx].append(c[i++]);
            }
            for (int idx = numRows - 2; idx >= 1 && i < c.length; idx--) {
                sb[idx].append(c[i++]);
            }
        }
        for (int idx = 1; idx < numRows; idx++) {
            sb[0].append(sb[idx]);
        }
        return sb[0].toString();
    }
}
