package com.seanazlin.fi;

import java.util.function.Predicate;

public class PredicateEg {
    public static void main(String[] args) {
        Predicate<String> startWithA = (x)->x.startsWith("A");
        System.out.println(startWithA.test("Apple"));
        System.out.println(startWithA.test("Orange"));

        System.out.println(startWithA.negate().test("Apple"));
        Predicate<String> checkIfEmpty = (x)->x.isEmpty();
        Predicate<String> checkIfEmpty2 = String::isEmpty;
        Predicate<String> checkIfNotEmpty = checkIfEmpty.negate();
        System.out.println(checkIfEmpty.test("abc"));
        System.out.println(checkIfNotEmpty.test("abc"));
        System.out.println(checkIfNotEmpty.test(""));

        Predicate<Integer> greaterThanHundred = (x)->x > 100;
        System.out.println(greaterThanHundred.test(150));
        System.out.println(greaterThanHundred.test(50));

        Predicate<String> endsWithA = (x)->x.endsWith("A");
        Predicate<String> startsAndEndsWithA = startWithA.and(endsWithA);
        System.out.println(startsAndEndsWithA.test("ABA"));
        System.out.println(startsAndEndsWithA.test("ABC"));
    }
}
