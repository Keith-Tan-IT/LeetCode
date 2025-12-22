
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
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode current = dummy.next;
        ListNode prev = dummy;
        while (current != null && current.next != null) {
            if (current.val != current.next.val) {
                prev = current;
                current = current.next;
            }
            else {
                while (current.next != null && current.val == current.next.val) {
                current.next = current.next.next;
                }
                prev.next = current.next;
                current = current.next;
            }
            
        }
        return dummy.next;
    }
}
