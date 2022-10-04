/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carrental;
import java.util.ArrayList;


public class Car {
    private String carPlate;
    private String carBrand;
    private String carModel;
    private double dailyRentalRate;
    
    //CONSTUCTORS
    public Car(ArrayList<String> carInfo){
        carPlate = carInfo.get(0);
        carBrand = carInfo.get(1);
        carModel = carInfo.get(2);
        
        //string to double
        String sDailyRentalRate = carInfo.get(3);
        dailyRentalRate = Double.parseDouble(sDailyRentalRate);
    }
    
    
    //GETTERS
    public String getCarPlate() { return carPlate; }
    public String getCarBrand() { return carBrand; }
    public String getCarModel() { return carModel; }
    public double getDailyRentalRate() { return dailyRentalRate; }
    
    //SETTERS
    public void setCarPlate(String _carPlate) { carPlate = _carPlate; }
    public void setCarBrand(String _carBrand) { carBrand = _carBrand; }
    public void setCarModel(String _carModel) { carModel = _carModel; }
    public void setDailyRentalRate(double _dailyRentalRate) { dailyRentalRate = _dailyRentalRate; }
}
