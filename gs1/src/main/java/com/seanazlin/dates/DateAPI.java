package com.seanazlin.dates;

import java.time.*;
import java.util.Date;
import java.util.TimeZone;

public class DateAPI {
    public static void main(String[] args) {
//        Clock c = Clock.systemUTC();
        Clock c = Clock.systemDefaultZone(); // Still UTC?
        Instant instance = c.instant();
        System.out.println(c);
        System.out.println(instance);

        // Still prints UTC...
        System.out.println(c.withZone(ZoneId.of("America/Los_Angeles")).instant());

        // Prints in PST
        System.out.println(Date.from(instance));

        System.out.println(ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("America/Los_Angeles")));
        System.out.println(ZonedDateTime.now());
        System.out.println(ZonedDateTime.now(ZoneId.of("America/Los_Angeles")));
    }
}
