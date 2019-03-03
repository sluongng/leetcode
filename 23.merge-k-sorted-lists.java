import java.util.List;
import java.util.LinkedList;

/*
 * @lc app=leetcode id=23 lang=java
 *
 * [23] Merge k Sorted Lists
 *
 * https://leetcode.com/problems/merge-k-sorted-lists/description/
 *
 * algorithms
 * Hard (32.96%)
 * Total Accepted:    342.6K
 * Total Submissions: 1M
 * Testcase Example:  '[[1,4,5],[1,3,4],[2,6]]'
 *
 * Merge k sorted linked lists and return it as one sorted list. Analyze and
 * describe its complexity.
 * 
 * Example:
 * 
 * 
 * Input:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * Output: 1->1->2->3->4->4->5->6
 * 
 * 
 */
/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode(int x) { val = x; } }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {

        if (lists.length == 0) {
            return null;
        }

        int min = Integer.MAX_VALUE;
        int minIndex = Integer.MAX_VALUE;

        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null && lists[i].val < min) {
                min = lists[i].val;
                minIndex = i;
            }
        }

        if(minIndex == Integer.MAX_VALUE) {
            return null;
        }
        ListNode result = new ListNode(min);
        if (lists[minIndex] != null) {
            lists[minIndex] = lists[minIndex].next;
            result.next = mergeKLists(lists);
        } else {
            List<ListNode> newList = new LinkedList<>();
            int i = 0;
            while(i < lists.length) {
                if (i != minIndex) {
                    newList.add(lists[i]);
                }
                i++;
            }
            ListNode[] newArr = new ListNode[newList.size()];
            newList.toArray(newArr);
            result.next = mergeKLists(newArr);
        }

        return result;
    }

    // TESTING SECTION
    // TODO: comment the below for leetcode submission

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public void should_run() {
        ListNode list1 = new ListNode(1);
        ListNode list2 = new ListNode(4);
        list1.next = list2;

        ListNode list3 = new ListNode(2);
        ListNode list4 = new ListNode(3);
        list3.next = list4;

        Solution solution = new Solution();

        ListNode result = solution.mergeKLists(new ListNode[] { list1, list3 });

        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }

    // Run Command:
    // javac 23.merge-k-sorted-lists.java && java -cp . Solution
    public static void main(String[] args) {
        Solution test = new Solution();
        test.should_run();
    }
}
