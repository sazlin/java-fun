package com.seanazlin.dates;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ParseEg {
    public static void main(String[] args) {
        String date = "2016-04-17";
        System.out.println(LocalDate.parse(date));
        System.out.println(LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }
}
