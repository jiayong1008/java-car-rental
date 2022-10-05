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

    private static ArrayList<Customer> customers = new ArrayList<Customer>();
    private static ArrayList<Admin> admins = new ArrayList<Admin>();
    private static ArrayList<Car> cars = new ArrayList<Car>();
    private static final String userFile = "src\\carrental\\database\\users.txt";
    private static final String carFile = "src\\carrental\\database\\cars.txt";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        loadUsers();
        loadCars();
        // loadBookings();
        login();
    }

    // GETTERS
    public static ArrayList<Admin> getAdmins() { return admins; }
    public static ArrayList<Customer> getCustomers() { return customers; }
    public static ArrayList<Car> getCars() { return cars; }
    public static String getCarFile() { return carFile; }

    // ADDING INFORMATION
    public static void addCars(Car car) { cars.add(car); }

    
    public static void login() 
    {
        LoginFrame loginf = new LoginFrame();
        loginf.setVisible(true);
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

            while ((line = br.readLine()) != null) 
            { // Read line by line till End Of File (EOF)
                String[] values = line.split(", "); // Split values by comma
                ArrayList<String> personInfo = new ArrayList<String>(Arrays.asList(values));

                if (personInfo.get(1).equals("customer")) {
                    Customer cust = new Customer(personInfo);
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
    }

    public static void loadCars()
    {
        // Preventing redundancy
        cars.clear(); 
        String line;

        try {
            // May throw FileNotFoundException
            BufferedReader br = new BufferedReader(new FileReader(carFile));
            br.readLine(); // Skip first line (header)

            while ((line = br.readLine()) != null) 
            { // Read line by line till End Of File (EOF)
                String[] values = line.split(", "); // Split values by comma
                ArrayList<String> carInfo = new ArrayList<String>(Arrays.asList(values));
                Car car = new Car(carInfo);
                cars.add(car);
            }
            br.close();  
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadBookings()
    {

    }
    
}