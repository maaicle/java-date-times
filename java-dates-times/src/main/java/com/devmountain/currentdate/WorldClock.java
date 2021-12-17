package com.devmountain.currentdate;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class WorldClock {

    public LocalDate getNowForDate() { return LocalDate.now(); }

    public LocalDateTime getNowForDateAndTime() {
        return LocalDateTime.now();
    }

    public DayOfWeek getDayOfWeekForNow() {
        return DayOfWeek.from(LocalDate.now());
    }

    public int getDayOfMonthForNow() {
        return LocalDate.now().getDayOfMonth();
    }

    public int getDayOfYearForNow() {
        return LocalDate.now().getDayOfYear();
    }

    public ZonedDateTime getNowDateTimeForNewYork() {
        //returns all the time zone codes
//        System.out.println(ZoneId.getAvailableZoneIds());
        //returns the offset from a TemporalAccessor.
        System.out.println(ZoneOffset.from(ZonedDateTime.now()));
        //returns the zoneId from a TemporalAccessor.
        System.out.println(ZoneId.from(ZonedDateTime.now()));
        //returns the ZonedDateTime of from a specific time zone.
        return ZonedDateTime.now(ZoneId.of("America/New_York"));
    }

    public ZonedDateTime getNowDateTimeForLA() {
        return ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));
    }

    public ZonedDateTime getNowDateTimeForLondon() {
        return ZonedDateTime.now(ZoneId.of("Europe/London"));
    }

    public ZonedDateTime getNowDateTimeForMoscow() {
        return ZonedDateTime.now(ZoneId.of("Europe/Moscow"));
    }

    public ZonedDateTime getNowDateTimeForTokyo() {
        return ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
    }



}
