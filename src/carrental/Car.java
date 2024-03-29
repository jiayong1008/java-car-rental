package carrental;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class Car {
    
    private final String carID;
    private String carPlate;
    private String carBrand;
    private String carModel;
    private double dailyRentalRate;
    public static int id = 1;
    
    // CONSTUCTORS
    public Car(ArrayList<String> carInfo) 
    {
        carPlate = carInfo.get(1);
        carBrand = carInfo.get(2);
        carModel = carInfo.get(3);
        
        // Assign car ID manually if not provided
        int tmpID = Integer.parseInt(carInfo.get(0).substring(1));
        tmpID = (tmpID >= 0) ? tmpID : id;
        carID = "R" + String.format("%04d", tmpID);
        id = ++tmpID;

        // String to double
        String sDailyRentalRate = carInfo.get(4);
        dailyRentalRate = Double.parseDouble(sDailyRentalRate);
    }
    
    
    // GETTERS
    public String getCarID() { return carID; }
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
    public String toString() {
        return String.format("%s - %s %s - Rental Rate: RM %.02f per day.\n",
                carPlate, carBrand, carModel, dailyRentalRate);
    }

    public boolean isDuplicate() 
    {
        ArrayList<Car> cars = CarRental.getCars();
        
        for (Car car : cars) {
            // Car duplication is trigerred when it has the same car plate
            if (car.getCarPlate().equals(carPlate)) {
                id--;
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
                "%s, %s, %s, %s, %.2f\n", 
                carID, carPlate, carBrand, carModel, dailyRentalRate
            );
            pw.write(line);
            pw.close();

            // Add to ResortBooking's 'customers' ArrayList
            CarRental.addCar(this);
            return true;

        } catch (FileNotFoundException e) {
            System.out.println("User file does not exist");
        } catch (IOException e) {
            System.out.println("Oops..something went wrong.");
        }
        
        return false;
    }

    public boolean updateInfo(String _carPlate, String _carBrand, String _carModel, double _dailyRentalRate)
    {
        setCarPlate(_carPlate);
        setCarBrand(_carBrand);
        setCarModel(_carModel);
        setDailyRentalRate(_dailyRentalRate);

        return rewriteFile();
    }

    public static boolean rewriteFile()
    {
        String line;

        try {
            // May throw FileNotFoundException
            BufferedWriter bw = new BufferedWriter(new FileWriter(CarRental.getCarFile()));
            PrintWriter pw = new PrintWriter(bw);
            pw.write("carID, carPlate, carBrand, carModel, dailyRentalRate\n");
            
            for (Car car : CarRental.getCars()) {
                line = String.format(
                    "%s, %s, %s, %s, %.02f\n", 
                    car.getCarID(),
                    car.getCarPlate(),
                    car.getCarBrand(),
                    car.getCarModel(),
                    car.getDailyRentalRate());
                pw.write(line);
            }
            
            pw.close();
            return true;

        } catch (FileNotFoundException e) {
            System.out.println("User file does not exist");
        } catch (IOException e) {
            System.out.println("Oops..something went wrong.");
        }

        return false;
    }
}
