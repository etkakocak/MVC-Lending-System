package view;

import java.util.List;
import model.Item;
import model.Service;

/**
 * This interface is for ConsoleUI class.
 * This gets  data from Controller to send it to the Console.
 */
public interface ViewInterface {
  void welcome();

  void getUserChoice();

  void displayMainMenu();

  void displayBadChoice();

  void advanceDayCounter(Service service);

  void viewMemberDetails(Service service);

  void memberSettingMenu();

  String newName();

  String newPassword();

  void displayGood();

  String[] displayAllItems(List<Item> items, Service service);

  String[] postAnItem(Service service);

  int setItem();

  void itemSettingMenu();

  String setCategory();

  String setItemName();

  String setDescription();

  int setItemCost();

  String[] setItemToDelete(Service service);

  int displayAllMembers(Service service);

  int displayAllItemsAdmin(List<Item> items, Service service);

  String [] createMember(Service service);

  void cannotAdd();

  boolean first();

  boolean second();

  boolean third();

  boolean fourth();

  boolean fifth();

  boolean sixth();

  boolean seventh();

  boolean eight();
}
