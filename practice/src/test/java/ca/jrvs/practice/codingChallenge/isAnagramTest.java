package ca.jrvs.practice.codingChallenge;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class isAnagramTest {
    isAnagram anagram;

    @Before
    public void setUp() {
        anagram = new isAnagram();
    }

    @Test
    public void test1() {
        boolean expected = true;
        boolean result = anagram.solution("iceman", "cinema");

        Assert.assertEquals(expected, result);
    }

    @Test
    public void test2() {
        boolean expected = false;
        boolean result = anagram.solution("iceman", "cinemas");

        Assert.assertEquals(expected, result);
    }
}
