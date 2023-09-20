package view;

import java.util.Scanner;

public class ConsoleUI {
    private Scanner scanner;

    public ConsoleUI() {
        this.scanner = new Scanner(System.in, "UTF-8");
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
        System.out.println("Posting item...");
    }

    public void displayAllItems() {
        System.out.println("Displaying all items...");
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
        System.out.println("Displaying all items...");
    }

}
