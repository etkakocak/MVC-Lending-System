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
    AuthenticationController aut = new AuthenticationController();
    aut.initializeMembers();
    Login login = new Login(aut);
    login.startLoginProcess();
  }
} 
