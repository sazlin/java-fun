package com.seanazlin.fi;

import java.util.function.*;

// TODO 2: Create an example of each of the java.util.function interfaces
// https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html
public class FunctionalInterfacePkgExamples {

    //    Represents an operation that accepts two input arguments and returns no result.
    BiConsumer<String,String> biConsumer = (x,y)-> System.out.println(x+y);

    //    Represents a function that accepts two arguments and produces a result.
    BiFunction<String,String,String> biFunction = (x,y)->x+y;

    //    Represents an operation upon two operands of the same type, producing a result of the same type as the operands.
    BinaryOperator<String> binaryOperator = (x,y)->x+y;

    //    Represents a predicate (boolean-valued function) of two arguments.
    BiPredicate<String,String> biPredicate = (x,y)->x.equals(y);
    BiPredicate<String,String> biPredicate2 = String::equals;

    //    Represents a supplier of boolean-valued results.
    BooleanSupplier booleanSupplier = ()->Math.random() > 0.5;

    //    Represents an operation that accepts a single input argument and returns no result.
    Consumer<String> consumer = (x)-> System.out.println(x);
    Consumer<String> consumer2 = System.out::println;

    //    Represents an operation upon two double-valued operands and producing a double-valued result.
    DoubleBinaryOperator doubleBinaryOperator = (x,y)->x-y;

    //    Represents an operation that accepts a single double-valued argument and returns no result.
    DoubleConsumer doubleConsumer = (x) -> System.out.println(x);
    DoubleConsumer doubleConsumer2 = System.out::println;

    //    Represents a function that accepts a double-valued argument and produces a result.
    DoubleFunction<String> doubleFunction = String::valueOf;

    //    Represents a predicate (boolean-valued function) of one double-valued argument.
    DoublePredicate doublePredicate = (x)->x>0.5;

    //    Represents a supplier of double-valued results.
    DoubleSupplier doubleSupplier = ()->Math.random();
    DoubleSupplier doubleSupplier2 = Math::random;

    //    Represents a function that accepts a double-valued argument and produces an int-valued result.
    DoubleToIntFunction doubleToIntFunction = (x)->(int)x;

    //    Represents a function that accepts a double-valued argument and produces a long-valued result.
    DoubleToLongFunction doubleToLongFunction = (x)->(long)x;

    //    Represents an operation on a single double-valued operand that produces a double-valued result.
    DoubleUnaryOperator doubleUnaryOperator = (x)->x * 2.0;

    //    Represents a function that accepts one argument and produces a result.
    Function<String,String> function = (x)->x + "a suffix";
    //    Represents an operation upon two int-valued operands and producing an int-valued result.
    IntBinaryOperator intBinaryOperator = (x,y)->x + y;
    IntBinaryOperator intBinaryOperator2 = Integer::sum;

    //    Represents an operation that accepts a single int-valued argument and returns no result.
    IntConsumer intConsumer = (x)-> System.out.println(x);
    IntConsumer intConsumer2 = System.out::println;

    //    Represents a function that accepts an int-valued argument and produces a result.
    IntFunction<String> intFunction = String::valueOf;

    //    Represents a predicate (boolean-valued function) of one int-valued argument.
    IntPredicate intPredicate = (x)->x>10;

    //    Represents a supplier of int-valued results.
    IntSupplier intSupplier = ()->(int)(Math.random()*100);

    //    Represents a function that accepts an int-valued argument and produces a double-valued result.
    IntToDoubleFunction intToDoubleFunction = (x)->x * 50.0d;

    //    Represents a function that accepts an int-valued argument and produces a long-valued result.
    IntToLongFunction intToLongFunction = (x)->(long)x;

    //    Represents an operation on a single int-valued operand that produces an int-valued result.
    IntUnaryOperator intUnaryOperator = (x)->x * 2;

    //    Represents an operation upon two long-valued operands and producing a long-valued result.
    LongBinaryOperator longBinaryOperator = (x,y)->x+y;
    LongBinaryOperator longBinaryOperator2 = Long::sum;

    //    Represents an operation that accepts a single long-valued argument and returns no result.
    LongConsumer longConsumer = (x)-> System.out.println(++x);

    //    Represents a function that accepts a long-valued argument and produces a result.
    LongFunction<String> longFunction = (x)->String.valueOf(x * 2);
/*
    //    Represents a predicate (boolean-valued function) of one long-valued argument.
    LongPredicate longPredicate = (x)->

    //    Represents a supplier of long-valued results.
    LongSupplier

    //    Represents a function that accepts a long-valued argument and produces a double-valued result.
    LongToDoubleFunction

    //    Represents a function that accepts a long-valued argument and produces an int-valued result.
    LongToIntFunction

    //    Represents an operation on a single long-valued operand that produces a long-valued result.
    LongUnaryOperator

        //    Represents an operation that accepts an object-valued and a double-valued argument, and returns no result.
    ObjDoubleConsumer<String>

    //    Represents an operation that accepts an object-valued and a int-valued argument, and returns no result.
    ObjIntConsumer<String>

    //    Represents an operation that accepts an object-valued and a long-valued argument, and returns no result.
    ObjLongConsumer<String>

    //    Represents a predicate (boolean-valued function) of one argument.
    Predicate<String>

    //    Represents a supplier of results.
    Supplier<String>

    //    Represents a function that accepts two arguments and produces a double-valued result.
    ToDoubleBiFunction<String,String>

    //    Represents a function that produces a double-valued result.
    ToDoubleFunction<String>

    //    Represents a function that accepts two arguments and produces an int-valued result.
    ToIntBiFunction<String,String>

    //    Represents a function that produces an int-valued result.
    ToIntFunction<String>

    //    Represents a function that accepts two arguments and produces a long-valued result.
    ToLongBiFunction<String,String>

    //    Represents a function that produces a long-valued result.
    ToLongFunction<String>
    UnaryOperator<String>
    */
}
