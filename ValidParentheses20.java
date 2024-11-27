package DSAA.Leetcode;

import java.util.HashMap;
import java.util.Stack;

public class ValidParentheses20 {

    /**
     * The idea is to push the left-side brackets into a stack as they appear when traversing the list.
     * For example, in {){[], first, {, {, and [ are pushed into the stack.
     * Then, pop elements from the stack and compare them with the right-side brackets
     * like ) and ] to check if they match.
     * This approach ensures the correct order of the right-side brackets.
     * <p>
     *
     * @param s
     * @return
     */


    public boolean isValid1(String s) {
        // Initialize a stack to store unpaired opening brackets
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            }

            // If it's closing brackets, check if top of the stack is a paired opening brackets
            else if (c == ')' || c == ']' || c == '}') {
                // If stack is empty means no paired opening brackets return false
                if (stack.isEmpty()) {
                    return false;
                }

                char top = stack.pop();
                if ((c == ')' && top != '(') ||
                        (c == '}' && top != '{') ||
                        (c == ']' && top != '[')) {
                    return false;
                }
            }
        }
        // If stack is empty, means all brackets are matched return true otherwise false
        return stack.isEmpty();

    }

    /**
     * METHOD 2 Using hashMap
     *
     */
    public boolean isValid2(String s) {
        if (s.isEmpty()) {
            return true;
        }
        Stack<Character> leftStack = new Stack<>();
        HashMap<Character, Character> openClose = new HashMap<Character, Character>();
        openClose.put('(', ')');
        openClose.put('{', '}');
        openClose.put('[', ']');
        for (char c : s.toCharArray()) {
            if (openClose.containsKey(c)) {
                leftStack.push(c); // opening bracket
            } else if (openClose.containsValue(c)) {  //closing bracket
                if (leftStack.isEmpty() || openClose.get(leftStack.pop()) != c) {
                    return false;
                }
            }

        }
        return leftStack.isEmpty();
    }

    public static void main(String[] args) {
        String s1 = "()[]{}";
        String s2 = "(]";
        String s3 = "([])";
        ValidParentheses20 v1 = new ValidParentheses20();
        System.out.println(v1.isValid1(s1));
        System.out.println(v1.isValid1(s2));
        System.out.println(v1.isValid1(s3));
        System.out.println(v1.isValid2(s1));
        System.out.println(v1.isValid2(s2));
        System.out.println(v1.isValid2(s3));


    }
}
