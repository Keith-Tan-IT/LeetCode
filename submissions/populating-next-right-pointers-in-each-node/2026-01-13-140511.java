/**
 * TIP
 * Problem: 116. Populating Next Right Pointers in Each Node
 *
 * Goal:
 * - Connect each node’s next pointer to its right neighbor.
 * - Must use O(1) extra space.
 *
 * -------------------------------------------------------------
 * Key Insight:
 * - Tree is PERFECT:
 *   → every node has 2 children
 *   → all leaves at same level
 *
 * -------------------------------------------------------------
 * Core Trick:
 * - Use already established `next` pointers.
 * - No queue needed.
 *
 * -------------------------------------------------------------
 * Pointer Connections:
 * - node.left.next  = node.right
 * - node.right.next = node.next.left (if node.next exists)
 *
 * -------------------------------------------------------------
 * Algorithm:
 * 1) Start from the leftmost node of each level.
 * 2) Traverse the level using `next` pointers.
 * 3) Connect children while moving horizontally.
 * 4) Move down to the next level.
 *
 * -------------------------------------------------------------
 * Example:
 * Input:
 *        1
 *      /   \
 *     2     3
 *
 * Output:
 * 2.next → 3
 *
 * -------------------------------------------------------------
 * Complexity:
 * Time  = O(n)
 * Space = O(1)
 *
 * -------------------------------------------------------------
 * Takeaways:
 * - Perfect tree enables pointer-only traversal.
 * - BFS is easy but not optimal.
 * - This pattern is reused in many pointer problems.
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
        Node leftMost = root;
        while (leftMost.left != null) {
            Node curr = leftMost;
            curr.left.next = curr.right;
            while (curr.next != null) {
                curr.right.next = curr.next.left;
                curr = curr.next;
                curr.left.next = curr.right;
            }
            leftMost = leftMost.left;
        }
        return root;
    }
}