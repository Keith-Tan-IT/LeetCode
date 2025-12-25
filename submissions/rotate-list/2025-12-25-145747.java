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
    public ListNode rotateRight(ListNode head, int k) {
        if (k == 0 || head == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int length = 0;
        while (dummy.next != null) {
            dummy = dummy.next;
            length++;
        }
        dummy.next = head;
        for (int i = 1; i < length - k % length; i++) {
            head = head.next;
        }
        dummy = head.next;
        head.next = null;
        return dummy;
    }
}