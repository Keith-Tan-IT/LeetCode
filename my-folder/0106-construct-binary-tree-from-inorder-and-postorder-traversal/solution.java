/**
 * TIP
 * Problem: 106. Construct Binary Tree from Inorder and Postorder Traversal
 *
 * Goal:
 * - Reconstruct a binary tree from inorder and postorder traversals.
 *
 * -------------------------------------------------------------
 * Key Insights:
 * - Postorder gives ROOT LAST.
 * - Inorder splits LEFT and RIGHT subtrees.
 *
 * -------------------------------------------------------------
 * Core Trick:
 * - Traverse postorder from the END.
 * - Build RIGHT subtree first (because postorder = L → R → Root).
 * - Use HashMap for inorder index lookup.
 *
 * -------------------------------------------------------------
 * Algorithm:
 * 1) Build HashMap: inorder value → index.
 * 2) Start postIndex from postorder.length - 1.
 * 3) Pick root from postorder[postIndex--].
 * 4) Split inorder at root index.
 * 5) Recursively build:
 *      - right subtree FIRST
 *      - left subtree second
 *
 * -------------------------------------------------------------
 * Example:
 * inorder   = [9,3,15,20,7]
 * postorder = [9,15,7,20,3]
 *
 * Root = 3
 * Right subtree root = 20
 * Left subtree root = 9
 *
 * -------------------------------------------------------------
 * Complexity:
 * Time  = O(n)
 * Space = O(n) (HashMap + recursion stack)
 *
 * -------------------------------------------------------------
 * Takeaways:
 * - Postorder must be processed BACKWARDS.
 * - Always build RIGHT subtree first.
 * - HashMap is required for O(n) performance.
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
    Map<Integer,Integer> inorderMap = new HashMap<>();
    int postIndex;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        postIndex = postorder.length - 1;
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return helper(postorder, 0, postorder.length - 1);
    }
    
    private TreeNode helper(int[] postorder, int inLeft, int inRight) {
        if (inLeft > inRight) {
            return null;
        }
        int rootVal = postorder[postIndex--];
        TreeNode node = new TreeNode(rootVal);
        int mid = inorderMap.get(rootVal);

        node.right = helper(postorder, mid + 1, inRight);
        node.left = helper(postorder, inLeft, mid - 1);
        return node;
    } 
}
