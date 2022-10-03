package carrental;

import java.util.ArrayList;

public class Customer extends User {

    private final String customerID;

    // CONSTRUCTORS
    public Customer(ArrayList <String> userInfo)
    {
        // calling superclass constructor to populate basic info
        super(userInfo);

        // assigning user ID manually if not provided
        int userID = Integer.parseInt(userInfo.get(0).substring(1));
        userID = (userID >= 0) ? userID : id;
        customerID = "C" + String.format("%04d", userID);
        id++;
    }

    // ABSTRACT METHODS
    public String toString()
    {
        return String.format("%s (Customer) - %s", customerID, name);
    }
    
    public boolean addToFile()
    {
        return true;
    }
}
