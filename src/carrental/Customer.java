package carrental;

import java.util.ArrayList;

public class Customer extends User {

    private final int customerID;

    // CONSTRUCTORS
    public Customer(ArrayList <String> userInfo)
    {
        // calling superclass constructor to populate basic info
        super(userInfo);

        // assigning user ID manually if not provided
        int userID = Integer.parseInt(userInfo.get(0).substring(1));
        customerID = (userID >= 0) ? userID : id;
        id++;
    }

    // ABSTRACT METHODS
    public String toString()
    {
        return String.format("C%04d (Customer) - %s", customerID, name);
    }
    
    public boolean addToFile()
    {
        return true;
    }
}
