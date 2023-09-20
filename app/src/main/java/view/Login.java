package view;

import java.util.Scanner;
import controller.AuthenticationController;
import model.Member;
import model.Admin;

public class Login {
    private Scanner scanner;
    private AuthenticationController authController;

    public Login(AuthenticationController authController) {
        scanner = new Scanner(System.in);
        this.authController = authController;
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
                String[] adminCredentials = getCredentials();
                Admin admin = authController.validateAdmin(adminCredentials[0], adminCredentials[1]);
                if (admin != null) {
                    routeToAdminConsoleUI(admin);
                } else {
                    System.out.println("Invalid member credentials.");
                }
                break;
            case 2:
                // Handle member login
                String[] memberCredentials = getCredentials();
                Member loggedInMember = authController.validateMember(memberCredentials[0], memberCredentials[1]);
                if (loggedInMember != null) {
                    routeToMemberConsoleUI(loggedInMember);
                } else {
                    System.out.println("Invalid member credentials.");
                }
                break;
            case 3:
                // Handle member account creation
                System.out.print("Enter your name: ");
                String name = scanner.nextLine();
                
                System.out.print("Enter your email: ");
                String email = scanner.nextLine();

                System.out.print("Enter your phone number: ");
                String mobile = scanner.nextLine();

                System.out.print("Enter your username: ");
                String username = scanner.nextLine();

                System.out.print("Enter your password: ");
                String password = scanner.nextLine();

                authController.createMemberAccount(name, email, mobile, username, password);
                System.out.println("Member account created successfully.");

                Member newMember = authController.validateMember(username, password);
                routeToMemberConsoleUI(newMember);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    public void routeToMemberConsoleUI(Member loggedInMember) {
        ConsoleUI UI = new ConsoleUI(loggedInMember);
        UI.displayMainMenu();
    }

    public void routeToAdminConsoleUI(Admin admin) {
        ConsoleUI UI = new ConsoleUI(admin);
        UI.displayAdminMenu();
    }
}
