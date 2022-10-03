package carrental;

import java.util.ArrayList;

public class Admin extends User {

    private final String adminID;

    // CONSTRUCTORS
    public Admin(ArrayList <String> userInfo)
    {
        // calling superclass constructor to populate basic info
        super(userInfo);

        // assigning user ID manually if not provided
        int userID = Integer.parseInt(userInfo.get(0).substring(1));
        userID = (userID >= 0) ? userID : id;
        adminID = "A" + String.format("%04d", userID);
        id++;
    }

    // ABSTRACT METHODS
    @Override
    public String toString()
    {
        return String.format("%s (Admin) - %s", adminID, name);
    }
    
    @Override
    public boolean addToFile()
    {
        return true;
    }
}
