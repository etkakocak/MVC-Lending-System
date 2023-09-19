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

    public void routeToConsoleUI() {
        // ToDo
    }
}
