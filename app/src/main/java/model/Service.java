package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Handles the business logic and data management for the Stuff Lending System.
 */
public class Service {
  private List<Member> members;
  private List<Item> items;
  private List<Contract> contracts;
  private Time time;

  /**
   * Constructor for Service.
   */
  public Service(Time time) {
    this.members = new ArrayList<>();
    this.items = new ArrayList<>();
    this.contracts = new ArrayList<>();
    this.time = time;

    // Hard-coded data for initial members and items (as per requirements)
    loadInitialData();
  }

  /**
   * Loads some hard-coded members and items into the system.
   */
  private void loadInitialData() {
    // Member 1
    addMember("Alice", "alice@example.com", "1234567890");
    Member alice = getMemberByEmail("alice@example.com");
    addHardcodedItem(alice, "Hammer", "A sturdy hammer", Item.Category.TOOL, 5);

    // Member 2
    addMember("Martin", "martin@hotmail.com", "0129954637");
    Member m1 = getMemberByEmail("martin@hotmail.com");
    addHardcodedItem(m1, "VW Golf 2019", "Manual", Item.Category.VEHICLE, 50);
    addHardcodedItem(m1, "Xbox Series S", "Two controllers", Item.Category.GAME, 10);
    m1.addCredits(300);

    // Member 3
    addMember("Manu", "manu@example.com", "0987654321");
    Member m2 = getMemberByEmail("manu@example.com");
    m2.addCredits(100);

    Item l2 = getItemByNameAndOwner("Xbox Series S", m1);
    addHardcodedContract(alice, l2, 5, 7);
  }

  // Member Management Methods

  /**
   * Adds a new member to the system.
   */
  public boolean addMember(String name, String email, String phoneNumber) {
    if (isEmailOrPhoneExists(email, phoneNumber)) {
      return false;
    }
    String memberId;
    do {
      memberId = new GenerateId().generateNewId();
    } while (isMemberIdExists(memberId));

    Member newMember = new Member(memberId, name, email, phoneNumber, time.getCurrentDay());
    members.add(newMember);
    return true;
  }

  /**
   * Deletes a member from the system.
   */
  public boolean deleteMember(String memberId) {
    Member memberToRemove = getMemberById(memberId);
    if (memberToRemove != null) {
      // Remove member's items
      for (Item item : memberToRemove.getOwnedItems()) {
        items.remove(item);
      }
      members.remove(memberToRemove);
      return true;
    }
    return false;
  }

  /**
   * Updates a member's information.
   */
  public boolean updateMember(String memberId, String newName, String newEmail, String newPhone) {
    Member member = getMemberById(memberId);
    if (member == null) {
      return false;
    }
    // Check for email and phone uniqueness
    if (!member.getEmail().equals(newEmail) && isEmailExists(newEmail)) {
      return false;
    }
    if (!member.getPhoneNumber().equals(newPhone) && isPhoneExists(newPhone)) {
      return false;
    }
    member.setName(newName);
    member.setEmail(newEmail);
    member.setPhoneNumber(newPhone);
    return true;
  }

  /**
   * Retrieves a member's detailed information.
   */
  public Member viewMember(String memberId) {
    for (Member member : members) {
      if (member.getMemberId().equals(memberId)) {
        // Create a new Member copy object with the same data
        Member copy = new Member(
            memberId,
            member.getName(),
            member.getEmail(),
            member.getPhoneNumber(),
            member.getCreationDay());
        copy.addCredits(member.getCredits());
        copy.setItemList(member.getOwnedItems());
        return copy;
      }
    }
    return null;
  }

  /**
   * Lists all members with basic information.
   */
  public List<Member> listMembers() {
    return Collections.unmodifiableList(members);
  }

  /**
   * Lists all members with detailed information.
   */
  public String listMembersVerbose() {
    StringBuilder sb = new StringBuilder();
    for (Member member : members) {
      sb.append(member.toVerboseString()).append("\n");
    }
    return sb.toString();
  }

  // Item Management Methods

  /**
   * Adds a new item to the system.
   */
  public boolean addItem(String ownerid, String name, String description,
      Item.Category category, int costPerDay) {
    Member owner = getMemberById(ownerid);
    if (owner == null) {
      return false;
    }
    // Check if owner already has an item with the same name
    if (ownerHasItemWithName(owner, name)) {
      return false; // Item with same name already exists
    }
    Item newItem = new Item(name, description, category, owner, time.getCurrentDay(), costPerDay);
    items.add(newItem);
    owner.addItem(newItem);
    owner.addCredits(100); // Owner gets 100 credits
    return true;
  }

  /**
   * Adds a hardcoded new item to the system.
   */
  public boolean addHardcodedItem(Member owner, String name, String description,
      Item.Category category, int costPerDay) {
    if (owner == null) {
      return false;
    }
    // Check if owner already has an item with the same name
    if (ownerHasItemWithName(owner, name)) {
      return false; // Item with same name already exists
    }
    Item newItem = new Item(name, description, category, owner, time.getCurrentDay(), costPerDay);
    items.add(newItem);
    owner.addItem(newItem);
    owner.addCredits(100); // Owner gets 100 credits
    return true;
  }

  /**
   * Deletes item.
   */
  public boolean deleteItem(String itemName, String ownerid) {
    Member owner = getMemberById(ownerid);
    Item itemToRemove = getItemByNameAndOwner(itemName, owner);
    if (itemToRemove != null) {
      // Remove item from owner's owned items
      owner.removeItem(itemToRemove);
      // Remove item from global items list
      items.remove(itemToRemove);
      // Remove contracts associated with this item
      removeContractsByItem(itemToRemove);
      return true;
    }
    return false;
  }

  /**
   * Removes all contracts associated with a specific item.
   */
  private void removeContractsByItem(Item item) {
    contracts.removeIf(contract -> contract.getItem().equals(item));
  }

  /**
   * Updates an item's information.
   */
  public boolean updateItem(Item item, String newName,
      String newDescription, Item.Category newCategory, int newCostPerDay) {
    if (item == null) {
      return false;
    }
    // Check if owner already has an item with the new name
    if (!item.getName().equalsIgnoreCase(newName)
        && ownerHasItemWithName(item.getOwner(), newName)) {
      return false; // Item with same name already exists
    }
    item.setName(newName);
    item.setDescription(newDescription);
    item.setCategory(newCategory);
    item.setCostPerDay(newCostPerDay);
    return true;
  }

  /**
   * Retrieves an item's detailed information.
   */
  public Item viewItem(String itemName, String ownerid) {
    Member owner = getMemberById(ownerid);
    return getItemByNameAndOwner(itemName, owner);
  }

  /**
   * Lists all items.
   */
  public List<Item> listItems() {
    return Collections.unmodifiableList(items);
  }

  // Contract Management Methods

  /**
   * Establishes a new lending contract.
   */
  public boolean addContract(String borrowerid, Item item, int startDay, int endDay) {
    Member borrower = getMemberById(borrowerid);
    if (borrower == null || item == null) {
      return false;
    }
    if (startDay < time.getCurrentDay() || endDay < startDay) {
      return false; // Invalid date range
    }
    // Check if borrower is not the owner
    if (borrower.equals(item.getOwner())) {
      return false; // Cannot borrow own item
    }
    // Check if item is available
    if (!item.isAvailable(startDay, endDay)) {
      return false;
    }
    // Calculate total cost
    int duration = (endDay - startDay) + 1;
    int totalCost = duration * item.getCostPerDay();

    // Check if borrower has enough credits
    if (borrower.getCredits() < totalCost) {
      return false;
    }
    // Create contract
    Contract contract = new Contract(borrower, item, startDay, endDay);
    contracts.add(contract);
    item.addContract(contract);

    return true;
  }

  /**
   * Establishes a new hardcoded lending contract.
   */
  public boolean addHardcodedContract(Member borrower, Item item, int startDay, int endDay) {
    if (borrower == null || item == null) {
      return false;
    }
    if (startDay < time.getCurrentDay() || endDay < startDay) {
      return false; // Invalid date range
    }
    // Check if borrower is not the owner
    if (borrower.equals(item.getOwner())) {
      return false; // Cannot borrow own item
    }
    // Check if item is available
    if (!item.isAvailable(startDay, endDay)) {
      return false;
    }
    // Calculate total cost
    int duration = (endDay - startDay) + 1;
    int totalCost = duration * item.getCostPerDay();

    // Check if borrower has enough credits
    if (borrower.getCredits() < totalCost) {
      return false;
    }
    // Create contract
    Contract contract = new Contract(borrower, item, startDay, endDay);
    contracts.add(contract);
    item.addContract(contract);

    return true;
  }

  /**
   * Lists all contracts.
   */
  public List<Contract> listContracts() {
    return Collections.unmodifiableList(contracts);
  }

  // Time Management

  /**
   * Advances the current day by one.
   */
  public void advanceDay() {
    time.advanceDay();
    int currentDay = time.getCurrentDay();

    // Transfer credits if a contract starts
    for (Contract contract : contracts) {
      if (contract.getStartDay() == currentDay && !contract.getIsPaid()) {
        int totalCost = contract.getTotalCost();
        Member borrower = contract.getLender();
        Member owner = contract.getItem().getOwner();
        borrower.deductCredits(totalCost);
        owner.addCredits(totalCost);
        contract.setPaid(true);
      }
    }
  }

  /**
   * Gets the current day.
   *
   * @return The current day.
   */
  public int getCurrentDay() {
    return time.getCurrentDay();
  }

  // Helper Methods (Private)

  /**
   * Checks if an email or phone number already exists in the system.
   */
  private boolean isEmailOrPhoneExists(String email, String phoneNumber) {
    for (Member member : members) {
      if (member.getEmail().equalsIgnoreCase(email)
          || member.getPhoneNumber().equals(phoneNumber)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks if an email already exists in the system.
   */
  private boolean isEmailExists(String email) {
    for (Member member : members) {
      if (member.getEmail().equalsIgnoreCase(email)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks if a phone number already exists in the system.
   */
  private boolean isPhoneExists(String phoneNumber) {
    for (Member member : members) {
      if (member.getPhoneNumber().equals(phoneNumber)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks if a member ID already exists in the system.
   */
  private boolean isMemberIdExists(String memberId) {
    for (Member member : members) {
      if (member.getMemberId().equals(memberId)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Retrieves a member by their ID.
   */
  private Member getMemberById(String memberId) {
    for (Member member : members) {
      if (member.getMemberId().equals(memberId)) {
        return member;
      }
    }
    return null;
  }

  /**
   * Retrieves a member by their email.
   */
  private Member getMemberByEmail(String email) {
    for (Member member : members) {
      if (member.getEmail().equalsIgnoreCase(email)) {
        return member;
      }
    }
    return null;
  }

  /**
   * Retrieves an item by its name and owner.
   */
  private Item getItemByNameAndOwner(String itemName, Member owner) {
    for (Item item : owner.getOwnedItems()) {
      if (item.getName().equalsIgnoreCase(itemName)) {
        return item;
      }
    }
    return null;
  }

  /**
   * Checks if the owner already has an item with the same name.
   */
  public boolean ownerHasItemWithName(Member owner, String itemName) {
    for (Item item : owner.getOwnedItems()) {
      if (item.getName().equalsIgnoreCase(itemName)) {
        return true;
      }
    }
    return false;
  }
}
