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
    
    public boolean addToFile()
    {
        String role = "customer";
        String line;

        try {
            // May throw FileNotFoundException
            BufferedWriter bw = new BufferedWriter(new FileWriter(CarRental.getUserFile(), true));
            PrintWriter pw = new PrintWriter(bw);
            
            line = String.format(
                "%s, %s, %s, %s, %s, %s, %s, %s, %s\n", 
            customerID, role, name, gender, contactNo, email, ic, username, password
            );
            pw.write(line);
            pw.close();

            // Add to CarRental's 'customers' ArrayList
            CarRental.addCustomer(this);
            return true;

        } catch (FileNotFoundException e) {
            System.out.println("User file does not exist");
        } catch (IOException e) {
            System.out.println("Oops..something went wrong.");
        }
        
        return false;
    }
    
}