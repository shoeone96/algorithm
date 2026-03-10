package y2026.week1.day2;

import y2026.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution2 {
    public boolean isPalindrome(ListNode head) {
        Deque<Integer> deque = new ArrayDeque<>();
        ListNode first = head;
        while (first != null) {
            deque.add(first.val);
            first = first.next;
        }

        while (!deque.isEmpty()) {
            if (deque.pollLast() != head.val) {
                return false;
            }
            head = head.next;
        }

        return true;
    }
}
