package controller;

import model.Item;
import model.Member;
import java.util.ArrayList;
import java.util.List;

public class ItemController {
    private AuthenticationController authController;
    private List<Item> items;

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
        Member member3 = authController.getMemberByUsername("aiman");
        if(member3 != null) {
            Item item1 = new Item("Electronics", "MacBook Pro", "A clean computer for temporary works", 300, member3);
            member3.addOwnedItem(item1);
        }
    }
}
