/*
 * @lc app=leetcode id=2 lang=java
 *
 * [2] Add Two Numbers
 *
 * https://leetcode.com/problems/add-two-numbers/description/
 *
 * algorithms
 * Medium (30.58%)
 * Total Accepted:    770.6K
 * Total Submissions: 2.5M
 * Testcase Example:  '[2,4,3]\n[5,6,4]'
 *
 * You are given two non-empty linked lists representing two non-negative
 * integers. The digits are stored in reverse order and each of their nodes
 * contain a single digit. Add the two numbers and return it as a linked list.
 * 
 * You may assume the two numbers do not contain any leading zero, except the
 * number 0 itself.
 * 
 * Example:
 * 
 * 
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 * 
 * 
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return addWithRemain(l1, l2, 0);
    }

    private ListNode addWithRemain(ListNode l1, ListNode l2, int remain) {
        if (l1 == null && l2 == null) {
            return remain == 0
                ? null
                : new ListNode(remain);
        }

        if (l1 == null) {
            int sum = l2.val + remain;
            ListNode result = new ListNode(sum > 9 ? sum - 10 : sum);
            result.next = addWithRemain(null, l2.next, sum > 9 ? 1 : 0);
            return result;
        }

        if (l2 == null) {
            int sum = l1.val + remain;
            ListNode result = new ListNode(sum > 9 ? sum - 10 : sum);
            result.next = addWithRemain(l1.next, null, sum > 9 ? 1 : 0);
            return result;
        }

        int sum = l1.val + l2.val + remain;
        ListNode result = new ListNode(sum > 9 ? sum - 10 : sum);
        result.next = addWithRemain(l1.next, l2.next, sum > 9 ? 1 : 0);
        return result;
    }
}

