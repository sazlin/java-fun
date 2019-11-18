package com.seanazlin.dates;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

public class InstantEg {
    public static void main(String[] args) {
        Instant instant = Instant.now();
        instant.plus(2, ChronoUnit.MINUTES);
        instant.minusSeconds(60);
        Period p = Period.ofDays(1);
        instant.plus(p);
        LocalDate now = LocalDate.now();
        Period year = Period.between(now, now.plus(365, ChronoUnit.DAYS));
        System.out.println(year.getDays()); // 30 (not entire period)
        System.out.println(year.get(ChronoUnit.DAYS)); // 30 (not entire period)

        ZonedDateTime d = ZonedDateTime.now(ZoneId.of("Europe/Paris"));
        ZonedDateTime d2 = ZonedDateTime.now(ZoneOffset.UTC);

        System.out.println(d);
        System.out.println(d2);
    }
}
