package model;

/**
 * This is encaptulation class for items.
 */
public class Item {
  private String category;
  private String name;
  private String description;
  private int creationDate;
  private int costPerDay;
  private String owner;

  /**
   * The Item class.
   */
  public Item(String category, String name, String description, int costPerDay, 
      String owner, Time creationDate) {
    this.category = category;
    this.name = name;
    this.description = description;
    this.costPerDay = costPerDay;
    this.owner = owner;
    this.creationDate = creationDate.getDate();
  }

  /**
   * To return Item object.
   */
  public Item(Item anItem) {
    this.name = anItem.name;
    this.category = anItem.category;
    this.description = anItem.description;
    this.costPerDay = anItem.costPerDay;
    this.owner = anItem.owner;
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

  public String getOwner() {
    return owner;
  }
}
