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
    int max = 0;
    public int longestZigZag(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // left = false, right = true; 
        maxZigZag(root.left, false, 0);
        maxZigZag(root.right, true, 0);
        return max;
    }

    public void maxZigZag (TreeNode node, boolean direction, int depth) {
        max = Math.max(depth, max);
        if(node == null) {
            return;
        }
        maxZigZag(node.right, true, (!direction) ? depth + 1 : 0);
        maxZigZag(node.left, false, (direction) ? depth + 1 : 0);
    }
}
