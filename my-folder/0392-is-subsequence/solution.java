class Solution {
    public boolean isSubsequence(String s, String t) {
        return dfs(s, t, s.length(), t.length());
    }
    
    public boolean dfs (String s, String t, int i, int j) {
        if (i == 0) {
            return true;
        }
        if (j == 0) {
            return false;
        }
        if (s.charAt(i - 1) == t.charAt(j - 1)) {
            return dfs(s, t, i - 1, j - 1);
        }
        else {
            return dfs(s, t, i, j - 1);
        }
    }
}
