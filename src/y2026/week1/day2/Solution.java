package y2026.week1.day2;

import y2026.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Solution {
    public boolean isPalindrome(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }

        int size = list.size();

        for (int i = 0; i < size / 2; i++) {
            if (!Objects.equals(list.get(i), list.get(list.size() - 1 - i))) {
                return false;
            }
        }

        return true;
    }
}

