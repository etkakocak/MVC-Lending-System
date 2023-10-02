package controller;

import view.ViewInterface;
import model.Service;

public class ObjectController {

    public void start(Service m, ViewInterface v) {
        v.initializeStartObjects(m);

        v.displayLoginMenu();

        int choice = v.getLoginType();

        if (choice == 1) {
            v.adminLoginProcess(m);
        } else if (choice == 2) {
            v.memberLoginProcess(m);
        } else if (choice == 3) {
            v.memberCreateProcess(m);
        } else {
            v.displayError();
            start(m, v);
        }
    }
}
