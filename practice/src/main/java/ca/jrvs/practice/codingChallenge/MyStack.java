package ca.jrvs.practice.codingChallenge;

import java.util.LinkedList;
import java.util.Queue;

// Ticket: https://www.notion.so/jarvisdev/Implement-Stack-using-Queue-2bc0b33d3c734c89b6a780cf09e32672
public class MyStack {
    public int size;
    public Queue<Integer> queue;

    // O(1) construction
    public MyStack() {
        size = 0;
        queue = new LinkedList<Integer>();
        
    }
    
    // O(1) operation
    public void push(int x) {
        queue.add(x);
        size++;
    }
    
    // O(n) operation
    public int pop() {
        for (int i=1; i<size; i++) {
            int val = queue.remove();
            queue.add(val);
        }
        
        size--;
        return queue.remove();
    }
    
    // O(n) operation
    public int top() {
        int val;
        for (int i=1; i<size; i++) {
            val = queue.remove();
            queue.add(val);
        }
        
        val = queue.remove();
        queue.add(val);
        return val;
    }
    
    // O(1) operation
    public boolean empty() {
        return size==0;
    }
}
