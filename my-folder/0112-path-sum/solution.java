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
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        Stack<Integer> sum = new Stack<>();
        Stack<TreeNode> tree = new Stack<>();
        tree.push(root);
        sum.push(root.val);
        while (!tree.empty()) {
            TreeNode node = tree.pop();
            int currentSum = sum.pop();
            if (node.left == null && node.right == null) {
                if (currentSum == targetSum) {
                    return true;
                }
            }
            else {
                if (node.left != null) {
                    tree.push(node.left);
                    sum.push(node.left.val + currentSum);
                }
                if (node.right != null) {
                    tree.push(node.right);
                    sum.push(node.right.val + currentSum);
                }
            }
        }
        return false;
    }
}
