package com.seanazlin.directories;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BoxEg {
    public static void main(String[] args) {
        // boxed() will box all the prims in the stream
        List<Integer> integers = IntStream.of(1,2,3).boxed().collect(Collectors.toList());
    }
}
