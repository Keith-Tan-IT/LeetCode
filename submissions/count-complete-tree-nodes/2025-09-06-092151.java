/**
 * TIP
 * Problem: Understanding 1 << k (Bitwise Left Shift)
 *
 * Summary:
 * - `<<` is the bitwise left shift operator in Java.
 * - `1 << k` shifts the number 1 left by `k` bits, which is equivalent to 2^k.
 * - This is often used in problems involving powers of 2, complete binary trees, or bitmasking.
 *
 * Example:
 * 1 << 3 = 8   // 1000 in binary
 * 1 << 4 = 16  // 10000 in binary
 *
 * Usage in LeetCode:
 * - Common in problems like "Count Complete Tree Nodes" where you calculate nodes in a full binary subtree:
 *   int nodes = 1 << leftHeight;  // computes number of nodes in a perfect binary subtree
 *
 * Pitfall:
 * - Remember operator precedence: `(1 << k)` is safe, avoid writing `1 << k + 1` without parentheses.
 * - `<<` only works with integer types; results may overflow if k is too large.
 *
 * Key points:
 * - Efficient way to compute powers of 2.
 * - Often used in combination with recursion or tree traversal to calculate counts.
 */

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
