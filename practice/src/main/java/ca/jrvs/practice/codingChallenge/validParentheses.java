package ca.jrvs.practice.codingChallenge;

import java.util.Stack;

public class validParentheses {
    public boolean solution(String s) {
        if (s.length() < 2) {
            return false;
        }
        
        Stack<Character> stack = new Stack<Character>();
        
        for (int i=0; i<s.length(); i++) {
            Character bracket = Character.valueOf(s.charAt(i));
            System.out.println(bracket);
            
                
            switch(bracket) {
                case('('):
                case('{'):
                case('['):
                    stack.push(bracket);
                    break;
                    
                case (')'):
                    if (stack.empty()) {
                        return false;
                    } else if (stack.peek().equals('(')) {
                        stack.pop();
                    } else {
                        return false;
                    }
                    break;

                case ('}'):
                    if (stack.empty()) {
                        return false;
                    } else if (stack.peek().equals('{')) {
                        stack.pop();
                    } else {
                        return false;
                    }
                    break;
                    
                case (']'):
                    if (stack.empty()) {
                        return false;
                    } else if (stack.peek().equals('[')) {
                        stack.pop();
                    } else {
                        return false;
                    }
                    break;
            }
        }
        if (stack.empty()) {
            return true;
        } else {
            return false;
        }
    }
}
