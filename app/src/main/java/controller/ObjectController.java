package controller;

import java.util.List;
import model.Contract;
import model.Item;
import model.Item.Category;
import model.Member;
import model.Service;
import model.Time;
import view.ViewInterface;

/**
 * ObjectController class.
 * Handles user input, interacts with the model (Service), and updates the view.
 */
public class ObjectController {
  private Service service;
  private ViewInterface view;
  private Time time;

  /**
   * Constructor for ObjectController.
   */
  public ObjectController(Service service, ViewInterface view, Time time) {
    this.service = service;
    this.view = view;
    this.time = time;
  }

  /**
   * Starts the main menu loop of the application.
   */
  public void start() {
    boolean exit = false;
    while (!exit) {
      view.displayMenu();
      String choice = view.getUserInput();

      switch (choice) {
        case "1":
          addMember();
          break;
        case "2":
          listMembersSimple();
          break;
        case "3":
          listMembersVerbose();
          break;
        case "4":
          deleteMember();
          break;
        case "5":
          updateMember();
          break;
        case "6":
          viewMemberDetails();
          break;
        case "7":
          addItem();
          break;
        case "8":
          listItems();
          break;
        case "9":
          deleteItem();
          break;
        case "10":
          updateItem();
          break;
        case "11":
          viewItemDetails();
          break;
        case "12":
          establishContract();
          break;
        case "13":
          listContracts();
          break;
        case "14":
          advanceDay();
          break;
        case "15":
          exit = true;
          view.showMessage("Exiting the application...");
          break;
        default:
          view.showMessage("Invalid choice. Please try again.");
      }
    }
  }

  // Member Management Methods

  private void addMember() {
    view.showMessage("Add Member:");
    view.showMessage("Name: ");
    String name = view.getUserInput();
    view.showMessage("Email: ");
    String email = view.getUserInput();
    view.showMessage("Phone Number: ");
    String phoneNumber = view.getUserInput();

    boolean success = service.addMember(name, email, phoneNumber);
    if (success) {
      view.showMessage("Member added successfully.");
    } else {
      view.showMessage("Email or phone number already exists.");
    }
  }

  private void listMembersSimple() {
    List<Member> members = service.listMembers();
    for (Member member : members) {
      view.showMessage(member.toSimpleString());
    }
  }

  private void listMembersVerbose() {
    String verboseInfo = service.listMembersVerbose();
    view.showMessage(verboseInfo);
  }

  private void deleteMember() {
    view.showMessage("Enter the ID of the member to delete:");
    String memberId = view.getUserInput();
    boolean success = service.deleteMember(memberId);
    if (success) {
      view.showMessage("Member deleted successfully.");
    } else {
      view.showMessage("Member not found.");
    }
  }

  private void updateMember() {
    view.showMessage("Enter the ID of the member to update:");
    String memberId = view.getUserInput();
    Member member = service.viewMember(memberId);
    if (member == null) {
      view.showMessage("Member not found.");
      return;
    }
    view.showMessage("New name (" + member.getName() + "): ");
    String newName = view.getUserInput();
    view.showMessage("New email (" + member.getEmail() + "): ");
    String newEmail = view.getUserInput();
    view.showMessage("New phone number (" + member.getPhoneNumber() + "): ");
    String newPhone = view.getUserInput();

    boolean success = service.updateMember(memberId, newName, newEmail, newPhone);
    if (success) {
      view.showMessage("Member updated successfully.");
    } else {
      view.showMessage("Update failed. Email or phone number already exists.");
    }
  }

  private void viewMemberDetails() {
    view.showMessage("Enter the ID of the member to view details:");
    String memberId = view.getUserInput();
    Member member = service.viewMember(memberId);
    if (member != null) {
      view.showMessage(member.toVerboseString());
    } else {
      view.showMessage("Member not found.");
    }
  }

  // Item Management Methods

  private void addItem() {
    view.showMessage("Add Item:");
    view.showMessage("Enter the ID of the owner:");
    String memberId = view.getUserInput();
    Member owner = service.viewMember(memberId);
    if (owner == null) {
      view.showMessage("Member not found.");
      return;
    }
    view.showMessage("Item Name: ");
    String name = view.getUserInput();
    // Check if owner already has an item with the same name
    if (service.ownerHasItemWithName(owner, name)) {
      view.showMessage("You already own an item with that name.");
      return;
    }
    // Collect category
    view.showMessage("Category (TOOL, VEHICLE, GAME, TOY, SPORT, OTHER): ");
    String categoryStr = view.getUserInput();
    Category category;
    try {
      category = Category.valueOf(categoryStr.toUpperCase());
    } catch (IllegalArgumentException e) {
      view.showMessage("Invalid category.");
      return;
    }
    // Collect cost per day
    view.showMessage("Cost Per Day: ");
    int costPerDay;
    try {
      costPerDay = Integer.parseInt(view.getUserInput());
    } catch (NumberFormatException e) {
      view.showMessage("Invalid cost.");
      return;
    }
    // Collect description
    view.showMessage("Description: ");
    String description = view.getUserInput();

    boolean success = service.addItem(owner, name, description, category, costPerDay);
    if (success) {
      view.showMessage("Item added successfully.");
    } else {
      view.showMessage("Failed to add item. You may already own an item with that name.");
    }
  }

  private void listItems() {
    List<Item> items = service.listItems();
    for (Item item : items) {
      view.showMessage(item.toSimpleString());
    }
  }