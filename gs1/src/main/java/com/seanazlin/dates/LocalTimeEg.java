package com.seanazlin.dates;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class LocalTimeEg {
    public static void main(String[] args) {
        LocalDateTime t1 = LocalDateTime.now();
        LocalDateTime t2 = t1.plus(2, ChronoUnit.MINUTES);

        System.out.println(t1.isBefore(t2));
        System.out.println(ChronoUnit.SECONDS.between(t1,t2)); // 120
        System.out.println(t1.getMonth()); // ex. NOVEMBER
        System.out.println(t1.getDayOfWeek());  // ex. TUESDAY
        System.out.println(t1.getHour()); // ex. 11

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-mm-yyyy");
        DateTimeFormatter formatter2 = DateTimeFormatter.ISO_DATE_TIME;
        System.out.println(formatter.format(t1)); // ex. 12-31-2019
        System.out.println(formatter2.format(t1)); // ex: 2019-11-12T11:31:57.979


    }
}
