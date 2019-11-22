package com.seanazlin.java8;

public class Main {
    public static void main(String[] args) {
        // anonymous class example
        SampleInterface sampleInterface = new SampleInterface() {
            @Override
            public double calSample() {
                return 0;
            }
        };
        System.out.println(sampleInterface.getPie());

        // simple class accessed by its interface
        SampleInterface sampleInterfaceNew = new SampleClass();
        System.out.println(sampleInterfaceNew.getPie());

        // Custom Functional Interface / SAM + Lambda expression example
        SampleFunctionalInterface si = ()->System.out.println("Say hello");
        si.sampleHello();

//        SampleFT<String,Integer> sf = (data)->Integer.valueOf(data);
//        System.out.println(sf.perform("100"));


        SampleFT<String,Integer> sf = (data)->{
            System.out.println(data + " is data");
            return Integer.valueOf(data);
        };
        System.out.println(sf.perform("100"));
    }
}
