package com.seanazlin.fi;

import com.seanazlin.factory.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

// TODO 1: read from a text file, check to see if a line is empty, whether line starts with A, convert the lines to upper case,
// output results to std out to line by line
// use functional interfaces

// TODO 2: read from a txt file, if line is upper case then convert to lower case, and vice versa, and write back to a different file
public class SupplierEg {
    public static void main(String[] args) {
        // used in for loops to create objects
        Supplier<String> stringSupplier = String::new;
        System.out.println(stringSupplier.get());

        // Consumer will perform an operation on a single object argument, also useful for forEach
        Consumer<Car> xyz = (car)->System.out.println(car);
        Car car = new Car("van", 2);
        Car car2 = new Car("truck", 3);
        xyz.accept(car);

        List<Car> cars = new ArrayList<>();
        cars.add(car);
        cars.add(car2);

        cars.forEach(xyz);
        cars.forEach(System.out::println);
    }
}
