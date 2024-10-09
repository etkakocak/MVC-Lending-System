package model;

/**
 * Represents a lending contract in the Stuff Lending System.
 */
public class Contract {
  private Member lender; // The member who is borrowing the item
  private Item item; // The item being lent
  private int startDay; // The day the contract starts
  private int endDay; // The day the contract ends
  private int totalCost; // The total cost of the lending contract

  /**
   * Constructor for Contract.
   */
  public Contract(Member lender, Item item, int startDay, int endDay) {
    this.lender = lender;
    this.item = item;
    this.startDay = startDay;
    this.endDay = endDay;
    this.totalCost = calculateTotalCost();
  }

  // Getters

  public Member getLender() {
    return lender;
  }

  public Item getItem() {
    return item;
  }

  public int getStartDay() {
    return startDay;
  }

  public int getEndDay() {
    return endDay;
  }

  public int getTotalCost() {
    return totalCost;
  }

  /**
   * Calculates the total cost of the contract based on the duration and
   * cost per day of the item.
   */
  private int calculateTotalCost() {
    int duration = (endDay - startDay) + 1; 
    return duration * item.getCostPerDay();
  }

  /**
   * Returns the contract data as string.
   */
  @Override
  public String toString() {
    return "Item: " + item.getName() 
        + ", Lender: " + lender.getName() 
        + ", Start Day: " + startDay 
        + ", End Day: " + endDay 
        + ", Total Cost: " + totalCost;
  }
}
