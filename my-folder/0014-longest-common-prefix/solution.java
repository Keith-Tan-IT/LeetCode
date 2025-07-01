class Solution {
    public String longestCommonPrefix(String[] strs) {
        StringBuilder prefix = new StringBuilder();
        int idx = 0;
        while (idx < strs[0].length()) {
            Character temp = strs[0].charAt(idx);
            for (int i = 1; i < strs.length; i++) {
                if (idx >= strs[i].length() || temp != strs[i].charAt(idx)) {
                    return prefix.toString();
                }
            }
            prefix.append(temp);
            idx++;
        }
        return prefix.toString();    
    }
}
