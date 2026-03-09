package me.astralisvox.astralibs;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

public class DateTimeUtils {
    private LocalDate date;
    private LocalTime time;
    private final String dateFormat;
    private final String timeFormat;

    /**
     *
     * Constructor for the DateTimeUtils class.
     *
     * @param date (LocalDate)
     * @param time (LocalTime)
     * @param dateFormat (String)
     * @param timeFormat (String)
     */
    public DateTimeUtils(final LocalDate date, final LocalTime time, final String dateFormat, final String timeFormat) {
        this.date = date;
        this.time = time;
        this.timeFormat = timeFormat;
        this.dateFormat = dateFormat;
    }

    /**
     * Allows you for formatting the date and time into the correct format.
     *
     * @return the formatted date and time with the set format.
     */
    public DateTimeFormatter formatDateTime() {
        return DateTimeFormatter.ofPattern(dateFormat + " " + timeFormat);
    }

    /**
     * Allows for getting the date and time after it's been formatted. Makes use of ${@link #formatDateTime()}
     * @return The Formatted date and time
     */
    public String getDateTime() {
        return LocalDateTime.of(date, time).format(formatDateTime());
    }

    /**
     * Allows for getting only the date after it's been formatted correctly.
     * @return The formatted date
     */
    public String getDate() {
        return LocalDate.of(date.getYear(), date.getMonth(), date.getDayOfMonth()).format(DateTimeFormatter.ofPattern(dateFormat));
    }

    /**
     * Allows for getting only the time after it's been formatted correctly.
     * @return The formatted time
     */
    public String getTime() {
        return LocalTime.of(time.getHour(), time.getMinute(), time.getSecond()).format(DateTimeFormatter.ofPattern(timeFormat));
    }

    /**
     * Allows for getting the day of the month
     * @return The number value for the day of the month
     */
    public int getDayOfMonth() {
        return date.getDayOfMonth();
    }

    /**
     * Allows for getting the name of the month
     * @return The name of the month
     */
    public String getMonth() {
        return date.getMonth().toString();
    }

    /**
     * Allows for getting the month in the year
     * @return The number value for the month in the year
     */
    public int getMonthNumber() {
        return date.getMonthValue();
    }

    /**
     * Allows for getting how many days in the month
     * @return The number value for the days in the month
     */
    public int getLengthOfMonth() {
        return date.lengthOfMonth();
    }

    /**
     * Allows for changing the time to another time
     */
    public void setTime(final int hour, final int minute, final int second) {
        this.time = LocalTime.of(hour, minute, second);
    }

    /**
     * Allows for changing the date to another date
     */
    public void setDate(final int day, final int month, final int year) {
        this.date = LocalDate.of(year, month, day);
    }

    /**
     * Allows for adding days to the date
     * @return The updated date
     */
    public LocalDate addDays(final int daysToAdd) {
        return date.plusDays(daysToAdd);
    }

    /**
     * Allows for adding months to the date
     * @return The updated date
     */
    public LocalDate addMonths(final int monthsToAdd) {
        return date.plusMonths(monthsToAdd);
    }

    /**
     * Allows for adding years to the date
     * @return The update date
     */
    public LocalDate addYears(final int yearsToAdd) {
        return date.plusYears(yearsToAdd);
    }

    /**
     * Allows for adding weeks to the date
     * @return The updated date
     */
    public LocalDate addWeeks(final int weeksToAdd) {
        return date.plusWeeks(weeksToAdd);
    }

    /**
     * Allows for adding hours to the time
     * @return The updated time
     */
    public LocalTime addHours(final int hoursToAdd) {
        return time.plusHours(hoursToAdd);
    }

    /**
     * Allows for adding minutes to the time
     * @return The updated time
     */
    public LocalTime addMinutes(final int minutesToAdd) {
        return time.plusHours(minutesToAdd);
    }

    /**
     * Allows for adding seconds to the time
     * @return The updated time
     */
    public LocalTime addSeconds(final int secondsToAdd) {
        return time.plusHours(secondsToAdd);
    }

    /**
     * Allows for taking days away from the date
     * @return The updated date
     */
    public LocalDate takeDays(final int daysToTake) {
        return date.minusDays(daysToTake);
    }

    /**
     * Allows for taking months away from the date
     * @return The updated date
     */
    public LocalDate takeMonths(final int monthsToTake) {
        return date.minusMonths(monthsToTake);
    }

    /**
     * Allows for taking years away from the date
     * @return The updated date
     */
    public LocalDate takeYears(final int yearsToTake) {
        return date.minusYears(yearsToTake);
    }

    /**
     * Allows for taking weeks away from the date
     * @return The updated date
     */
    public LocalDate takeWeeks(final int weeksToTake) {
        return date.minusWeeks(weeksToTake);
    }

    /**
     * Allows for taking hours away from the time
     * @return The updated time
     */
    public LocalTime takeHours(final int hoursToTake) {
        return time.minusHours(hoursToTake);
    }

    /**
     * Allows for taking minutes away from the time
     * @return The updated time
     */
    public LocalTime takeMinutes(final int minutesToTake) {
        return time.minusHours(minutesToTake);
    }

    /**
     * Allows for taking seconds away from the time
     * @return The updated time
     */
    public LocalTime takeSeconds(final int secondsToTake) {
        return time.minusHours(secondsToTake);
    }

    /**
     * Allows for getting name value for the day for a specific date
     * @return The name of the day
     */
    public String getDayOfWeek() {
        final String dayOfWeek;

        switch(date.getDayOfWeek().getValue()) {
            case 1:
                dayOfWeek = "Monday";
                break;
            case 2:
                dayOfWeek = "Tuesday";
                break;
            case 3:
                dayOfWeek = "Wednesday";
                break;
            case 4:
                dayOfWeek = "Thursday";
                break;
            case 5:
                dayOfWeek = "Friday";
                break;
            case 6:
                dayOfWeek = "Saturday";
                break;
            case 7:
                dayOfWeek = "Sunday";
                break;
            default:
                dayOfWeek = "";
        }
        return dayOfWeek;
    }

    /**
     * Allows for getting the day of the year
     * @return The number value for the day of the year
     */
    public int getDayOfYear() {
        return date.getDayOfYear();
    }

    /**
     * Allows for getting the year
     * @return The number value for the year
     */
    public int getYear() {
        return date.getYear();
    }

    /**
     * Allows for getting the hour
     * @return The number value for the hour
     */
    public int getHour() {
        return time.getHour();
    }

    /**
     * Allows for getting the minute
     * @return The number value for the minute
     */
    public int getMinute() {
        return time.getMinute();
    }

    /**
     * Allows for getting the second
     * @return The number value for the second
     */
    public int getSecond() {
        return time.getSecond();
    }

    public String compareDate(LocalDate dateToCompare) {
        Period period = Period.between(date, dateToCompare);

        String comparedDate = period.getYears() + " Years, " + period.getMonths() + " Months, " + period.getDays() + " Days";
        return comparedDate.replace("-", "");
    }

    public String compareTime(LocalTime pastTime) {
        int hours1 = time.get(ChronoField.HOUR_OF_DAY);
        int hours2 = pastTime.get(ChronoField.HOUR_OF_DAY);
        int minute1 = time.get(ChronoField.MINUTE_OF_HOUR);
        int minute2 = pastTime.get(ChronoField.MINUTE_OF_HOUR);
        int seconds1 = time.get(ChronoField.SECOND_OF_MINUTE);
        int seconds2 = pastTime.get(ChronoField.SECOND_OF_MINUTE);

        String comparedTime =  (hours1 - hours2) + " Hours, " + (minute1 - minute2) + " Minutes, " + (seconds1 - seconds2) + " Seconds";
        return comparedTime.replace("-", "");
    }

    /**
     * Allows for getting a LocalDate from a long
     * @param dateToConvert - The long to convert into a LocalDate
     * @return LocalDate - The converted LocalDate
     */
    public static LocalDate convertLongToLocalDate(long dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Allows for getting a LocalTime from a long
     * @param timeToConvert - The long to convert into a LocalDate
     * @return LocalTime - The converted LocalTime
     */
    public static LocalTime convertLongToLocalTime(long timeToConvert) {
        return Instant.ofEpochMilli(timeToConvert).atZone(ZoneId.systemDefault()).toLocalTime();
    }
}
