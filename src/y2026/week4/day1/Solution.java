package y2026.week4.day1;

import y2026.ListNode;

public class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode node = new ListNode(0);
        ListNode root = node;
        root.next = head;

        while (node.next != null && node.next.next != null) {
            ListNode next = node.next.next;
            ListNode now = node.next;
            now.next = next.next;
            node.next = next;
            next.next = now;
            node = node.next.next;
        }

        return root.next;
    }
}
