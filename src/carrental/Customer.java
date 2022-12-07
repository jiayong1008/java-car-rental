package carrental;

import java.util.ArrayList;


public class Customer extends User {

    private final String customerID;

    // CONSTRUCTORS
    public Customer(ArrayList <String> userInfo)
    {
        // calling superclass constructor to populate basic info
        super(userInfo);

        // Assign user ID manually if not provided
        // User ID will be provided when the program reads directly from database
        // User ID will NOT be provided during initial creation of user
        // The program knows user ID is not provided when the passed in value of user ID is 0
        int userID = Integer.parseInt(userInfo.get(0).substring(1));
        userID = (userID >= 0) ? userID : id;
        customerID = "C" + String.format("%04d", userID);
        id = ++userID;
    }

    // ABSTRACT METHODS
    public String getUserID() { return customerID; }
    public String getRole() { return "Customer"; }

    public String toString()
    {
        return String.format("%s (Customer) - %s", customerID, name);
    }    
}