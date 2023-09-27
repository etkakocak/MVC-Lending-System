package view;

import java.util.Scanner;
import controller.ObjectController;
import model.Member;
import model.Admin;

public class Login {
    private Scanner scanner;
    private ObjectController objController;

    public Login(ObjectController objController) {
        scanner = new Scanner(System.in);
        this.objController = objController;
    }

    public void displayLoginMenu() {
        System.out.println("Welcome to the Stuff Lending System Login!");
        System.out.println("1. Login as Admin");
        System.out.println("2. Login as Member");
        System.out.println("3. Create Member Account");
        System.out.print("Enter your choice: ");
    }

    public int getLoginType() {
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    public String[] getCredentials() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        return new String[] { username, password };
    }

    public void startLoginProcess() {
        displayLoginMenu();
        int choice = getLoginType();

        switch (choice) {
            case 1:
                // Handle admin login
                boolean adminValidated = false;
                while (!adminValidated) {
                    String[] adminCredentials = getCredentials();
                    Admin admin = objController.validateAdmin(adminCredentials[0], adminCredentials[1]);
                    if (admin != null) {
                        routeToAdminConsoleUI(admin, objController);
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
                    Member loggedInMember = objController.validateMember(memberCredentials[0], memberCredentials[1]);
                    if (loggedInMember != null) {
                        routeToMemberConsoleUI(loggedInMember, objController);
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

                System.out.print("Enter your username: ");
                String username = scanner.nextLine();

                System.out.print("Enter your password: ");
                String password = scanner.nextLine();

                if (objController.canAddMember(email, mobile)) {
                    objController.createMemberAccount(name, email, mobile, username, password);
                    System.out.println("Member account created successfully.");

                    Member newMember = objController.validateMember(username, password);
                    routeToMemberConsoleUI(newMember, objController);
                } else {
                    System.out.println("E-post eller mobilnummer används redan!");
                }
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    public void routeToMemberConsoleUI(Member loggedInMember, ObjectController objController) {
        ConsoleUI UI = new ConsoleUI(loggedInMember, objController);
        UI.displayMainMenu();
    }

    public void routeToAdminConsoleUI(Admin admin, ObjectController objController) {
        ConsoleUI UI = new ConsoleUI(admin, objController);
        UI.displayAdminMenu();
    }
}
