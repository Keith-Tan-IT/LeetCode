/**
 * TIP
 * Problem: 114. Flatten Binary Tree to Linked List
 *
 * Goal:
 * - Flatten the binary tree into a linked list in-place.
 * - Follow preorder traversal: root → left → right.
 * - Use right pointer as next, left pointer must be null.
 *
 * -------------------------------------------------------------
 * Brute Force:
 * - Perform preorder traversal.
 * - Store nodes in a list.
 * - Rewire pointers from the list.
 *
 * Drawback:
 * - Uses O(n) extra space.
 *
 * -------------------------------------------------------------
 * Optimized Insight:
 * - We want preorder order.
 * - Reverse preorder (right → left → root) allows in-place linking.
 *
 * -------------------------------------------------------------
 * Core Trick (Reverse Preorder):
 * - Traverse right subtree first.
 * - Then traverse left subtree.
 * - Maintain a global `prev` pointer.
 *
 * Steps:
 * 1) Flatten right subtree.
 * 2) Flatten left subtree.
 * 3) root.right = prev
 * 4) root.left = null
 * 5) prev = root
 *
 * -------------------------------------------------------------
 * Example:
 * Tree:
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 *
 * Result:
 * 1 → 2 → 3 → 4 → 5 → 6
 *
 * -------------------------------------------------------------
 * Complexity:
 * Time  = O(n)
 * Space = O(h) (recursion stack)
 *
 * -------------------------------------------------------------
 * Takeaways:
 * - Reverse traversal can simplify pointer problems.
 * - `prev` represents the next node in the final list.
 * - No extra data structure needed.
 * - This is a classic in-place tree transformation pattern.
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
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        flatten(root.left);
        flatten(root.right);
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = null;
        root.right = left;
        TreeNode curr = root;
        while (curr.right != null) {
            curr = curr.right;
        }
        curr.right = right;
    }
}
