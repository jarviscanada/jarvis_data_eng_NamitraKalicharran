package ca.jrvs.practice.codingChallenge;

import java.util.LinkedList;
import java.util.Queue;

public class MyStack {
    public int size;
    public Queue<Integer> queue;

    public MyStack() {
        size = 0;
        queue = new LinkedList<Integer>();
        
    }
    
    public void push(int x) {
        queue.add(x);
        size++;
    }
    
    public int pop() {
        for (int i=1; i<size; i++) {
            int val = queue.remove();
            queue.add(val);
        }
        
        size--;
        return queue.remove();
    }
    
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
    
    public boolean empty() {
        return size==0;
    }
}
