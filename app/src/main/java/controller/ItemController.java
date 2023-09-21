package controller;

import model.Item;
import model.Member;
import java.util.ArrayList;
import java.util.List;

public class ItemController {
    public List<Item> items;

    public ItemController() {
        items = new ArrayList<>();
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

    public void initializeItems() {
        AuthenticationController authController = AuthenticationController.getInstance();
        List<Member> allMembers = authController.getAllMembers();

        Member member3 = null;
        for (Member member : allMembers) {
            if ("aiman".equals(member.getUsername())) {
                member3 = member;
                Item item1 = new Item("Electronics", "MacBook Pro", "A clean computer for temporary works", 300, member3);
                items.add(item1);
                member3.addOwnedItem(item1);
                break;
            } else {
                System.out.println("coudnt find the user bree.");
            }
        }
    }
}
