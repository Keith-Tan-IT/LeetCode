/* Brute force Solution
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        return dfs(text1, text2, 0, 0);
    }
    public int dfs (String text1, String text2, int start1, int start2) {
        if (start1 >= text1.length() || start2 >= text2.length()) {
            return 0;
        }
        else if (text1.charAt(start1) == text2.charAt(start2)) {
            return 1 + dfs(text1, text2, start1 + 1, start2 + 1);
        }
        else {
            return Math.max(dfs(text1, text2, start1 + 1, start2), dfs(text1, text2, start1, start2 + 1));
        }
    }
}
*/
class Solution {
    public int[][] memo;
    public int longestCommonSubsequence(String text1, String text2) {
        memo = new int[text1.length()][text2.length()];
        for (int[] rows : memo) {
            Arrays.fill(rows, -1);
        }
        return dfs(text1, text2, 0, 0);
    }
    public int dfs (String text1, String text2, int t1, int t2) {
        if (t1 == text1.length() || t2 == text2.length()) {
            return 0;
        }
        else if (memo[t1][t2] != -1) {
            return memo[t1][t2];
        }
        else if (text1.charAt(t1) == text2.charAt(t2)) {
            memo[t1][t2] = 1 + dfs(text1, text2, t1 + 1, t2 + 1);
        }
        else {
            memo[t1][t2] = Math.max(dfs(text1, text2, t1 + 1, t2), 
            dfs(text1, text2, t1, t2 + 1));
        }
        return memo[t1][t2];
    }
}
