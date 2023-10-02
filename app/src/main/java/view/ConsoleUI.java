package view;

import java.util.List;
import java.util.Scanner;
import model.Member;
import model.Item;
import model.Admin;
import model.Service;
import model.Time;
import model.Contract;

public class ConsoleUI implements ViewInterface {
    private Scanner scanner;

    public ConsoleUI() {
        this.scanner = new Scanner(System.in, "UTF-8");
    }

    public String[] getCredentials() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        return new String[] { username, password };
    }

    public int getLoginType() {
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    public void displayLoginMenu(Service service) {
        System.out.println("Welcome to the Stuff Lending System Login!");
        System.out.println("1. Login as Admin");
        System.out.println("2. Login as Member");
        System.out.println("3. Create Member Account");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                // Handle admin login
                boolean adminValidated = false;
                while (!adminValidated) {
                    String[] adminCredentials = getCredentials();
                    Admin admin = service.validateAdmin(adminCredentials[0], adminCredentials[1]);
                    if (admin != null) {
                        displayAdminMenu(service, admin);
                        adminValidated = true;
                    } else {
                        System.out.println("Invalid admin credentials. Try again or type 'exit' to quit.");
                        String exitChoice = scanner.nextLine();
                        if ("exit".equalsIgnoreCase(exitChoice)) {
                            break;
                        }
                    }
                }
                break;
            case 2:
                // Handle member login
                boolean memberValidated = false;
                while (!memberValidated) {
                    String[] memberCredentials = getCredentials();
                    Member loggedInMember = service.validateMember(memberCredentials[0], memberCredentials[1]);
                    if (loggedInMember != null) {
                        displayMainMenu(service, loggedInMember);
                        memberValidated = true;
                    } else {
                        System.out.println("Invalid member credentials. Try again or type 'exit' to quit.");
                        String exitChoice = scanner.nextLine();
                        if ("exit".equalsIgnoreCase(exitChoice)) {
                            break;
                        }
                    }
                }
                break;
            case 3:
                // Handle member account creation
                System.out.print("Enter your name: ");
                String name = scanner.nextLine();

                System.out.print("Enter your email: ");
                String email = scanner.nextLine();

                System.out.print("Enter your phone number: ");
                int mobile = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Enter your username: ");
                String username = scanner.nextLine();

                System.out.print("Enter your password: ");
                String password = scanner.nextLine();

                if (service.canAddMember(email, mobile)) {
                    service.createMemberAccount(name, email, mobile, username, password);
                    System.out.println("Member account created successfully.");

                    Member newMember = service.validateMember(username, password);
                    displayMainMenu(service, newMember);
                } else {
                    System.out.println(
                            "E-post eller mobilnummer används redan! Please try again or type 'exit' to quit..");
                    String exitChoice = scanner.nextLine();
                    if ("exit".equalsIgnoreCase(exitChoice)) {
                        break;
                    } else {
                        displayLoginMenu(service);
                    }
                }
                break;
            default:
                System.out.println("Invalid choice. Please try again or type 'exit' to quit..");
                String exitChoice = scanner.nextLine();
                if ("exit".equalsIgnoreCase(exitChoice)) {
                    break;
                } else {
                    displayLoginMenu(service);
                }
        }
    }

    public void displayMainMenu(Service service, Member loggedInMember) {
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
                    postAnItem(loggedInMember, service);
                    break;
                case 2:
                    displayAllItems(loggedInMember, service);
                    break;
                case 3:
                    viewMemberDetails(loggedInMember, service);
                    break;
                case 4:
                    advanceDayCounter(service);
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void postAnItem(Member loggedInMember, Service service) {
        System.out.println("\nEnter item category (Tool, Vehicle, Game, Toy, Sport, Other): ");
        String category = scanner.nextLine();
        category = scanner.nextLine();

        System.out.println("Enter item name: ");
        String name = scanner.nextLine();

        System.out.println("Enter item description: ");
        String descContent = scanner.nextLine();

        System.out.println("Enter cost per day to lend the item: ");
        int costPerDay = scanner.nextInt();

        service.addItem(category, name, descContent, costPerDay, loggedInMember);
        System.out.println("Item posted successfully!");
    }

    public void displayAllItems(Member loggedInMember, Service service) {
        List<Item> items = service.getAllItems();

        System.out.println("\nItems available for lending:");
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i).getCategory() + " / " + items.get(i).getName()
                    + " / " + items.get(i).getDescription() + "\nCost per day: " + items.get(i).getCostPerDay()
                    + "\nCreated: Day " + items.get(i).getCreationDate());
        }

        System.out.println("\nEnter the number of the item you want to loan or 0 to go back: ");
        int choice = scanner.nextInt();

        if (choice > 0 && choice <= items.size()) {
            Item selectedItem = items.get(choice - 1);

            // Check if the member is trying to borrow their own item
            if (loggedInMember.equals(selectedItem.getOwner())) {
                System.out.println("You cannot borrow your own item.");
                return;
            }

            System.out.println("How many days do you want to loan the item?");
            int loanDays = scanner.nextInt();

            int totalCost = loanDays * selectedItem.getCostPerDay();

            if (loggedInMember.getCredits() >= totalCost) {
                int startDate = Time.getDate();

                int endDate = startDate + loanDays;

                Time.setDate(endDate);

                service.addContract(startDate, endDate, selectedItem, loggedInMember);
                loggedInMember.addCredits(-totalCost);
                selectedItem.getOwner().addCredits(totalCost);

                System.out.println("Item loaned successfully!");
                System.out.println("\n--------- Loan Receipt ---------");
                System.out.println("Borrower: " + loggedInMember.getName());
                System.out.println("Item Loaned: " + selectedItem.getName());
                System.out.println("Start Date: Day " + startDate);
                System.out.println("End Date: Day " + endDate);
                System.out.println("Total Cost: " + totalCost + " credits");
                System.out.println("Owner of the Item: " + selectedItem.getOwner().getName());
                System.out.println("--------------------------------");
            } else {
                System.out.println("You don't have enough credits to loan this item.");
            }
        }
    }

    public void advanceDayCounter(Service service) {
        System.out.println("\nAdvancing the day...");
        service.dayCounter();
        System.out.println("You are now on day " + Time.getDate());
    }

    public void viewMemberDetails(Member loggedInMember, Service service) {
        System.out.println("\nYour account details: ");
        System.out.println("Name: " + loggedInMember.getName());
        System.out.println("Username: " + loggedInMember.getUsername());
        System.out.println("Member ID: " + loggedInMember.getMemberId());
        System.out.println("Email: " + loggedInMember.getEmail());
        System.out.println("Phone number: " + loggedInMember.getMobile());
        System.out.println("Account is created: Day " + loggedInMember.getCreationDate());
        System.out.println("Your credit: " + loggedInMember.getCredits());

        System.out.println("\nCreated Contracts:");
        List<Contract> contracts = loggedInMember.getContracts();
        for (int i = 0; i < contracts.size(); i++) {
            System.out.println((i + 1) + ". " + contracts.get(i).getItem().getName()
                    + "\nOwner: " + contracts.get(i).getItem().getOwner().getName()
                    + "\nStart Date: Day " + contracts.get(i).getStartDate()
                    + "\nEnd Date: Day " + contracts.get(i).getEndDate());
        }

        System.out.println("\nPosted Items:");
        List<Item> items = loggedInMember.getOwnedItems();
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i).getCategory() + " / " + items.get(i).getName()
                    + " / " + items.get(i).getDescription() + "\nCost per day: " + items.get(i).getCostPerDay()
                    + "\nCreated: Day " + items.get(i).getCreationDate());
        }

        int setChoice = 0;
        while (setChoice != 4) {
            System.out.println("\n---- Settings Menu ----");
            System.out.println("1. Change member information");
            System.out.println("2. Change item information");
            System.out.println("3. Delete an item");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            setChoice = scanner.nextInt();

            switch (setChoice) {
                case 1:
                    int memberChoice = 0;
                    while (memberChoice != 3) {
                        System.out.println("\n---- Member Settings Menu ----");
                        System.out.println("1. Change your name");
                        System.out.println("2. Change your password");
                        System.out.println("3. Exit");
                        System.out.print("Enter your choice: ");
                        memberChoice = scanner.nextInt();
                        scanner.nextLine();
                        switch (memberChoice) {
                            case 1:
                                System.out.print("Enter your new name: ");
                                String name = scanner.nextLine();
                                loggedInMember.setName(name);
                                System.out.println("Updated successfully!");
                                break;
                            case 2:
                                System.out.print("Enter your new password: ");
                                String password = scanner.nextLine();
                                loggedInMember.setPassword(password);
                                System.out.println("Updated successfully!");
                                break;
                            case 3:
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                    }
                    break;
                case 2:
                    System.out.println("\nEnter the number of the item if you want to update the informations: ");
                    int getItemChoice = scanner.nextInt();
                    Item itemToUpdate = items.get(getItemChoice - 1);
                    int itemChoice = 0;
                    while (itemChoice != 5) {
                        System.out.println("\n---- Item Settings Menu ----");
                        System.out.println("1. Change items category");
                        System.out.println("2. Change items name");
                        System.out.println("3. Change items description");
                        System.out.println("4. Change items cost per day");
                        System.out.println("5. Exit");
                        System.out.print("Enter your choice: ");
                        itemChoice = scanner.nextInt();
                        scanner.nextLine();
                        switch (itemChoice) {
                            case 1:
                                System.out.print("Enter new category: ");
                                String category = scanner.nextLine();
                                itemToUpdate.setCategory(category);
                                System.out.println("Updated successfully!");
                                break;
                            case 2:
                                System.out.print("Enter new name: ");
                                String name = scanner.nextLine();
                                itemToUpdate.setName(name);
                                System.out.println("Updated successfully!");
                                break;
                            case 3:
                                System.out.print("Enter new description: ");
                                String description = scanner.nextLine();
                                itemToUpdate.setDescription(description);
                                System.out.println("Updated successfully!");
                                break;
                            case 4:
                                System.out.print("Enter new cost per day: ");
                                int cost = scanner.nextInt();
                                itemToUpdate.setCostPerDay(cost);
                                System.out.println("Updated successfully!");
                                break;
                            case 5:
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                    }
                    break;
                case 3:
                    System.out.println("\nEnter the number of the item if you want to delete it or 0 to go back: ");
                    int choice = scanner.nextInt();

                    if (choice > 0 && choice <= items.size()) {
                        Item itemToDelete = items.get(choice - 1);
                        service.deleteMemberItem(itemToDelete);
                        System.out.println("Item deleted successfully!");
                    }
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void displayAdminMenu(Service service, Admin admin) {
        if (admin != null) {
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
                        displayAllMembers(service);
                        break;
                    case 2:
                        displayAllItemsAdmin(service);
                        break;
                    case 3:
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }

    public void displayAllMembers(Service service) {
        List<Member> members = service.getAllMembers();
        System.out.println("\nAll Members: ");
        for (int i = 0; i < members.size(); i++) {
            System.out.println((i + 1) + ". " + members.get(i).getMemberId() + " / " + members.get(i).getUsername()
                    + "\nName: " + members.get(i).getName() + "\nEmail: " + members.get(i).getEmail()
                    + "\nPhone number: " + members.get(i).getMobile() + "\nCredits: " + members.get(i).getCredits()
                    + "\nAccount is created: Day " + members.get(i).getCreationDate());
        }

        System.out.println("\nEnter the number of the member you want to ban or 0 to go back: ");
        int choice = scanner.nextInt();

        if (choice > 0 && choice <= members.size()) {
            service.deleteMember(choice);
            System.out.println("Member is banned and all owned items is deleted!");
        }
    }

    public void displayAllItemsAdmin(Service service) {
        List<Item> items = service.getAllItems();
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i).getCategory() + " / " + items.get(i).getName()
                    + " / " + items.get(i).getDescription() + "\nOwner: " + items.get(i).getOwner()
                    + "\nCost per day: " + items.get(i).getCostPerDay()
                    + "\nCreated: Day " + items.get(i).getCreationDate());
        }

        System.out.println("\nEnter the number of the item if you want to delete it or 0 to go back: ");
        int choice = scanner.nextInt();

        if (choice > 0 && choice <= items.size()) {
            service.deleteItem(choice);
            System.out.println("Item deleted successfully!");
        }
    }

}
