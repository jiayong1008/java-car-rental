/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carrental;

/**
 *
 * @author vinie
 */
public class Car {
    private String carPlate;
    private String carBrand;
    private double dailyRentalRate;
    
    //CONSTUCTORS
    public Car(){}
    // public Car
    
    
    //GETTERS
    public String getCarPlate() { return carPlate; }
    public String getCarBrand() { return carBrand; }
    public double getDailyRentalRate() { return dailyRentalRate; }
    
    //SETTERS
    public void setCarPlate(String _carPlate) { carPlate = _carPlate; }
    public void setCarBrand(String _carBrand) { carBrand = _carBrand; }
    public void setDailyRentalRate(double _dailyRentalRate) { dailyRentalRate = _dailyRentalRate; }
}
