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
        int count = 0;
        while(root != null) {
            if(height - 1 == height(root.right)) {
                count += 1 << height;
                root = root.right; 
            }
            else {
                count += 1 << height - 1;
                root = root.left;
            }
            height--;
        }
        return count;
    }

    public int height(TreeNode root) {
        return root == null ? -1 : 1 + height(root.left);
    }
}
