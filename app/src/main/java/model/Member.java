package model;

import java.util.ArrayList;

/**
 * This is encaptulation class for members.
 */
public class Member {
  private String memberId;
  private String name;
  private String email;
  private int mobile;
  private int creationDate;
  private int credits;
  private ArrayList<Item> ownedItems = new ArrayList<>();
  private final Time currentDate;

  /**
   * The Member class.
   */
  protected Member(String name, String memberId, String email, int mobile, Time currentDate) {
    this.name = name;
    this.email = email;
    this.mobile = mobile;
    this.currentDate = currentDate;
    this.creationDate = this.currentDate.getDate();
    this.credits = 0;
    this.memberId = memberId;
  }

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

  public int getMobile() {
    return mobile;
  }

  public void setMobile(int mobile) {
    this.mobile = mobile;
  }

  public int getCreationDate() {
    return creationDate;
  }

  public int getCredits() {
    return credits;
  }

  public void addCredits(int credits) {
    this.credits += credits;
  }

  public ArrayList<Item> getOwnedItems() {
    return new ArrayList<>(ownedItems);
  }

  public void addOwnedItem(Item item) {
    this.ownedItems.add(item);
    addCredits(100);
  }

  public void removeOwnedItem(Item item) {
    this.ownedItems.remove(item);
  }

  @Override
  public String toString() {
    return "" + name;
  }
}
