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
        if (root == null) {
            return 0; 
        }
        Stack <TreeNode> node = new Stack();
        Stack <Integer> depths = new Stack();
        node.push(root);
        depths.push(1);
        int max = 1;

        while (!node.isEmpty()) {
            TreeNode current = node.pop();
            int depth = depths.pop();
            if (current.right == null && current.left == null) {
                max = Math.max(depth,max);
            }

            if (current.right != null) {
                node.push(current.right);
                depths.push(depth + 1);
            }
            
            if (current.left != null) {
                node.push(current.left);
                depths.push(depth + 1);
            }
            
        }
        return max;
    }
}
