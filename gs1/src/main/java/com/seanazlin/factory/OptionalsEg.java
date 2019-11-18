package com.seanazlin.factory;

import java.util.Optional;
import java.util.function.BiPredicate;

public class OptionalsEg {
    public static void main(String[] args) {
        // Always try to return Optional instead of Object or null
        Optional<String> nullableString = Optional.of("xyz");
        Optional<String> emptyString = Optional.ofNullable(null);
        System.out.println(nullableString.isPresent());
        System.out.println(nullableString.get());
        System.out.println(emptyString.orElse("default"));

        BiPredicate<String,String> biPredicate = (x,y)->x.equals(y);
    }
}
