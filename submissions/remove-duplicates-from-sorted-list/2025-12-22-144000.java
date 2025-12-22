/**
 * TIP
 * Problem: 83. Remove Duplicates from Sorted List
 *
 * Goal:
 * - Remove duplicate values from a SORTED linked list.
 * - Keep exactly one occurrence of each value.
 *
 * -------------------------------------------------------------
 * Key Idea:
 * Use a single pointer to compare adjacent nodes.
 *
 * Since the list is sorted:
 * - Duplicates will always be next to each other.
 *
 * -------------------------------------------------------------
 * Algorithm (One Pass):
 * 1️⃣ Set current = head
 * 2️⃣ While current and current.next exist:
 *      - If current.val == current.next.val
 *          → skip duplicate: current.next = current.next.next
 *      - Else
 *          → move current forward
 * 3️⃣ Return head
 *
 * -------------------------------------------------------------
 * Example:
 * Input:  1 → 1 → 2 → 3 → 3
 * Output: 1 → 2 → 3
 *
 * -------------------------------------------------------------
 * Complexity:
 * Time  = O(n)
 * Space = O(1)
 *
 * -------------------------------------------------------------
 * Takeaways:
 * - Sorted list ⇒ duplicates are adjacent
 * - Compare values, NOT node references
 * - Dummy node is NOT required here
 * - Pointer rewiring avoids extra memory
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
    public ListNode deleteDuplicates(ListNode head) {
        ListNode current = head;
        while (current != null) {
            if (current.next == null) {
                break;
            }
            if (current.val == current.next.val) {
                current.next = current.next.next;
            }
            else {
                current = current.next;
            }
        }
        return head;
    }
}