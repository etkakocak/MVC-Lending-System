package controller;

import java.util.List;
import model.Contract;
import model.Item;
import model.Member;
import model.Service;
import model.Time;
import view.ViewInterface;

/**
 * This class controls the objects in this app.
 */
public class ObjectController {
  private Service model;
  private ViewInterface view;
  private Time time;

  /**
   * ObjectController class.
   */
  public ObjectController(Service model, ViewInterface view, Time time) {
    this.model = model.getModel();
    this.view = view;
    this.time = time.getTime();
  }

  /**
   * Application starts.
   */
  public void start() {
    view.welcome();
    model.initializeStartObjects();
    mainMenu();
  }

  /**
   * This is for handling of the main menu in ConsoleUI.
   */
  public boolean mainMenu() {
    view.displayMainMenu();
    view.getUserChoice();

    if (view.first()) {
      memberMenu();
    } else if (view.second()) {
      itemMenu();
    } else if (view.third()) {
      createContract();
    } else if (view.fourth()) {
      time.nextDay();
      view.sendOutput("Current day: " + time.getDate());
      mainMenu();
    }
    return !(view.fifth());
  }

  /**
   * This is for handling of the member menu in ConsoleUI.
   */
  public void memberMenu() {
    view.displayMemberMenu();
    view.getUserChoice();

    if (view.first()) {
      view.sendOutput("Enter the ID of the member you want to delete: ");
      String member = view.getString();
      Member memberToDelete = model.findMember(member);
      try {
        model.deleteMember(memberToDelete);
        view.sendOutput("Member deleted successfully");
      } catch (IllegalArgumentException e) {
        view.sendOutput("Member do not exist");
      }
      memberMenu();
    } else if (view.second()) {
      view.sendOutput("Enter member name: ");
      String name = view.getString();
      view.sendOutput("Enter member email: ");
      String email = view.getString();
      view.sendOutput("Enter member mobile: ");
      int mobile = view.getInt();
      if (model.createMemberAccount(name, email, mobile, time)) {
        view.sendOutput("Member created successfully!");
      } else {
        view.sendOutput("Member with same email or mobile exist!");
      }
      memberMenu();
    } else if (view.third()) {
      List<Member> members = model.getAllMembers();
      view.listMembers(members);
      memberMenu();
    } else if (view.fourth()) {
      view.sendOutput("Enter the ID of the member you want to view info: ");
      String member = view.getString();
      Member memberToView = model.findMember(member);
      try {
        view.viewMemberDetails(memberToView);
      } catch (IllegalArgumentException e) {
        view.sendOutput("Member do not exist");
      }
      memberMenu();
    } else if (view.fifth()) {
      view.sendOutput("Enter the ID of the member you want to change info: ");
      String member = view.getString();
      Member memberToChange = model.findMember(member);
      view.sendOutput("Enter new member name: ");
      String name = view.getString();
      view.sendOutput("Enter new member email: ");
      String email = view.getString();
      view.sendOutput("Enter new member mobile: ");
      int mobile = view.getInt();
      try {
        model.updateMemberAccount(memberToChange, name, email, mobile);
        view.sendOutput("Member updated successfully!");
      } catch (IllegalArgumentException e) {
        view.sendOutput("Member do not exist");
      }
      memberMenu();
    } else if (view.sixth()) {
      mainMenu();
    } else {
      memberMenu();
    }
  }

  /**
   * This is for handling of the item menu in ConsoleUI.
   */
  public void itemMenu() {
    view.displayItemMenu();
    view.getUserChoice();

    if (view.first()) {
      view.sendOutput("Enter the ID of the item you want to delete: ");
      String item = view.getString();
      Item itemToDelete = model.findItem(item);
      if (itemToDelete != null) {
        model.deleteItem(itemToDelete);
        view.sendOutput("Item deleted successfully");
      } else {
        view.sendOutput("Item do not exist");
      }
      itemMenu();
    } else if (view.second()) {
      view.sendOutput("Enter item category (Tool, Vehicle, Game, Toy, Sport, Other): ");
      String category = view.getString();
      view.sendOutput("Enter item name: ");
      String name = view.getString();
      view.sendOutput("Enter item description: ");
      String descContent = view.getString();
      view.sendOutput("Enter cost per day to lend the item: ");
      int costPerDay = view.getInt();
      view.sendOutput("Enter the ID of the owner of the item: ");
      String ownerid = view.getString();

      try {
        Member owner = model.findMember(ownerid);
        model.addItem(category, name, descContent, costPerDay, time, owner);
        view.sendOutput("Item posted successfully!");
      } catch (IllegalArgumentException e) {
        view.sendOutput(e.getMessage());
      }
      itemMenu();
    } else if (view.third()) {
      List<Item> items = model.getAllItems();
      view.listItems(items);
      itemMenu();
    } else if (view.fourth()) {
      view.sendOutput("Enter the ID of the item you want to change info: ");
      String item = view.getString();
      Item itemToChange = model.findItem(item);
      if (itemToChange != null) {
        view.sendOutput("Enter new item name: ");
        String name = view.getString();
        view.sendOutput("Enter new item category: ");
        String category = view.getString();
        view.sendOutput("Enter new item description: ");
        String description = view.getString();
        view.sendOutput("Enter new item price: ");
        int price = view.getInt();
        try {
          model.updateItem(itemToChange, name, category, description, price);
          view.sendOutput("Item updated successfully!");
        } catch (IllegalArgumentException e) {
          view.sendOutput(e.getMessage());
        }
      } else {
        view.sendOutput("Item do not exist");
      }
      itemMenu();
    } else if (view.fifth()) {
      mainMenu();
    } else {
      itemMenu();
    }
  }

  /**
   * This is for creation a new Contract.
   */
  public void createContract() {
    view.sendOutput("Enter the ID of the item to loan: ");
    String itemid = view.getString();
    Item item = model.findItem(itemid);
    view.sendOutput("Enter the ID of the member that wants to loan: ");
    String memberid = view.getString();
    Member member = model.findMember(memberid);
    view.sendOutput("How many days do you want to loan the item? ");
    int date = view.getInt();
    int startDate = time.getDate();
    int endDate = startDate + date;
    int loanDays = endDate - startDate;
    int totalCost = loanDays * item.getCostPerDay();

    if (member.getCredits() >= totalCost) {
      view.sendOutput("Member don't have enough credits to loan this item.");
      mainMenu();
    } else {
      model.addContract(item, member, startDate, endDate, totalCost);
      view.sendOutput("Contract created successfully!");
    }
    List<Contract> contracts = model.getAllContracts();
    view.listContracts(contracts);
    mainMenu();
  }
}
