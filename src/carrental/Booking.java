package carrental;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Booking {
    
    // INITIALIZATIONS
    private final String bookingID;
    private final String custID;
    private final String carNo;
    private Customer customer;
    private Car car;
    private LocalDate bookingDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private double bookingFee;
    
    // CONSTRUCTORS
    public Booking(ArrayList<String> bookingInfo)
    {
        bookingID = bookingInfo.get(0);
        String sBookingDate = bookingInfo.get(3);
        String sstartDate = bookingInfo.get(4);
        String sendDate = bookingInfo.get(5);
        
        //string to double
        String sBookingFee = bookingInfo.get(6);
        bookingFee = Double.parseDouble(sBookingFee);
        
        //Date Format
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        
        //Convert String to Date
        bookingDate = LocalDate.parse(sBookingDate, format);
        startDate = LocalDate.parse(sstartDate, format);
        endDate = LocalDate.parse(sendDate, format);

        // Todo - Initialize Customer and Car object
        custID = bookingInfo.get(1);
        carNo = bookingInfo.get(2);
//        customerID = bookingInfo.get(1);
//        carPlate = bookingInfo.get(2);
    }
    
    // GETTERS
    public String getBookingId() {return bookingID;} 
    public String getCustID() {return custID;} 
    public String getCarNo() {return carNo;} 
    public Customer getCustomer() {return customer;} 
    public Car getCar() {return car;}
    public LocalDate getBookingDate() {return bookingDate;} 
    public LocalDate getStartDate() {return startDate;} 
    public LocalDate getEndDate() {return endDate;} 
    public double getBookingFee() {return bookingFee;}

    public int getRentalDuration() 
    {
        try {
            long duration = ChronoUnit.DAYS.between(startDate, endDate);
            return (int) duration;
        } catch (Exception e) {
            return -1;
        }   
    }
    
    // SETTERS
    // public void setBookingId(String _bookingId) { bookingId = _bookingId; }
    public void setCustomer(Customer _customer) { customer = _customer; }
    public void setCar(Car _car) { car = _car; }
//    public void setCustID(String _custID) { custID = _custID; }
//    public void setCarNo(String _carNo) { carNo = _carNo; }
    public void setBookingDate(LocalDate _bookingDate) { bookingDate = _bookingDate; }
    public void setStartDate(LocalDate _startDate) { startDate = _startDate; }
    public void setEndDate(LocalDate _endDate) { endDate = _endDate; }
    public void setBookingFee(double _bookingFee) { bookingFee = _bookingFee; }
    
    public static boolean rewriteFile()
    {
        String line;

        try {
            // May throw FileNotFoundException
            BufferedWriter bw = new BufferedWriter(new FileWriter(CarRental.getCarFile()));
            PrintWriter pw = new PrintWriter(bw);
            pw.write("carPlate, carBrand, carModel, dailyRentalRate\n");
            
            for (Car car : CarRental.getCars()) {
                line = String.format(
                    "%s, %s, %s, %.02f\n", 
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
