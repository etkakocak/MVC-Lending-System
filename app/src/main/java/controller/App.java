package controller;

import model.Service;
import model.Time;
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
    Time time = new Time();
    Service m = new Service(time);
    ViewInterface v = new ConsoleUi();
    // = new SwedishConsoleUi(); (för svenska verison)
    ObjectController ctrl = new ObjectController(m, v, time);
    // = new SwedishController(m, v, time); (för svenska verison)
    ctrl.start();
  }
} 
