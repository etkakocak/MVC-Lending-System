package model;

/**
 * This is encaptulation class for contracts.
 */
public class Contract {
  private int startDate;
  private int endDate;
  private String item;
  private String lender;
  private int cost;

  /**
   * The Contract class.
   */
  public Contract(int startDate, int endDate, String item, String lender, int cost) {
    this.startDate = startDate;
    this.endDate = endDate;
    this.item = item;
    this.lender = lender;
    this.cost = cost;
  }

  /**
   * To return Contract object.
   */
  public Contract(Contract theContract) {
    this.startDate = theContract.startDate;
    this.endDate = theContract.endDate;
    this.item = theContract.item;
    this.lender = theContract.lender;
    this.cost = theContract.cost;
  }

  public int getCost() {
    return cost;
  }

  public void setCost(int cost) {
    this.cost = cost;
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
    return item;
  }

  public String getLender() {
    return lender;
  }
}