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
import java.util.List;

public class Booking {
    
    // INITIALIZATIONS
    private final String bookingID;
    private Customer customer;
    private Car car;
    private LocalDate bookingDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private double bookingFee;
    private String status;
    public static int id = 1;
    
    // CONSTRUCTORS
    public Booking(ArrayList<String> bookingInfo)
    {
        // assigning user ID manually if not provided
        int bookingId = Integer.parseInt(bookingInfo.get(0).substring(1));
        bookingId = (bookingId >= 0) ? bookingId : id;
        bookingID = "B" + String.format("%04d", bookingId);
        id = ++bookingId;
        
        String sBookingDate = bookingInfo.get(3);
        String sstartDate = bookingInfo.get(4);
        String sendDate = bookingInfo.get(5);
        
        //string to double
        String sBookingFee = bookingInfo.get(6);
        bookingFee = Double.parseDouble(sBookingFee);
        
        //Date Format
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        //Convert String to Date
        bookingDate = LocalDate.parse(sBookingDate, format);
        startDate = LocalDate.parse(sstartDate, format);
        endDate = LocalDate.parse(sendDate, format);

        // Initialize Customer and Car object
        String custID = bookingInfo.get(1);
        String carNo = bookingInfo.get(2);
        status = bookingInfo.get(7);

        for (Customer cust : CarRental.getCustomers()) 
        {
            if (cust.getUserID().equals(custID)) {
                customer = cust;
                break;
            }
        }

        for (Car _car : CarRental.getCars()) 
        {
            if (_car.getCarPlate().equals(carNo)) {
                car = _car;
                break;
            }
        }
       
    }
    
    // GETTERS
    public String getBookingId() {return bookingID;} 
    public Customer getCustomer() {return customer;} 
    public Car getCar() {return car;}
    public LocalDate getBookingDate() {return bookingDate;} 
    public LocalDate getStartDate() {return startDate;} 
    public LocalDate getEndDate() {return endDate;} 
    public double getBookingFee() {return bookingFee;}
    public String getStatus() {return status;} 
    
    // SETTERS
    public void setCustomer(Customer _customer) { customer = _customer; }
    public void setCar(Car _car) { car = _car; }
    public void setBookingDate(LocalDate _bookingDate) { bookingDate = _bookingDate; }
    public void setStartDate(LocalDate _startDate) { startDate = _startDate; }
    public void setEndDate(LocalDate _endDate) { endDate = _endDate; }
    public void setBookingFee(double _bookingFee) { bookingFee = _bookingFee; }
    public void setStatus(String _status) { status = _status; }
    
    public int getRentalDuration() 
    {
        try {
            long duration = ChronoUnit.DAYS.between(startDate, endDate);
            return (int) duration;
        } catch (Exception e) {
            return -1;
        }   
    }

    public boolean isDuplicate() 
    {
        List<Booking> bookings = new ArrayList<Booking>();
        bookings.addAll(CarRental.getBookings());

        // User duplication is trigerred when user has the same IC
        for (Booking booking : bookings) {
            if (booking.getBookingId().equals(bookingID)) {
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
            BufferedWriter bw = new BufferedWriter(new FileWriter(CarRental.getBookingFile(), true));
            PrintWriter pw = new PrintWriter(bw);
            
            line = String.format(
                "%s, %s, %s, %s, %s, %s, %.2f, %s\n", 
                bookingID, customer.getUserID(), car.getCarPlate(), bookingDate, startDate, endDate, bookingFee, status
            );
            pw.write(line);
            pw.close();

            // Add to ResortBooking's 'customers' ArrayList
            CarRental.addBooking(this);
            return true;

        } catch (FileNotFoundException e) {
            System.out.println("Booking file does not exist");
        } catch (IOException e) {
            System.out.println("Oops..something went wrong.");
        }
        
        return false;
    }
    
    public boolean updateInfo (String _carNo, LocalDate _startDate, LocalDate _endDate, Double _bookingFee)
    {
        for (Car car : CarRental.getCars())
        {
            if (car.getCarPlate().equals(_carNo))
            setCar(car);
        }
        setStartDate(_startDate);
        setEndDate(_endDate);
        setBookingFee(_bookingFee);
        return rewriteFile();
    }
    
    public boolean updateStatus (String _status)
    {
        setStatus(_status);
        return rewriteFile();
    }

    public static boolean rewriteFile() 
    {
        String line;

        try {
            // May throw FileNotFoundException
            BufferedWriter bw = new BufferedWriter(new FileWriter(CarRental.getBookingFile()));
            PrintWriter pw = new PrintWriter(bw);
            pw.write("bookingId, customerId, carPlate, bookingDate, startDate, endDate, bookingFee, status\n");

            List <Booking> bookings = new ArrayList<Booking>();
            bookings.addAll(CarRental.getBookings());
            
            for (Booking booking: bookings) {
                line = String.format(
                    "%s, %s, %s, %s, %s, %s, %.02f, %s\n", 
                    booking.getBookingId(),
                    booking.getCustomer().getUserID(),
                    booking.getCar().getCarPlate(),
                    booking.getBookingDate(),
                    booking.getStartDate(),
                    booking.getEndDate(),
                    booking.getBookingFee(),
                    booking.getStatus());
                pw.write(line);
            }
            
            pw.close();
            return true;

        } catch (FileNotFoundException e) {
            System.out.println("Booking file does not exist");
        } catch (IOException e) {
            System.out.println("Oops..something went wrong.");
        }

        return false;
    }
}