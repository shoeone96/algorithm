package y2026.week2.day1;

import y2026.ListNode;

public class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode answer = new ListNode();
        ListNode start = answer;
        while (list1 != null || list2 != null) {
            if (list1 == null) {
                while (list2 != null) {
                    answer.next = list2;
                    answer = answer.next;
                    list2 = list2.next;
                }
                break;
            }

            if (list2 == null) {
                while (list1 != null) {
                    answer.next = list1;
                    answer = answer.next;
                    list1 = list1.next;
                }
                break;
            }

            if (list1.val <= list2.val) {
                answer.next = list1;
                answer = answer.next;
                list1 = list1.next;
            } else {
                answer.next = list2;
                answer = answer.next;
                list2 = list2.next;
            }
        }

        return start.next;
    }
}
