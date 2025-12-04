/**
 * TIP
 * Problem: 71. Simplify Path
 *
 * Goal:
 * - Convert a Unix-style absolute path into its canonical form.
 * - Handle ".", "..", and multiple slashes.
 *
 * -------------------------------------------------------------
 * Key Idea:
 * Use a stack to simulate navigation in a file system.
 *
 * Rules:
 * - "" or "."    → ignore
 * - ".."         → pop from stack (go up one directory)
 * - normal name  → push onto stack
 *
 * Why this works:
 * - Stack naturally captures the structure of directory navigation.
 * - Prevents invalid upward movement beyond "/".
 *
 * -------------------------------------------------------------
 * Algorithm:
 * 1) Split the path by "/".
 * 2) For each part:
 *      - Skip empty or "."
 *      - If ".." → pop if possible
 *      - Else → push the directory name
 * 3) Rebuild the canonical path from the stack.
 *
 * -------------------------------------------------------------
 * Example:
 * Input:  "/a/./b/../../c/"
 *
 * Steps:
 * stack = ["a"]
 * stack = ["a","b"]
 * ".." → pop b → ["a"]
 * ".." → pop a → []
 * push "c" → ["c"]
 *
 * Result = "/c"
 *
 * -------------------------------------------------------------
 * Complexity:
 * Time  = O(n)
 * Space = O(n) for the stack
 *
 * -------------------------------------------------------------
 * Takeaways:
 * - Stack perfectly models directory traversal.
 * - Always ignore ".", empty segments.
 * - ".." pops only when stack is not empty.
 */

class Solution {
    public String simplifyPath(String path) {
        String[] split = path.split("/");
        Deque<String> stack = new ArrayDeque<>();
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
            s.append("/").append(stack.removeLast());
        }
        return s.length() == 0 ? "/" : s.toString();
    }
}
