package view;

import model.Service;

public interface ViewInterface {
    void displayError();
    void initializeStartObjects(Service service);
    void displayLoginMenu();
    int getLoginType();
    void adminLoginProcess(Service service);
    void memberLoginProcess(Service service);
    void memberCreateProcess(Service service);
}
