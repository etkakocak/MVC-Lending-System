package model;

/**
 * Time management within the Stuff Lending System.
 */
public class Time {
  private int currentDay;

  /**
   * Constructor for Time.
   */
  public Time() {
    this.currentDay = 0;
  }

  /**
   * Increases the current day.
   */
  public void advanceDay() {
    currentDay++;
  }

  /**
   * Returns the current day.
   */
  public int getCurrentDay() {
    return currentDay;
  }

  /**
   * Sets the current day to the specified value.
   */
  public void setCurrentDay(int day) {
    this.currentDay = day;
  }

  /**
   * Returns the current day as string.
   */
  @Override
  public String toString() {
    return "Current day: " + currentDay;
  }
}
