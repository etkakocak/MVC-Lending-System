package view;

import java.util.List;
import java.util.Scanner;
import model.Contract;
import model.Item;
import model.Member;

/**
 * This is the main view class in Swedish.
 * This is for user the user console.
 */
public class SwedishConsoleUi implements ViewInterface {
  private Scanner scanner;
  private int choice;

  public SwedishConsoleUi() {
    this.scanner = new Scanner(System.in, "UTF-8");
  }

  @Override
  public void getUserChoice() {
    choice = getInput();
  }

  public void welcome() {
    System.out.println("Välkommen till stuff lending system...");
  }

  /**
   * To print main menu.
   */
  public void displayMainMenu() {
    System.out.println("\n---- Huvudmeny ----");
    System.out.println("1. Gå till medlemsmenyn");
    System.out.println("2. Gå till item menyn");
    System.out.println("3. Gå till kontraktmenyn");
    System.out.println("4. Simulera till nästa dag");
    System.out.println("5. Exit");
    System.out.print("Ange ditt val: ");
  }

  /**
   * To print member menu.
   */
  public void displayMemberMenu() {
    System.out.println("\n---- Medlemsmenyn ----");
    System.out.println("1. Radera en medlem");
    System.out.println("2. Skapa en medlem");
    System.out.println("3. Lista alla medlemmar");
    System.out.println("4. Visa medlemsinfo");
    System.out.println("5. Ändra medlemsinfo");
    System.out.println("6. Exit");
    System.out.print("Ange ditt val: ");
  }

  /**
   * To print item menu.
   */
  public void displayItemMenu() {
    System.out.println("\n---- Item Menyn ----");
    System.out.println("1. Radera en item");
    System.out.println("2. Skapa en item");
    System.out.println("3. Lista alla items");
    System.out.println("4. Ändra item info");
    System.out.println("5. Exit");
    System.out.print("Ange ditt val: ");
  }

  public void displayBadChoice() {
    System.out.println("Ogiltigt val. Var god försök igen.");
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
    System.out.println("\nMedlemsdetaljer: ");
    System.out.println("Namn: " + member.getName());
    System.out.println("Medlem ID: " + member.getMemberId());
    System.out.println("Email: " + member.getEmail());
    System.out.println("Mobil: " + member.getMobile());
    System.out.println("Kontot är skapat: Dag "
        + member.getCreationDate());
    System.out.println("Medlemskredit: " + member.getCredits());
  }

  /**
   * To list all members.
   */
  public void listMembers(List<Member> members) {
    System.out.println("\nLista av medlemmar:");
    for (int i = 0; i < members.size(); i++) {
      System.out.println((i + 1) + ". " + members.get(i).getMemberId() + " / "
          + "\nNamn: " + members.get(i).getName()
          + "\nEmail: " + members.get(i).getEmail() + "\nMobil: "
          + members.get(i).getMobile() + "\nKredit: " + members.get(i).getCredits()
          + "\nKontot är skapat: Dag " + members.get(i).getCreationDate());
    }
  }

  /**
   * To list all items.
   */
  public void listItems(List<Item> items) {
    System.out.println("\nLista av items:");
    for (int i = 0; i < items.size(); i++) {
      System.out.println((i + 1) + ". " + items.get(i).getCategory()
          + " / " + items.get(i).getName() + " / " + items.get(i).getDescription()
          + "\nID: " + items.get(i).getItemId()
          + "\nKostnad per dag: " + items.get(i).getCostPerDay()
          + "\nSkapat: Dag " + items.get(i).getCreationDate());
    }
  }

  /**
   * To list all contracts.
   */
  public void listContracts(List<Contract> contracts) {
    System.out.println("\nLista av kontrakten:");
    for (Contract contract : contracts) {
      System.out.println("\n--------- Lånekvitto ---------");
      System.out.println("Långivare: " + contract.getLender());
      System.out.println("Lånad item: " + contract.getItem());
      System.out.println("Startdag: Dag " + contract.getStartDate());
      System.out.println("Slutdag: Dag " + contract.getEndDate());
      System.out.println("Total kostnaden: " + contract.getCost() + " krediter");
      System.out.println("--------------------------------");
    }
  }
}
