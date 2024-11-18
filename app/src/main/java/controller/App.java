package controller;

import model.Service;
import model.Time;
import view.ConsoleUi;
import view.ViewInterface;

/**
 * Responsible for starting the application.
 */
public class App {
  /**
   * Application starting point.
   *
   * @param args command line arguments.
   */
  public static void main(String[] args) {
    Time time = new Time();
    Service model = new Service(time);
    ViewInterface view = new ConsoleUi();
    ObjectController controller = new ObjectController(model, view, time);
    controller.start();
  }
}
