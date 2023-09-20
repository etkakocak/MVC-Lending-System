package view;

import java.util.Scanner;
import controller.AuthenticationController;

public class Login {
    private Scanner scanner;
    private AuthenticationController authController;

    public Login() {
        scanner = new Scanner(System.in);
        authController = new AuthenticationController();
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

        return new String[]{username, password};
    }

    public void startLoginProcess() {
        displayLoginMenu();
        int choice = getLoginType();
        
        switch (choice) {
            case 1:
                // Handle admin login
                String[] adminCredentials = getCredentials();
                boolean adminValid = authController.validateAdmin(adminCredentials[0], adminCredentials[1]);
                if (adminValid) {
                    routeToAdminConsoleUI();
                } else {
                    System.out.println("Invalid admin credentials.");
                }
                break;
            case 2:
                // Handle member login
                String[] memberCredentials = getCredentials();
                boolean memberValid = authController.validateMember(memberCredentials[0], memberCredentials[1]);
                if (memberValid) {
                    routeToMemberConsoleUI();
                } else {
                    System.out.println("Invalid member credentials.");
                }
                break;
            case 3:
                // Handle member account creation
                String[] newMemberCredentials = getCredentials();
                authController.createMemberAccount(newMemberCredentials[0], newMemberCredentials[1]);
                System.out.println("Member account created successfully.");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    public void routeToMemberConsoleUI() {
        ConsoleUI UI = new ConsoleUI();
        UI.displayMainMenu();
    }

    public void routeToAdminConsoleUI() {
        ConsoleUI UI = new ConsoleUI();
        UI.displayAdminMenu();
    }
}
