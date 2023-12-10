package model;

/**
 * This is encaptulation class for items.
 */
public class Item {
  private String itemId;
  private String category;
  private String name;
  private String description;
  private int costPerDay;
  private final Member owner;
  private final int creationDate;
  private final Time currentDate;

  /**
   * The Item class.
   */
  protected Item(String category, String name, String description, 
      int costPerDay, Time currentDate, String itemId, Member owner) {
    this.category = category;
    this.name = name;
    this.owner = owner;
    this.description = description;
    this.costPerDay = costPerDay;
    this.itemId = itemId;
    this.currentDate = currentDate;
    this.creationDate = this.currentDate.getDate();
  }

  public String getItemId() {
    return itemId;
  }

  public String getOwner() {
    return owner.getName();
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getCreationDate() {
    return creationDate;
  }

  public int getCostPerDay() {
    return costPerDay;
  }

  public void setCostPerDay(int costPerDay) {
    this.costPerDay = costPerDay;
  }
}
