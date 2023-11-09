package controller;

import java.util.List;
import model.Item;
import model.Service;
import view.ViewInterface;

/**
 * This class controls the objects in this app.
 */
public class ObjectController {
  private Service model;
  private ViewInterface view;

  /**
   * ObjectController class.
   */
  public ObjectController(Service model, ViewInterface view) {
    this.model = model;
    this.view = view;
  }

  /**
   * This method gets data from model and view to start the app.
   */
  public void start() {
    model.setLoggedInMember();
    if (model.getLoggedInMember() != null) {
      memberMenu();
    }

    view.welcome();
  }

  /**
   * This is for handling of the member menu in ConsoleUI.
   */
  public boolean memberMenu() {
    view.displayMainMenu();
    view.getUserChoice();

    if (view.first()) {
      String[] itemDetails = view.postAnItem(model);
      model.addItem(itemDetails[0], itemDetails[1], itemDetails[2], itemDetails[3], itemDetails[4]);
      memberMenu();
    } else if (view.second()) {
      List<Item> items = model.getAllItems();
      String[] contractDetails = view.displayAllItems(items, model);
      if (contractDetails != null) {
        model.addContract(contractDetails[0], contractDetails[1], contractDetails[2], 
            contractDetails[3], contractDetails[4]);
        memberMenu();
      } else {
        memberMenu();
      }
    } else if (view.third()) {
      settingMenu();
    } else if (view.fourth()) {
      model.dayCounter();
      view.advanceDayCounter(model);
      memberMenu();
    } else if (view.fifth()) {
      int memberToDelete = view.displayAllMembers(model);
      if (memberToDelete != 0) {
        model.deleteMember(memberToDelete);
        memberMenu();
      } else {
        memberMenu();
      }
    } else if (view.sixth()) {
      List<Item> items = model.getAllItems();
      int itemToDelete = view.displayAllItemsAdmin(items, model);
      if (itemToDelete != 0) {
        model.deleteItem(itemToDelete);
        memberMenu();
      } else {
        memberMenu();
      }
    }
    return !(view.seventh());
  }

  /**
   * This is for handling of the setting menu for member data.
   */
  public void memberSettingMenu() {
    view.memberSettingMenu();
    view.getUserChoice();

    if (view.first()) {
      model.getLoggedInMember().setName(view.newName());
      view.displayGood();
      memberSettingMenu();
    } else if (view.second()) {
      model.getLoggedInMember().setPassword(view.newPassword());
      view.displayGood();
      memberSettingMenu();
    } else if (view.third()) {
      settingMenu();
    } else {
      memberSettingMenu();
    }
  }

  /**
   * This is for handling of the setting menu for item data.
   */
  public void itemSettingMenu() {
    view.itemSettingMenu();
    view.getUserChoice();
    Item itemToUpdate = model.setItem(view.setItem());

    if (view.first()) {
      String category = view.setCategory();
      itemToUpdate.setCategory(category);
      view.displayGood();
      itemSettingMenu();
    } else if (view.second()) {
      String itemName = view.setItemName();
      itemToUpdate.setName(itemName);
      view.displayGood();
      itemSettingMenu();
    } else if (view.third()) {
      String description = view.setDescription();
      itemToUpdate.setDescription(description);
      view.displayGood();
      itemSettingMenu();
    } else if (view.fourth()) {
      int cost = view.setItemCost();
      itemToUpdate.setCostPerDay(cost);
      view.displayGood();
      itemSettingMenu();
    } else if (view.fifth()) {
      settingMenu();
    } else {
      itemSettingMenu();
    }
  }

  /**
   * This is for handling of the setting menu in ConsoleUI.
   */
  public void settingMenu() {
    view.viewMemberDetails(model);
    view.getUserChoice();

    if (view.first()) {
      memberSettingMenu();
    } else if (view.second()) {
      itemSettingMenu();
    } else if (view.third()) {
      String[] itemDetails = view.setItemToDelete(model);
      model.deleteOwnedItem(itemDetails[0], itemDetails[1]);
    } else if (view.fourth()) {
      memberMenu();
    } else {
      settingMenu();
    }
  }
}
