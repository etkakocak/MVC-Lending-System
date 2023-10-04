package controller;

import model.Service;
import view.ConsoleUi;
import view.ViewInterface;

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
    ViewInterface v = new ConsoleUi();
    ObjectController ctrl = new ObjectController();
    ctrl.start(m, v);
  }
} 
