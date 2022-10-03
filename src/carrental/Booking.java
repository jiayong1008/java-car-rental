/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carrental;

/**
 *
 * @author vinie
 */

import java.util.ArrayList;
import java.util.Date;

public class Booking {
    
    // INITIALIZATIONS
    private final String bookingId;
    private Customer customer;
    private Car car;
    private Date bookingDate;
    private Date startDate;
    private Date endDate;
    private double bookingFee;
    
    // CONSTRUCTORS
    public Booking(ArrayList<String> bookingInfo)
    {
        bookingId = bookingInfo.get(2);
        
    }
    
    // GETTERS
    public String getBookingId() {return bookingId;} 
    public Customer getCustomer() {return customer;} 
    public Car getCar() {return car;}
    public Date getBookingDate() {return bookingDate;} 
    public Date getStartDate() {return startDate;} 
    public Date getEndDate() {return endDate;} 
    public double getBokkingFee() {return bookingFee;} 
    
    // SETTERS
    // public void setBookingId(String _bookingId) { bookingId = _bookingId; }
    public void setCustomer(Customer _customer) { customer = _customer; }
    public void setCar(Car _car) { car = _car; }
    public void setBookingDate(Date _bookingDate) { bookingDate = _bookingDate; }
    public void setStartDate(Date _startDate) { startDate = _startDate; }
    public void setEndDate(Date _endDate) { endDate = _endDate; }
    public void setBookingFee(double _bookingFee) { bookingFee = _bookingFee; }
    
    
}
