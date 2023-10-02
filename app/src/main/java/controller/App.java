package controller;

import model.Service;
import view.ViewInterface;
import view.ConsoleUI;

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
    Service m = new Service();
    ViewInterface v = new ConsoleUI();
    ObjectController ctrl = new ObjectController();
    ctrl.start(m, v);
  }
} 
