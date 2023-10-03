package controller;

import view.ViewInterface;
import model.Service;
import model.Member;
import model.Admin;
import model.Item;
import model.Contract;

public class ObjectController {

    public void start(Service m, ViewInterface v) {
        Member[] startMembers = v.initializeStartMembers(m);
        for (Member member : startMembers) {
            m.addMember(member.getName(), member.getEmail(), member.getMobile(), member.getUsername(),
                    member.getPassword());
        }

        Admin systemAdmin = v.initializeStartAdmin(m);
        m.admins.add(systemAdmin);

        Item[] startItems = v.initializeStartItems(m);
        for (Item item : startItems) {
            m.addItem(item.getCategory(), item.getName(), item.getDescription(), item.getCostPerDay(), item.getOwner());
        }

        v.displayLoginMenu();

        int choice = v.getLoginType();

        if (choice == 1) {
            Admin thisAdmin = v.adminLoginProcess(m);
            if (thisAdmin != null) {
                adminMenu(m, v, thisAdmin);
            }
        } else if (choice == 2) {
            Member loggedInMember = v.memberLoginProcess(m);
            if (loggedInMember != null) {
                memberMenu(m, v, loggedInMember);
            }
        } else if (choice == 3) {
            Member createdMember = v.memberCreateProcess(m);
            if (createdMember != null) {
                memberMenu(m, v, createdMember);
            }
        } else {
            v.displayError();
            start(m, v);
        }
    }

    public void memberMenu(Service m, ViewInterface v, Member loggedInMember) {
        int choice = v.displayMainMenu();
        switch (choice) {
            case 1:
                Item item = v.postAnItem(loggedInMember, m);
                m.addItem(item.getCategory(), item.getName(), item.getDescription(), item.getCostPerDay(),
                        item.getOwner());
                memberMenu(m, v, loggedInMember);
                break;
            case 2:
                Contract contract = v.displayAllItems(loggedInMember, m);
                if (contract != null) {
                    int cost = contract.getCost();
                    m.addContract(contract.getStartDate(), contract.getEndDate(), contract.getItem(),
                            contract.getLender(), cost);
                    loggedInMember.addCredits(-cost);
                    contract.getOwner().addCredits(cost);
                    memberMenu(m, v, loggedInMember);
                } else {
                    memberMenu(m, v, loggedInMember);
                }
                break;
            case 3:
                settingMenu(v, m, loggedInMember);
                break;
            case 4:
                v.advanceDayCounter(m);
                break;
            case 5:
                break;
            default:
                v.displayBadChoice();
                memberMenu(m, v, loggedInMember);
        }
    }

    public void adminMenu(Service m, ViewInterface v, Admin admin) {
        int choice = v.displayAdminMenu();
        switch (choice) {
            case 1:
                Member memberToDelete = v.displayAllMembers(m);
                if (memberToDelete != null) {
                    m.deleteMember(memberToDelete); 
                    adminMenu(m, v, admin);
                } else {
                    adminMenu(m, v, admin);
                }
                break;
            case 2:
                Item itemToDelete = v.displayAllItemsAdmin(m);
                if (itemToDelete != null) {
                    m.deleteItem(itemToDelete);
                    adminMenu(m, v, admin);
                } else {
                    adminMenu(m, v, admin);
                }
                break;
            case 3:
                break;
            default:
                v.displayBadChoice();
        }
    }

    public void settingMenu(ViewInterface v, Service m, Member loggedInMember) {
        int setChoice = v.viewMemberDetails(loggedInMember, m);
        switch (setChoice) {
            case 1:
                int memberChoice = v.memberSettingMenu();
                switch (memberChoice) {
                    case 1:
                        String name = v.newName();
                        loggedInMember.setName(name);
                        v.displayGood();
                        settingMenu(v, m, loggedInMember);
                        break;
                    case 2:
                        String password = v.newPassword();
                        loggedInMember.setPassword(password);
                        v.displayGood();
                        settingMenu(v, m, loggedInMember);
                        break;
                    case 3:
                        settingMenu(v, m, loggedInMember);
                        break;
                    default:
                        v.displayBadChoice();
                        settingMenu(v, m, loggedInMember);
                    }
                break;
            case 2:
                Item itemToUpdate = v.setItem(m);
                int itemChoice = v.itemSettingMenu();
                switch (itemChoice) {
                    case 1:
                        String category = v.setCategory();
                        itemToUpdate.setCategory(category);
                        v.displayGood();
                        settingMenu(v, m, loggedInMember);
                        break;
                    case 2:
                        String itemName = v.setItemName();
                        itemToUpdate.setName(itemName);
                        settingMenu(v, m, loggedInMember);
                        v.displayGood();
                        break;
                    case 3:
                        String description = v.setDescription();
                        itemToUpdate.setDescription(description);
                        settingMenu(v, m, loggedInMember);
                        v.displayGood();
                        break;
                    case 4:
                        int cost = v.setItemCost();
                        itemToUpdate.setCostPerDay(cost);
                        settingMenu(v, m, loggedInMember);
                        v.displayGood();
                        break;
                    case 5:
                        settingMenu(v, m, loggedInMember);
                        break;
                    default:
                        v.displayBadChoice();
                        settingMenu(v, m, loggedInMember);
                    }
                break;
            case 3:
                Item itemToDelete = v.setItemToDelete(loggedInMember);
                if (itemToDelete != null) {
                    m.deleteMemberItem(itemToDelete);
                    settingMenu(v, m, loggedInMember);
                } else {
                    settingMenu(v, m, loggedInMember);
                }
                break;
            case 4:
                memberMenu(m, v, loggedInMember);
                break;
            default:
                v.displayBadChoice();
                settingMenu(v, m, loggedInMember);
                break;
        }
    }
}
