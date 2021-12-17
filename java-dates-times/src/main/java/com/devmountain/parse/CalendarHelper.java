package com.devmountain.parse;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class CalendarHelper {

    private final DateTimeFormatter formatter;
    private final LocalDate birthdayLocalDate;
    private final LocalDate nowLocalDate;
    private final int currentYear;
    private List<LocalDate> holidayList;
    private LocalDate closestHoliday;

    public CalendarHelper(String birthdayString) {
        //create DateTimeFormatter with the pattern "M/d/yyyy"
        //formatter = ???;
        this.formatter = DateTimeFormatter.ofPattern("M/d/yyyy");

        //convert String to LocalDate
        this.birthdayLocalDate = LocalDate.parse(birthdayString, formatter);
//        System.out.println(formatter.format(birthdayLocalDate));
        this.nowLocalDate = LocalDate.now();
        this.currentYear = LocalDate.now().getYear();

        this.holidayList = initHolidayList(currentYear, nowLocalDate);
    }

    public void displayDaysRemainingToHolidaysAndBirthday() {
        /*
         * Step 1: print out the current date
         */
        System.out.println("The current date is: " + formatter.format(nowLocalDate));

        /*
         * Hint:
         * 1. you need to loop through each of the holiday to
         *    calculate the difference of days (e.g. remainingDay) between the current day
         *    and the holiday. If your current day has passed a holiday,
         *    you need to calculate the different days between your current
         *    day of the year and the corresponding holiday in next year
         * 2. you need to find the holiday with the smallest "remainingDay"
         * 3. you then calculate the "remainingDay" between the current date and the
         *    coming birthday
         */

        // you code here
        Long closestDate = 0L;
        for (LocalDate eachHoliday : holidayList) {
             //you code here
                //Display the remainingDay between the current day and the corresponding holiday
            if (nowLocalDate.isAfter(eachHoliday)) {
                eachHoliday = eachHoliday.plusYears(1);
            }
            Long remainingDay = ChronoUnit.DAYS.between(nowLocalDate, eachHoliday);
            System.out.println("There are " + remainingDay + " days remaining before Holiday (" + formatter.format(eachHoliday) + ")");

            //Finding closest holiday to today.
            if (eachHoliday.equals(holidayList.get(0))) {
                closestDate = remainingDay;
                closestHoliday = eachHoliday;
            }
            else if (remainingDay < closestDate){
                closestDate = remainingDay;
                closestHoliday = eachHoliday;
            }


        }
        //Display the Holiday which is closest to the current day

        System.out.println("the closest Holiday to the current date (" + formatter.format(nowLocalDate) + ") is: " + formatter.format(closestHoliday));

        /*
         * Now start calculating the remaining day between the current date and the birthday
         */
//        LocalDate birthday = birthdayLocalDate.withYear(Integer.parseInt(String.valueOf(Year.now())));
        LocalDate nextBirthdayLocalDate = birthdayLocalDate.withYear(Year.now().getValue());
        if (nowLocalDate.isAfter(nextBirthdayLocalDate)) {
            nextBirthdayLocalDate = nextBirthdayLocalDate.plusYears(1);
        }
        Long remainingDay = ChronoUnit.DAYS.between(nowLocalDate, nextBirthdayLocalDate);
        Long age = ChronoUnit.YEARS.between(birthdayLocalDate, nextBirthdayLocalDate);

         //Display the remainingDay between the current day and the birthday
        System.out.println("There are " + remainingDay + " days remaining before your " + age + "th birthday (" + formatter.format(nextBirthdayLocalDate) + ")");
    }


    private List<LocalDate> initHolidayList(int currentYear, LocalDate nowLocalDate) {
        List<LocalDate> holidayList = new ArrayList<>(5);
        int nextYear = currentYear + 1;
        //initialize July 4th holiday
        LocalDate julyFourth = LocalDate.of(currentYear, Month.JULY, 4);
        if(nowLocalDate.isAfter(julyFourth))
            julyFourth = LocalDate.of(nextYear, Month.JULY, 4);
        holidayList.add(julyFourth);

        //initialize New Year holiday
        LocalDate newYear = LocalDate.of(currentYear, Month.JANUARY, 1);
        if(nowLocalDate.isAfter(newYear))
            newYear = LocalDate.of(nextYear, Month.JANUARY, 1);
        holidayList.add(newYear);

        //initialize Christmas holiday
        LocalDate christmas = LocalDate.of(currentYear, Month.DECEMBER, 25);
        if(nowLocalDate.isAfter(christmas))
            christmas = LocalDate.of(nextYear, Month.DECEMBER, 25);
        holidayList.add(christmas);

        return holidayList;
    }


}
