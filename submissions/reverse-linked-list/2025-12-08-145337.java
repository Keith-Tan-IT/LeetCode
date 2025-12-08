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
    public ListNode reverseList(ListNode head) {
        ListNode temp = null;
        ListNode result = null;
        if (head == null) {
            return null;
        }
        while(head.next != null) {
            temp = head.next;
            head.next = result;
            result = head;
            head = temp; 
        }
        head.next = result;
        return head;
    }
}