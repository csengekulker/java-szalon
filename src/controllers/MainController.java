package controllers;

import models.Restapi;
import views.MainView;

public class MainController {
    MainView view;
    Restapi service;
    
    public MainController () {
        this.service = new Restapi();

        this.view = new MainView(this.service);
    }
}
