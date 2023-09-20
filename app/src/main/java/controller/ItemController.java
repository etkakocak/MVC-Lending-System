package controller;

import model.Item;
import model.Member;
import model.Description;
import java.util.ArrayList;
import java.util.List;

public class ItemController {
    private List<Item> items;

    public ItemController() {
        this.items = new ArrayList<>();
    }

    public void addItem(String category, String name, Description description, int costPerDay, Member owner) {
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
}
