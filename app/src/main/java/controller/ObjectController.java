package controller;

import java.util.List;
import model.Item;
import model.Service;
import view.ViewInterface;

/**
 * This class controls the objects in this app.
 */
public class ObjectController {
  public Service model;
  public ViewInterface view;

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
  }

  /**
   * This is for handling of the member menu in ConsoleUI.
   */
  public boolean memberMenu() {
    view.welcome();
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
      String[] newMemberDetails = view.createMember(model);
      if (newMemberDetails != null) {
        if (model.canAddMember(newMemberDetails[1], newMemberDetails[2]) != false) {
          model.addMember(newMemberDetails[0], newMemberDetails[1], newMemberDetails[2], 
              newMemberDetails[3], newMemberDetails[4]);
          view.displayGood();
        } else {
          view.cannotAdd();
        }
        memberMenu();
      } else {
        memberMenu();
      }
    } else if (view.seventh()) {
      List<Item> items = model.getAllItems();
      int itemToDelete = view.displayAllItemsAdmin(items, model);
      if (itemToDelete != 0) {
        model.deleteItem(itemToDelete);
        memberMenu();
      } else {
        memberMenu();
      }
    }
    return !(view.eight());
  }

  /**
   * This is for handling of the setting menu for member data.
   */
  public void memberSettingMenu() {
    view.memberSettingMenu();
    view.getUserChoice();

    if (view.first()) {
      model.getMemberByUsername(model.getLoggedInMember()).setName(view.newName());
      view.displayGood();
      memberSettingMenu();
    } else if (view.second()) {
      model.getMemberByUsername(model.getLoggedInMember()).setPassword(view.newPassword());
      view.displayGood();
      memberSettingMenu();
    } else if (view.third()) {
      memberMenu();
    } else {
      memberSettingMenu();
    }
  }

  /**
   * This is for handling of the setting menu for item data.
   */
  public void itemSettingMenu(Item itemToUpdate) {
    view.itemSettingMenu();
    view.getUserChoice();

    if (view.first()) {
      itemToUpdate.setCategory(view.setCategory());
      view.displayGood();
      itemSettingMenu(itemToUpdate);
    } else if (view.second()) {
      itemToUpdate.setName(view.setItemName());
      view.displayGood();
      itemSettingMenu(itemToUpdate);
    } else if (view.third()) {
      itemToUpdate.setDescription(view.setDescription());
      view.displayGood();
      itemSettingMenu(itemToUpdate);
    } else if (view.fourth()) {
      itemToUpdate.setCostPerDay(view.setItemCost());
      view.displayGood();
      itemSettingMenu(itemToUpdate);
    } else if (view.fifth()) {
      settingMenu();
    } else {
      itemSettingMenu(itemToUpdate);
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
      Item itemToUpdate = model.setItemMember(view.setItem());
      itemSettingMenu(itemToUpdate);
    } else if (view.third()) {
      String[] itemDetails = view.setItemToDelete(model);
      model.deleteOwnedItem(itemDetails[0], itemDetails[1]);
      settingMenu();
    } else if (view.fourth()) {
      memberMenu();
    } else {
      settingMenu();
    }
  }
}
