class Solution {
    public boolean isBalanced(String num) {
        int diff = 0, sign = 1;
        for (int i = 0; i < num.length(); i++) {
            diff += sign * (num.charAt(i) - '0');
            sign *= -1; 
        }
        return diff == 0;
    }
}