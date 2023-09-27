package model;

public class Time {
    private static int Day;

    public Time() {
        Day = 0;
    }

    public static int getDate() {
        return Day;
    }

    public static void setDate(int newDay) {
        Day = newDay;
    }
}
