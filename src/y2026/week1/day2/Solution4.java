package y2026.week1.day2;

import y2026.ListNode;

public class Solution4 {
    public boolean isPalindrome(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        if (fast != null) {
            slow = slow.next;
        }

        ListNode reverse = null;
        while (slow != null) {
            ListNode next = slow.next;
            slow.next = reverse;
            reverse = slow;
            slow = next;
        }

        while (reverse != null) {
            if (reverse.val != head.val) {
                return false;
            }

            reverse = reverse.next;
            head = head.next;
        }

        return true;
    }
}
