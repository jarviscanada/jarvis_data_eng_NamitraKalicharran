package ca.jrvs.practice.codingChallenge;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class fibonacciTest {
    fibonacci fib;

    @Before
    public void setup() throws Exception {
        fib = new fibonacci();
    }

    @Test
    public void test1() {
        int expected = 0;

        Assert.assertEquals(expected, fib.solution(0));
    }

    @Test
    public void test2() {
        int expected = 1;

        Assert.assertEquals(expected, fib.solution(1));
    }

    @Test
    public void test3() {
        int expected = 6765;
        Assert.assertEquals(expected, fib.solution(20));
    }
}
