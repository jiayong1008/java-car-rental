package carrental;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class Car {
    private String carPlate;
    private String carBrand;
    private String carModel;
    private double dailyRentalRate;
    
    // CONSTUCTORS
    public Car() {}
    public Car(ArrayList<String> carInfo) {
        carPlate = carInfo.get(0);
        carBrand = carInfo.get(1);
        carModel = carInfo.get(2);
        
        //string to double
        String sDailyRentalRate = carInfo.get(3);
        dailyRentalRate = Double.parseDouble(sDailyRentalRate);
    }
    
    
    // GETTERS
    public String getCarPlate() { return carPlate; }
    public String getCarBrand() { return carBrand; }
    public String getCarModel() { return carModel; }
    public double getDailyRentalRate() { return dailyRentalRate; }
    
    // SETTERS
    public void setCarPlate(String _carPlate) { carPlate = _carPlate; }
    public void setCarBrand(String _carBrand) { carBrand = _carBrand; }
    public void setCarModel(String _carModel) { carModel = _carModel; }
    public void setDailyRentalRate(double _dailyRentalRate) { dailyRentalRate = _dailyRentalRate; }

    // CAR-SPECIFIC METHODS
    public boolean isDuplicate() 
    {
        ArrayList<Car> cars = CarRental.getCars();
        
        for (Car car : cars) {
            // Car duplication is trigerred when it has the same car plate
            if (car.getCarPlate().equals(carPlate)) {
                // id--;
                return true;
            }
        }
        return false;
    }

    public boolean addToFile()
    {
        String line;

        try {
            // May throw FileNotFoundException
            BufferedWriter bw = new BufferedWriter(new FileWriter(CarRental.getCarFile(), true));
            PrintWriter pw = new PrintWriter(bw);
            
            line = String.format(
                "%s, %s, %s, %.2f\n", 
                carPlate, carBrand, carModel, dailyRentalRate
            );
            pw.write(line);
            pw.close();

            // Add to ResortBooking's 'customers' ArrayList
            CarRental.addCars(this);
            return true;

        } catch (FileNotFoundException e) {
            System.out.println("User file does not exist");
        } catch (IOException e) {
            System.out.println("Oops..something went wrong.");
        }
        
        return false;
    }
}
