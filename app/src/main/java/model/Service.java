package model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class holds the all data from model classes to send them to the
 * Controller.
 * This is information expert och greater class.
 */
public class Service {
  private List<Member> members;
  private List<Admin> admins;
  private List<Item> items;
  private List<Contract> contracts;
  private Time time;
  private Member loggedInMember;

  /**
   * The Service class.
   */
  public Service() {
    members = new ArrayList<>();
    admins = new ArrayList<>();
    items = new ArrayList<>();
    contracts = new ArrayList<>();
    time = new Time();
    initializeStartObjects();
  }

  /**
   * Create start objects for application.
   */
  public void initializeStartObjects() {
    Member member1 = new Member("Etka", "etka@lending.com", 0031,
        "etka", "etka123", getTime());

    Member member2 = new Member("Sanaa", "sanaa@lending.com", 0022,
        "sanaa", "sanaa123", getTime());

    Member member3 = new Member("Aiman", "aiman@lending.com", 0062,
        "aiman", "aiman123", getTime());

    members.add(member1);
    members.add(member2);
    members.add(member3);

    Admin admin1 = new Admin("gadmin", "thegadmin03");
    admins.add(admin1);

    Item item1 = new Item("Electronics", "MacBook Pro",
        "A clean computer for temporary works", 30, member3, getTime());
    Item item2 = new Item("Veichle", "BMW M5 2021", "Max 100 miles per loan period.", 300,
        member1, getTime());

    items.add(item1);
    member3.addOwnedItem(item1);
    items.add(item2);
    member1.addOwnedItem(item2);
  }

  /**
   * To get username and password from user as input.
   */
  public String[] setCredentials() {
    String username = "etka";
    String password = "etka123";

    return new String[] { username, password };
  }

  /**
   * To login as member.
   */
  public Member memberLoginProcess() {
    boolean memberValidated = false;
    while (!memberValidated) {
      String[] memberCredentials = setCredentials();
      Member loggedInMember = validateMember(memberCredentials[0], memberCredentials[1]);
      if (loggedInMember != null) {
        memberValidated = true;
        return loggedInMember;
      } 
    }
    return null;
  }

  public void setLoggedInMember() {
    loggedInMember = memberLoginProcess();
  }

  public Member getLoggedInMember() {
    return loggedInMember;
  }

  /**
   * Login methods starts here.
   * For member login. To get the member by username.
   */
  public Member getMemberByUsername(String username) {
    for (Member member : members) {
      if (member.getUsername().equals(username)) {
        return member;
      }
    }
    return null;
  }

  /**
   * To find an item by its name.
   */
  public Item getItemByName(String itemname) {
    for (Item item : items) {
      if (item.getName().equals(itemname)) {
        return item;
      }
    }
    return null;
  }

  /**
   * For admin login. To get the admin by username.
   */
  public Admin getAdminByUsername(String username) {
    for (Admin admin : admins) {
      if (admin.getUsername().equals(username)) {
        return admin;
      }
    }
    return null;
  }

  /**
   * For admin login, (to get right password for the admin).
   */
  public Admin validateAdmin(String username, String password) {
    Admin admin = getAdminByUsername(username);
    if (admin != null && admin.getPassword().equals(password)) {
      return admin;
    }
    return null;
  }

  /**
   * For member login, (to get right password for the member).
   */
  public Member validateMember(String username, String password) {
    for (Member member : members) {
      if (member.getUsername().equals(username) && member.getPassword().equals(password)) {
        return member;
      }
    }
    return null;
  }

  public void createMemberAccount(String name, String email, int mobile,
      String username, String password) {
    Member newMember = new Member(name, email, mobile, username, password, time);
    members.add(newMember);
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

  // getters for lists
  public List<Member> getAllMembers() {
    return members;
  }

  public List<Item> getAllItems() {
    return items;
  }

  public Time getTime() {
    return time;
  }

  /**
   * To find the logged in member.
   */
  public Member getMember(int index) {
    if (index >= 0 && index < members.size()) {
      return members.get(index);
    } else {
      return null;
    }
  }

  /**
   * Delete or add methods starts here.
   * To delete an item.
   */
  public void deleteItem(int choice) {
    Item itemToDelete = items.get(choice - 1);
    itemToDelete.getOwner().getOwnedItems().remove(itemToDelete);
    itemToDelete.getOwner().addCredits(-100);
    items.remove(itemToDelete);
  }

  /**
   * To delete an item of an member.
   */
  public void deleteMemberItem(Item item) {
    item.getOwner().getOwnedItems().remove(item);
    item.getOwner().addCredits(-100);
    items.remove(item);
  }

  /**
   * To delete a member owned item.
   */
  public void deleteOwnedItem(String owner, String choice) {
    Member itemOwner = getMemberByUsername(owner);
    int itemChoice = Integer.parseInt(choice);
    Item itemToDelete = itemOwner.getOwnedItems().get(itemChoice - 1);
    itemToDelete.getOwner().getOwnedItems().remove(itemToDelete);
    itemToDelete.getOwner().addCredits(-100);
    items.remove(itemToDelete);
  }

  /**
   * To delete a member.
   */
  public void deleteMember(int choice) {
    Member memberToDelete = members.get(choice - 1);
    List<Item> itemsToDelete = memberToDelete.getOwnedItems();
    items.removeAll(itemsToDelete);
    members.remove(memberToDelete);
  }

  /**
   * To add new item.
   */
  public void addItem(String category, String name, String description,
      String stringCostPerDay, String stringOwner) {
    int costPerDay = Integer.parseInt(stringCostPerDay);
    Member owner = getMemberByUsername(stringOwner);
    Item item = new Item(category, name, description, costPerDay, owner, time);
    items.add(item);
    owner.addOwnedItem(item);
  }

  public void addMember(String name, String email, int mobile, String username, String password) {
    Member member = new Member(name, name, mobile, username, password, time);
    members.add(member);
  }

  /**
   * To create a new contract.
   */
  public void addContract(String stringStDate, String stringEnDate, String stringTheItem, 
      String stringTheLender, String stringCost) {
    int stDate = Integer.parseInt(stringStDate);
    int enDate = Integer.parseInt(stringEnDate);
    Member theLender = getMemberByUsername(stringTheLender);
    Item theItem = getItemByName(stringTheItem);
    int cost = Integer.parseInt(stringCost);
    Contract newContract = new Contract(stDate, enDate, theItem, theLender, cost);
    contracts.add(newContract);
    theLender.addOwnedContract(newContract);
  }

  public void dayCounter() {
    int nextDay = time.getDate() + 1;
    time.setDate(nextDay);
  }

  public Item setItem(int getItemChoice) {
    Item itemToUpdate = items.get(getItemChoice - 1);
    return itemToUpdate;
  }
}
