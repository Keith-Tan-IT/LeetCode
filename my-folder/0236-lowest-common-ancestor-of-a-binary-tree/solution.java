/**
 * TIP
 * Problem: Lowest Common Ancestor of a Binary Tree
 *
 * Goal:
 * - Find the lowest node that has both p and q as descendants.
 *
 * -------------------------------------------------------------
 * Key Insight:
 * - Each recursive call answers:
 *   "Does my subtree contain p, q, both, or neither?"
 *
 * -------------------------------------------------------------
 * Base Case:
 * - If root == null → return null
 * - If root == p or root == q → return root
 *
 * -------------------------------------------------------------
 * Core Logic:
 * - Recurse left and right
 * - If BOTH sides return non-null → root is LCA
 * - If only one side is non-null → bubble it up
 *
 * -------------------------------------------------------------
 * Return Rule:
 * left == null ? right :
 * right == null ? left :
 * root
 *
 * -------------------------------------------------------------
 * Example:
 * p in left subtree, q in right subtree
 * → current node is LCA
 *
 * -------------------------------------------------------------
 * Complexity:
 * Time  = O(n)
 * Space = O(h) recursion stack
 *
 * -------------------------------------------------------------
 * Takeaways:
 * - No need for parent pointers
 * - No global state required
 * - LCA emerges during recursion unwind
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) {
            return right;
        }
        else if (right == null) {
            return left;
        }
        else {
            return root;
        }
    }
}
