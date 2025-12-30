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
                small = small.next = head;
            }
            else {
                big = big.next = head;
            }
            head = head.next;
        }
        small.next = bigger.next;
        big.next = null;
        return smaller.next;
    }
}