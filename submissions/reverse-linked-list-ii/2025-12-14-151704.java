/**
 * TIP
 * Problem: 92. Reverse Linked List II
 *
 * Goal:
 * - Reverse only the sublist from index left → right (1-indexed).
 * - Do it in one pass, O(1) extra space.
 *
 * -------------------------------------------------------------
 * Key Idea:
 * Use the "head insertion technique":
 * - Keep a pointer `prev` to the node BEFORE the sublist.
 * - Repeatedly take the next node and insert it right after `prev`.
 *
 * -------------------------------------------------------------
 * Algorithm:
 * 1) Create dummy node to handle edge cases cleanly.
 * 2) Move `prev` to the node before `left`.
 * 3) Let `curr = prev.next`, then repeat:
 *        next = curr.next
 *        curr.next = next.next
 *        next.next = prev.next
 *        prev.next = next
 *    This rotates nodes into the reversed section.
 *
 * -------------------------------------------------------------
 * Example:
 * List: 1 → 2 → 3 → 4 → 5
 * Reverse left=2, right=4
 *
 * Steps:
 * - Insert 3 after 1 → 1,3,2,4,5
 * - Insert 4 after 1 → 1,4,3,2,5
 *
 * Result: 1 → 4 → 3 → 2 → 5
 *
 * -------------------------------------------------------------
 * Complexity:
 * Time  = O(n)
 * Space = O(1)
 *
 * -------------------------------------------------------------
 * Takeaways:
 * - Use dummy to simplify boundary handling.
 * - Only pointers are moved; no new nodes.
 * - Head-insertion technique is the cleanest O(1) in-place reversal.
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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        for (int i = 1; i < left; i++) {
            prev = prev.next;
        }
        ListNode curr = prev.next;
        for (int i = 0; i < right - left; i++) {
            ListNode next = curr.next;
            curr.next = next.next;
            next.next = prev.next;
            prev.next = next;
        }
        return dummy.next;
    }
}