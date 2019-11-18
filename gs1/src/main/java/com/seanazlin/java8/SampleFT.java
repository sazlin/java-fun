package com.seanazlin.java8;

// metaprogramming example
@FunctionalInterface
public interface SampleFT<F,T> {
    T perform(F item);
}
