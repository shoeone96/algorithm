package y2026.week2.day3;

import y2026.ListNode;

public class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode before = null;
        ListNode current = head;
        while (current != null) {
            ListNode next = current.next;
            current.next = before;
            before = current;
            current = next;
        }
        return before;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.reverseList(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))));
    }
}
