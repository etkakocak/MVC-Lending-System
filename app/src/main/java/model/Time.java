package model;

public class Time {
    private int day;

    Time() {
        day = 0;
    }

    public int getDate() {
        return day;
    }

    public void setDate(int newDay) {
        day = newDay;
    }

    public String toString() {
        return "" + day;
    }
}
