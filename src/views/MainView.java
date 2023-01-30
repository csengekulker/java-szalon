package views;

import models.Restapi;

// no view, only logs
public class MainView {
    Restapi service;

    public MainView (Restapi service) {
        this.service = service;
        this.init();
    }

    public void init() {
        this.service.getVehicles();
    }
}
