package ca.jrvs.apps.practice;

import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LambdaStreamImp implements LambdaStreamExc{

    @Override
    public Stream<String> createStrStream(String... strings) {
        Stream<String> stream = Stream.of(strings);
        return stream;
    }

    @Override
    public Stream<String> toUpperCase(String... strings) {
        Stream<String> stream = Stream.of(strings);
        stream = stream.map(String::toUpperCase);
        return stream;
    }

    @Override
    public Stream<String> filter(Stream<String> stringStream, String pattern) {
        return stringStream.filter(str -> !Pattern.matches(pattern, str));
    }

    @Override
    public IntStream createIntStream(int[] arr) {
        return IntStream.of(arr);
    }

    @Override
    public <E> List<E> toList(Stream<E> stream) {
        return stream.collect(Collectors.toList());
    }

    @Override
    public List<Integer> toList(IntStream intStream) {
        return intStream.boxed().collect(Collectors.toList());
    }

    @Override
    public IntStream createIntStream(int start, int end) {
        return IntStream.range(start, end+1);
    }

    @Override
    public DoubleStream squareRootIntStream(IntStream intStream) {
        return intStream.mapToDouble(Math::sqrt);
    }

    @Override
    public IntStream getOdd(IntStream intStream) {
        return intStream.filter(n -> !((n % 2) == 0));
    }

    @Override
    public Consumer<String> getLambdaPrinter(String prefix, String suffix) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void printMessages(String[] messages, Consumer<String> printer) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void printOdd(IntStream intStream, Consumer<String> printer) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Stream<Integer> flatNestedInt(Stream<List<Integer>> ints) {
        Stream<Integer> intStream = ints.flatMap(
            list -> list.stream().map(n -> n*n)
        );
        return intStream;
    }
    
}
