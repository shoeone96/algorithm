package y2026.week4.day4;

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

        return null;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.reverseBetween(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))), 2, 4);
        
    }
}
