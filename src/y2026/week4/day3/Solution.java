package y2026.week4.day3;

import y2026.ListNode;

public class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null) return head;
        ListNode root = new ListNode(0);
        root.next = head;
        ListNode start = root;
        for (int i = 0; i < left - 1; i++) {
            start = start.next;
        }

        ListNode end = start.next;

        for (int i = 0; i < right - left; i++) {
            ListNode tmp = start.next;
            start.next = end.next;
            end.next = end.next.next;
            start.next.next = tmp;
        }

        return root.next;
    }
}
