/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package carrental;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JiaYong
 */
public class AdminFrame extends javax.swing.JFrame {

    private static User user;
    private final SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
    DefaultListModel lm = new DefaultListModel<>();
    Object[] columns = new Object[8]; // For individual table row
    private int receiptID; // Current booking ID of receipt

    /**
     * Creates new form AdminFrame
     */
    public AdminFrame(User admin) {
        user = admin;
        initComponents();
        loadUsers();
        loadCars();
        loadBookings();
        loadNewBookings();
    }

// ================     HELPER FUNCTIONS        ========================

    // Populate record to table - Overload method (User)
    public void addTableRow(DefaultTableModel model, User user) 
    {
        columns[0] = user.getUserID();
        columns[1] = user.getName();
        columns[2] = user.getRole();
        columns[3] = user.getGender();
        columns[4] = user.getContactNo();
        columns[5] = user.getEmail();
        columns[6] = user.getIC();
        columns[7] = user.getUsername();
        model.addRow(columns);
    }

    // Populate record to table - Overload method (Car)
    public void addTableRow(DefaultTableModel model, Car car) 
    {
        columns[0] = car.getCarID();
        columns[1] = car.getCarPlate();
        columns[2] = car.getCarBrand();
        columns[3] = car.getCarModel();
        columns[4] = String.format("%.2f", car.getDailyRentalRate());
        model.addRow(columns);
    }
    
    // Populate record to table - Overload method (Booking)
    public void addTableRow(DefaultTableModel model, Booking booking) 
    {
        columns[0] = booking.getBookingId();
        columns[1] = booking.getCustomer().getUserID();
        columns[2] = booking.getCar().getCarPlate();
        columns[3] = booking.getBookingDate();
        columns[4] = booking.getStartDate();
        columns[5] = booking.getEndDate();
        columns[6] = booking.getBookingFee();
        model.addRow(columns);        
    }

    // Load users in table
    private void loadUsers() 
    {
        // Polymorphism - adding admin (subclass) and customer (subclass) to User (superclass) list 
        List<User> users = new ArrayList<User>();
        users.addAll(CarRental.getAdmins());
        users.addAll(CarRental.getCustomers());

        DefaultTableModel tableModel = (DefaultTableModel) tableUsers.getModel();
        tableModel.setRowCount(0);
        int index = users.size();
        
        for (int i = 0; i < index; i++) {
            addTableRow(tableModel, users.get(i));
        }
    }

    // Load cars in table
    private void loadCars() 
    {
        ArrayList<Car> cars = CarRental.getCars();
        DefaultTableModel tableModel = (DefaultTableModel) tableCars.getModel();
        tableModel.setRowCount(0);
        int index = cars.size();
        
        for (int i = 0; i < index; i++) {
            addTableRow(tableModel, cars.get(i));
        }
    }

    private void loadBookings() 
    {
        ArrayList<Booking> bookings = CarRental.getBookings();
        DefaultTableModel tableModel = (DefaultTableModel) tableBookings.getModel();
        tableModel.setRowCount(0);
        int index = bookings.size();
        
        for (int i = 0; i < index; i++) {
            addTableRow(tableModel, bookings.get(i));
        }
    }
    
    private void loadNewBookings() {
        DefaultTableModel tableModel = (DefaultTableModel) tableNewBookings.getModel();
        tableModel.setRowCount(0);
        
        for (Booking booking: CarRental.getBookings()) {
            if(booking.getStatus().equals("WAITING FOR CONFIRMATION")){
                columns[0] = booking.getBookingId();
                columns[1] = booking.getBookingDate();
                columns[2] = booking.getCar().getCarPlate();
                columns[3] = booking.getStartDate();
                columns[4] = booking.getEndDate();
                columns[5] = booking.getBookingFee();
                tableModel.addRow(columns);
            }
            
        }
    }

    private Booking validateBookingID() {
        try {
            String bookingID = txtBookIdReceipt.getText().trim().toUpperCase();

            for (Booking booking : CarRental.getBookings())
            {
                if (!booking.getBookingId().equals(bookingID)) continue;
                
                receiptID = Integer.parseInt(bookingID.substring(1));
                return booking;
            }
            return null;

        } catch (Exception e) {
            return null;
        }
    }

    private void loadReceipt(Booking booking) {

        receiptID = Integer.parseInt(booking.getBookingId().substring(1));
        float subtotal = (float) booking.getBookingFee();
        float tax = subtotal * CarRental.getTax();
        float total = subtotal + tax;

        txtAreaReceipt.setText("  *******************************************\n");
        txtAreaReceipt.setText(txtAreaReceipt.getText() + String.format("  *                BOOKING %s RECEIPT            *\n", booking.getBookingId()));
        txtAreaReceipt.setText(txtAreaReceipt.getText() + "  *******************************************\n");
        txtAreaReceipt.setText(txtAreaReceipt.getText() + String.format("\t    %s \n\n", booking.getBookingDate()));

        txtAreaReceipt.setText(txtAreaReceipt.getText() + String.format("  Customer Name:\t%s\n", booking.getCustomer().getName()));
        txtAreaReceipt.setText(txtAreaReceipt.getText() + String.format("  Customer ID:\t\t%s\n", booking.getCustomer().getUserID()));
        txtAreaReceipt.setText(txtAreaReceipt.getText() + String.format("  Car Model:\t\t%s\n", booking.getCar().getCarModel()));
        txtAreaReceipt.setText(txtAreaReceipt.getText() + String.format("  Car Plate:\t\t%s\n", booking.getCar().getCarPlate()));
        txtAreaReceipt.setText(txtAreaReceipt.getText() + String.format("  Rental Start Date:\t%s\n", booking.getStartDate()));
        txtAreaReceipt.setText(txtAreaReceipt.getText() + String.format("  Rental End Date:\t%s\n", booking.getEndDate()));
        txtAreaReceipt.setText(txtAreaReceipt.getText() + String.format("  Rental Duration:\t%d days\n", booking.getRentalDuration()));
        txtAreaReceipt.setText(txtAreaReceipt.getText() + String.format("  Price per Day:\t\tRM%.2f\n", booking.getCar().getDailyRentalRate()));
        txtAreaReceipt.setText(txtAreaReceipt.getText() + String.format("  Subtotal:\t\tRM%.2f\n", subtotal));
        txtAreaReceipt.setText(txtAreaReceipt.getText() + String.format("  Tax (10%%):\t\tRM%.2f\n", tax));

        txtAreaReceipt.setText(txtAreaReceipt.getText() + "  ___________________________________________\n");
        txtAreaReceipt.setText(txtAreaReceipt.getText() + String.format("   TOTAL:\t\tRM%.2f\n", total));
    }

    private void generateReport (LocalDate startDate, LocalDate endDate) {

        String strStartDate = startDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
        String strEndDate = endDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
        ArrayList<Booking> bookings = new ArrayList<Booking>();
        ArrayList<Customer> custs = new ArrayList<Customer>();
        ArrayList<Car> rentedCars = new ArrayList<Car>();
        HashMap<String, Double> carBrands = new HashMap<String, Double>();  
        LocalDate bookDate;
        Double totalRevenue = 0.0;
        int totalDaysRented = 0;

        // Searching for booking records matching start and end date
        for (Booking booking : CarRental.getBookings())
        {
            bookDate = booking.getBookingDate();
            // Another way - (bookDate.compareTo(startDate) >= 0 && bookDate.compareTo(endDate) <= 0)
            if ((bookDate.isAfter(startDate) || bookDate.isEqual(startDate)) && 
                (bookDate.isBefore(endDate) || bookDate.isEqual(endDate)))
            {
                bookings.add(booking);
            }
        }

        // Calculate report info
        for (Booking booking : bookings)
        {
            Double bookingFee = booking.getBookingFee();
            totalRevenue += bookingFee;
            totalDaysRented += booking.getRentalDuration();

            Car car = booking.getCar();
            String carBrand = car.getCarBrand();

            if (!carBrands.containsKey(carBrand))
            carBrands.put(carBrand, bookingFee);

            else
            carBrands.replace(carBrand, carBrands.get(carBrand) + bookingFee);

            if (!rentedCars.contains(car)) 
            rentedCars.add(car);

            if (!custs.contains(booking.getCustomer())) 
            custs.add(booking.getCustomer());
        }

        Float avgBookDuration = (float) totalDaysRented / bookings.size();
        avgBookDuration = avgBookDuration.isNaN() ? 0.0F : avgBookDuration;

        txtAreaReport.setText("  *******************************************\n");
        txtAreaReport.setText(txtAreaReport.getText() + "  *                RAPID CAR SALES REPORT            *\n");
        txtAreaReport.setText(txtAreaReport.getText() + "  *******************************************\n");
        txtAreaReport.setText(txtAreaReport.getText() + String.format(" Date: %s ~ %s\n\n", strStartDate, strEndDate));

        txtAreaReport.setText(txtAreaReport.getText() + String.format(" Total Revenue:\tRM%.2f\n", totalRevenue));
        txtAreaReport.setText(txtAreaReport.getText() + String.format(" Total Customers:\t%d\n", custs.size()));
        txtAreaReport.setText(txtAreaReport.getText() + String.format(" Total Cars Rented:\t%d\n", rentedCars.size()));
        txtAreaReport.setText(txtAreaReport.getText() + String.format(" Total Days Rented:\t%d\n", totalDaysRented));
        txtAreaReport.setText(txtAreaReport.getText() + String.format(" Average Booking Duration:\t%.2f days\n\n", avgBookDuration));

        if (carBrands.size() == 0) {
            txtAreaReport.setText(txtAreaReport.getText() + String.format(" No Cars Rented Between:\n %s to %s.\n", strStartDate, strEndDate));
            return;
        }

        txtAreaReport.setText(txtAreaReport.getText() + " Car Brand \t\t Revenue (RM)\n");
        txtAreaReport.setText(txtAreaReport.getText() + "  ___________________________________________\n");
        for (Map.Entry m : carBrands.entrySet()) {    
            txtAreaReport.setText(txtAreaReport.getText() + String.format(" %s \t\t %.2f\n", m.getKey(), m.getValue()));   
        }  

        txtAreaReport.setText(txtAreaReport.getText() + "  ___________________________________________\n");
        txtAreaReport.setText(txtAreaReport.getText() + String.format("   TOTAL:\t\tRM%.2f\n", totalRevenue));
    }



// ================     END OF HELPER FUNCTIONS        ========================

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel59 = new javax.swing.JLabel();
        btnBookingsSearch1 = new javax.swing.JButton();
        bg = new javax.swing.JPanel();
        pnlHeader = new javax.swing.JPanel();
        lblRapidCar = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        tabManageBookings = new javax.swing.JPanel();
        formBook2 = new javax.swing.JPanel();
        btnBookingsSearch = new javax.swing.JButton();
        btnBookingsEdit = new javax.swing.JButton();
        btnBookingsDelete = new javax.swing.JButton();
        txtBookingCP = new javax.swing.JTextField();
        txtBookingDate = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        txtCustomerID = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        txtBookingID = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        txtPickUp = new javax.swing.JTextField();
        txtAmount = new javax.swing.JTextField();
        txtDropOff = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        btnBookingsAdd = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableBookings = new javax.swing.JTable();
        tabManageCars = new javax.swing.JPanel();
        formBook3 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        txtCarPlate = new javax.swing.JTextField();
        txtCarBrand = new javax.swing.JTextField();
        txtCarModel = new javax.swing.JTextField();
        txtCarDailyRate = new javax.swing.JTextField();
        btnAddCar = new javax.swing.JButton();
        btnSearchCar = new javax.swing.JButton();
        btnEditCar = new javax.swing.JButton();
        btnDeleteCar = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        btnResetCar = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tableCars = new javax.swing.JTable();
        tabManageUsers = new javax.swing.JPanel();
        formBook = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        comRole = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtContact = new javax.swing.JTextField();
        btnUserSearch = new javax.swing.JButton();
        txtUserFullName = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        comGender = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtNric = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        pass = new javax.swing.JPasswordField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnUserAdd = new javax.swing.JButton();
        btnUserEdit = new javax.swing.JButton();
        btnUserDelete = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        btnUserReset = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableUsers = new javax.swing.JTable();
        tabReceipts = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtAreaReceipt = new javax.swing.JTextArea();
        jLabel43 = new javax.swing.JLabel();
        txtBookIdReceipt = new javax.swing.JTextField();
        btnNextReceipt = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        btnPrintReceipt = new javax.swing.JButton();
        btnSearchReceipt = new javax.swing.JButton();
        btnFirstReceipt = new javax.swing.JButton();
        btnPreviousReceipt = new javax.swing.JButton();
        btnLastReceipt = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        tabReport = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtAreaReport = new javax.swing.JTextArea();
        jLabel57 = new javax.swing.JLabel();
        jCalendar2 = new com.toedter.calendar.JCalendar();
        btnReportStartDate = new javax.swing.JButton();
        btnReportEndDate = new javax.swing.JButton();
        txtReportStartDate = new javax.swing.JTextField();
        txtReportEndDate = new javax.swing.JTextField();
        btnGenerateReport = new javax.swing.JButton();
        tabHistory = new javax.swing.JPanel();
        btnSendConfirmation = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableNewBookings = new javax.swing.JTable();

        jLabel59.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(255, 51, 51));
        jLabel59.setText("*");

        btnBookingsSearch1.setBackground(new java.awt.Color(255, 204, 102));
        btnBookingsSearch1.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnBookingsSearch1.setForeground(new java.awt.Color(255, 255, 255));
        btnBookingsSearch1.setText("Search");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bg.setBackground(new java.awt.Color(0, 0, 0));

        pnlHeader.setBackground(new java.awt.Color(0, 0, 0));

        lblRapidCar.setBackground(new java.awt.Color(0, 0, 0));
        lblRapidCar.setFont(new java.awt.Font("Poppins Medium", 3, 24)); // NOI18N
        lblRapidCar.setForeground(new java.awt.Color(255, 204, 102));
        lblRapidCar.setText("RapidCar");

        btnLogout.setBackground(new java.awt.Color(255, 102, 51));
        btnLogout.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlHeaderLayout = new javax.swing.GroupLayout(pnlHeader);
        pnlHeader.setLayout(pnlHeaderLayout);
        pnlHeaderLayout.setHorizontalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(lblRapidCar, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLogout)
                .addGap(83, 83, 83))
        );
        pnlHeaderLayout.setVerticalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRapidCar)
                    .addComponent(btnLogout))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jTabbedPane1.setBackground(new java.awt.Color(0, 0, 0));
        jTabbedPane1.setForeground(new java.awt.Color(255, 204, 102));
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        tabManageBookings.setBackground(new java.awt.Color(255, 204, 102));

        formBook2.setBackground(new java.awt.Color(0, 0, 0));

        btnBookingsSearch.setBackground(new java.awt.Color(255, 204, 102));
        btnBookingsSearch.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnBookingsSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnBookingsSearch.setText("Search");
        btnBookingsSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBookingsSearchMouseExited(evt);
            }
        });
        btnBookingsSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBookingsSearchActionPerformed(evt);
            }
        });

        btnBookingsEdit.setBackground(new java.awt.Color(255, 204, 102));
        btnBookingsEdit.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnBookingsEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnBookingsEdit.setText("Edit");
        btnBookingsEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBookingsEditActionPerformed(evt);
            }
        });

        btnBookingsDelete.setBackground(new java.awt.Color(255, 204, 102));
        btnBookingsDelete.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnBookingsDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnBookingsDelete.setText("Delete");
        btnBookingsDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBookingsDeleteActionPerformed(evt);
            }
        });

        txtBookingCP.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        txtBookingCP.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtBookingCP.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtBookingCP.setPreferredSize(new java.awt.Dimension(21, 28));
        txtBookingCP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBookingCPActionPerformed(evt);
            }
        });

        txtBookingDate.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        txtBookingDate.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtBookingDate.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtBookingDate.setPreferredSize(new java.awt.Dimension(21, 28));
        txtBookingDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBookingDateActionPerformed(evt);
            }
        });

        jLabel41.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 204, 102));
        jLabel41.setText("Booking ID");

        txtCustomerID.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        txtCustomerID.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtCustomerID.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtCustomerID.setPreferredSize(new java.awt.Dimension(21, 28));
        txtCustomerID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCustomerIDActionPerformed(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 204, 102));
        jLabel42.setText("Customer ID");

        txtBookingID.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        txtBookingID.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtBookingID.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtBookingID.setPreferredSize(new java.awt.Dimension(21, 28));
        txtBookingID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBookingIDActionPerformed(evt);
            }
        });

        jLabel51.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(255, 204, 102));
        jLabel51.setText("Car Plate");

        jLabel52.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(255, 204, 102));
        jLabel52.setText("Booking Date");

        txtPickUp.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        txtPickUp.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPickUp.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtPickUp.setPreferredSize(new java.awt.Dimension(21, 28));
        txtPickUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPickUpActionPerformed(evt);
            }
        });

        txtAmount.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        txtAmount.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtAmount.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtAmount.setPreferredSize(new java.awt.Dimension(21, 28));
        txtAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAmountActionPerformed(evt);
            }
        });

        txtDropOff.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        txtDropOff.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtDropOff.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtDropOff.setPreferredSize(new java.awt.Dimension(21, 28));
        txtDropOff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDropOffActionPerformed(evt);
            }
        });

        jLabel53.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(255, 204, 102));
        jLabel53.setText("Pick-Up");

        jLabel54.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(255, 204, 102));
        jLabel54.setText("Drop-Off");

        jLabel55.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(255, 204, 102));
        jLabel55.setText("Amount");

        jLabel56.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(255, 51, 51));
        jLabel56.setText("*");

        jLabel58.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(255, 51, 51));
        jLabel58.setText("*");

        jLabel60.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(255, 51, 51));
        jLabel60.setText("*");

        jLabel61.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(255, 51, 51));
        jLabel61.setText("*");

        jLabel62.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(255, 51, 51));
        jLabel62.setText("*");

        jLabel63.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(255, 51, 51));
        jLabel63.setText("*");

        jLabel64.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(255, 51, 51));
        jLabel64.setText("*");

        btnBookingsAdd.setBackground(new java.awt.Color(255, 204, 102));
        btnBookingsAdd.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnBookingsAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnBookingsAdd.setText("Add");
        btnBookingsAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBookingsAddMouseExited(evt);
            }
        });
        btnBookingsAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBookingsAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout formBook2Layout = new javax.swing.GroupLayout(formBook2);
        formBook2.setLayout(formBook2Layout);
        formBook2Layout.setHorizontalGroup(
            formBook2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formBook2Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addGroup(formBook2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel61)
                    .addComponent(jLabel62)
                    .addComponent(jLabel63)
                    .addComponent(jLabel64))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(formBook2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formBook2Layout.createSequentialGroup()
                        .addComponent(jLabel42)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(formBook2Layout.createSequentialGroup()
                        .addGroup(formBook2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(formBook2Layout.createSequentialGroup()
                                .addComponent(jLabel41)
                                .addGap(49, 49, 49)
                                .addGroup(formBook2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtCustomerID, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtBookingID, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(formBook2Layout.createSequentialGroup()
                                .addComponent(jLabel53)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtPickUp, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(formBook2Layout.createSequentialGroup()
                                .addComponent(jLabel55)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(formBook2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(formBook2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 161, Short.MAX_VALUE)
                                .addGroup(formBook2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel60)
                                    .addComponent(jLabel56)
                                    .addComponent(jLabel58))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(formBook2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel51)
                                    .addComponent(jLabel52)
                                    .addComponent(jLabel54))
                                .addGap(34, 34, 34)
                                .addGroup(formBook2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtBookingDate, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDropOff, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtBookingCP, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(195, 195, 195))
                            .addGroup(formBook2Layout.createSequentialGroup()
                                .addGap(87, 87, 87)
                                .addComponent(btnBookingsAdd)
                                .addGap(45, 45, 45)
                                .addComponent(btnBookingsSearch)
                                .addGap(51, 51, 51)
                                .addComponent(btnBookingsEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(btnBookingsDelete)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        formBook2Layout.setVerticalGroup(
            formBook2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formBook2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(formBook2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(txtBookingID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBookingCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel51)
                    .addComponent(jLabel56)
                    .addComponent(jLabel61))
                .addGap(14, 14, 14)
                .addGroup(formBook2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(txtCustomerID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBookingDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel52)
                    .addComponent(jLabel58)
                    .addComponent(jLabel62))
                .addGap(17, 17, 17)
                .addGroup(formBook2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPickUp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel53)
                    .addComponent(jLabel54)
                    .addComponent(txtDropOff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel60)
                    .addComponent(jLabel63))
                .addGroup(formBook2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formBook2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(formBook2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel55)
                            .addComponent(jLabel64)))
                    .addGroup(formBook2Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(formBook2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnBookingsDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBookingsEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBookingsSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBookingsAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        tableBookings.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        tableBookings.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Booking ID", "Customer ID", "Car Plate", "Booking Date", "Pick-Up", "Drop-Off", "Amount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableBookings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableBookingsMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tableBookings);

        javax.swing.GroupLayout tabManageBookingsLayout = new javax.swing.GroupLayout(tabManageBookings);
        tabManageBookings.setLayout(tabManageBookingsLayout);
        tabManageBookingsLayout.setHorizontalGroup(
            tabManageBookingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabManageBookingsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabManageBookingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(formBook2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4))
                .addContainerGap())
        );
        tabManageBookingsLayout.setVerticalGroup(
            tabManageBookingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabManageBookingsLayout.createSequentialGroup()
                .addComponent(formBook2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Manage Bookings", tabManageBookings);

        tabManageCars.setBackground(new java.awt.Color(255, 204, 102));

        formBook3.setBackground(new java.awt.Color(0, 0, 0));

        jLabel22.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 204, 102));
        jLabel22.setText("Car Plate");

        jLabel23.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 204, 102));
        jLabel23.setText("Car Brand");

        jLabel30.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 204, 102));
        jLabel30.setText("Car Model");

        jLabel31.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 204, 102));
        jLabel31.setText("Daily Rate");

        txtCarPlate.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        txtCarPlate.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtCarPlate.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtCarPlate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCarPlateActionPerformed(evt);
            }
        });

        txtCarBrand.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        txtCarBrand.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtCarBrand.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtCarBrand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCarBrandActionPerformed(evt);
            }
        });

        txtCarModel.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        txtCarModel.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtCarModel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtCarModel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCarModelActionPerformed(evt);
            }
        });

        txtCarDailyRate.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        txtCarDailyRate.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtCarDailyRate.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtCarDailyRate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCarDailyRateActionPerformed(evt);
            }
        });

        btnAddCar.setBackground(new java.awt.Color(255, 204, 102));
        btnAddCar.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnAddCar.setForeground(new java.awt.Color(255, 255, 255));
        btnAddCar.setText("Add");
        btnAddCar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCarActionPerformed(evt);
            }
        });

        btnSearchCar.setBackground(new java.awt.Color(255, 204, 102));
        btnSearchCar.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnSearchCar.setForeground(new java.awt.Color(255, 255, 255));
        btnSearchCar.setText("Search");
        btnSearchCar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchCarActionPerformed(evt);
            }
        });

        btnEditCar.setBackground(new java.awt.Color(255, 204, 102));
        btnEditCar.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnEditCar.setForeground(new java.awt.Color(255, 255, 255));
        btnEditCar.setText("Edit");
        btnEditCar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditCarActionPerformed(evt);
            }
        });

        btnDeleteCar.setBackground(new java.awt.Color(255, 204, 102));
        btnDeleteCar.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnDeleteCar.setForeground(new java.awt.Color(255, 255, 255));
        btnDeleteCar.setText("Delete");
        btnDeleteCar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteCarActionPerformed(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 51, 51));
        jLabel40.setText("*");

        jLabel39.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 51, 51));
        jLabel39.setText("*");

        jLabel46.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 51, 51));
        jLabel46.setText("*");

        jLabel45.setFont(new java.awt.Font("Poppins Light", 0, 12)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 204, 102));
        jLabel45.setText("Required fields");

        jLabel44.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 51, 51));
        jLabel44.setText("*");

        jLabel47.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(255, 51, 51));
        jLabel47.setText("*");

        btnResetCar.setBackground(new java.awt.Color(255, 204, 102));
        btnResetCar.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnResetCar.setForeground(new java.awt.Color(255, 255, 255));
        btnResetCar.setText("Reset");
        btnResetCar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetCarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout formBook3Layout = new javax.swing.GroupLayout(formBook3);
        formBook3.setLayout(formBook3Layout);
        formBook3Layout.setHorizontalGroup(
            formBook3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formBook3Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(formBook3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(formBook3Layout.createSequentialGroup()
                        .addGroup(formBook3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel40)
                            .addComponent(jLabel39))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(formBook3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(formBook3Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtCarPlate, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(formBook3Layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addGap(40, 40, 40)
                                .addComponent(txtCarBrand, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(formBook3Layout.createSequentialGroup()
                        .addComponent(btnAddCar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSearchCar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(formBook3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formBook3Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(btnResetCar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formBook3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel45)
                        .addGap(71, 71, 71)))
                .addGroup(formBook3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(formBook3Layout.createSequentialGroup()
                        .addComponent(btnEditCar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDeleteCar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(formBook3Layout.createSequentialGroup()
                        .addGroup(formBook3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel44)
                            .addComponent(jLabel47))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(formBook3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30)
                            .addComponent(jLabel31))
                        .addGap(18, 18, 18)
                        .addGroup(formBook3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCarModel, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCarDailyRate, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(118, 118, 118))
        );
        formBook3Layout.setVerticalGroup(
            formBook3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formBook3Layout.createSequentialGroup()
                .addGroup(formBook3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(formBook3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(formBook3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(jLabel44)
                            .addComponent(txtCarModel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(formBook3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(formBook3Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(formBook3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel31)
                                    .addComponent(jLabel47)))
                            .addGroup(formBook3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txtCarDailyRate, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(30, 30, 30)
                        .addGroup(formBook3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEditCar)
                            .addComponent(btnDeleteCar)))
                    .addGroup(formBook3Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(formBook3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(jLabel40)
                            .addComponent(txtCarPlate, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGroup(formBook3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(formBook3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(formBook3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel39)
                                    .addComponent(txtCarBrand, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addGap(33, 33, 33))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formBook3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(formBook3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel45)
                                    .addComponent(jLabel46))
                                .addGap(49, 49, 49)))
                        .addGroup(formBook3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSearchCar)
                            .addComponent(btnAddCar)
                            .addComponent(btnResetCar))))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        tableCars.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        tableCars.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Car ID", "Car Plate", "Car Brand", "Car Model", "Daily Rental Rate (RM)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableCars.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableCarsMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tableCars);

        javax.swing.GroupLayout tabManageCarsLayout = new javax.swing.GroupLayout(tabManageCars);
        tabManageCars.setLayout(tabManageCarsLayout);
        tabManageCarsLayout.setHorizontalGroup(
            tabManageCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabManageCarsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabManageCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(formBook3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 998, Short.MAX_VALUE))
                .addContainerGap())
        );
        tabManageCarsLayout.setVerticalGroup(
            tabManageCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabManageCarsLayout.createSequentialGroup()
                .addComponent(formBook3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Manage Cars", tabManageCars);

        tabManageUsers.setBackground(new java.awt.Color(255, 204, 102));

        formBook.setBackground(new java.awt.Color(0, 0, 0));
        formBook.setForeground(new java.awt.Color(255, 204, 102));
        formBook.setPreferredSize(new java.awt.Dimension(951, 266));

        jLabel2.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 204, 102));
        jLabel2.setText("Full Name");

        jLabel3.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 204, 102));
        jLabel3.setText("User Role");

        comRole.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        comRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Customer", "Admin" }));
        comRole.setMinimumSize(new java.awt.Dimension(100, 28));
        comRole.setPreferredSize(new java.awt.Dimension(100, 28));
        comRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comRoleActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 204, 102));
        jLabel4.setText("Contact no.");

        txtContact.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        txtContact.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtContact.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtContact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContactActionPerformed(evt);
            }
        });

        btnUserSearch.setBackground(new java.awt.Color(255, 204, 102));
        btnUserSearch.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnUserSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnUserSearch.setText("Search");
        btnUserSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserSearchActionPerformed(evt);
            }
        });

        txtUserFullName.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        txtUserFullName.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtUserFullName.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtUserFullName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserFullNameActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 204, 102));
        jLabel6.setText("Gender");

        comGender.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        comGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));

        jLabel7.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 204, 102));
        jLabel7.setText("Email");

        txtEmail.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        txtEmail.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtEmail.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 204, 102));
        jLabel8.setText("NRIC");

        txtNric.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        txtNric.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtNric.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtNric.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNricActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 204, 102));
        jLabel9.setText("Username");

        txtUsername.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        txtUsername.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtUsername.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsernameActionPerformed(evt);
            }
        });

        pass.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        pass.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));

        jLabel13.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 51, 51));
        jLabel13.setText("*");

        jLabel14.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 51, 51));
        jLabel14.setText("*");

        jLabel10.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 204, 102));
        jLabel10.setText("Password");

        btnUserAdd.setBackground(new java.awt.Color(255, 204, 102));
        btnUserAdd.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnUserAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnUserAdd.setText("Add");
        btnUserAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserAddActionPerformed(evt);
            }
        });

        btnUserEdit.setBackground(new java.awt.Color(255, 204, 102));
        btnUserEdit.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnUserEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnUserEdit.setText("Edit");
        btnUserEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserEditActionPerformed(evt);
            }
        });

        btnUserDelete.setBackground(new java.awt.Color(255, 204, 102));
        btnUserDelete.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnUserDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnUserDelete.setText("Delete");
        btnUserDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserDeleteActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Poppins Light", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 204, 102));
        jLabel11.setText("Required fields");

        jLabel15.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 51, 51));
        jLabel15.setText("*");

        jLabel27.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 51, 51));
        jLabel27.setText("*");

        jLabel28.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 51, 51));
        jLabel28.setText("*");

        jLabel32.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 51, 51));
        jLabel32.setText("*");

        jLabel48.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(255, 51, 51));
        jLabel48.setText("*");

        jLabel49.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 51, 51));
        jLabel49.setText("*");

        jLabel50.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(255, 51, 51));
        jLabel50.setText("*");

        btnUserReset.setBackground(new java.awt.Color(255, 204, 102));
        btnUserReset.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnUserReset.setForeground(new java.awt.Color(255, 255, 255));
        btnUserReset.setText("Reset");
        btnUserReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout formBookLayout = new javax.swing.GroupLayout(formBook);
        formBook.setLayout(formBookLayout);
        formBookLayout.setHorizontalGroup(
            formBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formBookLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(formBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(formBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formBookLayout.createSequentialGroup()
                        .addGroup(formBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4))
                        .addGap(20, 20, 20)
                        .addGroup(formBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(formBookLayout.createSequentialGroup()
                                .addComponent(comGender, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(formBookLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(txtContact, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(formBookLayout.createSequentialGroup()
                        .addGroup(formBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(formBookLayout.createSequentialGroup()
                                .addComponent(btnUserAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56)
                                .addComponent(btnUserSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(formBookLayout.createSequentialGroup()
                                .addGroup(formBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addGap(33, 33, 33)
                                .addGroup(formBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtUserFullName)
                                    .addComponent(comRole, 0, 206, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addGroup(formBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel49, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formBookLayout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11)
                                .addGap(42, 42, 42)
                                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formBookLayout.createSequentialGroup()
                                .addComponent(btnUserReset, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formBookLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel13)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(formBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(formBookLayout.createSequentialGroup()
                        .addGroup(formBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(formBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(formBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtNric, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(formBookLayout.createSequentialGroup()
                        .addComponent(btnUserEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnUserDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(121, Short.MAX_VALUE))
        );
        formBookLayout.setVerticalGroup(
            formBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formBookLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(formBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formBookLayout.createSequentialGroup()
                        .addGroup(formBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel49))
                        .addGroup(formBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(formBookLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(formBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel50))
                                .addGap(19, 19, 19))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formBookLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNric, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(formBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(formBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel9)
                                .addComponent(jLabel28)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(formBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(formBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnUserDelete)
                            .addComponent(btnUserEdit))
                        .addGap(36, 36, 36))
                    .addGroup(formBookLayout.createSequentialGroup()
                        .addGroup(formBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel48)
                            .addComponent(txtUserFullName, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(formBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(comRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32)
                            .addComponent(jLabel11)
                            .addComponent(jLabel15))
                        .addGap(18, 18, 18)
                        .addGroup(formBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel14)
                            .addComponent(comGender, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(13, 13, 13)
                        .addGroup(formBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel27)
                            .addComponent(txtContact, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(formBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnUserAdd)
                            .addComponent(btnUserSearch)
                            .addComponent(btnUserReset, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40))))
        );

        tableUsers.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        tableUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User ID", "Name", "Role", "Gender", "Contact No.", "Email", "NRIC", "* Username"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableUsers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableUsersMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableUsers);

        javax.swing.GroupLayout tabManageUsersLayout = new javax.swing.GroupLayout(tabManageUsers);
        tabManageUsers.setLayout(tabManageUsersLayout);
        tabManageUsersLayout.setHorizontalGroup(
            tabManageUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabManageUsersLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabManageUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(formBook, javax.swing.GroupLayout.DEFAULT_SIZE, 999, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        tabManageUsersLayout.setVerticalGroup(
            tabManageUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabManageUsersLayout.createSequentialGroup()
                .addComponent(formBook, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Manage Users", tabManageUsers);

        tabReceipts.setBackground(new java.awt.Color(0, 0, 0));

        txtAreaReceipt.setEditable(false);
        txtAreaReceipt.setColumns(20);
        txtAreaReceipt.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtAreaReceipt.setRows(5);
        jScrollPane5.setViewportView(txtAreaReceipt);

        jLabel43.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 204, 102));
        jLabel43.setText("Booking ID");

        txtBookIdReceipt.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        txtBookIdReceipt.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtBookIdReceipt.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtBookIdReceipt.setPreferredSize(new java.awt.Dimension(21, 28));
        txtBookIdReceipt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBookIdReceiptActionPerformed(evt);
            }
        });

        btnNextReceipt.setBackground(new java.awt.Color(255, 204, 102));
        btnNextReceipt.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnNextReceipt.setForeground(new java.awt.Color(255, 255, 255));
        btnNextReceipt.setText(">");
        btnNextReceipt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextReceiptActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Poppins Medium", 1, 20)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 204, 102));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Receipt");

        btnPrintReceipt.setBackground(new java.awt.Color(255, 204, 102));
        btnPrintReceipt.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnPrintReceipt.setForeground(new java.awt.Color(255, 255, 255));
        btnPrintReceipt.setText("Print Receipt");
        btnPrintReceipt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintReceiptActionPerformed(evt);
            }
        });

        btnSearchReceipt.setBackground(new java.awt.Color(255, 204, 102));
        btnSearchReceipt.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnSearchReceipt.setForeground(new java.awt.Color(255, 255, 255));
        btnSearchReceipt.setText("Search");
        btnSearchReceipt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchReceiptActionPerformed(evt);
            }
        });

        btnFirstReceipt.setBackground(new java.awt.Color(255, 204, 102));
        btnFirstReceipt.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnFirstReceipt.setForeground(new java.awt.Color(255, 255, 255));
        btnFirstReceipt.setText("<< First");
        btnFirstReceipt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstReceiptActionPerformed(evt);
            }
        });

        btnPreviousReceipt.setBackground(new java.awt.Color(255, 204, 102));
        btnPreviousReceipt.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnPreviousReceipt.setForeground(new java.awt.Color(255, 255, 255));
        btnPreviousReceipt.setText("<");
        btnPreviousReceipt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousReceiptActionPerformed(evt);
            }
        });

        btnLastReceipt.setBackground(new java.awt.Color(255, 204, 102));
        btnLastReceipt.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnLastReceipt.setForeground(new java.awt.Color(255, 255, 255));
        btnLastReceipt.setText("Last >>");
        btnLastReceipt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastReceiptActionPerformed(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(255, 204, 204));

        jLabel37.setFont(new java.awt.Font("Poppins Light", 0, 12)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(51, 51, 51));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("* Tip: Search for booking ID in 'Manage");

        jLabel38.setFont(new java.awt.Font("Poppins Light", 0, 12)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(51, 51, 51));
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setText("Bookings' tab to get Booking ID.");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel37)
                .addGap(0, 0, 0)
                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout tabReceiptsLayout = new javax.swing.GroupLayout(tabReceipts);
        tabReceipts.setLayout(tabReceiptsLayout);
        tabReceiptsLayout.setHorizontalGroup(
            tabReceiptsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabReceiptsLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(tabReceiptsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(tabReceiptsLayout.createSequentialGroup()
                        .addGroup(tabReceiptsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnFirstReceipt, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPreviousReceipt, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(tabReceiptsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnLastReceipt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                            .addComponent(btnNextReceipt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(tabReceiptsLayout.createSequentialGroup()
                        .addComponent(jLabel43)
                        .addGap(39, 39, 39)
                        .addGroup(tabReceiptsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnPrintReceipt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSearchReceipt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtBookIdReceipt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(43, 43, 43)
                .addGroup(tabReceiptsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );
        tabReceiptsLayout.setVerticalGroup(
            tabReceiptsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabReceiptsLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(tabReceiptsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabReceiptsLayout.createSequentialGroup()
                        .addGroup(tabReceiptsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tabReceiptsLayout.createSequentialGroup()
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtBookIdReceipt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(tabReceiptsLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel43)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSearchReceipt, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPrintReceipt, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addGroup(tabReceiptsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnPreviousReceipt, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNextReceipt, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tabReceiptsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnFirstReceipt, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLastReceipt, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(51, 51, 51))
                    .addGroup(tabReceiptsLayout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(18, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("Receipts", tabReceipts);

        tabReport.setBackground(new java.awt.Color(0, 0, 0));

        txtAreaReport.setEditable(false);
        txtAreaReport.setColumns(20);
        txtAreaReport.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtAreaReport.setRows(5);
        jScrollPane7.setViewportView(txtAreaReport);

        jLabel57.setFont(new java.awt.Font("Poppins Medium", 1, 20)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(255, 204, 102));
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel57.setText("Report");

        jCalendar2.setBackground(new java.awt.Color(153, 255, 204));

        btnReportStartDate.setBackground(new java.awt.Color(255, 204, 102));
        btnReportStartDate.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnReportStartDate.setForeground(new java.awt.Color(255, 255, 255));
        btnReportStartDate.setText("Start Date");
        btnReportStartDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportStartDateActionPerformed(evt);
            }
        });

        btnReportEndDate.setBackground(new java.awt.Color(255, 204, 102));
        btnReportEndDate.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnReportEndDate.setForeground(new java.awt.Color(255, 255, 255));
        btnReportEndDate.setText("End Date");
        btnReportEndDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportEndDateActionPerformed(evt);
            }
        });

        txtReportStartDate.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        txtReportStartDate.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtReportStartDate.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtReportStartDate.setPreferredSize(new java.awt.Dimension(21, 28));
        txtReportStartDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtReportStartDateActionPerformed(evt);
            }
        });

        txtReportEndDate.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        txtReportEndDate.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtReportEndDate.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtReportEndDate.setPreferredSize(new java.awt.Dimension(21, 28));
        txtReportEndDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtReportEndDateActionPerformed(evt);
            }
        });

        btnGenerateReport.setBackground(new java.awt.Color(255, 204, 102));
        btnGenerateReport.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnGenerateReport.setForeground(new java.awt.Color(255, 255, 255));
        btnGenerateReport.setText("Generate Report");
        btnGenerateReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerateReportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabReportLayout = new javax.swing.GroupLayout(tabReport);
        tabReport.setLayout(tabReportLayout);
        tabReportLayout.setHorizontalGroup(
            tabReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabReportLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(tabReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jCalendar2, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(tabReportLayout.createSequentialGroup()
                        .addGroup(tabReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnReportStartDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnReportEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtReportStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tabReportLayout.createSequentialGroup()
                        .addGap(217, 217, 217)
                        .addGroup(tabReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnGenerateReport, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtReportEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                .addGroup(tabReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                    .addComponent(jLabel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(124, Short.MAX_VALUE))
        );
        tabReportLayout.setVerticalGroup(
            tabReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabReportLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(tabReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tabReportLayout.createSequentialGroup()
                        .addComponent(jCalendar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tabReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnReportStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtReportStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tabReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnReportEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtReportEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnGenerateReport, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tabReportLayout.createSequentialGroup()
                        .addComponent(jLabel57)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Reports", tabReport);

        tabHistory.setBackground(new java.awt.Color(0, 0, 0));

        btnSendConfirmation.setBackground(new java.awt.Color(255, 204, 102));
        btnSendConfirmation.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnSendConfirmation.setForeground(new java.awt.Color(255, 255, 255));
        btnSendConfirmation.setText("Send Confirmation");
        btnSendConfirmation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendConfirmationActionPerformed(evt);
            }
        });

        tableNewBookings.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        tableNewBookings.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Booking ID", "Booking Date", "Car Plate", "Pick-up", "Drop-off", "Amount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableNewBookings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableNewBookingsMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tableNewBookingsMouseExited(evt);
            }
        });
        jScrollPane3.setViewportView(tableNewBookings);

        javax.swing.GroupLayout tabHistoryLayout = new javax.swing.GroupLayout(tabHistory);
        tabHistory.setLayout(tabHistoryLayout);
        tabHistoryLayout.setHorizontalGroup(
            tabHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabHistoryLayout.createSequentialGroup()
                .addGroup(tabHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSendConfirmation, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 963, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 48, Short.MAX_VALUE))
        );
        tabHistoryLayout.setVerticalGroup(
            tabHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabHistoryLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSendConfirmation)
                .addContainerGap(80, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("New Bookings", tabHistory);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlHeader, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                .addComponent(pnlHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        this.setVisible(false);
        new LoginFrame().setVisible(true);
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void btnGenerateReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerateReportActionPerformed

        // Extracting text box values
        String inputStartDate = txtReportStartDate.getText().trim();
        String inputEndDate = txtReportEndDate.getText().trim();

        // Do nothing if end date is not provided
        if (inputStartDate.isEmpty() || inputEndDate.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Please insert start and end date first.");
            return;
        }

        // Date formatting
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(inputStartDate, dateFormat);
        LocalDate endDate = LocalDate.parse(inputEndDate, dateFormat);
        long duration = ChronoUnit.DAYS.between(startDate, endDate);

        // Validation - Check for valid duration
        if ((int) duration <= 0)
        {
            JOptionPane.showMessageDialog(this, "Start date must be before end date.");
            txtReportStartDate.setText("");
            txtReportEndDate.setText("");
            return;
        }
        generateReport(startDate, endDate);
    }//GEN-LAST:event_btnGenerateReportActionPerformed

    private void txtReportEndDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtReportEndDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtReportEndDateActionPerformed

    private void txtReportStartDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtReportStartDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtReportStartDateActionPerformed

    private void btnReportEndDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportEndDateActionPerformed

        // Extracting text box values
        String inputEndDate = datef.format(jCalendar2.getDate());
        String inputStartDate = txtReportStartDate.getText().trim();
        txtReportEndDate.setText(inputEndDate);

        // Do nothing if end date is not provided
        if (inputStartDate.isEmpty()) return;

        // Date formatting
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(inputStartDate, dateFormat);
        LocalDate endDate = LocalDate.parse(inputEndDate, dateFormat);
        long duration = ChronoUnit.DAYS.between(startDate, endDate);

        if ((int) duration <= 0)
        {
            JOptionPane.showMessageDialog(this, "Start date must be before end date.");
            txtReportEndDate.setText("");
        }
    }//GEN-LAST:event_btnReportEndDateActionPerformed

    private void btnReportStartDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportStartDateActionPerformed

        // Extracting text box values
        String inputStartDate = datef.format(jCalendar2.getDate());
        String inputEndDate = txtReportEndDate.getText().trim();
        txtReportStartDate.setText(inputStartDate);

        // Do nothing if end date is not provided
        if (inputEndDate.isEmpty()) return;

        // Date formatting
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(inputStartDate, dateFormat);
        LocalDate endDate = LocalDate.parse(inputEndDate, dateFormat);
        long duration = ChronoUnit.DAYS.between(startDate, endDate);

        if ((int) duration <= 0)
        {
            JOptionPane.showMessageDialog(this, "Start date must be before end date.");
            txtReportStartDate.setText("");
        }
    }//GEN-LAST:event_btnReportStartDateActionPerformed

    private void btnLastReceiptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastReceiptActionPerformed

        int bookingSize = CarRental.getBookings().size();
        if (bookingSize == 0)
        {
            JOptionPane.showMessageDialog(this, "No booking receipts to show.");
            return;
        }

        // Extracting last booking record
        Booking booking = CarRental.getBookings().get(bookingSize - 1);
        loadReceipt(booking);
        txtBookIdReceipt.setText(booking.getBookingId());
    }//GEN-LAST:event_btnLastReceiptActionPerformed

    private void btnPreviousReceiptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousReceiptActionPerformed
        Booking booking = null;

        while (booking == null) {
            // ReceiptID starts from 1
            receiptID--;
            if (receiptID > 0 && receiptID <= CarRental.getBookings().size())
            {
                String requestedBookingId = String.format("B%04d", receiptID);
                for (Booking carBooking : CarRental.getBookings())
                {
                    if (carBooking.getBookingId().equals(requestedBookingId))
                    {
                        booking = carBooking;
                        loadReceipt(booking);
                        txtBookIdReceipt.setText(requestedBookingId);
                        break;
                    }
                }
            } else { // Already at first record
                receiptID++;
                break;
            }
        }
    }//GEN-LAST:event_btnPreviousReceiptActionPerformed

    private void btnFirstReceiptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstReceiptActionPerformed

        if (CarRental.getBookings().size() == 0)
        {
            JOptionPane.showMessageDialog(this, "No booking receipts to show.");
            return;
        }

        // Extracting first booking record
        Booking booking = CarRental.getBookings().get(0);
        loadReceipt(booking);
        txtBookIdReceipt.setText(booking.getBookingId());
    }//GEN-LAST:event_btnFirstReceiptActionPerformed

    private void btnSearchReceiptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchReceiptActionPerformed
        Booking booking = validateBookingID();

        if (booking == null)
        JOptionPane.showMessageDialog(this, "Invalid Booking ID.");

        else
        loadReceipt(booking); // populate receipt
    }//GEN-LAST:event_btnSearchReceiptActionPerformed

    private void btnPrintReceiptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintReceiptActionPerformed
        try {
            txtAreaReceipt.print();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Could not print, check your printer.");
        }
    }//GEN-LAST:event_btnPrintReceiptActionPerformed

    private void btnNextReceiptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextReceiptActionPerformed
        Booking booking = null;

        while (booking == null) {
            // ReceiptID starts from 1
            receiptID++;
            // TODO - Change CarRental.getBookings().size() to Highest ID
            if (receiptID > 0 && receiptID <= CarRental.getBookings().size())
            {
                String requestedBookingId = String.format("B%04d", receiptID);
                for (Booking carBooking : CarRental.getBookings())
                {
                    if (carBooking.getBookingId().equals(requestedBookingId))
                    {
                        booking = carBooking;
                        loadReceipt(booking);
                        txtBookIdReceipt.setText(requestedBookingId);
                        break;
                    }
                }

            } else { // Already at last record
                receiptID--;
                break;
            }
        }
    }//GEN-LAST:event_btnNextReceiptActionPerformed

    private void txtBookIdReceiptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBookIdReceiptActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBookIdReceiptActionPerformed

    private void tableUsersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableUsersMouseClicked

        DefaultTableModel tableModel = (DefaultTableModel) tableUsers.getModel();
        int row = tableUsers.getSelectedRow();

        if (row >= 0) {
            txtUserFullName.setText((String) tableModel.getValueAt(row, 1));
            comRole.setSelectedItem((String) tableModel.getValueAt(row, 2));
            comGender.setSelectedItem((String) tableModel.getValueAt(row, 3));
            txtContact.setText((String) tableModel.getValueAt(row, 4));
            txtEmail.setText((String) tableModel.getValueAt(row, 5));
            txtNric.setText((String) tableModel.getValueAt(row, 6));
            txtUsername.setText((String) tableModel.getValueAt(row, 7));
        }
    }//GEN-LAST:event_tableUsersMouseClicked

    private void btnUserResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserResetActionPerformed
        txtUserFullName.setText("");
        txtContact.setText("");
        txtNric.setText("");
        txtUsername.setText("");
        loadUsers();
    }//GEN-LAST:event_btnUserResetActionPerformed

    private void btnUserDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserDeleteActionPerformed

        DefaultTableModel tableModel = (DefaultTableModel) tableUsers.getModel();
        int row = tableUsers.getSelectedRow();

        if (row < 0)
        JOptionPane.showMessageDialog(this, "Please select a user to delete.");

        else
        {
            String userID = (String) tableUsers.getValueAt(row, 0); // UserID col
            String role = (String) tableModel.getValueAt(row, 2);
            List<User> users = new ArrayList<User>();

            if (role.equals("Admin"))
            users.addAll(CarRental.getAdmins());

            else
            users.addAll(CarRental.getCustomers());

            for (User user : users)
            {
                if (!user.getUserID().equals(userID)) continue;

                if (role.equals("Admin"))
                CarRental.getAdmins().remove(user);

                else
                CarRental.getCustomers().remove(user);

                if (User.rewriteFile()) {
                    tableModel.removeRow(row);
                    txtUserFullName.setText("");
                    txtContact.setText("");
                    txtEmail.setText("");
                    txtNric.setText("");
                    txtUsername.setText("");
                    JOptionPane.showMessageDialog(this, "User deleted successfully.");
                }
                else
                JOptionPane.showMessageDialog(this, "User deletion failed - something went wrong.");

                break;
            }
        }
    }//GEN-LAST:event_btnUserDeleteActionPerformed

    private void btnUserEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserEditActionPerformed

        // User details that are editable - name, contact, email. Others are illogical to be modified
        DefaultTableModel tableModel = (DefaultTableModel) tableUsers.getModel();
        int row = tableUsers.getSelectedRow();
        String name = txtUserFullName.getText().trim();
        String contact = txtContact.getText().trim();
        String email = txtEmail.getText().trim();

        if (row < 0)
        JOptionPane.showMessageDialog(this, "Please select a user to edit.");

        else if (name.isEmpty() || contact.isEmpty() || email.isEmpty())
        JOptionPane.showMessageDialog(this, "Incomplete information.");

        else
        {
            String userID = (String) tableUsers.getValueAt(row, 0); // UserID col
            List<User> users = new ArrayList<User>();

            if (((String) tableModel.getValueAt(row, 2)).equals("Admin"))
            users.addAll(CarRental.getAdmins());

            else
            users.addAll(CarRental.getCustomers());

            for (User user : users)
            {
                if (!user.getUserID().equals(userID)) continue;

                if (user.getName().equals(name) && user.getContactNo().equals(contact) && user.getEmail().equals(email))
                JOptionPane.showMessageDialog(this, "Nothing to edit.");

                else
                {
                    if (user.updateInfo(name, contact, email)) {
                        JOptionPane.showMessageDialog(this, "User updated successfully.");
                        loadUsers();
                        break;
                    }
                    else
                    JOptionPane.showMessageDialog(this, "User update failed - something went wrong.");
                }
            }
        }
    }//GEN-LAST:event_btnUserEditActionPerformed

    private void btnUserAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserAddActionPerformed

        String name = txtUserFullName.getText().trim();
        String contact = txtContact.getText().trim();
        String email = txtEmail.getText().trim();
        String ic = txtNric.getText().trim();
        String gender = comGender.getSelectedItem().toString().toLowerCase();
        String role = comRole.getSelectedItem().toString();
        String username = txtUsername.getText().trim();
        String password = new String(pass.getPassword()).trim();

        if (name.isEmpty() || contact.isEmpty() || email.isEmpty() ||
            ic.isEmpty() || username.isEmpty() || password.isEmpty() )
        {
            JOptionPane.showMessageDialog(this, "Please fill in all necessary information to add user.");
        }
        else { // Complete information provided

            ArrayList<String> userInfo = new ArrayList<String>();

            Collections.addAll(
                userInfo, "T-1", role.toLowerCase(), name.toUpperCase(),
                gender.toLowerCase(), contact, email.toLowerCase(), ic, username.toLowerCase(), password
            );
            User user = role.equals("Customer") ? new Customer(userInfo) : new Admin(userInfo);

            if (!user.isDuplicate()) {
                if (user.addToFile()) {
                    loadUsers();
                    JOptionPane.showMessageDialog(this, "User added successfully");
                } else {
                    JOptionPane.showMessageDialog(this, "User not added - Something went wrong.");
                }
            } else {
                user = null; // Deleting it (by making it eligible for garbage collection)
                JOptionPane.showMessageDialog(this, "User not added - Duplication detected.");
            }
        }
    }//GEN-LAST:event_btnUserAddActionPerformed

    private void txtUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsernameActionPerformed

    private void txtNricActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNricActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNricActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void txtUserFullNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserFullNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUserFullNameActionPerformed

    private void btnUserSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserSearchActionPerformed

        String role = comRole.getSelectedItem().toString().toLowerCase();
        String gender = comGender.getSelectedItem().toString().toLowerCase();
        String name = txtUserFullName.getText().trim().toUpperCase();
        String contact = txtContact.getText().trim();
        String email = txtEmail.getText().trim();
        String ic = txtNric.getText().trim();
        String username = txtUsername.getText().trim().toLowerCase();
        List<User> users = new ArrayList<User>();

        if (role.equals("customer")) {
            users.addAll(CarRental.getCustomers());
        }
        else { // Admins
            users.addAll(CarRental.getAdmins());
        }

        DefaultTableModel tableModel = (DefaultTableModel) tableUsers.getModel();
        tableModel.setRowCount(0); // Delete all previous rows

        // Adding all users who hv the matched description to table
        for (User user : users) {

            // Filtering matched users
            if (user.getName().toUpperCase().contains(name) && gender.equals(user.getGender().toLowerCase())
                && user.getContactNo().contains(contact) && user.getEmail().contains(email) &&
                user.getIC().contains(ic) && user.getUsername().toLowerCase().contains(username))
            {
                columns[0] = user.getUserID();
                columns[1] = user.getName();
                columns[2] = user.getRole();
                columns[3] = user.getGender();
                columns[4] = user.getContactNo();
                columns[5] = user.getEmail();
                columns[6] = user.getIC();
                columns[7] = user.getUsername();
                tableModel.addRow(columns);
            }
        }
    }//GEN-LAST:event_btnUserSearchActionPerformed

    private void txtContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContactActionPerformed

    private void comRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comRoleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comRoleActionPerformed

    private void tableCarsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableCarsMouseClicked

        DefaultTableModel tableModel = (DefaultTableModel) tableCars.getModel();
        int row = tableCars.getSelectedRow();

        if (row >= 0) {
            txtCarPlate.setText((String) tableModel.getValueAt(row, 1));
            txtCarBrand.setText((String) tableModel.getValueAt(row, 2));
            txtCarModel.setText((String) tableModel.getValueAt(row, 3));
            txtCarDailyRate.setText((String) tableModel.getValueAt(row, 4));
        }
    }//GEN-LAST:event_tableCarsMouseClicked

    private void btnResetCarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetCarActionPerformed
        txtCarPlate.setText("");
        txtCarBrand.setText("");
        txtCarModel.setText("");
        txtCarDailyRate.setText("");
        loadCars();
    }//GEN-LAST:event_btnResetCarActionPerformed

    private void btnDeleteCarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteCarActionPerformed

        DefaultTableModel tableModel = (DefaultTableModel) tableCars.getModel();
        int row = tableCars.getSelectedRow();

        if (row < 0)
        JOptionPane.showMessageDialog(this, "Please select a car to delete.");
        else
        {
            // Current car can be obtained from cars array list based on the row selected in the cars table
            ArrayList<Car> cars = CarRental.getCars();
            Car car = cars.get(row);
            cars.remove(car);

            if (Car.rewriteFile()) {
                tableModel.removeRow(row);
                txtCarPlate.setText("");
                txtCarBrand.setText("");
                txtCarModel.setText("");
                txtCarDailyRate.setText("");
                JOptionPane.showMessageDialog(this, "Car deleted successfully.");
                loadCars();
            }
            else
            JOptionPane.showMessageDialog(this, "Car deletion failed - something went wrong.");

        }
    }//GEN-LAST:event_btnDeleteCarActionPerformed

    private void btnEditCarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditCarActionPerformed

        int row = tableCars.getSelectedRow();
        String carPlate = txtCarPlate.getText().trim().toUpperCase();
        String carBrand = txtCarBrand.getText().trim();
        String carModel = txtCarModel.getText().trim();
        String dailyRate = txtCarDailyRate.getText().trim();
        Double dailyRentalRate = 0.0;

        if (row < 0)
        JOptionPane.showMessageDialog(this, "Please select a car to edit.");

        else if (carPlate.isEmpty() || carBrand.isEmpty() || carModel.isEmpty() || dailyRate.isEmpty())
        JOptionPane.showMessageDialog(this, "Incomplete information provided.");

        else
        {
            // Validate daily rate is a numeric (double) value
            try {
                dailyRentalRate = Double.parseDouble(dailyRate);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Daily rental rate must be a numeric value.");
                return;
            }

            String carID = (String) tableCars.getValueAt(row, 0); // CarID col

            for (Car car : CarRental.getCars())
            {
                if (!car.getCarID().equals(carID)) continue;

                if (car.updateInfo(carPlate, carBrand, carModel, dailyRentalRate)) {
                    loadCars();
                    JOptionPane.showMessageDialog(this, "Car updated successfully.");
                } else
                JOptionPane.showMessageDialog(this, "Failed to update car - something went wrong.");
            }

        }
    }//GEN-LAST:event_btnEditCarActionPerformed

    private void btnSearchCarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchCarActionPerformed

        String carPlate = txtCarPlate.getText().trim().toUpperCase();
        String carBrand = txtCarBrand.getText().trim().toLowerCase();
        String carModel = txtCarModel.getText().trim().toLowerCase();
        String dailyRate = txtCarDailyRate.getText().trim();
        Double dailyRentalRate = -1.0;

        // If daily rate field is not empty, ensure that it is a numeric (double) value
        if (!dailyRate.isEmpty())
        {
            try {
                dailyRentalRate = Double.parseDouble(dailyRate);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Daily rental rate must be a numeric value.");
                return;
            }
        }

        DefaultTableModel tableModel = (DefaultTableModel) tableCars.getModel();
        tableModel.setRowCount(0); // Delete all previous rows

        for (Car car : CarRental.getCars())
        {
            if (car.getCarPlate().toUpperCase().contains(carPlate) &&
                car.getCarBrand().toLowerCase().contains(carBrand) &&
                car.getCarModel().toLowerCase().contains(carModel) &&
                (dailyRentalRate < 0 || dailyRentalRate >= 0 && car.getDailyRentalRate() == dailyRentalRate))
            {
                columns[0] = car.getCarID();
                columns[1] = car.getCarPlate();
                columns[2] = car.getCarBrand();
                columns[3] = car.getCarModel();
                columns[4] = String.format("%.2f", car.getDailyRentalRate());
                tableModel.addRow(columns);
            }
        }
    }//GEN-LAST:event_btnSearchCarActionPerformed

    private void btnAddCarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCarActionPerformed

        String carPlate = txtCarPlate.getText().trim().toUpperCase();
        String carBrand = txtCarBrand.getText().trim();
        String carModel = txtCarModel.getText().trim();
        String dailyRate = txtCarDailyRate.getText().trim();
        Double dailyRentalRate = 0.0;

        if (carPlate.isEmpty() || carBrand.isEmpty() || carModel.isEmpty() || dailyRate.isEmpty())
        JOptionPane.showMessageDialog(this, "Please fill in all necessary information to add user.");

        else {

            // Validate daily rate is a numeric (double) value
            try {
                dailyRentalRate = Double.parseDouble(dailyRate);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Daily rental rate must be a numeric value.");
                return;
            }

            // Validate daily rate is a positive value
            if (dailyRentalRate <= 0) {
                JOptionPane.showMessageDialog(this, "Daily rental rate must be a positive value.");
                return;
            }

            // Complete information provided - create new car object and add to database
            ArrayList<String> carInfo = new ArrayList<String>(
                Arrays.asList("R-1", carPlate, carBrand, carModel, dailyRate)
            );
            Car car = new Car(carInfo);

            if (!car.isDuplicate()) {

                if (car.addToFile()) {
                    loadCars();
                    JOptionPane.showMessageDialog(this, "Car added successfully");
                }
                else
                JOptionPane.showMessageDialog(this, "Car not added - Something went wrong.");

            } else {
                car = null; // Deleting it (by making it eligible for garbage collection)
                JOptionPane.showMessageDialog(this, "Car not added - Duplication detected.");
            }
        }
    }//GEN-LAST:event_btnAddCarActionPerformed

    private void txtCarDailyRateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCarDailyRateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCarDailyRateActionPerformed

    private void txtCarModelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCarModelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCarModelActionPerformed

    private void txtCarBrandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCarBrandActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCarBrandActionPerformed

    private void txtCarPlateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCarPlateActionPerformed

    }//GEN-LAST:event_txtCarPlateActionPerformed

    private void tableBookingsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableBookingsMouseClicked

        DefaultTableModel tableModel = (DefaultTableModel) tableBookings.getModel();
        int row = tableBookings.getSelectedRow();

        if (row >= 0) {
            txtBookingID.setText((String) tableModel.getValueAt(row, 0));
            txtCustomerID.setText((String) tableModel.getValueAt(row, 1));
            txtBookingCP.setText((String) tableModel.getValueAt(row, 2));
            txtBookingDate.setText((String) tableModel.getValueAt(row, 3).toString());
            txtPickUp.setText((String) tableModel.getValueAt(row, 4).toString());
            txtDropOff.setText((String) tableModel.getValueAt(row, 5).toString());
            txtAmount.setText((String) tableModel.getValueAt(row, 6).toString());
        }
    }//GEN-LAST:event_tableBookingsMouseClicked

    private void btnBookingsAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBookingsAddActionPerformed
        String customerID = txtCustomerID.getText().trim().toUpperCase();
        String bookingCP = txtBookingCP.getText().trim().toUpperCase();
        LocalDate bookingDate = java.time.LocalDate.now();
        String sBookingDate = bookingDate.toString();
        String sPickUp = txtPickUp.getText().trim();
        String sDropOff = txtDropOff.getText().trim();
        String sAmount = "0.0";
        String status = "WAITING FOR CONFIRMATION";
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        long d = 0;
        Double rentalRate = 0.0;
        LocalDate pickUp = LocalDate.parse(sPickUp, format);
        LocalDate dropOff = LocalDate.parse(sDropOff, format);
        Double amount = 0.0;
        ArrayList<String> customerInFile=new ArrayList<String>();
        ArrayList<String> carInFile=new ArrayList<String>();
        
        for (Car car : CarRental.getCars()){
            if(car.getCarPlate().contains(bookingCP)){
                rentalRate = car.getDailyRentalRate();
            }
        }
        d = getDateDiff(pickUp,dropOff,TimeUnit.MILLISECONDS);
        double duration = d;
        amount = rentalRate * duration * 110 / 100;
        sAmount = amount.toString();
            
        long dInFile = 0;
        int tduration, dInFileI;
        tduration = (int)d;
        ArrayList<String> carNotAvailable=new ArrayList<String>();
        for (Car car : CarRental.getCars()){
            for (Booking booking : CarRental.getBookings()){    
                dInFile = getDateDiff(booking.getStartDate(),booking.getEndDate(),TimeUnit.MILLISECONDS);
                dInFileI =  (int)dInFile;
                LocalDate dateInDuration = booking.getStartDate();
                for (int i = 0; i <= dInFileI; i++) {
                    LocalDate pickUpTemp = pickUp;
                    for (int j = 0; j <= tduration; j++){
                        if (pickUpTemp.equals(dateInDuration)){
                            if (!carNotAvailable.contains(booking.getCar().getCarPlate())){
                                carNotAvailable.add(booking.getCar().getCarPlate());
                            }
                        }
                        pickUpTemp = pickUpTemp.plusDays(1);
                    }

                    dateInDuration = dateInDuration.plusDays(1);
                }
                       
            }
                     
        }
        

        for (Customer customer : CarRental.getCustomers()){
            customerInFile.add(customer.getUserID());

        }
        for(Car car : CarRental.getCars()){
            carInFile.add(car.getCarPlate());
        }

        if (customerID.isEmpty() || bookingCP.isEmpty() || sBookingDate.isEmpty() || sPickUp.isEmpty() || sDropOff.isEmpty() || sAmount.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please fill in all necessary information to add booking.");
        }
        else if(carNotAvailable.contains(bookingCP)){
            JOptionPane.showMessageDialog(this, "Car not available, please choose another car.");
        }
        
        else if(!customerInFile.contains(customerID)){
            JOptionPane.showMessageDialog(this, "Customer does not exists.");
        }

        else if(!carInFile.contains(bookingCP)){
            JOptionPane.showMessageDialog(this, "Car does not exists.");
        }

        else if(pickUp.getDayOfMonth()==dropOff.getDayOfMonth() && pickUp.getMonthValue()==dropOff.getMonthValue() && pickUp.getYear()==dropOff.getYear()){
            JOptionPane.showMessageDialog(this, "Fail to add booking, invalid pick up and drop off date.");
        }

        else if(pickUp==dropOff || dropOff.getYear() < pickUp.getYear() || dropOff.getYear() - pickUp.getYear() > 1){
            JOptionPane.showMessageDialog(this, "Fail to add booking, invalid pick up and drop off date.");
        }

        else if(dropOff.getMonthValue() < pickUp.getMonthValue()){
            JOptionPane.showMessageDialog(this, "Fail to add booking, invalid pick up and drop off date.");
        }

        else if(dropOff.getDayOfMonth() < pickUp.getDayOfMonth()){
            JOptionPane.showMessageDialog(this, "Fail to add booking, invalid pick up and drop off date.");
        }

        else {

            ArrayList<String> bookingInfo = new ArrayList<String>(
                Arrays.asList("B-1", customerID, bookingCP, sBookingDate, sPickUp, sDropOff, sAmount, status)
            );
            Booking booking = new Booking(bookingInfo);

            if (!booking.isDuplicate()) {

                if (booking.addToFile()) {
                    loadBookings();
                    JOptionPane.showMessageDialog(this, "Booking added successfully");
                }
                else
                JOptionPane.showMessageDialog(this, "Booking not added - Something went wrong.");

            } else {
                booking = null; // Deleting it (by making it eligible for garbage collection)
                JOptionPane.showMessageDialog(this, "Booking not added - Duplication detected.");
            }
        }
        txtBookingID.setText("");
        txtCustomerID.setText("");
        txtBookingCP.setText("");
        txtPickUp.setText("");
        txtDropOff.setText("");
        txtAmount.setText("");
        txtBookingDate.setText("");
    }//GEN-LAST:event_btnBookingsAddActionPerformed

    private void btnBookingsAddMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBookingsAddMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBookingsAddMouseExited

    private void txtDropOffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDropOffActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDropOffActionPerformed

    private void txtAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAmountActionPerformed

    private void txtPickUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPickUpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPickUpActionPerformed

    private void txtBookingIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBookingIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBookingIDActionPerformed

    private void txtCustomerIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCustomerIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCustomerIDActionPerformed

    private void txtBookingDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBookingDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBookingDateActionPerformed

    private void txtBookingCPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBookingCPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBookingCPActionPerformed

    private void btnBookingsDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBookingsDeleteActionPerformed
        DefaultTableModel tableModel = (DefaultTableModel) tableBookings.getModel();
        int row = tableBookings.getSelectedRow();

        if (row < 0)
        JOptionPane.showMessageDialog(this, "Please select a booking to delete.");
        else
        {
            // Current car can be obtained from cars array list based on the row selected in the cars table
            ArrayList<Booking> bookings = CarRental.getBookings();
            Booking booking = bookings.get(row);
            bookings.remove(booking);

            if (Booking.rewriteFile()) {
                tableModel.removeRow(row);
                txtBookingID.setText("");
                txtCustomerID.setText("");
                txtBookingCP.setText("");
                txtBookingDate.setText("");
                txtPickUp.setText("");
                txtDropOff.setText("");
                txtAmount.setText("");
                JOptionPane.showMessageDialog(this, "Booking deleted successfully.");
                loadBookings();
            } else {
                JOptionPane.showMessageDialog(this, "Booking deletion failed - something went wrong.");
            }
        }
    }//GEN-LAST:event_btnBookingsDeleteActionPerformed

    private void btnBookingsEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBookingsEditActionPerformed
        int row = tableBookings.getSelectedRow();
        String bookingCP = txtBookingCP.getText().trim().toUpperCase();
        String sPickUp = txtPickUp.getText().trim();
        String sDropOff = txtDropOff.getText().trim();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate pickUp = LocalDate.parse(sPickUp, format);
        LocalDate dropOff = LocalDate.parse(sDropOff, format);
        Double amount = 0.0;
        long d = 0;
        Double rentalRate = 0.0;

        if (row < 0){
            JOptionPane.showMessageDialog(this, "Please select a booking to edit.");
        }

        else if (bookingCP.isEmpty() || sPickUp.isEmpty() || sDropOff.isEmpty())
        JOptionPane.showMessageDialog(this, "Incomplete information provided.");
        
        else if(pickUp.getDayOfMonth()==dropOff.getDayOfMonth() && pickUp.getMonthValue()==dropOff.getMonthValue() && pickUp.getYear()==dropOff.getYear()){
            JOptionPane.showMessageDialog(this, "Fail to add booking, invalid pick up and drop off date.");
        }

        else if(pickUp==dropOff || dropOff.getYear() < pickUp.getYear() || dropOff.getYear() - pickUp.getYear() > 1){
            JOptionPane.showMessageDialog(this, "Fail to add booking, invalid pick up and drop off date.");
        }

        else if(dropOff.getMonthValue() < pickUp.getMonthValue()){
            JOptionPane.showMessageDialog(this, "Fail to add booking, invalid pick up and drop off date.");
        }

        else if(dropOff.getDayOfMonth() < pickUp.getDayOfMonth()){
            JOptionPane.showMessageDialog(this, "Fail to add booking, invalid pick up and drop off date.");
        }

        else
        {
            for (Car car : CarRental.getCars()){
                if(car.getCarPlate().contains(bookingCP)){
                    rentalRate = car.getDailyRentalRate();//get the rental rate by carplate
                }
            }
            d = getDateDiff(pickUp,dropOff,TimeUnit.MILLISECONDS);//calculate difference between dropoff date and pick up date
            double duration = d;//convert d(long) to double
            amount = rentalRate * duration;//calculate amount
            String bookingID = (String) tableBookings.getValueAt(row, 0);

            for (Booking booking : CarRental.getBookings())
            {
                if (!booking.getBookingId().equals(bookingID)) continue;
                if (booking.getCar().getCarPlate().equals(bookingCP) && booking.getStartDate().equals(sPickUp) && booking.getEndDate().equals(sDropOff)){
                    JOptionPane.showMessageDialog(this, "Nothing to edit.");
                }
                else
                {
                    if (booking.updateInfo(bookingCP, pickUp, dropOff, amount)) {
                        JOptionPane.showMessageDialog(this, "Booking updated successfully.");
                        loadBookings();
                        break;
                    }
                    else
                    JOptionPane.showMessageDialog(this, "Booking update failed - something went wrong.");
                }
            }

        }
    }//GEN-LAST:event_btnBookingsEditActionPerformed

    private void btnBookingsSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBookingsSearchActionPerformed
        String bookingID = txtBookingID.getText().trim().toUpperCase();
        String customerID = txtCustomerID.getText().trim().toUpperCase();
        String bookingCP = txtBookingCP.getText().trim().toUpperCase();

        // If daily rate field is not empty, ensure that it is a numeric (double) value
        if (bookingID.isEmpty() && customerID.isEmpty() && bookingCP.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Please enter booking ID, customer ID, or car plate to search bookings.");
        }

        DefaultTableModel tableModel = (DefaultTableModel) tableBookings.getModel();
        tableModel.setRowCount(0); // Delete all previous rows

        for (Booking booking : CarRental.getBookings())
        {
            if (booking.getBookingId().toUpperCase().contains(bookingID) &&
                booking.getCustomer().getUserID().toUpperCase().contains(customerID) &&
                booking.getCar().getCarPlate().toUpperCase().contains(bookingCP))
            {
                columns[0] = booking.getBookingId();
                columns[1] = booking.getCustomer().getUserID();
                columns[2] = booking.getCar().getCarPlate();
                columns[3] = booking.getBookingDate().toString();
                columns[4] = booking.getStartDate().toString();
                columns[5] = booking.getEndDate().toString();
                columns[6] = String.format("%.2f", booking.getBookingFee());
                txtBookingDate.setText("");
                txtPickUp.setText("");
                txtDropOff.setText("");
                txtAmount.setText("");
                tableModel.addRow(columns);
            }
        }
    }//GEN-LAST:event_btnBookingsSearchActionPerformed

    private void btnBookingsSearchMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBookingsSearchMouseExited

    }//GEN-LAST:event_btnBookingsSearchMouseExited

    private void btnSendConfirmationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendConfirmationActionPerformed
        int row = tableNewBookings.getSelectedRow();
        String status = "CONFIRMED";
        
        if (row < 0){
            JOptionPane.showMessageDialog(this, "Please select a booking to send.");
            }
        else{
            String bookingID = (String) tableNewBookings.getValueAt(row, 0);
            for (Booking booking : CarRental.getBookings())
            {
                if (booking.getBookingId().equals(bookingID)){
                    if (booking.updateStatus(status)) {
                        JOptionPane.showMessageDialog(this, "Confirmation sent successfully.");
                        loadNewBookings();
                        break;
                }
                else{
                    JOptionPane.showMessageDialog(this, "Fail to send booking confirmation.");
                    }
                }
 
            }
        }
    }//GEN-LAST:event_btnSendConfirmationActionPerformed

    private void tableNewBookingsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableNewBookingsMouseClicked

    }//GEN-LAST:event_tableNewBookingsMouseClicked

    private void tableNewBookingsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableNewBookingsMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tableNewBookingsMouseExited

    public static long getDateDiff(LocalDate date1, LocalDate date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getDayOfYear() - date1.getDayOfYear();
        return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);

    }
        
    /*    private void txtContactActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
    }    */                                      


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdminFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminFrame(user).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JButton btnAddCar;
    private javax.swing.JButton btnBookingsAdd;
    private javax.swing.JButton btnBookingsDelete;
    private javax.swing.JButton btnBookingsEdit;
    private javax.swing.JButton btnBookingsSearch;
    private javax.swing.JButton btnBookingsSearch1;
    private javax.swing.JButton btnDeleteCar;
    private javax.swing.JButton btnEditCar;
    private javax.swing.JButton btnFirstReceipt;
    private javax.swing.JButton btnGenerateReport;
    private javax.swing.JButton btnLastReceipt;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnNextReceipt;
    private javax.swing.JButton btnPreviousReceipt;
    private javax.swing.JButton btnPrintReceipt;
    private javax.swing.JButton btnReportEndDate;
    private javax.swing.JButton btnReportStartDate;
    private javax.swing.JButton btnResetCar;
    private javax.swing.JButton btnSearchCar;
    private javax.swing.JButton btnSearchReceipt;
    private javax.swing.JButton btnSendConfirmation;
    private javax.swing.JButton btnUserAdd;
    private javax.swing.JButton btnUserDelete;
    private javax.swing.JButton btnUserEdit;
    private javax.swing.JButton btnUserReset;
    private javax.swing.JButton btnUserSearch;
    private javax.swing.JComboBox<String> comGender;
    private javax.swing.JComboBox<String> comRole;
    private javax.swing.JPanel formBook;
    private javax.swing.JPanel formBook2;
    private javax.swing.JPanel formBook3;
    private com.toedter.calendar.JCalendar jCalendar2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblRapidCar;
    private javax.swing.JPasswordField pass;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel tabHistory;
    private javax.swing.JPanel tabManageBookings;
    private javax.swing.JPanel tabManageCars;
    private javax.swing.JPanel tabManageUsers;
    private javax.swing.JPanel tabReceipts;
    private javax.swing.JPanel tabReport;
    private javax.swing.JTable tableBookings;
    private javax.swing.JTable tableCars;
    private javax.swing.JTable tableNewBookings;
    private javax.swing.JTable tableUsers;
    private javax.swing.JTextField txtAmount;
    private javax.swing.JTextArea txtAreaReceipt;
    private javax.swing.JTextArea txtAreaReport;
    private javax.swing.JTextField txtBookIdReceipt;
    private javax.swing.JTextField txtBookingCP;
    private javax.swing.JTextField txtBookingDate;
    private javax.swing.JTextField txtBookingID;
    private javax.swing.JTextField txtCarBrand;
    private javax.swing.JTextField txtCarDailyRate;
    private javax.swing.JTextField txtCarModel;
    private javax.swing.JTextField txtCarPlate;
    private javax.swing.JTextField txtContact;
    private javax.swing.JTextField txtCustomerID;
    private javax.swing.JTextField txtDropOff;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNric;
    private javax.swing.JTextField txtPickUp;
    private javax.swing.JTextField txtReportEndDate;
    private javax.swing.JTextField txtReportStartDate;
    private javax.swing.JTextField txtUserFullName;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
