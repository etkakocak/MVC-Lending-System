package controller;

import view.Login;

/**
 * Responsible for staring the application.
 */
public class App {
  /**
   * Application starting point.

   * @param args command line arguments.
   */
  public static void main(String[] args) {
    // adapt to start the application in your way
    ObjectController obj = new ObjectController();
    obj.initializeStartObjects();
    Login login = new Login(obj);
    login.displayLoginMenu();
  }
} 
