package model;

/**
 * This is encaptulation class for the registration times in the app.
 */
public class Time {
  private int day;

  Time() {
    day = 0;
  }

  Time(Time insTime) {
    day = insTime.day;
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
