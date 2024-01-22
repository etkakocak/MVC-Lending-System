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
public class SwedishController {
  private Service model;
  private ViewInterface view;
  private Time time;

  /**
   * ObjectController class in Swedish.
   */
  public SwedishController(Service model, ViewInterface view, Time time) {
    this.model = model;
    this.view = view;
    this.time = time;
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
      view.sendOutput("Nuvarande dag: " + time.getDate());
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
      view.sendOutput("Ange ID för den medlem du vill radera: ");
      String member = view.getString();
      Member memberToDelete = model.findMember(member);
      try {
        model.deleteMember(memberToDelete);
        view.sendOutput("Medlem raderas framgångsrikt");
      } catch (IllegalArgumentException e) {
        view.sendOutput("Medlem finns inte");
      }
      memberMenu();
    } else if (view.second()) {
      view.sendOutput("Ange medlem namn: ");
      String name = view.getString();
      view.sendOutput("Ange medlem email: ");
      String email = view.getString();
      view.sendOutput("Ange medlem mobil: ");
      int mobile = view.getInt();
      if (model.createMemberAccount(name, email, mobile, time)) {
        view.sendOutput("Medlem skapades framgångsrikt!");
      } else {
        view.sendOutput("Medlem med samma email eller mobil finns!");
      }
      memberMenu();
    } else if (view.third()) {
      List<Member> members = model.getAllMembers();
      view.listMembers(members);
      memberMenu();
    } else if (view.fourth()) {
      view.sendOutput("Ange ID för den medlem du vill se info: ");
      String member = view.getString();
      Member memberToView = model.findMember(member);
      try {
        view.viewMemberDetails(memberToView);
      } catch (IllegalArgumentException e) {
        view.sendOutput("Medlem finns inte");
      }
      memberMenu();
    } else if (view.fifth()) {
      view.sendOutput("Ange ID för den medlem du vill ändra info: ");
      String member = view.getString();
      Member memberToChange = model.findMember(member);
      view.sendOutput("Ange nytt medlemsnamn: ");
      String name = view.getString();
      view.sendOutput("Ange ny medlems email: ");
      String email = view.getString();
      view.sendOutput("Ange ny medlemsmobil: ");
      int mobile = view.getInt();
      try {
        model.updateMemberAccount(memberToChange, name, email, mobile);
        view.sendOutput("Medlem uppdaterad framgångsrikt!");
      } catch (IllegalArgumentException e) {
        view.sendOutput("Medlem finns inte");
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
        view.sendOutput("Item raderas framgångsrikt");
      } else {
        view.sendOutput("Item finns inte");
      }
      itemMenu();
    } else if (view.second()) {
      view.sendOutput("\nAnge kategori av item (verktyg, fordon, spel, leksak, sport, annat): ");
      String category = view.getString();
      view.sendOutput("Ange item namn: ");
      String name = view.getString();
      view.sendOutput("Ange item beskrivning: ");
      String descContent = view.getString();
      view.sendOutput("Ange kostnad per dag för att låna item: ");
      int costPerDay = view.getInt();
      view.sendOutput("Ange ID för ägaren till item: ");
      String ownerid = view.getString();

      try {
        Member owner = model.findMember(ownerid);
        model.addItem(category, name, descContent, costPerDay, time, owner);
        view.sendOutput("Item publicerades framgångsrikt!");
      } catch (IllegalArgumentException e) {
        view.sendOutput(e.getMessage());
      }
      itemMenu();
    } else if (view.third()) {
      List<Item> items = model.getAllItems();
      view.listItems(items);
      itemMenu();
    } else if (view.fourth()) {
      view.sendOutput("Ange ID för den item du vill ändra info: ");
      String item = view.getString();
      Item itemToChange = model.findItem(item);
      if (itemToChange != null) {
        view.sendOutput("Ange nytt item namn: ");
        String name = view.getString();
        view.sendOutput("Ange ny item kategori: ");
        String category = view.getString();
        view.sendOutput("Ange ny item beskrivning: ");
        String description = view.getString();
        view.sendOutput("Ange ny item kostnad: ");
        int price = view.getInt();
        try {
          model.updateItem(itemToChange, name, category, description, price);
          view.sendOutput("Item uppdaterad framgångsrikt!");
        } catch (IllegalArgumentException e) {
          view.sendOutput(e.getMessage());
        }
      } else {
        view.sendOutput("Item finns inte");
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
    view.sendOutput("Ange ID för item du vill låna: ");
    String itemid = view.getString();
    Item item = model.findItem(itemid);
    view.sendOutput("Ange ID för medlemmen som vill låna: ");
    String memberid = view.getString();
    Member member = model.findMember(memberid);
    view.sendOutput("Hur många dagar vill du låna item? ");
    int date = view.getInt();
    int startDate = time.getDate();
    int endDate = startDate + date;
    int loanDays = endDate - startDate;
    int totalCost = loanDays * item.getCostPerDay();

    if (member.getCredits() >= totalCost) {
      view.sendOutput("Medlem har inte tillräckligt med krediter för att låna denna item.");
      mainMenu();
    } else {
      model.addContract(item, member, startDate, endDate, totalCost);
      view.sendOutput("Kontraktet skapades framgångsrikt!");
    }
    List<Contract> contracts = model.getAllContracts();
    view.listContracts(contracts);
    mainMenu();
  }
}
