package y2026.week3.day3;

import y2026.ListNode;

public class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode node = new ListNode(0);
        ListNode root = node;
        node.next = head;
        while (node.next != null && node.next.next != null) {
            ListNode a = node.next;
            ListNode b = node.next.next;
            a.next = b.next;
            node.next = b;
            node.next.next = a;
            node = node.next.next;
        }

        return root.next;
    }
}
