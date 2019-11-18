package com.seanazlin.factory;

public interface CarFactory<C extends Car> {
    C createObject(String type, int number);
}
