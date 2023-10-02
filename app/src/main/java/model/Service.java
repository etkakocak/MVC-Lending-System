package model;

import java.util.List;
import java.util.ArrayList;

public class Service {
    public List<Member> members;
    public List<Admin> admins;
    public List<Item> items;
    public List<Contract> contracts;

    public Service() {
        members = new ArrayList<>();
        admins = new ArrayList<>();
        items = new ArrayList<>();
        contracts = new ArrayList<>();
        initializeStartObjects();
    }
    
    // login methods
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

    public boolean canAddMember(String email, int mobile) {
        for (Member member : members) {
            if (member.getEmail().equals(email) || member.getMobile() == mobile) {
                return false;
            }
        }
        return true;
    }

    // getters for lists
    public List<Member> getAllMembers() {
        return members;
    }

    public List<Item> getAllItems() {
        return items;
    }

    // delete or add methods
    public void deleteItem(int choice) {
        Item itemToDelete = items.get(choice - 1);
        itemToDelete.getOwner().getOwnedItems().remove(itemToDelete);
        itemToDelete.getOwner().addCredits(-100);
        items.remove(itemToDelete);
    }

    public void deleteMemberItem(Item item) {
        item.getOwner().getOwnedItems().remove(item);
        item.getOwner().addCredits(-100);
        items.remove(item);
    }

    public void deleteMember(int choice) {
        Member memberToBan = members.get(choice - 1);
        List<Item> itemsToDelete = memberToBan.getOwnedItems();
        items.removeAll(itemsToDelete);
        members.remove(memberToBan);
    }

    public void addItem(String category, String name, String description, int costPerDay, Member owner) {
        Item item = new Item(category, name, description, costPerDay, owner);
        items.add(item);
        owner.addOwnedItem(item);
    }

    public void addContract(int stDate, int enDate, Item theItem, Member theLender) {
        Contract newContract = new Contract(stDate, enDate, theItem, theLender);
        contracts.add(newContract);
        theLender.addOwnedContract(newContract);
    }

    public void dayCounter() {
        int nextDay = Time.getDate() + 1;
        Time.setDate(nextDay);   
    }

    // ????
    public void initializeStartObjects() {
        Member member1 = new Member("Etka", "etka@lending.com", 0031, "etka", "etka123");
        members.add(member1);
        Member member2 = new Member("Sanaa", "sanaa@lending.com", 0022, "sanaa", "sanaa123");
        members.add(member2);
        Member member3 = new Member("Aiman", "aiman@lending.com", 0062, "aiman", "aiman123");
        members.add(member3);

        Admin admin1 = new Admin("gadmin", "thegadmin03");
        admins.add(admin1);

        Item item1 = new Item("Electronics", "MacBook Pro", "A clean computer for temporary works", 30, member3);
        items.add(item1);
        member3.addOwnedItem(item1);

        Item item2 = new Item("Veichle", "BMW M5 2021", "Max 100 miles per loan period.", 300, member1);
        items.add(item2);
        member1.addOwnedItem(item2);
    }
}

