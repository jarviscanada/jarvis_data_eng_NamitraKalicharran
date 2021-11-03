package ca.jrvs.apps.practice;
import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;

import java.util.List;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.Arrays;
import java.util.Iterator;

public class LambdaStreamTest {
    LambdaStreamImp lambdaStream;

    public void compareStreams(Stream<?> stream1, Stream<?> stream2) {
        Iterator<?> iter1 = stream1.iterator(), iter2 = stream2.iterator();

        while (iter1.hasNext() && iter2.hasNext()) {
            Assert.assertEquals(iter1.next(), iter2.next());
        }
    }

    public void compareStreams(IntStream stream1, IntStream stream2) {
        Iterator<?> iter1 = stream1.iterator(), iter2 = stream2.iterator();

        while (iter1.hasNext() && iter2.hasNext()) {
            Assert.assertEquals(iter1.next(), iter2.next());
        }
    }

    public void compareStreams(DoubleStream stream1, DoubleStream stream2) {
        Iterator<?> iter1 = stream1.iterator(), iter2 = stream2.iterator();

        while (iter1.hasNext() && iter2.hasNext()) {
            Assert.assertEquals(iter1.next(), iter2.next());
        }
    }

    @Before
    public void setUp() throws Exception {
        lambdaStream = new LambdaStreamImp();
    }

    @Test
    public void testStrStream() {
        Stream<String> expected = Stream.of("Hello", "World");
        Stream<String> testStream = lambdaStream.createStrStream("Hello", "World");
        compareStreams(expected, testStream);
    }

    @Test
    public void testToUpper() {
        Stream<String> expected = Stream.of("HELLO", "WORLD");
        Stream<String> testStream = lambdaStream.toUpperCase("Hello", "World");
        compareStreams(expected, testStream);
    }

    @Test
    public void testFilter() {
        Stream<String> expected = Stream.of("my", "name");
        Stream<String> testStream = lambdaStream.filter(
            Stream.of("Hello", "World", "my", "name"), ".*o.*"
        );
        compareStreams(expected, testStream);
    }

    @Test
    public void testToList() {
        List<String> expected = Arrays.asList("a", "b", "c", "d", "e", "f");
        List<String> testList = lambdaStream.toList(Stream.of("a", "b", "c", "d", "e", "f"));
        Assert.assertEquals(expected, testList);
    }

    @Test
    public void testCreateIntStream() {
        IntStream expected = IntStream.of(1, 2, 3, 4, 5);
        IntStream testStream = lambdaStream.createIntStream(1, 5);
        compareStreams(expected, testStream);
    }

    @Test
    public void testCreateIntStream2() {
        int[] arr = {1, 2, 3, 4, 5};
        IntStream expected = IntStream.of(1, 2, 3, 4, 5);
        IntStream testStream = lambdaStream.createIntStream(arr);
        compareStreams(expected, testStream);
    }

    @Test
    public void testSquareRoot() {
        DoubleStream expected = DoubleStream.of(1, 2, 3, 4, 5);
        DoubleStream testStream = lambdaStream.squareRootIntStream(IntStream.of(1*1, 2*2, 3*3, 4*4, 5*5));
        compareStreams(expected, testStream);
    }

    @Test
    public void testGetOdd() {
        IntStream expected = IntStream.of(1, 3, 5);
        IntStream testStream = lambdaStream.getOdd(IntStream.of(1, 2, 3, 4, 5));
        compareStreams(expected, testStream);
    }

    @Test
    public void testFlatNestedInt() {
        Stream<List<Integer>> testStream = Stream.of(
            Arrays.asList(1, 2),
            Arrays.asList(3, 4),
            Arrays.asList(5, 6)
        );
        
        Stream<Integer> output = lambdaStream.flatNestedInt(testStream);
        Stream<Integer> expected = Stream.of(1, 4, 9, 16, 25, 36);
        compareStreams(expected, output);
    }
}
