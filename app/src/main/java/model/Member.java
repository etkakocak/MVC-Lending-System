package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
  private List<Item> ownedItems;
  private List<Contract> ownedContracts;
  private List<String> existingMemberIds;
  private String username;
  private String password;

  /**
   * The Member class.
   */
  public Member(String name, String email, int mobile, String username,
      String password, Time creationDate) {
    this.name = name;
    this.email = email;
    this.mobile = mobile;
    this.username = username;
    this.password = password;
    this.creationDate = creationDate.getDate();
    this.credits = 0;
    ownedItems = new ArrayList<>();
    ownedContracts = new ArrayList<>();
    existingMemberIds = new ArrayList<>();
    this.memberId = generateMemberId(new Random());
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  private String generateMemberId(Random random) {
    String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    StringBuilder sb;
    String generatedId;
    do {
      sb = new StringBuilder(6);
      for (int i = 0; i < 6; i++) {
        int index = random.nextInt(alphaNumericString.length());
        sb.append(alphaNumericString.charAt(index));
      }
      generatedId = sb.toString();
    } while (existingMemberIds.contains(generatedId));

    existingMemberIds.add(generatedId);
    return generatedId;
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

  public List<Contract> getContracts() {
    return ownedContracts;
  }

  public void addOwnedContract(Contract contract) {
    this.ownedContracts.add(contract);
  }

  public int getCredits() {
    return credits;
  }

  public void addCredits(int credits) {
    this.credits += credits;
  }

  public List<Item> getOwnedItems() {
    return ownedItems;
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
