package model;

public class Time {
    private int day;
    private int currentDay = 0;
    

    public Time() {
        this.day = 0; 
    }

    public int getDay() {
        return day;
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
