package controller;

import view.ViewInterface;
import model.Service;
import model.Member;
import model.Admin;
import model.Item;


public class ObjectController {

    public void start(Service m, ViewInterface v) {
        Member[] startMembers = v.initializeStartMembers(m);
        for (Member member : startMembers) {
            m.addMember(member.getName(), member.getEmail(), member.getMobile(), member.getUsername(), member.getPassword());
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
            v.adminLoginProcess(m);
        } else if (choice == 2) {
            v.memberLoginProcess(m);
        } else if (choice == 3) {
            v.memberCreateProcess(m);
        } else {
            v.displayError();
            start(m, v);
        }
    }
}
