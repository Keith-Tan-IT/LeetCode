class Solution {
    public int numDecodings(String s) {
        int[] memo = new int[s.length()];
        if (s.length() == 0) {
            return 0;
        }
        else {
            return dfs(s, 0, memo);
        }
    }
    public int dfs (String s, int curr, int[] memo) {
        if (curr == s.length()) {
            return 1;
        }
        if (s.charAt(curr) == '0') {
            return 0;
        }
        if (memo[curr] != 0) {
            return memo[curr];
        }
        int ways = dfs (s, curr + 1, memo);
        if (curr + 1 < s.length() && (s.charAt(curr) == '1' || s.charAt(curr) == '2' && s.charAt(curr + 1) < '7')) {
            ways += dfs(s, curr + 2, memo);
        }
        memo[curr] = ways;
        return memo[curr];
    }
}