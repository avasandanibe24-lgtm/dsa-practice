import java.util.Stack;

class Solution {
    public String reverseParentheses(String s) {
        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {
            if (ch == ')') {
                // Collect characters until '('
                StringBuilder temp = new StringBuilder();
                while (!stack.isEmpty() && stack.peek() != '(') {
                    temp.append(stack.pop());
                }
                stack.pop(); // remove '('

                // Push reversed substring back
                for (char c : temp.toString().toCharArray()) {
                    stack.push(c);
                }
            } else {
                stack.push(ch);
            }
        }

        // Build final string from stack
        StringBuilder result = new StringBuilder();
        for (char c : stack) {
            result.append(c);
        }
        return result.toString();
    }

  
}
