package com.fitge.api.util.generator;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

public class DateTimeGenerator {
    public static Instant nowInstant() {
        return Instant.now();
    }

    public static Long nowInMs() {
        return System.currentTimeMillis();
    }

    public static Date nowDate() {
        return new Date(nowInMs());
    }

    public static LocalDate todayDate() {
        return LocalDate.now(ZoneOffset.UTC);
    }

}
