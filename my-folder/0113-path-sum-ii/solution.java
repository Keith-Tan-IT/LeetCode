/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<Integer> path = new LinkedList <>();
        List<List<Integer>> result = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        TreeNode prev = null;
        int sum = 0;
        while (curr != null || !stack.empty()) {
            while (curr != null) {
                stack.push(curr);
                path.add(curr.val);
                sum += curr.val;
                curr = curr.left;
                System.out.println(path);
            }
            curr = stack.peek();
            if (curr.right != null && curr.right != prev) {
                curr = curr.right;
                continue;
            }
            if (curr.left == null && curr.right == null) {
                if (sum == targetSum) {
                    result.add(new LinkedList(path));
                }
            }
            System.out.println(path);
            stack.pop();
            path.removeLast();
            sum -= curr.val;
            prev = curr;
            curr = null;
        }
        return result;
    }
}
