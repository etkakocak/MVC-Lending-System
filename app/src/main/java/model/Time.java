package model;

public class Time {
    public int Day;

    public Time() {
        this.Day = 0;
    }

    public int getDate() {
        return Day;
    }

    public void setDate(int Day) {
        this.Day = Day;
    }

    @Override
    public String toString() {
        return "Day: " + Day;
    }
}
