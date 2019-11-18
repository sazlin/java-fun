package com.seanazlin.fi;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class RegExpPredicatesEg {
    public static void main(String[] args) {
        Predicate<String> filter = Pattern.compile("^(.+)@gmail.com").asPredicate();
        List<String> emails = Arrays.asList(
          "sean@gmail.com",
          "bill123@yahoo.com",
          "Cam998@gmail.com"
        );
        emails.stream().filter(filter).forEach(System.out::println);
    }
}
