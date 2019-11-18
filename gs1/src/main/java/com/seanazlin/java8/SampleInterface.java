package com.seanazlin.java8;

public interface SampleInterface {
    double calSample();
    default double getPie(){
        return 3.14;
    };
}
