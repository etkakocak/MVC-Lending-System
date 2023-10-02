package controller;

import view.ViewInterface;
import model.Service;

public class ObjectController {

    public void start(Service m, ViewInterface v) {
        v.displayLoginMenu(m);
        
    }
}
