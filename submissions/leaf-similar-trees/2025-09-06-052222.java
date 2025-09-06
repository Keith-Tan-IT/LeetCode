/**
 * TIP
 * Problem: 872. Leaf-Similar Trees
 *
 * Summary:
 * When comparing boxed Integer values in Java, `==` is a reference comparison.
 * Values between -128 and 127 are cached (so `==` might appear to work), but
 * for general correctness use `Objects.equals(a, b)` or compare primitives:
 *   a.intValue() == b.intValue()
 *
 * Example:
 * Integer a = 200, b = 200;
 * System.out.println(a == b); // false
 * System.out.println(Objects.equals(a, b)); // true
 *
 * Pitfall:
 * If you `new Integer(200)` references differ too. Avoid relying on interning.
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
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        Stack <Integer> leaves1 = new Stack<>();
        Stack <Integer> leaves2 = new Stack<>();
        Stack <TreeNode> node1 = new Stack<>();
        Stack <TreeNode> node2 = new Stack<>();
    
        node1.push(root1);
        node2.push(root2);

        while(!node1.empty()) {
            TreeNode curr1 = node1.pop();
            if (curr1.left != null) {
                node1.push(curr1.left);
            }
            if (curr1.right != null) {
                node1.push(curr1.right);
            }
            if (curr1.left == null && curr1.right == null) {
                leaves1.push(curr1.val);
            }
        }
        while (!node2.empty()) {
            TreeNode curr2 = node2.pop();
            if (curr2.left !=null) {
                node2.push(curr2.left);
            }
            if (curr2.right != null) {
                node2.push(curr2.right);
            }
            if (curr2.left == null && curr2.right == null) {
                leaves2.push(curr2.val);
            }
        }
        System.out.println(leaves1.size());        
        System.out.println(leaves2.size());
        System.out.println(leaves1);
        System.out.println(leaves2);



        if (leaves1.size() != leaves2.size()) {
            return false;
        }
        while (!leaves1.empty() && !leaves2.empty()) {
            if (!leaves1.pop().equals(leaves2.pop())) {
                return false;
            }
        }    
        return true;
    }
}