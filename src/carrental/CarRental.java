/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package carrental;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author JiaYong
 */

/***************       MAIN CLASS        ***********/
public class CarRental {

    private static ArrayList<User> customers = new ArrayList<User>();
    private static ArrayList<Admin> admins = new ArrayList<Admin>();
    // private static ArrayList<Booking> bookings = new ArrayList<Booking>();
    private static final String userFile = "src\\carrental\\database\\users.txt";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        loadUsers();
        loadCars();
        loadBookings();
    }

    public static void loadUsers()
    {
        // Preventing redundancy
        customers.clear(); 
        admins.clear();
        String line;

        try {
            // May throw FileNotFoundException
            BufferedReader br = new BufferedReader(new FileReader(userFile));
            
            br.readLine(); // Skip first line (header)
            while ((line = br.readLine()) != null) { // Read line by line till End Of File (EOF)
                String[] values = line.split(", "); // Split values by comma
                System.out.println(Arrays.toString(values));
                ArrayList<String> personInfo = new ArrayList<String>(Arrays.asList(values));

                if (personInfo.get(1).equals("customer")) {
                    User cust = new Customer(personInfo);
                    customers.add(cust);
                } else if (personInfo.get(1).equals("admin")) {
                    Admin admin = new Admin(personInfo);
                    admins.add(admin);
                }
            }
            br.close();  
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 

        System.out.println(customers.get(0));
        System.out.println(admins.get(0));
    }

    public static void loadCars()
    {
        
    }

    public static void loadBookings()
    {

    }
    
}