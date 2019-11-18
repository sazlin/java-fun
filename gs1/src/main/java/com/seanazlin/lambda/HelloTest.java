package com.seanazlin.lambda;

public class HelloTest {
    public String sayHello(String name, Hello hello){
        return hello.hello(name);
    }

    public static void main(String[] args){
        HelloTest hTest = new HelloTest();
        hTest.sayHello("hi there", new Hello() {
            @Override
            public String hello(String name) {
                return name;
            }
        });
        hTest.sayHello("hi there 2", (String name)->{
            System.out.println(name);
            return name;
        });
        Hello hello = anything->anything + "hello";
        System.out.println(hello.hello("hi"));
    }
}
