class Solution {
    public boolean isValid(String s) {
        Stack <Character> parentheses = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                parentheses.push(')');
            } else if (s.charAt(i) == '[') {
                parentheses.push(']');
            } else if (s.charAt(i) == '{') {
                parentheses.push('}');
            } else if (parentheses.isEmpty() || parentheses.pop() != s.charAt(i)) {
                return false;
            }
        }
        return parentheses.isEmpty(); 
    }
}
