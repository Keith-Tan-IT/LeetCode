/**
 * TIP
 * Problem: 105. Construct Binary Tree from Preorder and Inorder Traversal
 *
 * Goal:
 * - Reconstruct a binary tree from preorder and inorder traversals.
 *
 * -------------------------------------------------------------
 * Key Insights:
 * - Preorder gives root first.
 * - Inorder splits left and right subtrees.
 *
 * -------------------------------------------------------------
 * Core Trick:
 * - Use a HashMap to store inorder value → index.
 * - Use a global preorder index to track root selection.
 *
 * -------------------------------------------------------------
 * Algorithm:
 * 1) Build HashMap for inorder indices.
 * 2) Pick root from preorder[preIndex++].
 * 3) Find root position in inorder.
 * 4) Recursively build:
 *      left subtree → inorder[left .. mid-1]
 *      right subtree → inorder[mid+1 .. right]
 *
 * -------------------------------------------------------------
 * Example:
 * preorder = [3,9,20,15,7]
 * inorder  = [9,3,15,20,7]
 *
 * Root = 3
 * Left subtree = [9]
 * Right subtree = [15,20,7]
 *
 * -------------------------------------------------------------
 * Complexity:
 * Time  = O(n)
 * Space = O(n) for HashMap + recursion stack
 *
 * -------------------------------------------------------------
 * Takeaways:
 * - Preorder defines root order.
 * - Inorder defines subtree boundaries.
 * - HashMap is mandatory to achieve O(n).
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
    Map <Integer, Integer> inorderMap = new HashMap<>();
    int preIndex = 0;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return helper(preorder, 0, inorder.length);
    }
    public TreeNode helper(int[] preorder, int inLeft, int inRight) {
        if (inLeft >= inRight) {
            return null;
        }
        int rootVal = preorder[preIndex++];
        TreeNode root = new TreeNode(rootVal);
        int mid = inorderMap.get(rootVal);
        root.left = helper(preorder, inLeft, mid);
        root.right = helper(preorder, mid + 1, inRight);
        return root;
    }
}