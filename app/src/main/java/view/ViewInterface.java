package view;

/**
 * Interface for the view component in the MVC architecture.
 */
public interface ViewInterface {
  /**
   * Displays the main menu to the user.
   */
  void displayMenu();

  /**
   * Displays a message to the user.
   */
  void showMessage(String message);

  /**
   * Gets input from the user.
   */
  String getUserInput();

  /**
   * Gets menu choice from the user.
   */
  MenuOption getUserChoice();
}