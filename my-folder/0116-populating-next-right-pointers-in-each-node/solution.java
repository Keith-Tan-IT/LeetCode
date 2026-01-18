/**
 * TIP
 * Problem: 116. Populating Next Right Pointers in Each Node
 *
 * Goal:
 * - Connect each node to its next right node.
 * - Use O(1) extra space.
 *
 * -------------------------------------------------------------
 * Tree Property:
 * - Perfect binary tree
 * - Every parent has BOTH left and right children
 *
 * -------------------------------------------------------------
 * Key Insight:
 * - Use already established next pointers from parent level
 * - Build connections top-down
 *
 * -------------------------------------------------------------
 * Core Connections:
 * 1) left.next = right
 * 2) right.next = parent.next.left (if parent.next exists)
 *
 * -------------------------------------------------------------
 * Algorithm:
 * - If root is null → return
 * - Connect root.left → root.right
 * - If root.next exists → connect root.right → root.next.left
 * - Recurse left, then right
 *
 * -------------------------------------------------------------
 * Example:
 * Tree:
 *        1
 *      /   \
 *     2     3
 *    / \   / \
 *   4  5  6   7
 *
 * Result:
 * 4 → 5 → 6 → 7 → null
 *
 * -------------------------------------------------------------
 * Complexity:
 * Time  = O(n)
 * Space = O(h) recursion stack
 *
 * -------------------------------------------------------------
 * Takeaways:
 * - This solution ONLY works for perfect binary trees
 * - Relies on parent-level next pointers
 * - No queue, no extra data structure
 */


/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        if (root.left != null) {
            root.left.next = root.right;
            if (root.next != null) {
                root.right.next = root.next.left;
            }
            connect(root.left);
            connect(root.right);
        }
        return root;
    }
}
