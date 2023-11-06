package model;

/**
 * This is encaptulation class for contracts.
 */
public class Contract {
  private int startDate;
  private int endDate;
  private Item item;
  private Member lender;
  private Member owner;
  private int cost;

  /**
   * The Contract class.
   */
  public Contract(int startDate, int endDate, Item item, Member lender, int cost) {
    this.startDate = startDate;
    this.endDate = endDate;
    this.item = item;
    this.lender = lender;
    this.owner = item.getOwner();
    this.cost = cost;
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

  public Item getItem() {
    return item;
  }

  public void setItem(Item item) {
    this.item = item;
  }

  public Member getLender() {
    return lender;
  }

  public void setLender(Member lender) {
    this.lender = lender;
  }

  public Member getOwner() {
    return owner;
  }
}