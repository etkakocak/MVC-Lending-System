package controller;

import java.util.ArrayList;
import java.util.List;

import model.Member;
import model.Admin;
import model.Item;
import model.Contract;
import model.Time;

public class ObjectController {
    public List<Member> members;
    public List<Admin> admins;
    public List<Item> items;
    public List<Contract> contracts;

    public ObjectController() {
        members = new ArrayList<>();
        admins = new ArrayList<>();
        items = new ArrayList<>();
        contracts = new ArrayList<>();
    }


    public Member getMemberByUsername(String username) {
        for (Member member : members) {
            if (member.getUsername().equals(username)) {
                return member;
            }
        }
        return null;
    }

    public Admin getAdminByUsername(String username) {
        for (Admin admin : admins) {
            if (admin.getUsername().equals(username)) {
                return admin;
            }
        }
        return null;
    }

    public Admin validateAdmin(String username, String password) {
        Admin admin = getAdminByUsername(username);
        if (admin != null && admin.getPassword().equals(password)) {
            return admin;
        }
        return null;
    }

    public Member validateMember(String username, String password) {
        for (Member member : members) {
            if (member.getUsername().equals(username) && member.getPassword().equals(password)) {
                return member;
            }
        }
        return null;
    }
    
    public void createMemberAccount(String name, String email, int mobile, String username, String password) {
        Member newMember = new Member(name, email, mobile, username, password);
        members.add(newMember);
    }

    public List<Member> getAllMembers() {
        return members;
    }

    public void initializeStartObjects() {
        Member member1 = new Member("Etka", "etka@lending.com", 0031, "etka", "etka123");
        members.add(member1);
        Member member2 = new Member("Sanaa", "sanaa@lending.com", 0022, "sanaa", "sanaa123");
        members.add(member2);
        Member member3 = new Member("Aiman", "aiman@lending.com", 0062, "aiman", "aiman123");
        members.add(member3);

        Admin admin1 = new Admin("gadmin", "thegadmin03");
        admins.add(admin1);

        Item item1 = new Item("Electronics", "MacBook Pro", "A clean computer for temporary works", 300, member3);
        items.add(item1);
        member3.addOwnedItem(item1);
    }


    public void addItem(String category, String name, String description, int costPerDay, Member owner) {
        Item item = new Item(category, name, description, costPerDay, owner);
        items.add(item);
        owner.addOwnedItem(item);
    }

    // Method to fetch all items
    public List<Item> getAllItems() {
        return items;
    }

    // Method to delete an item by its name
    public void deleteItemByName(String itemName) {
        items.removeIf(item -> item.getName().equals(itemName));
    }


    public void addContract(Time stDate, Time enDate, Item theItem, Member theLender) {
        Contract newContract = new Contract(stDate, enDate, theItem, theLender);
        contracts.add(newContract);
    }
}
