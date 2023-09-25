package model;

import java.util.Date;

public class Time {
    private int day;
    private int currentDay = 0;
    private int time;
    

    public Time() {
        this.day = 0; 
    }

    public int getDay() {
        return day;
    }

    public int getTime() {
        return time;
    }

    public void setDay(int day) {
        this.day = day;
    }

    
    public void advanceDay() {
        currentDay++;

}
public int getCurrentDay() {
    return currentDay;
}
}
