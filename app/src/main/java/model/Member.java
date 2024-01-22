package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
  private static Set<String> uniqueEmails = new HashSet<>();
  private static Set<Integer> uniquemobileNos = new HashSet<>();

  /**
   * The Member class.
   */
  protected Member(String name, String memberId, String email, int mobile, Time currentDate) {
    this.name = name;
    this.setEmail(email);
    this.setMobile(mobile);
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

  /**
   * setting email unique.
   */
  public void setEmail(String email) {
    if (uniqueEmails.contains(email)) {
      throw new IllegalArgumentException("Email must be unique");
    }
    this.email = email;
    uniqueEmails.add(email);
  }

  public int getMobile() {
    return mobile;
  }

  /**
   * setting mobile number unique.
   */
  public void setMobile(int mobile) {
    if (uniquemobileNos.contains(mobile)) {
      throw new IllegalArgumentException("Mobile must be unique");
    }
    this.mobile = mobile;
    uniquemobileNos.add(mobile);
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

  /**
   * If member tries add same item.
   */
  public void addOwnedItem(Item newitem) {
    if (this.ownedItems.stream().anyMatch(item -> item.getName().equals(newitem.getName()))) {
      throw new IllegalArgumentException("Member already has a ownership for the given Item");
    } else {
      this.ownedItems.add(newitem);
      addCredits(100);
    }
  }

  public void removeOwnedItem(Item item) {
    this.ownedItems.remove(item);
  }

  @Override
  public String toString() {
    return "" + name;
  }
}