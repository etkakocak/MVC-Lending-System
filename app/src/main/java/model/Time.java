package model;

/**
 * This is encaptulation class for the registration times in the app.
 */
public class Time {
  private int day;

  public Time() {
    day = 0;
  }

  public int getDate() {
    return day;
  }

  public void setDate(int newDay) {
    day = newDay;
  }

  public void nextDay() {
    day++;
  }

  public String toString() {
    return "" + day;
  }
}
