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
        List < List <Integer>> result = new LinkedList<>();
        List<Integer> path = new LinkedList<>();
        dfs(root, targetSum, path, result);
        return result;
    }

    public void dfs (TreeNode root, int targetSum, List path, List <List<Integer>> result) {
        if (root == null) {
            return;
        }
        path.add(root.val);
        if (root.right == null && root.left == null) {
            if (root.val == targetSum) {
                result.add(new LinkedList(path));
            }
        }
        else {
            if (root.left != null) {
                dfs(root.left, targetSum - root.val, path, result);
            }
            if (root.right != null) {
                dfs(root.right, targetSum - root.val, path, result);
            }    
        }
        path.remove(path.size()-1);
    }
}
