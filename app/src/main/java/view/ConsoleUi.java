package view;

import java.util.List;
import java.util.Scanner;
import model.Contract;
import model.Item;
import model.Member;
import model.Service;

/**
 * This is the main view class.
 * This is for user the user console.
 */
public class ConsoleUi implements ViewInterface {
  private Scanner scanner;
  private int choice;

  public ConsoleUi() {
    this.scanner = new Scanner(System.in, "UTF-8");
  }

  @Override
  public void getUserChoice() {
    choice = getInput();
  }

  public void welcome() {
    System.out.println("Welcome to the stuff lending system...");
  }

  /**
   * To print main menu for member.
   */
  public void displayMainMenu() {
    System.out.println("\n---- Main Menu ----");
    System.out.println("1. Post an item");
    System.out.println("2. Display all items (to loan)");
    System.out.println("3. View member details");
    System.out.println("4. Advance the day counter");
    System.out.println("5. Display all members");
    System.out.println("6. Create a new member");
    System.out.println("7. Display all items (to delete)");
    System.out.println("8. Exit");
    System.out.print("Enter your choice: ");
  }

  public void displayBadChoice() {
    System.out.println("Invalid choice. Please try again.");
  }

  /**
   * To get user inputs in view and delete hidden
   * dependency between view and controller.
   */
  public int getInput() {
    try {
      int c = System.in.read();
      while (c == '\r' || c == '\n') {
        c = System.in.read();
      }
      return c;
    } catch (java.io.IOException e) {
      System.out.println("" + e);
      return 0;
    }
  }

  @Override
  public boolean first() {
    return choice == '1';
  }

  @Override
  public boolean second() {
    return choice == '2';
  }

  @Override
  public boolean third() {
    return choice == '3';
  }

  @Override
  public boolean fourth() {
    return choice == '4';
  }

  @Override
  public boolean fifth() {
    return choice == '5';
  }

  @Override
  public boolean sixth() {
    return choice == '6';
  }

  @Override
  public boolean seventh() {
    return choice == '7';
  }

  @Override
  public boolean eight() {
    return choice == '8';
  }

  /**
   * To create a new member.
   */
  public String [] createMember() {
    System.out.println("Enter member name: ");
    String name = scanner.nextLine();
    name = scanner.nextLine();
    System.out.println("Enter member email: ");
    String email = null;
    email = scanner.nextLine();
    System.out.println("Enter member mobile: ");
    String mobile = scanner.nextLine();
    System.out.println("Enter member username: ");
    String username = scanner.nextLine();
    System.out.println("Enter member password: ");
    String password = scanner.nextLine();
    return new String[] { name, email, mobile, username, password };
  }

  /**
   * Member can post an item.
   */
  public String[] postAnItem(Service service) {
    System.out.println("\nEnter item category (Tool, Vehicle, Game, Toy, Sport, Other): ");
    scanner.nextLine();
    String category = null;
    category = scanner.nextLine();

    System.out.println("Enter item name: ");
    String name = null;
    name = scanner.nextLine();

    System.out.println("Enter item description: ");
    String descContent = null;
    descContent = scanner.nextLine();

    System.out.println("Enter cost per day to lend the item: ");
    int costPerDay = scanner.nextInt();
    scanner.nextLine();

    System.out.println("Item posted successfully!");
    return new String[] { category, name, descContent, String.valueOf(costPerDay),
        service.getLoggedInMember() };
  }

  /**
   * Member can display all availible items.
   */
  public String[] displayAllItems(List<Item> items, Service service) {
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
      String memberName = service.getLoggedInMember();
      String itemOwner = service.getItemByName(selectedItem.getName()).getOwner();
      if (memberName.equals(itemOwner)) {
        System.out.println("You cannot borrow your own item.");
        return null;
      }

      System.out.println("How many days do you want to loan the item?");
      int loanDays = scanner.nextInt();

      int totalCost = loanDays * selectedItem.getCostPerDay();

      if (service.getMemberByUsername(memberName).getCredits() >= totalCost) {
        int startDate = service.getTime().getDate();
        int endDate = startDate + loanDays;

        System.out.println("Item loaned successfully!");
        System.out.println("\n--------- Loan Receipt ---------");
        System.out.println("Borrower: " + service.getLoggedInMember());
        System.out.println("Item Loaned: " + selectedItem.getName());
        System.out.println("Start Date: Day " + startDate);
        System.out.println("End Date: Day " + endDate);
        System.out.println("Total Cost: " + totalCost + " credits");
        System.out.println("Owner of the Item: "
            + service.getMemberByUsername(selectedItem.getOwner()).getName());
        System.out.println("--------------------------------");
        return new String[] { String.valueOf(startDate), String.valueOf(endDate),
            selectedItem.getName(), service.getLoggedInMember(),
            String.valueOf(totalCost) };
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
    System.out.println("You are now on day " + service.getTime());
  }

  /**
   * Member can view their account details and change them.
   */
  public void viewMemberDetails(Service service) {
    String memberName = service.getLoggedInMember();
    System.out.println("\nYour account details: ");
    System.out.println("Name: " + service.getMemberByUsername(memberName).getName());
    System.out.println("Username: " + memberName);
    System.out.println("Member ID: " + service.getMemberByUsername(memberName).getMemberId());
    System.out.println("Email: " + service.getMemberByUsername(memberName).getEmail());
    System.out.println("Phone number: " + service.getMemberByUsername(memberName).getMobile());
    System.out.println("Account is created: Day " 
        + service.getMemberByUsername(memberName).getCreationDate());
    System.out.println("Your credit: " + service.getMemberByUsername(memberName).getCredits());

    System.out.println("\nCreated Contracts:");
    List<Contract> contracts = service.getMemberByUsername(memberName).getContracts();
    for (int i = 0; i < contracts.size(); i++) {
      System.out.println((i + 1) + ". " + contracts.get(i).getItem()
          + "\nStart Date: Day " + contracts.get(i).getStartDate()
          + "\nEnd Date: Day " + contracts.get(i).getEndDate());
    }

    System.out.println("\nPosted Items:");
    List<Item> items = service.getMemberByUsername(memberName).getOwnedItems();
    for (int i = 0; i < items.size(); i++) {
      System.out.println((i + 1) + ". " + items.get(i).getCategory() + " / "
          + items.get(i).getName() + " / " + items.get(i).getDescription()
          + "\nCost per day: " + items.get(i).getCostPerDay()
          + "\nCreated: Day " + items.get(i).getCreationDate());
    }

    System.out.println("\n---- Settings Menu ----");
    System.out.println("1. Change member information");
    System.out.println("2. Change item information");
    System.out.println("3. Delete an item");
    System.out.println("4. Exit");
    System.out.print("Enter your choice: ");
  }

  /**
   * If the member wants to change their account informations.
   */
  public void memberSettingMenu() {
    System.out.println("\n---- Member Settings Menu ----");
    System.out.println("1. Change your name");
    System.out.println("2. Change your password");
    System.out.println("3. Exit");
    System.out.print("Enter your choice: ");
  }

  /**
   * If the member wants a new name.
   */
  public String newName() {
    System.out.print("Enter your new name: ");
    String name = scanner.nextLine();
    name = scanner.nextLine();
    return name;
  }

  /**
   * If the member wants to change password.
   */
  public String newPassword() {
    System.out.print("Enter your new password: ");
    String password = scanner.nextLine();
    password = scanner.nextLine();
    return password;
  }

  /**
   * If the member wants to change informations of an owned item.
   */
  public int setItem() {
    System.out.println("\nEnter the number of the item if you want to update the informations: ");
    int getItemChoice = scanner.nextInt();
    scanner.nextLine();
    return getItemChoice;
  }

  /**
   * To change item informations.
   */
  public void itemSettingMenu() {
    System.out.println("\n---- Item Settings Menu ----");
    System.out.println("1. Change items category");
    System.out.println("2. Change items name");
    System.out.println("3. Change items description");
    System.out.println("4. Change items cost per day");
    System.out.println("5. Exit");
    System.out.print("Enter your choice: ");
  }

  /**
   * To update items category.
   */
  public String setCategory() {
    System.out.print("Enter new category: ");
    String category = scanner.nextLine();
    category = scanner.nextLine();
    return category;
  }

  /**
   * To update items name.
   */
  public String setItemName() {
    System.out.print("Enter new name: ");
    String itemName = scanner.nextLine();
    itemName = scanner.nextLine();
    return itemName;
  }

  /**
   * To update items description.
   */
  public String setDescription() {
    System.out.print("Enter new description: ");
    String description = scanner.nextLine();
    description = scanner.nextLine();
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
  public String[] setItemToDelete(Service service) {
    System.out.println("\nEnter the number of the item if you want to delete it or 0 to go back: ");
    int choice = scanner.nextInt();

    String memberName = service.getLoggedInMember();
    if (choice > 0 && choice <= service.getMemberByUsername(memberName).getOwnedItems().size()) {
      System.out.println("Item deleted successfully!");
      return new String[] { service.getLoggedInMember(), String.valueOf(choice) };
    }
    return null;
  }

  public void displayGood() {
    System.out.println("Updated successfully!");
  }

  /**
   * The admin can display all members.
   */
  public int displayAllMembers(Service service) {
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
      System.out.println("Member is banned and all owned items is deleted!");
      return choice;
    }
    return 0;
  }

  /**
   * The admin can display all items.
   */
  public int displayAllItemsAdmin(List<Item> items, Service service) {
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
      System.out.println("Item deleted successfully!");
      return choice;
    }
    return 0;
  }
}
