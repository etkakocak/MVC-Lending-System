package view;

import model.Admin;
import model.Member;
import model.Service;

public interface ViewInterface {
    String[] getCredentials();
    void displayLoginMenu(Service service);
    void displayMainMenu(Service service, Member member);
    void postAnItem(Member member, Service service);
    void displayAllItems(Member member, Service service);
    void advanceDayCounter(Service service);
    void viewMemberDetails(Member member, Service service);
    void displayAdminMenu(Service service, Admin admin);
    void displayAllMembers(Service service);
    void displayAllItemsAdmin(Service service);
}
