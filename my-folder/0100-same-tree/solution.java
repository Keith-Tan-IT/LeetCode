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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        Stack <TreeNode> stackp = new Stack <>();
        Stack <TreeNode> stackq = new Stack <>();

        stackp.push(p);
        stackq.push(q);

        while (!stackp.isEmpty() && !stackq.isEmpty()) {
            TreeNode node1 = stackp.pop();
            TreeNode node2 = stackq.pop();

            if (node1 == null && node2 == null) {
                continue;
            }
            
            else if (node1 == null || node2 == null || node1.val != node2.val) {
                return false;
            }

            stackp.push(node1.left);
            stackp.push(node1.right);
            stackq.push(node2.left);
            stackq.push(node2.right);
        }
        return stackp.isEmpty() && stackq.isEmpty();
    }
}
