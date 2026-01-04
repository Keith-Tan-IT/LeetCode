/**
 * TIP
 * Problem: 86. Partition List
 *
 * Goal:
 * - Rearrange a linked list so that all nodes < x come before nodes >= x
 * - Preserve the original relative order (stable partition)
 *
 * -------------------------------------------------------------
 * Key Insight:
 * This is NOT sorting.
 * It is a stable partitioning problem.
 *
 * -------------------------------------------------------------
 * Optimal Strategy:
 * Use two separate lists:
 * 1) one for nodes < x
 * 2) one for nodes >= x
 *
 * Traverse the list once and append nodes to the correct list.
 *
 * -------------------------------------------------------------
 * Why Dummy Nodes?
 * - Simplifies edge cases (empty list, head changes)
 * - Avoids special handling for the first node
 *
 * -------------------------------------------------------------
 * Algorithm:
 * 1) Create two dummy nodes: smallDummy, largeDummy
 * 2) Use pointers small and large to build both lists
 * 3) Traverse original list:
 *      - if node.val < x → append to small list
 *      - else → append to large list
 * 4) Set large.next = null (avoid cycle)
 * 5) Connect small list to large list
 *
 * -------------------------------------------------------------
 * Example:
 * Input:  1 → 4 → 3 → 2 → 5 → 2, x = 3
 * Output: 1 → 2 → 2 → 4 → 3 → 5
 *
 * -------------------------------------------------------------
 * Complexity:
 * Time  = O(n)
 * Space = O(1) extra
 *
 * -------------------------------------------------------------
 * Takeaways:
 * - Stable order matters → no swapping
 * - Dummy nodes simplify linked list manipulation
 * - Always cut off the tail to prevent cycles
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode smaller = new ListNode(0), bigger = new ListNode(0);
        ListNode small = smaller, big = bigger; 
        while (head != null) {
            if (head.val < x) {
                small.next = head;
                small = small.next;
            }
            else {
                big.next = head;
                big = big.next;
            }
            head = head.next;
        }
        small.next = bigger.next;
        big.next = null;
        return smaller.next;
    }
}