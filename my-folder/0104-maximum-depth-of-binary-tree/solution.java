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
    public int maxDepth(TreeNode root) {
        if (root == null) {return 0;}
        int max = 1;

        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> depths = new Stack<>();

        stack.push(root);
        depths.push(max);

        while(!depths.empty()) {
            TreeNode node = stack.pop();
            int depth = depths.pop();
            max = Math.max(depth,max);
            if (node.left != null) {
                stack.push(node.left);
                depths.push(depth+1);
            }
            if (node.right != null) {
                stack.push(node.right);
                depths.push(depth+1);
            }
        }
        return max;
        
    }
}
