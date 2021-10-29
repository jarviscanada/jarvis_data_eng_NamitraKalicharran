package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class twoSumTest {
    twoSum tSum;

    @Before
    public void setUp() {
        tSum = new twoSum();
    }

    @Test
    public void test1() {
        int[] expected = {0, 1};
        Arrays.sort(expected);

        int[] nums = {2,7,11,15};
        int target = 9;
        int[] result = tSum.solution(nums, target);
        Arrays.sort(result);

        Assert.assertArrayEquals(expected, result);
    }
}
