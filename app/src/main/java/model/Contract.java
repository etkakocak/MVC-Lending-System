package model;

/**
 * This is encaptulation class for contracts.
 */
public class Contract {
  private int startDate;
  private int endDate;
  private final Item item;
  private final Member lender;
  private int cost;

  /**
   * The Contract class.
   */
  protected Contract(int startDate, int endDate, Item item, Member lender, int cost) {
    this.startDate = startDate;
    this.endDate = endDate;
    this.item = item;
    this.lender = lender;
  }

  public int getCost() {
    return cost;
  }

  public int getStartDate() {
    return startDate;
  }

  public void setStartDate(int startDate) {
    this.startDate = startDate;
  }

  public int getEndDate() {
    return endDate;
  }

  public void setEndDate(int endDate) {
    this.endDate = endDate;
  }

  public String getItem() {
    return item.getName();
  }

  public String getLender() {
    return lender.getName();
  }
}