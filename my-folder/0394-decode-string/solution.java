class Solution {
    public String decodeString(String s) {
        Stack<Integer> intStack= new Stack<>();
        Stack<StringBuilder> stringStack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int k = 0;
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                k = k * 10 + c - '0';
            }
            else if (c == '[') {
                intStack.push(k);
                stringStack.push(sb);
                k = 0;
                sb = new StringBuilder();
            }
            else if (c == ']') {
                int a = intStack.pop();
                StringBuilder prev = stringStack.pop();
                for (int i = 0; i < a; i++) {
                    prev.append(sb);
                }
                sb = prev;
            }
            else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
