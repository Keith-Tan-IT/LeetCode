/**
 * TIP
 * Problem: 150. Evaluate Reverse Polish Notation
 *
 * Goal:
 * - Evaluate an expression given in Reverse Polish Notation.
 * - Each token is either an integer or an operator: + - * /
 * - Division truncates toward zero.
 *
 * -------------------------------------------------------------
 * Key Idea:
 * Use a Stack to simulate postfix evaluation.
 *
 * Trick:
 * - When seeing a number → push to stack.
 * - When seeing an operator → pop top 2 numbers, apply op, push result.
 *
 * Why this works:
 * - RPN is designed for stack-based evaluation.
 * - No parentheses and no operator precedence needed.
 *
 * -------------------------------------------------------------
 * Algorithm:
 * For each token:
 *   - If operator:
 *        b = pop()
 *        a = pop()
 *        push(a <op> b)
 *   - Else (number):
 *        push(Integer.parseInt(token))
 *
 * After processing all tokens:
 *   return stack.pop()
 *
 * -------------------------------------------------------------
 * Example:
 * tokens = ["2","1","+","3","*"]
 *
 * Stack process:
 * push 2
 * push 1
 * + → pop 1 and 2 → push 3
 * push 3
 * * → pop 3 and 3 → push 9
 *
 * Answer = 9
 *
 * -------------------------------------------------------------
 * Complexity:
 * Time  = O(n)
 * Space = O(n) stack
 *
 * -------------------------------------------------------------
 * Takeaways:
 * - Stack fits postfix notation perfectly.
 * - Order matters: a op b → a = second pop, b = first pop.
 * - No need for infix conversion or operator precedence.
 */
class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            switch(token){
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-": {
                    int a = stack.pop();
                    int b = stack.pop();
                    stack.push(b - a);
                    break;
                }
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "/": {
                    int a = stack.pop();
                    int b = stack.pop();
                    stack.push(b / a);
                    break;
                }
                default:
                    stack.push(Integer.valueOf(token));
            }
        }
        return stack.pop();
    }
}