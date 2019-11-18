package com.seanazlin.fi;



import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FunctionalPractice {

    public static void main(String[] args) {
        // TODO 1: read from a text file, check to see if a line is empty, whether line starts with A, convert the lines to upper case,
        // output results to std out line by line
        // use functional interfaces ???
        System.out.println("Task 1");
        Predicate<String> startWithA = (x)->x.startsWith("A");
        Predicate<String> ifEmpty = String::isEmpty;
        Predicate<String> ifNotEmpty = ifEmpty.negate();
        Predicate<String> task1 = ifNotEmpty.and(startWithA);
        try(BufferedReader reader = new BufferedReader(new FileReader("input.txt"))){
            reader.lines().filter(task1).map(String::toUpperCase).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\nTask 2");
        // TODO 2: read from a txt file, if line is upper case then convert to lower case, and vice versa, and write back to a different file
        try(BufferedReader reader = new BufferedReader(new FileReader("input.txt")))
        {
            Consumer<String> writeLine = (x)->{
//                try {
//                    writer.write(x);
//                    writer.newLine();
//                } catch(IOException e){
//                    e.printStackTrace();
//                }
            };

            reader.lines().map(StringUtils::swapCase).forEachOrdered(writeLine);
            File file = new File("output.txt");
            FileUtils.writeLines(file, reader.lines()
                    .map(StringUtils::swapCase)
                    .collect(Collectors.toList()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
