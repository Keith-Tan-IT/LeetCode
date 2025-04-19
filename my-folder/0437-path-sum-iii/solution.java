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
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        Map<Long, Integer> map = new HashMap<>();
        map.put((long) 0,1);
        return dfs(root, 0, map, targetSum);
    }

    public int dfs (TreeNode root, long prefixSum, Map<Long,Integer> map, int targetSum) {
        if (root == null) { 
            return 0;
        }
        int count = 0;
        prefixSum += root.val;
        count += map.getOrDefault((prefixSum - targetSum), 0); 
        map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);

        count += dfs(root.left, prefixSum, map, targetSum);
        count += dfs(root.right, prefixSum, map, targetSum);
        map.put(prefixSum, map.get(prefixSum) - 1);
        return count;
    }
}
