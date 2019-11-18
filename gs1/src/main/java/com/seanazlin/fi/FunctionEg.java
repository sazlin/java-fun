package com.seanazlin.fi;

import java.util.function.Function;

public class FunctionEg {
    public static void main(String[] args) {
        //Predicate always returns boolean
        //Function can return any object
        Function<String,String> toUpperCase = (x)->x.toUpperCase();
        Function<String,String> substring = (x)->x.substring(0,2);

        System.out.println(toUpperCase.apply("abc"));
        System.out.println(toUpperCase.andThen(substring).apply("abc"));
    }
}
