package y2026.week3.day1;

import y2026.ListNode;

import java.math.BigInteger;

public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l1Reversed = reverseList(l1);
        ListNode l2Reversed = reverseList(l2);

        BigInteger result = toBigInt(l1Reversed).add(toBigInt(l2Reversed));

        return toReversedLinkedList(result);
    }

    public ListNode reverseList(ListNode head) {
        ListNode prev = null, node = head;
        while (node != null) {
            ListNode next = node.next;
            node.next = prev;
            prev = node;
            node = next;
        }

        return prev;
    }

    public BigInteger toBigInt(ListNode node) {
        String val = "";
        while (node != null) {
            val += node.val;
            node = node.next;
        }

        return new BigInteger(val);
    }

    public ListNode toReversedLinkedList(BigInteger val) {
        ListNode prev = null, node = null;
        for (char ele : String.valueOf(val).toCharArray()) {
            node = new ListNode(Character.getNumericValue(ele));
            node.next = prev;
            prev = node;
        }

        return node;
    }
}
