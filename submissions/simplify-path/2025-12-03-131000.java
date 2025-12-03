class Solution {
    public String simplifyPath(String path) {
        String[] split = path.split("/");
        Stack<String> stack = new Stack<>();
        Stack<String> result = new Stack<>();
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            if (split[i].equals(".") || split[i].equals("")) {
                continue;
            }
            else if (split[i].equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            }
            else {
                stack.push(split[i]);
            }
        }
        while (!stack.isEmpty()) {
            result.push(stack.pop());
        }
        while (!result.isEmpty()) {
            s.append("/").append(result.pop());
        }
        return s.length() == 0 ? "/" : s.toString();
    }
}