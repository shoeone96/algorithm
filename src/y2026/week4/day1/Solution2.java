package y2026.week4.day1;

import y2026.ListNode;

public class Solution2 {
    public ListNode swapPairs(ListNode head) {
        if (head != null && head.next != null) {
            ListNode temp = head.next;
            head.next = swapPairs(temp.next);
            temp.next = head;

            return temp;
        }

        return head;
    }
}
