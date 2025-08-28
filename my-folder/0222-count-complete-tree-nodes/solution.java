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
    public int countNodes(TreeNode root) {
        int height = height(root);
        return height == -1 ? 0 : height(root.right) == height - 1 ?
        (1 << height) + countNodes(root.right) : 
        (1 << (height - 1)) + countNodes(root.left);
    }

    public int height(TreeNode root) {
        return root == null ? -1 : 1 + height(root.left);
    }
}
