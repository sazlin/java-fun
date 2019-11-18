package com.seanazlin.dates;

public class SafeEg {
    public static void main(String[] args) {
        System.out.println(1000000 * 1000000); // not safe
        try {
            System.out.println(Math.multiplyExact(1000000, 1000000)); // raise runtime exception
        } catch (ArithmeticException e){
            e.printStackTrace();
        }

        System.out.println(Long.MAX_VALUE);
        System.out.println((int)Long.MAX_VALUE); // note safe
        try {
            System.out.println(Math.toIntExact(Long.MAX_VALUE));
        } catch (ArithmeticException e){
            e.printStackTrace();
        }

        System.out.println(Math.nextDown(100)); // 99.99999
        System.out.println(Math.nextDown(100000)); // 99999.99
    }
}
