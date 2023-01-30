package models;

public class Vehicle {

  String host;

  // implement interface
  String name;
  String licensePlate;
  String brand;
  double price;

  public Vehicle(String name, String licensePlate, String brand, double price) {
    this.name = name;
    this.licensePlate = licensePlate;
    this.brand = brand;
    this.price = price;

  }

  public Vehicle() {
  }

 
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLicensePlate() {
    return licensePlate;
  }

  public void setLicensePlate(String licensePlate) {
    this.licensePlate = licensePlate;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

}
