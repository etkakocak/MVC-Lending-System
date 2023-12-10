package view;

import java.util.List;
import java.util.Scanner;
import model.Contract;
import model.Item;
import model.Member;

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
   * To print main menu.
   */
  public void displayMainMenu() {
    System.out.println("\n---- Main Menu ----");
    System.out.println("1. Go to Member Menu");
    System.out.println("2. Go to Item Menu");
    System.out.println("3. Go to Contract Menu");
    System.out.println("4. Advance the day counter");
    System.out.println("5. Exit");
    System.out.print("Enter your choice: ");
  }

  /**
   * To print member menu.
   */
  public void displayMemberMenu() {
    System.out.println("\n---- Member Menu ----");
    System.out.println("1. Delete a member");
    System.out.println("2. Create a member");
    System.out.println("3. List all members");
    System.out.println("4. View member info");
    System.out.println("5. Change member info");
    System.out.println("6. Exit");
    System.out.print("Enter your choice: ");
  }

  /**
   * To print item menu.
   */
  public void displayItemMenu() {
    System.out.println("\n---- Item Menu ----");
    System.out.println("1. Delete an item");
    System.out.println("2. Create an item");
    System.out.println("3. List all items");
    System.out.println("4. Change item info");
    System.out.println("5. Exit");
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

  public void sendOutput(String output) {
    System.out.println(output);
  }

  /**
   * To get string inputs.
   */
  public String getString() {
    while (!scanner.hasNext()) {
    }
    String input = scanner.nextLine();
    if (input.isEmpty() && scanner.hasNextLine()) {
      input = scanner.nextLine();
    }
    return input;
  }

  /**
   * To get int inputs.
   */
  public int getInt() {
    return scanner.nextInt();
  }

  /**
   * Member can view their account details and change them.
   */
  public void viewMemberDetails(Member member) {
    System.out.println("\nMember details: ");
    System.out.println("Name: " + member.getName());
    System.out.println("Member ID: " + member.getMemberId());
    System.out.println("Email: " + member.getEmail());
    System.out.println("Phone number: " + member.getMobile());
    System.out.println("Account is created: Day "
        + member.getCreationDate());
    System.out.println("Member credit: " + member.getCredits());
  }

  /**
   * To list all members.
   */
  public void listMembers(List<Member> members) {
    System.out.println("\nList of Members:");
    for (int i = 0; i < members.size(); i++) {
      System.out.println((i + 1) + ". " + members.get(i).getMemberId() + " / "
          + "\nName: " + members.get(i).getName()
          + "\nEmail: " + members.get(i).getEmail() + "\nPhone number: "
          + members.get(i).getMobile() + "\nCredits: " + members.get(i).getCredits()
          + "\nAccount is created: Day " + members.get(i).getCreationDate());
    }
  }

  /**
   * To list all items.
   */
  public void listItems(List<Item> items) {
    System.out.println("\nList of Items:");
    for (int i = 0; i < items.size(); i++) {
      System.out.println((i + 1) + ". " + items.get(i).getCategory()
          + " / " + items.get(i).getName() + " / " + items.get(i).getDescription()
          + "\nID: " + items.get(i).getItemId()
          + "\nCost per day: " + items.get(i).getCostPerDay()
          + "\nCreated: Day " + items.get(i).getCreationDate());
    }
  }

  /**
   * To list all contracts.
   */
  public void listContracts(List<Contract> contracts) {
    System.out.println("\nList of Contracts:");
    for (Contract contract : contracts) {
      System.out.println("\n--------- Loan Receipt ---------");
      System.out.println("Lender: " + contract.getLender());
      System.out.println("Item Loaned: " + contract.getItem());
      System.out.println("Start Date: Day " + contract.getStartDate());
      System.out.println("End Date: Day " + contract.getEndDate());
      System.out.println("Total Cost: " + contract.getCost() + " credits");
      System.out.println("--------------------------------");
    }
  }
}
