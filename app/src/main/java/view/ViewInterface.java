package view;

import model.Service;
import model.Member;
import model.Admin;
import model.Item;

public interface ViewInterface {
    void displayError();
    Member[] initializeStartMembers(Service service);
    Admin initializeStartAdmin(Service service);
    Item[] initializeStartItems(Service service);
    void displayLoginMenu();
    int getLoginType();
    void adminLoginProcess(Service service);
    void memberLoginProcess(Service service);
    void memberCreateProcess(Service service);
}
