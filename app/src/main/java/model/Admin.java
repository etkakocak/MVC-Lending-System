package model;

import java.util.ArrayList;
import java.util.List;

public class Admin {
    private String username;
    private String password;
    private List<User> userList;
    private List<Item> itemList;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
        this.userList = new ArrayList<>();
        this.itemList = new ArrayList<>();
    }

    // Method to add a user
    public void addUser(User user) {
        userList.add(user);
        System.out.println("User " + user.getUsername() + " added successfully.");
    }

    // Method to remove a user
    public void removeUser(User user) {
        if (userList.contains(user)) {
            userList.remove(user);
            System.out.println("User " + user.getUsername() + " removed successfully.");
        } else {
            System.out.println("User " + user.getUsername() + " not found.");
        }
    }

    // Method to display all users
    public void displayUsers() {
        System.out.println("List of Users:");
        for (User user : userList) {
            System.out.println(user.getUsername());
        }
    }

    // Method to add an item
    public void addItem(Item item) {
        itemList.add(item);
        System.out.println("Item " + item.getName() + " added successfully.");
    }

    // Method to remove an item
    public void removeItem(Item item) {
        if (itemList.contains(item)) {
            itemList.remove(item);
            System.out.println("Item " + item.getName() + " removed successfully.");
        } else {
            System.out.println("Item " + item.getName() + " not found.");
        }
    }

    // Method to display all items
    public void displayItems() {
        System.out.println("List of Items:");
        for (Item item : itemList) {
            System.out.println(item.getName());
        }
    }

    // Admin login method
    public boolean login(String enteredPassword) {
        return password.equals(enteredPassword);
    }

    // Getter for username
    public String getUsername() {
        return username;
    }
}
