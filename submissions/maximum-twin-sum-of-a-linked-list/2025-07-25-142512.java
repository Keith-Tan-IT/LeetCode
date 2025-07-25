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
    public int pairSum(ListNode head) {
        ListNode slow = head, fast = head, secondHalf, prev = null;

        while (fast != null) {
            fast = fast.next.next;
            secondHalf = slow.next;
            slow.next = prev;
            prev = slow; 
            slow = secondHalf;
            System.out.println(slow.val);
        }

        int max = 0;
        while (prev != null) {
            max = Math.max(slow.val + prev.val, max);
            prev = prev.next;
            slow = slow.next;
        }
        return max;
    }
}
// 0 1 2 - 3 4 5 