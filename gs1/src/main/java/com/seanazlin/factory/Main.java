package com.seanazlin.factory;

public class Main {
    public static void main(String[] args) {
        CarFactory<Car> factory = Car::new;
        Car aCar = factory.createObject("sedan", 123);
        System.out.println(aCar);
    }
}
