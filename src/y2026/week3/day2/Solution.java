package y2026.week3.day2;

import y2026.ListNode;

public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode node = new ListNode(0);
        ListNode root = node;

        int sum, carry = 0, remainder;

        while (l1 != null || l2 != null || carry != 0) {
            sum = 0;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }

            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            remainder = (sum + carry) % 10;
            carry = (sum + carry) / 10;
            node.next = new ListNode(remainder);
            node = node.next;
        }

        return root.next;
    }
}
