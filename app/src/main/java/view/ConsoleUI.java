package view;

import java.util.List;
import java.util.Scanner;

import controller.ItemController;
import model.Description;
import model.Member;
import model.Item;
import model.Admin;

public class ConsoleUI {
    private Scanner scanner;
    private Member loggedInMember;
    private Admin admin;

    public ConsoleUI(Member loggedInMember) {
        this.scanner = new Scanner(System.in, "UTF-8");
        this.loggedInMember = loggedInMember;
    }

    public ConsoleUI(Admin admin) {
        this.scanner = new Scanner(System.in, "UTF-8");
        this.admin = admin;
    }

    public void displayMainMenu() {
        int choice = 0;

        while (choice != 5) {
            System.out.println("\n---- Main Menu ----");
            System.out.println("1. Post an item");
            System.out.println("2. Display all items");
            System.out.println("3. View member details");
            System.out.println("4. Advance the day counter");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    postAnItem();
                    break;
                case 2:
                    displayAllItems();
                    break;
                case 3:
                    viewMemberDetails();
                    break;
                case 4:
                    advanceDayCounter();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void postAnItem() {
        ItemController itemController = new ItemController();
        System.out.println("Enter item category (Tool, Vehicle, Game, Toy, Sport, Other): ");
        String category = scanner.nextLine();
        
        System.out.println("Enter item name: ");
        String name = scanner.nextLine();
        
        System.out.println("Enter item description: ");
        String descContent = scanner.nextLine();
        Description description = new Description(descContent);
        
        System.out.println("Enter cost per day to lend the item: ");
        int costPerDay = scanner.nextInt();
        
        itemController.addItem(category, name, description, costPerDay, loggedInMember);
        System.out.println("Item posted successfully!");
    }

    public void displayAllItems() {
        ItemController itemController = new ItemController();
        List<Item> items = itemController.getAllItems();

        System.out.println("\nItems available for lending:");
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i).getName() + " - " + items.get(i).getDescription());
        }

        System.out.println("\nEnter the number of the item you want to loan or 0 to go back: ");
        int choice = scanner.nextInt();

        if (choice > 0 && choice <= items.size()) {
            // ToDo: Implement loaning logic using ItemController
            System.out.println("Item loaned successfully!");
        }
    }

    public void advanceDayCounter() {
        System.out.println("Advancing the day counter...");
    }

    public void viewMemberDetails() {
        System.out.println("Member Details...");
    }

    public void displayAdminMenu() {
        int choice = 0;

        while (choice != 3) {
            System.out.println("\n---- Admin Panel ----");
            System.out.println("1. Display all members");
            System.out.println("2. Display all items");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayAllMembers();
                    break;
                case 2:
                    displayAllItemsAdmin();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void displayAllMembers() {
        System.out.println("Displaying all members...");
    }

    public void displayAllItemsAdmin() {
        ItemController itemController = new ItemController();
        List<Item> items = itemController.getAllItems();
        
        System.out.println("\nItems available:");
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i).getName() + " - " + items.get(i).getDescription());
        }
        
        System.out.println("\nEnter the number of the item you want to delete or 0 to go back: ");
        int choice = scanner.nextInt();
        
        if (choice > 0 && choice <= items.size()) {
            String itemName = items.get(choice - 1).getName();
            itemController.deleteItemByName(itemName);
            System.out.println("Item deleted successfully!");
        }
    }

}
