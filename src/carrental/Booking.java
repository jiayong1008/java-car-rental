package carrental;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Booking {
    
    // INITIALIZATIONS
    private final String bookingId;
    private Customer customer;
    private Car car;
    private LocalDate bookingDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private double bookingFee;
    
    // CONSTRUCTORS
    public Booking(ArrayList<String> bookingInfo)
    {
        bookingId = bookingInfo.get(0);
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
        
        
    }
    
    // GETTERS
    public String getBookingId() {return bookingId;} 
    public Customer getCustomer() {return customer;} 
    public Car getCar() {return car;}
    public LocalDate getBookingDate() {return bookingDate;} 
    public LocalDate getStartDate() {return startDate;} 
    public LocalDate getEndDate() {return endDate;} 
    public double getBookingFee() {return bookingFee;} 
    
    // SETTERS
    // public void setBookingId(String _bookingId) { bookingId = _bookingId; }
    public void setCustomer(Customer _customer) { customer = _customer; }
    public void setCar(Car _car) { car = _car; }
    public void setBookingDate(LocalDate _bookingDate) { bookingDate = _bookingDate; }
    public void setStartDate(LocalDate _startDate) { startDate = _startDate; }
    public void setEndDate(LocalDate _endDate) { endDate = _endDate; }
    public void setBookingFee(double _bookingFee) { bookingFee = _bookingFee; }
    
    
}
