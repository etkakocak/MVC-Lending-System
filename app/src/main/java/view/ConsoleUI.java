package view;

import java.util.List;
import java.util.Scanner;
import model.Admin;
import model.Contract;
import model.Item;
import model.Member;
import model.Service;

/**
 * This is the main view class.
 * This is for user Console.
 */
public class ConsoleUi implements ViewInterface {
  private Scanner scanner;

  public ConsoleUi() {
    this.scanner = new Scanner(System.in, "UTF-8");
  }

  /**
   * This method creates start members.
   */
  public Member[] initializeStartMembers(Service service) {
    Member member1 = new Member("Etka", "etka@lending.com", 0031, 
          "etka", "etka123", service.getTime());

    Member member2 = new Member("Sanaa", "sanaa@lending.com", 0022, 
          "sanaa", "sanaa123", service.getTime());

    Member member3 = new Member("Aiman", "aiman@lending.com", 0062, 
          "aiman", "aiman123", service.getTime());

    return new Member[] { member1, member2, member3 };
  }

  public Admin initializeStartAdmin(Service service) {
    Admin admin1 = new Admin("gadmin", "thegadmin03");
    return admin1;
  }

  /**
   * This method creates start items.
   */
  public Item[] initializeStartItems(Service service) {
    Member member1 = service.getMember(0);
    Member member3 = service.getMember(2);
    Item item1 = new Item("Electronics", "MacBook Pro", 
        "A clean computer for temporary works", 30, member3, service.getTime());
    Item item2 = new Item("Veichle", "BMW M5 2021", "Max 100 miles per loan period.", 300, member1,
        service.getTime());

    return new Item[] { item1, item2 };
  }

  /**
   * To get username and password from user as input.
   */
  public String[] getCredentials() {
    System.out.print("Enter username: ");
    String username = scanner.nextLine();

    System.out.print("Enter password: ");
    String password = scanner.nextLine();

    return new String[] { username, password };
  }

  /**
   * To get the type of login.
   */
  public int getLoginType() {
    int choice = scanner.nextInt();
    scanner.nextLine();
    return choice;
  }

  public void displayError() {
    System.out.println("\nError: Bad login choice!");
  }

  /**
   * To print login menu.
   */
  public void displayLoginMenu() {
    System.out.println("Welcome to the Stuff Lending System Login!");
    System.out.println("1. Login as Admin");
    System.out.println("2. Login as Member");
    System.out.println("3. Create Member Account");
    System.out.print("Enter your choice: ");
  }

  /**
   * To login as admin.
   */
  public Admin adminLoginProcess(Service service) {
    boolean adminValidated = false;
    while (!adminValidated) {
      String[] adminCredentials = getCredentials();
      Admin admin = service.validateAdmin(adminCredentials[0], adminCredentials[1]);
      if (admin != null) {
        adminValidated = true;
        return admin;
      } else {
        System.out.println("Invalid admin credentials. Try again or type 'exit' to quit.");
        String exitChoice = scanner.nextLine();
        if ("exit".equalsIgnoreCase(exitChoice)) {
          break;
        }
      }
    }
    return null;
  }

  /**
   * To login as member.
   */
  public Member memberLoginProcess(Service service) {
    boolean memberValidated = false;
    while (!memberValidated) {
      String[] memberCredentials = getCredentials();
      Member loggedInMember = service.validateMember(memberCredentials[0], memberCredentials[1]);
      if (loggedInMember != null) {
        memberValidated = true;
        return loggedInMember;
      } else {
        System.out.println("Invalid member credentials. Try again or type 'exit' to quit.");
        String exitChoice = scanner.nextLine();
        if ("exit".equalsIgnoreCase(exitChoice)) {
          break;
        }
      }
    }
    return null;
  }

  /**
   * To create a new account.
   */
  public Member memberCreateProcess(Service service) {
    boolean memberValidated = false;
    while (!memberValidated) {
      System.out.print("Enter your name: ");
      String name = scanner.nextLine();

      System.out.print("Enter your username: ");
      String username = scanner.nextLine();

      System.out.print("Enter your password: ");
      String password = scanner.nextLine();

      System.out.print("Enter your email: ");
      String email = scanner.nextLine();
      
      System.out.print("Enter your phone number: ");
      int mobile = scanner.nextInt();
      scanner.nextLine();

      if (service.canAddMember(email, mobile)) {
        service.createMemberAccount(name, email, mobile, username, password);
        System.out.println("Member account created successfully.");

        Member newMember = service.validateMember(username, password);
        memberValidated = true;
        return newMember;
      } else {
        System.out.println(
            "E-post eller mobilnummer används redan! Please try again or type 'exit' to quit..");
        String exitChoice = scanner.nextLine();
        if ("exit".equalsIgnoreCase(exitChoice)) {
          break;
        }
      }
    }
    return null;
  }

  /**
   * To print main menu for member.
   */
  public int displayMainMenu() {
    int choice = 0;
    while (choice != 5) {
      System.out.println("\n---- Main Menu ----");
      System.out.println("1. Post an item");
      System.out.println("2. Display all items");
      System.out.println("3. View member details");
      System.out.println("4. Advance the day counter");
      System.out.println("5. Exit");
      System.out.print("Enter your choice: ");
      choice = scanner.nextInt();
      return choice;
    }
    return 5;
  }

  public void displayBadChoice() {
    System.out.println("Invalid choice. Please try again.");
  }

  /**
   * Member can post an item.
   */
  public Item postAnItem(Member loggedInMember, Service service) {
    System.out.println("\nEnter item category (Tool, Vehicle, Game, Toy, Sport, Other): ");
    String category = scanner.nextLine();
    category = scanner.nextLine();

    System.out.println("Enter item name: ");
    String name = scanner.nextLine();

    System.out.println("Enter item description: ");
    String descContent = scanner.nextLine();

    System.out.println("Enter cost per day to lend the item: ");
    int costPerDay = scanner.nextInt();

    Item newItem = new Item(category, name, descContent, costPerDay, 
          loggedInMember, service.getTime());
    System.out.println("Item posted successfully!");
    return newItem;
  }

  /**
   * Member can display all availible items.
   */
  public Contract displayAllItems(Member loggedInMember, Service service) {
    List<Item> items = service.getAllItems();

    System.out.println("\nItems available for lending:");
    for (int i = 0; i < items.size(); i++) {
      System.out.println((i + 1) + ". " + items.get(i).getCategory() + " / " 
          + items.get(i).getName() + " / " + items.get(i).getDescription() 
          + "\nCost per day: " + items.get(i).getCostPerDay()
          + "\nCreated: Day " + items.get(i).getCreationDate());
    }

    System.out.println("\nEnter the number of the item you want to loan or 0 to go back: ");
    int choice = scanner.nextInt();

    if (choice > 0 && choice <= items.size()) {
      Item selectedItem = items.get(choice - 1);

      // Check if the member is trying to borrow their own item
      if (loggedInMember.equals(selectedItem.getOwner())) {
        System.out.println("You cannot borrow your own item.");
        return null;
      }

      System.out.println("How many days do you want to loan the item?");
      int loanDays = scanner.nextInt();

      int totalCost = loanDays * selectedItem.getCostPerDay();

      if (loggedInMember.getCredits() >= totalCost) {
        int startDate = service.getTime().getDate();
        int endDate = startDate + loanDays;
        service.getTime().setDate(endDate);

        System.out.println("Item loaned successfully!");
        System.out.println("\n--------- Loan Receipt ---------");
        System.out.println("Borrower: " + loggedInMember.getName());
        System.out.println("Item Loaned: " + selectedItem.getName());
        System.out.println("Start Date: Day " + startDate);
        System.out.println("End Date: Day " + endDate);
        System.out.println("Total Cost: " + totalCost + " credits");
        System.out.println("Owner of the Item: " + selectedItem.getOwner().getName());
        System.out.println("--------------------------------");
        Contract contract = new Contract(startDate, endDate, selectedItem, 
            loggedInMember, totalCost);
        return contract;
      } else {
        System.out.println("You don't have enough credits to loan this item.");
        return null;
      }
    }
    return null;
  }

  /**
   * To go on the next day.
   */
  public void advanceDayCounter(Service service) {
    System.out.println("\nAdvancing the day...");
    service.dayCounter();
    System.out.println("You are now on day " + service.getTime());
  }

  /**
   * Member can view their account details and change them.
   */
  public int viewMemberDetails(Member loggedInMember, Service service) {
    System.out.println("\nYour account details: ");
    System.out.println("Name: " + loggedInMember.getName());
    System.out.println("Username: " + loggedInMember.getUsername());
    System.out.println("Member ID: " + loggedInMember.getMemberId());
    System.out.println("Email: " + loggedInMember.getEmail());
    System.out.println("Phone number: " + loggedInMember.getMobile());
    System.out.println("Account is created: Day " + loggedInMember.getCreationDate());
    System.out.println("Your credit: " + loggedInMember.getCredits());

    System.out.println("\nCreated Contracts:");
    List<Contract> contracts = loggedInMember.getContracts();
    for (int i = 0; i < contracts.size(); i++) {
      System.out.println((i + 1) + ". " + contracts.get(i).getItem().getName()
          + "\nOwner: " + contracts.get(i).getItem().getOwner().getName()
          + "\nStart Date: Day " + contracts.get(i).getStartDate()
          + "\nEnd Date: Day " + contracts.get(i).getEndDate());
    }

    System.out.println("\nPosted Items:");
    List<Item> items = loggedInMember.getOwnedItems();
    for (int i = 0; i < items.size(); i++) {
      System.out.println((i + 1) + ". " + items.get(i).getCategory() + " / " 
          + items.get(i).getName() + " / " + items.get(i).getDescription() 
          + "\nCost per day: " + items.get(i).getCostPerDay()
          + "\nCreated: Day " + items.get(i).getCreationDate());
    }

    int setChoice = 0;
    while (setChoice != 4) {
      System.out.println("\n---- Settings Menu ----");
      System.out.println("1. Change member information");
      System.out.println("2. Change item information");
      System.out.println("3. Delete an item");
      System.out.println("4. Exit");
      System.out.print("Enter your choice: ");

      setChoice = scanner.nextInt();
      return setChoice;
    }
    return 4;
  }

  /**
   * If the member wants to change their account informations.
   */
  public int memberSettingMenu() {
    int memberChoice = 0;
    while (memberChoice != 3) {
      System.out.println("\n---- Member Settings Menu ----");
      System.out.println("1. Change your name");
      System.out.println("2. Change your password");
      System.out.println("3. Exit");
      System.out.print("Enter your choice: ");
      memberChoice = scanner.nextInt();
      scanner.nextLine();
      return memberChoice;
    }
    return 3;
  }

  /**
   * If the member wants a new name.
   */
  public String newName() {
    System.out.print("Enter your new name: ");
    String name = scanner.nextLine();
    return name;
  }

  /**
   * If the member wants to change password.
   */
  public String newPassword() {
    System.out.print("Enter your new password: ");
    String password = scanner.nextLine();
    return password;
  }

  /**
   * If the member wants to change informations of an owned item.
   */
  public Item setItem(Service service) {
    System.out.println("\nEnter the number of the item if you want to update the informations: ");
    int getItemChoice = scanner.nextInt();
    Item itemToUpdate = service.items.get(getItemChoice - 1);
    return itemToUpdate;
  }

  /**
   * To change item informations.
   */
  public int itemSettingMenu() {
    System.out.println("\n---- Item Settings Menu ----");
    System.out.println("1. Change items category");
    System.out.println("2. Change items name");
    System.out.println("3. Change items description");
    System.out.println("4. Change items cost per day");
    System.out.println("5. Exit");
    System.out.print("Enter your choice: ");
    int itemChoice = scanner.nextInt();
    scanner.nextLine();
    return itemChoice;
  }

  /**
   * To update items category.
   */
  public String setCategory() {
    System.out.print("Enter new category: ");
    String category = scanner.nextLine();
    return category;
  }

  /**
   * To update items name.
   */
  public String setItemName() {
    System.out.print("Enter new name: ");
    String itemName = scanner.nextLine();
    return itemName;
  }

  /**
   * To update items description.
   */
  public String setDescription() {
    System.out.print("Enter new description: ");
    String description = scanner.nextLine();
    return description;
  }

  /**
   * To update items cost.
   */
  public int setItemCost() {
    System.out.print("Enter new cost: ");
    int itemCost = scanner.nextInt();
    return itemCost;
  }

  /**
   * To delete an item.
   */
  public Item setItemToDelete(Member loggedInMember) {
    System.out.println("\nEnter the number of the item if you want to delete it or 0 to go back: ");
    int choice = scanner.nextInt();

    if (choice > 0 && choice <= loggedInMember.getOwnedItems().size()) {
      Item itemToDelete = loggedInMember.getOwnedItems().get(choice - 1);
      System.out.println("Item deleted successfully!");
      return itemToDelete;
    }
    return null;
  }

  public void displayGood() {
    System.out.println("Updated successfully!");
  }

  /**
   * To print admin menu.
   */
  public int displayAdminMenu() {
    int choice = 0;
    while (choice != 3) {
      System.out.println("\n---- Admin Panel ----");
      System.out.println("1. Display all members");
      System.out.println("2. Display all items");
      System.out.println("3. Exit");
      System.out.print("Enter your choice: ");
      choice = scanner.nextInt();
      return choice;
    }
    return 3;
  }

  /**
   * The admin can display all members. 
   */
  public Member displayAllMembers(Service service) {
    List<Member> members = service.getAllMembers();
    System.out.println("\nAll Members: ");
    for (int i = 0; i < members.size(); i++) {
      System.out.println((i + 1) + ". " + members.get(i).getMemberId() + " / " 
          + members.get(i).getUsername() + "\nName: " + members.get(i).getName() 
          + "\nEmail: " + members.get(i).getEmail() + "\nPhone number: " 
          + members.get(i).getMobile() + "\nCredits: " + members.get(i).getCredits()
          + "\nAccount is created: Day " + members.get(i).getCreationDate());
    }

    System.out.println("\nEnter the number of the member you want to ban or 0 to go back: ");
    int choice = scanner.nextInt();

    if (choice > 0 && choice <= members.size()) {
      Member memberToDelete = service.members.get(choice - 1);
      System.out.println("Member is banned and all owned items is deleted!");
      return memberToDelete;
    }
    return null;
  }

  /**
   * The admin can display all items.
   */
  public Item displayAllItemsAdmin(Service service) {
    List<Item> items = service.getAllItems();
    for (int i = 0; i < items.size(); i++) {
      System.out.println((i + 1) + ". " + items.get(i).getCategory() 
          + " / " + items.get(i).getName() + " / " + items.get(i).getDescription() 
          + "\nOwner: " + items.get(i).getOwner()
          + "\nCost per day: " + items.get(i).getCostPerDay()
          + "\nCreated: Day " + items.get(i).getCreationDate());
    }

    System.out.println("\nEnter the number of the item if you want to delete it or 0 to go back: ");
    int choice = scanner.nextInt();

    if (choice > 0 && choice <= items.size()) {
      Item itemToDelete = items.get(choice - 1);
      System.out.println("Item deleted successfully!");
      return itemToDelete;
    }
    return null;
  }
}
