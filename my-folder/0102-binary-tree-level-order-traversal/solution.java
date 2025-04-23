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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.offer(root);
        while (!nodes.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int currentSize = nodes.size();
            for (int i = 0; i < currentSize; i++) {
                if(nodes.peek().left != null) {
                    nodes.offer(nodes.peek().left);
                }
                if (nodes.peek().right != null) {
                    nodes.offer(nodes.peek().right);
                }
                level.add(nodes.poll().val);
            }
            result.add(level);
        }
        return result;
    }
}
