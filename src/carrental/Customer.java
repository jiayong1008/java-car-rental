package carrental;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
        {
        String line;

        try {
            // May throw FileNotFoundException
            BufferedWriter bw = new BufferedWriter(new FileWriter(CarRental.getUserFile(), true));
            PrintWriter pw = new PrintWriter(bw);
            String userID = "C00test";
            String role = "Customer";
            line = String.format(
                "%s, %s, %s, %s, %s, %s, %s, %s, %s\n", 
                userID, role, name, gender, contactNo, email, ic, username, password
            );
            pw.write(line);
            pw.close();

            // Add to ResortBooking's 'customers' ArrayList
            CarRental.addCustomers(this);
            return true;

        } catch (FileNotFoundException e) {
            System.out.println("User file does not exist");
        } catch (IOException e) {
            System.out.println("Oops..something went wrong.");
        }
        
        return false;
    }
}
