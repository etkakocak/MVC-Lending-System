package view;

import java.util.List;
import java.util.Scanner;
import controller.ObjectController;
import model.Member;
import model.Item;
import model.Admin;
import model.Contract;
import model.Time;

public class ConsoleUI {
    private Scanner scanner;
    private Member loggedInMember;
    private Admin admin;
    private ObjectController obj;
    public Time currentDay;


    public ConsoleUI(Member loggedInMember, ObjectController obj) {
        this.scanner = new Scanner(System.in, "UTF-8");
        this.loggedInMember = loggedInMember;
        this.obj = obj;
        currentDay = new Time();
    }

    public ConsoleUI(Admin admin, ObjectController obj) {
        this.scanner = new Scanner(System.in, "UTF-8");
        this.admin = admin;
        this.obj = obj;
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
        System.out.println("\nEnter item category (Tool, Vehicle, Game, Toy, Sport, Other): ");
        String category = scanner.nextLine();
        category = scanner.nextLine();

        System.out.println("Enter item name: ");
        String name = scanner.nextLine();

        System.out.println("Enter item description: ");
        String descContent = scanner.nextLine();

        System.out.println("Enter cost per day to lend the item: ");
        int costPerDay = scanner.nextInt();

        obj.addItem(category, name, descContent, costPerDay, loggedInMember);
        System.out.println("Item posted successfully!");
    }

    public void displayAllItems() {
        List<Item> items = obj.getAllItems();

        System.out.println("\nItems available for lending:");
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i).getCategory() + " / " + items.get(i).getName()
                    + " / " + items.get(i).getDescription() + "\nCost per day: " + items.get(i).getCostPerDay()
                    + "\nCreated: " + items.get(i).getCreationDate());
        }

        System.out.println("\nEnter the number of the item you want to loan or 0 to go back: ");
        int choice = scanner.nextInt();

        if (choice > 0 && choice <= items.size()) {
            Item selectedItem = items.get(choice - 1);
            System.out.println("How many days do you want to loan the item?");
            int loanDays = scanner.nextInt();

            int totalCost = loanDays * selectedItem.getCostPerDay();

            if (loggedInMember.getCredits() >= totalCost) {
                Time startDate = currentDay; 
                Time endDate = currentDay; 
                endDate.Day = currentDay.getDate() + loanDays; 
                obj.addContract(startDate, endDate, selectedItem, loggedInMember);
                loggedInMember.addCredits(-totalCost);
                System.out.println("Item loaned successfully!");
            } else {
                System.out.println("You don't have enough credits to loan this item.");
            }
        }

    }

    public void advanceDayCounter() {
        System.out.println("Advancing the day counter...");
    }

    public void viewMemberDetails() {
        System.out.println("\nYour account details: ");
        System.out.println("Name: " + loggedInMember.getName());
        System.out.println("Username: " + loggedInMember.getUsername());
        System.out.println("Member ID: " + loggedInMember.getMemberId());
        System.out.println("Email: " + loggedInMember.getEmail());
        System.out.println("Phone number: " + loggedInMember.getMobile());
        System.out.println("Account is created: " + loggedInMember.getCreationDate());
        System.out.println("Your credit: " + loggedInMember.getCredits());

        System.out.println("\nPosted Items:");
        List<Item> items = loggedInMember.getOwnedItems();
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i).getCategory() + " / " + items.get(i).getName()
                    + " / " + items.get(i).getDescription() + "\nCost per day: " + items.get(i).getCostPerDay()
                    + "\nCreated: " + items.get(i).getCreationDate());
        }

        System.out.println("\nEnter the number of the item if you want to delete it or 0 to go back: ");
        int choice = scanner.nextInt();

        if (choice > 0 && choice <= items.size()) {
            Item itemToDelete = items.get(choice - 1);
            items.remove(itemToDelete);
            System.out.println("Item deleted successfully!");
        }
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
        List<Member> members = obj.getAllMembers();
        System.out.println("\nAll Members: ");
        for (int i = 0; i < members.size(); i++) {
            System.out.println((i + 1) + ". " + members.get(i).getMemberId() + " / " + members.get(i).getUsername()
                    + "\nName: " + members.get(i).getName() + "\nEmail: " + members.get(i).getEmail()
                    + "\nPhone number: " + members.get(i).getMobile() + "\nCredits: " + members.get(i).getCredits()
                    + "\nAccount is created: " + members.get(i).getCreationDate());
        }

        System.out.println("\nEnter the number of the member you want to ban or 0 to go back: ");
        int choice = scanner.nextInt();

        if (choice > 0 && choice <= members.size()) {
            Member memberToBan = members.get(choice - 1);
            members.remove(memberToBan);
            System.out.println("Member banned successfully!");
        }
    }

    public void displayAllItemsAdmin() {
        List<Item> items = obj.getAllItems();
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i).getCategory() + " / " + items.get(i).getName()
                    + " / " + items.get(i).getDescription() + "\nOwner: " + items.get(i).getOwner()
                    + "\nCost per day: " + items.get(i).getCostPerDay()
                    + "\nCreated: " + items.get(i).getCreationDate());
        }

        System.out.println("\nEnter the number of the item if you want to delete it or 0 to go back: ");
        int choice = scanner.nextInt();

        if (choice > 0 && choice <= items.size()) {
            Item itemToDelete = items.get(choice - 1);
            items.remove(itemToDelete);
            System.out.println("Item deleted successfully!");
        }
    }

}
