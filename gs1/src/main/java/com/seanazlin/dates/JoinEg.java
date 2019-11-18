package com.seanazlin.dates;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class JoinEg {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("abc1", "xyz2");
        System.out.println(String.join("--", strings));

        StringJoiner joiner = new StringJoiner(",","{","}");
        joiner.add("welcome").add("to").add("java");
        System.out.println(joiner);

        String output = strings.stream().collect(Collectors.joining(",","{","}"));
        System.out.println(output);
    }
}
