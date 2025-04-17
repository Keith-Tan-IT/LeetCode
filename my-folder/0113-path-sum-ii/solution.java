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
    public class SpecialNode {
        TreeNode root;
        List<Integer> path ;
        int sum;

        public SpecialNode (TreeNode root, List<Integer> path, int sum) {
            this.root = root;
            this.path = new ArrayList(path);
            this.sum = sum;
        }
    }
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        Stack<SpecialNode> stack = new Stack<>();
        stack.push(new SpecialNode(root, new ArrayList<>(), targetSum));
        while (!stack.isEmpty()) {
            SpecialNode node = stack.pop();
            TreeNode curr = node.root;
            List<Integer> path = node.path;
            System.out.println(path);
            int sum = node.sum;
            if (curr != null) {
                path.add(curr.val);
                if(curr.right == null && curr.left == null && sum == curr.val) {
                    result.add(path);
                }
                stack.push(new SpecialNode(curr.right, path, sum - curr.val));
                stack.push(new SpecialNode(curr.left, path, sum - curr.val));
            }
        }
        return result;

    }
    
}
