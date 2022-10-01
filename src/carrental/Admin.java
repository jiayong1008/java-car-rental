package carrental;

import java.util.ArrayList;

public class Admin extends User {

    private final int adminID;

    // CONSTRUCTORS
    public Admin(ArrayList <String> userInfo)
    {
        // calling superclass constructor to populate basic info
        super(userInfo);

        // assigning user ID manually if not provided
        int userID = Integer.parseInt(userInfo.get(0).substring(1));
        adminID = (userID >= 0) ? userID : id;
        id++;
    }

    // ABSTRACT METHODS
    @Override
    public String toString()
    {
        return String.format("A%04d (Admin) - %s", adminID, name);
    }
    
    @Override
    public boolean addToFile()
    {
        return true;
    }
}
