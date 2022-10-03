package carrental;

import java.util.ArrayList;

abstract public class User {

    // INITIALIZATIONS
    protected String name;
    protected String gender;
    protected String contactNo;
    protected String email;
    protected String ic;
    protected String username;
    protected String password;
    protected static int id = 1;
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

    // ABSTRACT METHODS
    @Override
    public abstract String toString();
    public abstract boolean addToFile();
}
