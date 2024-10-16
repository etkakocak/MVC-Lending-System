package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a member in the Stuff Lending System.
 */
public class Member {
  private String memberId; 
  private String name;
  private String email;
  private String phoneNumber;
  private int credits;
  private int creationDay;
  private List<Item> ownedItems; 

  /**
   * Constructor for Member.
   */
  public Member(String memberId, String name, String email, String phoneNumber, int creationDay) {
    this.memberId = memberId;
    this.name = name;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.credits = 0; // Initial credits
    this.creationDay = creationDay;
    this.ownedItems = new ArrayList<>();
  }

  // Getters and Setters

  public String getMemberId() {
    return memberId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name; 
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email; 
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber; 
  }

  public int getCredits() {
    return credits;
  }

  public void addCredits(int amount) {
    this.credits += amount;
  }

  public void deductCredits(int amount) {
    this.credits -= amount;
  }

  public int getCreationDay() {
    return creationDay;
  }

  public List<Item> getOwnedItems() {
    return Collections.unmodifiableList(ownedItems);
  }

  public int getNumberOfOwnedItems() {
    return ownedItems.size();
  }

  /**
   * Adds an item.
   */
  public void addItem(Item item) {
    ownedItems.add(item);
  }

  /**
   * Removes an item.
   */
  public void removeItem(Item item) {
    ownedItems.remove(item);
  }

  /**
   * Returns the member data as string.
   */
  public String toSimpleString() {
    return "ID: " + memberId 
        + ", Name: " + name 
        + ", Email: " + email 
        + ", Credits: " + credits 
        + ", Owned Items: " + getNumberOfOwnedItems();
  }

  /**
   * Returns a detailed string representation of the member.
   * Includes all owned items and their details.
   */
  public String toVerboseString() {
    StringBuilder sb = new StringBuilder();
    sb.append("ID: ").append(memberId)
        .append("\nName: ").append(name)
        .append("\nEmail: ").append(email)
        .append("\nCredits: ").append(credits)
        .append("\nOwned Items:\n");

    if (ownedItems.isEmpty()) {
      sb.append("No items owned.\n");
    } else {
      for (Item item : ownedItems) {
        sb.append(item.toVerboseString()).append("\n");
      }
    }
    return sb.toString();
  }
}
