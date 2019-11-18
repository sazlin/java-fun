package com.seanazlin.factory;

import java.util.*;

public class CarComparator {
    public static void main(String[] args) {
        Comparator<Car> carComparator = (c1,c2)->c1.getCarNumber().compareTo(c2.getCarNumber());
        Car car1 = new Car("sedan", 1);
        Car car2 = new Car("sedan", 2);
        System.out.println(carComparator.compare(car1, car2)); // -1, 0, 1
        System.out.println(carComparator.reversed().compare(car1, car2)); // 1, 0, -1

        // TODO 1: Sort any Collection of Cars using a comparator
        List<Car> cars = Arrays.asList(
                new Car("van", 1),
                new Car("van", 6),
                new Car("van", 4),
                new Car("van", 2),
                new Car("van", 5),
                new Car("van", 3)
        );

        System.out.println(cars);
        cars.sort(carComparator);
        System.out.println(cars);
    }
}
