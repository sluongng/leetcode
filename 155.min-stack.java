import java.util.Optional;

/*
 * @lc app=leetcode id=155 lang=java
 *
 * [155] Min Stack
 *
 * https://leetcode.com/problems/min-stack/description/
 *
 * algorithms
 * Easy (36.26%)
 * Total Accepted:    283.6K
 * Total Submissions: 781.5K
 * Testcase Example:  '["MinStack","push","push","push","getMin","pop","top","getMin"]\n[[],[-2],[0],[-3],[],[],[],[]]'
 *
 * 
 * Design a stack that supports push, pop, top, and retrieving the minimum
 * element in constant time.
 * 
 * 
 * push(x) -- Push element x onto stack.
 * 
 * 
 * pop() -- Removes the element on top of the stack.
 * 
 * 
 * top() -- Get the top element.
 * 
 * 
 * getMin() -- Retrieve the minimum element in the stack.
 * 
 * 
 * 
 * 
 * Example:
 * 
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> Returns -3.
 * minStack.pop();
 * minStack.top();      --> Returns 0.
 * minStack.getMin();   --> Returns -2.
 * 
 * 
 */
class MinStack {

    private Node head;

    /** initialize your data structure here. */
    public MinStack() {
        head = null;
    }
    
    public void push(int x) {
        head = Optional.ofNullable(head)
            .map(head -> new Node(x, x > head.min ? head.min : x, head))
            .orElse(new Node(x, x));
    }
    
    public void pop() {
        head = head.next;
    }
    
    public int top() {
        return head.value;
    }
    
    public int getMin() {
        return head.min;
    }

    private class Node {
        int value;
        int min;
        Node next;

        public Node(int value, int min) {
            this(value, min, null);
        }

        public Node(int value, int min, Node tail) {
            this.value = value;
            this.min = min;
            this.next = tail;
        }

    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */

