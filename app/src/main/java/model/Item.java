package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents an item in the Stuff Lending System.
 */
public class Item {
  /**
   * Category of the item.
   */
  public enum Category {
    TOOL, VEHICLE, GAME, TOY, SPORT, OTHER
  }

  private String name;
  private String description;
  private Category category;
  private Member owner;
  private int creationDay;
  private int costPerDay;
  private List<Contract> contracts;

  /**
   * Constructor for Item.
   */
  public Item(String name, String description, Category category, 
      Member owner, int creationDay, int costPerDay) {
    this.name = name;
    this.description = description;
    this.category = category;
    this.owner = owner;
    this.creationDay = creationDay;
    this.costPerDay = costPerDay;
    this.contracts = new ArrayList<>();
  }

  // Getters and Setters

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name; 
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description; 
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category; 
  }

  public Member getOwner() {
    return owner;
  }

  public int getCreationDay() {
    return creationDay;
  }

  public int getCostPerDay() {
    return costPerDay;
  }

  public void setCostPerDay(int costPerDay) {
    this.costPerDay = costPerDay; 
  }

  public List<Contract> getContracts() {
    return Collections.unmodifiableList(contracts);
  }

  /**
   * Adds a contract.
   */
  public void addContract(Contract contract) {
    contracts.add(contract);
  }

  /**
   * Removes a contract.
   */
  public void removeContract(Contract contract) {
    contracts.remove(contract);
  }

  /**
   * Checks if the item is available for lending during a specified time period.
   */
  public boolean isAvailable(int startDay, int endDay) {
    for (Contract contract : contracts) {
      if (!(endDay < contract.getStartDay() || startDay > contract.getEndDay())) {
        return false; // Overlaps with an existing contract
      }
    }
    return true;
  }

  /**
   * Returns the item data as string.
   */
  public String toSimpleString() {
    return "Name: " + name 
        + ", Category: " + category 
        + ", Owner: " + owner.getName() 
        + ", Cost/Day: " + costPerDay;
  }

  /**
   * Returns a detailed string representation of the item.
   * Includes all contracts (historical and future).
   */
  public String toVerboseString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Item Name: ").append(name)
        .append("\nDescription: ").append(description)
        .append("\nCategory: ").append(category)
        .append("\nOwner: ").append(owner.getName())
        .append("\nCreation Day: ").append(creationDay)
        .append("\nCost Per Day: ").append(costPerDay)
        .append("\nContracts:\n");

    if (contracts.isEmpty()) {
      sb.append("No contracts for this item.\n");
    } else {
      for (Contract contract : contracts) {
        sb.append("Lent to: ").append(contract.getLender().getName())
            .append(", From Day ").append(contract.getStartDay())
            .append(" to Day ").append(contract.getEndDay()).append("\n");
      }
    }
    return sb.toString();
  }
}
