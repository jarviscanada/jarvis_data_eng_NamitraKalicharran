package ca.jrvs.practice.codingChallenge;

import java.util.ArrayList;

// ticket: https://www.notion.so/jarvisdev/Fibonacci-Number-Climbing-Stairs-e221f2686afa4d1dac2354b4ee6eebaa
public class fibonacci {
    /**
     * Big O: O(n)
     * Justification: This algorithm only makes n number of calculations and
     *      makes an array of size n. The final retrieval is O(1) so it doesn't add to
     *      the time complexity.
     */
    public int solution(int n) {
        ArrayList<Integer> fib = new ArrayList<Integer>();
        fib.add(0);
        fib.add(1);
        
        if (n == 0 || n == 1) {
            return fib.get(n);
        }
        
        int i = 1;
        while (i < n) {
            fib.add(fib.get(i-1) + fib.get(i));
            i++;
        }
        System.out.println(fib.toString());
        return fib.get(fib.size() - 1);
    }
}
