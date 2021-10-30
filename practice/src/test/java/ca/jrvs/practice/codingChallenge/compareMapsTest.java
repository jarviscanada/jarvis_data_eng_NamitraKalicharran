package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class compareMapsTest {
    compareMaps compare;

    @Before
    public void setUp() {
        compare = new compareMaps();
    }

    @Test
    public void test1() {
        HashMap<String, Boolean> map1 = new HashMap<String, Boolean>();
        map1.put("foo", true);
        HashMap<String, Boolean> map2 = new HashMap<String, Boolean>();
        map2.put("foo", true);

        Assert.assertEquals(true, compare.solution(map1, map2));
    }

    @Test
    public void test2() {
        HashMap<String, Boolean> map1 = new HashMap<String, Boolean>();
        map1.put("foo", true);
        map1.put("foo1", false);
        HashMap<String, Boolean> map2 = new HashMap<String, Boolean>();
        map2.put("foo", true);

        Assert.assertEquals(false, compare.solution(map1, map2));
    }

    @Test
    public void test3() {
        HashMap<String, Boolean> map1 = new HashMap<String, Boolean>();
        map1.put("foo", true);
        map1.put("foo1", false);
        HashMap<String, Boolean> map2 = new HashMap<String, Boolean>();
        map2.put("foo", true);
        map2.put("foo1", true);

        Assert.assertEquals(false, compare.solution(map1, map2));
    }

    @Test
    public void test4() {
        HashMap<String, Boolean> map1 = new HashMap<String, Boolean>();
        map1.put("foo", true);
        map1.put("foo1", false);
        HashMap<String, Boolean> map2 = new HashMap<String, Boolean>();
        map2.put("foo", true);
        map2.put("foo1", false);

        Assert.assertEquals(true, compare.solution(map1, map2));
    }
}
