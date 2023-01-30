package views;

import java.util.ArrayList;

import models.Restapi;
import models.Vehicle;

// no view, only logs
public class MainView {
    Restapi service;

    public MainView (Restapi service) {
        this.service = service;
        this.init();
    }

    public void init() {
        ArrayList<Vehicle> vehicles = this.service.getVehicles();
        for (Vehicle v : vehicles)
        System.out.printf("%10s | %12s | %6.2f\n", 
            v.getLicensePlate(),
            v.getPrice(),
            v.getBrand()
        );
    }
}
