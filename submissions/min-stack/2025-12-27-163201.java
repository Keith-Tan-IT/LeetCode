/**
 * TIP
 * Problem: 155. Min Stack
 *
 * Goal:
 * - Design a stack that supports:
 *      push(x), pop(), top(), getMin()
 *   All in O(1) time.
 *
 * -------------------------------------------------------------
 * Key Idea:
 * Use a stack that stores *both* the value and the current minimum at that time.
 *
 * Trick:
 * - Every push stores: (value, currentMin)
 * - currentMin = min(value, previousMin)
 * - This avoids rescanning or recomputing minimum.
 *
 * -------------------------------------------------------------
 * Why this works:
 * - Each node remembers the minimum of the stack *at that moment*.
 * - When popping, the previous minimum naturally becomes available.
 * - Clean O(1) for all operations.
 *
 * -------------------------------------------------------------
 * Example:
 * push 5 → store (5,5)
 * push 3 → store (3,3)
 * push 7 → store (7,3)
 *
 * pop → removes (7,3)
 * getMin() → 3
 *
 * -------------------------------------------------------------
 * Complexity:
 * Time  = O(1) for all operations
 * Space = O(n) for storing paired values
 *
 * -------------------------------------------------------------
 * Takeaways:
 * - Store the running minimum *alongside* each value.
 * - Avoid recomputing min after pop — let each stack layer hold the info.
 * - Classic "augmented stack" technique.
 */

class MinStack {
    Stack<Integer> stack;
    Stack<Integer> minStack;
    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }
    
    public void push(int val) {
        stack.push(val);
        if (minStack.isEmpty()) {
            minStack.push(val);
        }
        else {
            minStack.push(Math.min(val, minStack.peek()));
        }
    }
    
    public void pop() {
        stack.pop();
        minStack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */