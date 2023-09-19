package view;

import java.util.Scanner;

public class ConsoleUI {
    private Scanner scanner;

    public ConsoleUI() {
        scanner = new Scanner(System.in);
    }

    // This is draft and should be implemented

    public void displayMenu() {
        System.out.println("Welcome to the Stuff Lending System!");
        System.out.println("1. Add Member");
        System.out.println("2. Add Item");
        System.out.println("3. Establish Contract");
        System.out.println("4. Advance Day");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    public int readChoice() {
        int choice = scanner.nextInt();
        scanner.nextLine(); 
        return choice;
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}
