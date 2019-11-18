package com.seanazlin.directories;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DirectoryEg {
    public static void main(String[] args) {
        try {
            Files.list(Paths.get(".")).forEach(System.out::println);
            System.out.println("---");
            Files.list(Paths.get(".")).filter(Files::isRegularFile).forEach(System.out::println);
            System.out.println("---");
            Files.newDirectoryStream(Paths.get(".")).forEach(System.out::println);
            System.out.println("---");
            Files.newDirectoryStream(Paths.get("."), path->{System.out.println("*" + path.getFileName() + "|" + path.getFileName().startsWith("src")); return path.getFileName().startsWith("1");}).forEach(System.out::println);

            String content = "BiConsumer<T,U>\t\n" +
                    "Represents an operation that accepts two input arguments and returns no result.\n" +
                    "BiFunction<T,U,R>\t\n" +
                    "Represents a function that accepts two arguments and produces a result.\n" +
                    "BinaryOperator<T>\t\n" +
                    "Represents an operation upon two operands of the same type, producing a result of the same type as the operands.\n" +
                    "BiPredicate<T,U>\t\n" +
                    "Represents a predicate (boolean-valued function) of two arguments.\n" +
                    "BooleanSupplier\t\n" +
                    "Represents a supplier of boolean-valued results.\n" +
                    "Consumer<T>\t\n" +
                    "Represents an operation that accepts a single input argument and returns no result.\n" +
                    "DoubleBinaryOperator\t\n" +
                    "Represents an operation upon two double-valued operands and producing a double-valued result.\n" +
                    "DoubleConsumer\t\n" +
                    "Represents an operation that accepts a single double-valued argument and returns no result.\n" +
                    "DoubleFunction<R>\t\n" +
                    "Represents a function that accepts a double-valued argument and produces a result.\n" +
                    "DoublePredicate\t\n" +
                    "Represents a predicate (boolean-valued function) of one double-valued argument.\n" +
                    "DoubleSupplier\t\n" +
                    "Represents a supplier of double-valued results.";
            Files.write(Paths.get("./dirOutput.txt"), content.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
