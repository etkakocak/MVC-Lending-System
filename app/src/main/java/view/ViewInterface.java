package view;

import model.Admin;
import model.Contract;
import model.Item;
import model.Member;
import model.Service;

/**
 * This interface is for ConsoleUI class.
 * This gets  data from Controller to send it to the Console.
 */
public interface ViewInterface {
  // for login
  void displayError();

  Admin adminLoginProcess(Service service);

  int getLoginType();

  Member memberLoginProcess(Service service);

  Member memberCreateProcess(Service service);

  void displayLoginMenu();

  // for member
  int displayMainMenu();

  void displayBadChoice();

  void advanceDayCounter(Service service);

  int viewMemberDetails(Member member, Service service);

  int memberSettingMenu();

  String newName();

  String newPassword();

  void displayGood();

  // for Item
  Contract displayAllItems(Member member, Service service);

  Item postAnItem(Member member, Service service);

  Item setItem(Service service);

  int itemSettingMenu();

  String setCategory();

  String setItemName();

  String setDescription();

  int setItemCost();

  Item setItemToDelete(Member member);

  // for admin
  int displayAdminMenu();

  Member displayAllMembers(Service service);

  Item displayAllItemsAdmin(Service service);
}
