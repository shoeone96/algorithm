package y2026.week1.day2;

import y2026.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution3 {
    public boolean isPalindrome(ListNode head) {
        Deque<Integer> deque = new ArrayDeque<>();
        while (head != null) {
            deque.add(head.val);
            head = head.next;
        }

        while (!deque.isEmpty() && deque.size() > 1) {
            if (deque.pollFirst() != deque.pollLast()) {
                return false;
            }
        }

        return true;
    }
}
