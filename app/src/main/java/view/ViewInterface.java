package view;

import java.util.List;
import model.Contract;
import model.Item;
import model.Member;

/**
 * This interface is for ConsoleUI class.
 * This gets  data from Controller to send it to the Console.
 */
public interface ViewInterface {
  void welcome();

  void getUserChoice();

  void displayMainMenu();

  void displayBadChoice();

  void viewMemberDetails(Member member);

  boolean first();

  boolean second();

  boolean third();

  boolean fourth();

  boolean fifth();

  boolean sixth();

  void sendOutput(String output);

  String getString();

  int getInt();

  void displayMemberMenu();

  void displayItemMenu();

  void listMembers(List<Member> members);

  void listItems(List<Item> items);

  void listContracts(List<Contract> contracts);
}
