package example;

import java.util.*;
import java.util.stream.Stream;

public class DiffArrays {

    public static void fib(int n) {

        Stream.iterate(new int[]{0, 1}, arr -> new int[]{arr[1], arr[0] + arr[1]})
                .limit(n)
                .map(y -> y[0])
                .forEach(x -> System.out.println(x));
    }

    public static void main(String[] args) {
        fib(11);
    }
}
