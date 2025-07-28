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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int sum = 0, carry = 0;
        ListNode result = new ListNode();
        ListNode dummy = result;
        while (l1 != null || l2 != null || carry != 0) {
            if (l1 != null && l2 != null) {
                sum = (l1.val + l2.val + carry) % 10;
                carry = (l1.val + l2.val + carry) / 10;
            }
            else if (l1 != null) {
                sum = (l1.val + carry) % 10;
                carry = (l1.val + carry) / 10;
            }
            else if (l2 != null ) {
                sum = (l2.val + carry) % 10;
                carry = (l2.val + carry) / 10;
            }
            else {
                sum = carry;
                carry = 0;
            }
            ListNode curr = new ListNode(sum);
            result.next = curr;
            result = result.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        return dummy.next;
    }
}
