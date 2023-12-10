package model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class holds the all data from model classes to send them to the
 * Controller.
 */
public class Service {
  private final Time time;
  private List<Member> members = new ArrayList<>();
  private List<Item> items = new ArrayList<>();
  private List<Contract> contracts = new ArrayList<>();
  private final GenerateId generateId = new GenerateId();
  private final ArrayList<String> existingMemberIds = new ArrayList<>();

  /**
   * The Service class.
   */
  public Service(Time time) {
    this.time = time;
  }

  /**
   * Create start objects for application.
   */
  public void initializeStartObjects() {
    Member member1 = new Member("Etka", generateId(), "etka@lending.com", 3131, time);

    Member member2 = new Member("Sanaa", generateId(), "sanaa@lending.com", 2222, time);

    Member member3 = new Member("Aiman", generateId(), "aiman@lending.com", 6262, time);

    members.add(member1);
    members.add(member2);
    members.add(member3);

    Item item1 = new Item("Electronics", "MacBook Pro",
        "A clean computer for temporary works", 30, time, generateId(), member3);
    Item item2 = new Item("Veichle", "BMW M5 2021",
        "Max 100 miles per loan period.", 300, time, generateId(), member1);

    items.add(item1);
    items.add(item2);
    member3.addOwnedItem(item1);
    member1.addOwnedItem(item2);
  }

  /**
   * To generate an id.
   */
  public String generateId() {
    String objectId;
    do {
      objectId = generateId.generateNewId();
    } while (existingMemberIds.contains(objectId));
    existingMemberIds.add(objectId);
    return objectId;
  }

  /**
   * To create a new member.
   */
  public boolean createMemberAccount(String name, String email, int mobile, Time crTime) {
    if (canAddMember(email, mobile)) {
      Member newMember = new Member(name, generateId(), email, mobile, crTime);
      members.add(newMember);
      return true;
    } else {
      return false;
    }
  }

  /**
   * To uptade a member.
   */
  public boolean updateMemberAccount(Member member, String name, String email, int mobile) {
    if (canAddMember(email, mobile)) {
      member.setName(name);
      member.setEmail(email);
      member.setMobile(mobile);
      return true;
    } else {
      return false;
    }
  }

  /**
   * To uptade an item.
   */
  public boolean updateItem(Item item, String name, String category, 
        String description, int price) {
    try {
      item.setName(name);
      item.setCategory(category);
      item.setDescription(description);
      item.setCostPerDay(price);
      return true;
    } catch (IllegalArgumentException e) {
      return false;
    }
  }

  /**
   * This controls if the account can be created.
   * Check if the new member has same email or number as another member.
   */
  public boolean canAddMember(String email, int mobile) {
    for (Member member : members) {
      if (member.getEmail().equals(email) || member.getMobile() == mobile) {
        return false;
      }
    }
    return true;
  }

  /**
   * To get list of members.
   */
  public List<Member> getAllMembers() {
    return new ArrayList<>(members);
  }

  /**
   * To get list of items.
   */
  public List<Item> getAllItems() {
    return new ArrayList<>(items);
  }

  /**
   * To get list of contracts.
   */
  public List<Contract> getAllContracts() {
    return new ArrayList<>(contracts);
  }

  /**
   * To delete a member.
   */
  public void deleteMember(Member memberToDelete) {
    members.remove(memberToDelete);
  }

  /**
   * To delete an item.
   */
  public void deleteItem(Item itemToDelete) {
    items.remove(itemToDelete);
  }

  /**
   * To create a new contract.
   */
  public void addContract(Item item, Member lender, int startDate,
      int endDate, int cost) {
    Contract newContract = new Contract(startDate, endDate, item, lender, cost);
    contracts.add(newContract);
    lender.addCredits(-cost);
  }

  /**
   * To create a new item.
   */
  public void addItem(String name, String category, String description, int cost,
      Time currentDay, Member owner) {
    Item newItem = new Item(category, name, description, cost, currentDay, generateId(), owner);
    items.add(newItem);
  }

  /**
   * Find a member by its name.
   */
  public Member findMember(String memberId) {
    for (Member member : members) {
      if (member.getMemberId().equals(memberId)) {
        return member;
      }
    }
    return null;
  }

  /**
   * Find an item by its name.
   */
  public Item findItem(String itemId) {
    for (Item item : items) {
      if (item.getItemId().equals(itemId)) {
        return item;
      }
    }
    return null;
  }
}
