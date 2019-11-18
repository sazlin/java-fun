package com.seanazlin.java8;

public class Main {
    public static void main(String[] args) {
        SampleInterface sampleInterface = new SampleInterface() {
            @Override
            public double calSample() {
                return 0;
            }
        };
        SampleInterface sampleInterfaceNew = new SampleClass();
        sampleInterfaceNew.getPie();

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
