package carrental;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

abstract public class User {

    // INITIALIZATIONS
    protected String name;
    protected String gender;
    protected String contactNo;
    protected String email;
    protected String ic;
    protected String username;
    protected String password;
    public static int id = 1;
    protected static final String FILE = "C:\\Users\\JiaYong\\Documents\\NetBeansProjects\\CarRental\\src\\carrental\\database\\users.txt";

    // CONSTRUCTORS
    public User() {}
    public User(ArrayList<String> userInfo)
    {
        name = userInfo.get(2);
        gender = userInfo.get(3);
        contactNo = userInfo.get(4);
        email = userInfo.get(5);
        ic = userInfo.get(6);
        username = userInfo.get(7);
        password = userInfo.get(8);
    }    

    // GETTERS
    public String getName() { return name; }
    public String getGender() { return gender; }
    public String getContactNo() { return contactNo; }
    public String getEmail() { return email; }
    public String getIC() { return ic; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }

    // SETTERS
    public void setName(String _name) { name = _name; }
    public void setGender(String _gender) { gender = _gender; }
    public void setContactNo(String contact) { contactNo = contact; }
    public void setEmail(String _email) { email = _email; }
    public void setIC(String _ic) { ic = _ic; }
    public void setUsername(String _username) { username = _username; }
    public void setPassword(String _password) { password = _password; }

    public boolean addToFile()
    {
        String line;
        try {
            // May throw FileNotFoundException
            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE, true));
            PrintWriter pw = new PrintWriter(bw);
            
            line = String.format(
                "%s, %s, %s, %s, %s, %s, %s, %s, %s\n", 
                getUserID(), getRole().toLowerCase(), name, gender,
                contactNo, email, ic, username, password);
            pw.write(line);
            pw.close();

            // Incomplete here, subclass need to add this object to CarRental.
            return true;

        } catch (FileNotFoundException e) {
            System.out.println("User file does not exist");
        } catch (IOException e) {
            System.out.println("Oops..something went wrong.");
        }
        return false;
    }

    public boolean isDuplicate() 
    {
        List<User> users = new ArrayList<User>();
        users.addAll(CarRental.getAdmins());
        users.addAll(CarRental.getCustomers());

        // User duplication is trigerred when user has the same IC
        for (User user : users) {
            if (user.getIC().equals(ic)) {
                id--;
                return true;
            }
        }
        return false;
    }

    // NORMAL METHODS
    public boolean updateInfo (String _name, String contact, String _email)
    {
        setName(_name);
        setContactNo(contact);
        setEmail(_email);
        return rewriteFile();
    }

    public static boolean rewriteFile() 
    {
        String line;

        try {
            // May throw FileNotFoundException
            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE));
            PrintWriter pw = new PrintWriter(bw);
            pw.write("userID, role, name, gender, contact, email, IC / passport, username, password\n");

            List <User> users = new ArrayList<User>();
            users.addAll(CarRental.getAdmins());
            users.addAll(CarRental.getCustomers());
            
            for (User user : users) {
                line = String.format(
                    "%s, %s, %s, %s, %s, %s, %s, %s, %s\n", 
                    user.getUserID(), user.getRole().toLowerCase(), user.getName(), user.getGender(),
                    user.getContactNo(), user.getEmail(), user.getIC(), user.getIC(), user.getPassword()
                );
                pw.write(line);
            }
            
            pw.close();
            return true;

        } catch (FileNotFoundException e) {
            System.out.println("User file does not exist");
        } catch (IOException e) {
            System.out.println("Oops..something went wrong.");
        }

        return false;
    }

    // ABSTRACT METHODS
    public abstract String getUserID();
    public abstract String getRole();
    public abstract String toString();
    // public abstract boolean isDuplicate();
    // public abstract boolean addToFile();
}
