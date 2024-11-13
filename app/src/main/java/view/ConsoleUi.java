package view;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Handles user interaction via the console.
 */
public class ConsoleUi implements ViewInterface {
  private Scanner scanner;

  /**
   * Constructor for ConsoleUi.
   * Initializes the scanner for user input.
   */
  public ConsoleUi() {
    scanner = new Scanner(System.in, StandardCharsets.UTF_8);
  }

  /**
   * Displays the main menu to the user.
   */
  @Override
  public void displayMenu() {
    System.out.println("==== Stuff Lending System ====");
    System.out.println("1.  Add Member");
    System.out.println("2.  List Members (Simple)");
    System.out.println("3.  List Members (Verbose)");
    System.out.println("4.  Delete Member");
    System.out.println("5.  Update Member");
    System.out.println("6.  View Member Details");
    System.out.println("7.  Add Item");
    System.out.println("8.  List Items");
    System.out.println("9.  Delete Item");
    System.out.println("10. Update Item");
    System.out.println("11. View Item Details");
    System.out.println("12. Establish Contract");
    System.out.println("13. List Contracts");
    System.out.println("14. Advance Day");
    System.out.println("15. Exit");
    System.out.print("Your choice: ");
  }

  /**
   * Displays a message to the user.
   *
   * @param message The message to display.
   */
  @Override
  public void showMessage(String message) {
    System.out.println(message);
  }

  /**
   * Gets the user's menu choice.
   *
   * @return The user's choice as a MenuOption.
   */
  @Override
  public MenuOption getUserChoice() {
    String input = scanner.nextLine();
    try {
      int choiceNumber = Integer.parseInt(input);
      switch (choiceNumber) {
        case 1:
          return MenuOption.ADD_MEMBER;
        case 2:
          return MenuOption.LIST_MEMBERS_SIMPLE;
        case 3:
          return MenuOption.LIST_MEMBERS_VERBOSE;
        case 4:
          return MenuOption.DELETE_MEMBER;
        case 5:
          return MenuOption.UPDATE_MEMBER;
        case 6:
          return MenuOption.VIEW_MEMBER_DETAILS;
        case 7:
          return MenuOption.ADD_ITEM;
        case 8:
          return MenuOption.LIST_ITEMS;
        case 9:
          return MenuOption.DELETE_ITEM;
        case 10:
          return MenuOption.UPDATE_ITEM;
        case 11:
          return MenuOption.VIEW_ITEM_DETAILS;
        case 12:
          return MenuOption.ESTABLISH_CONTRACT;
        case 13:
          return MenuOption.LIST_CONTRACTS;
        case 14:
          return MenuOption.ADVANCE_DAY;
        case 15:
          return MenuOption.EXIT;
        default:
          return MenuOption.INVALID_OPTION;
      }
    } catch (NumberFormatException e) {
      return MenuOption.INVALID_OPTION;
    }
  }

  /**
   * Gets input from the user.
   *
   * @return The user's input as a String.
   */
  @Override
  public String getUserInput() {
    return scanner.nextLine();
  }
}
