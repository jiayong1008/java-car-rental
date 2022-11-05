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

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JiaYong
 */
public class AdminFrame extends javax.swing.JFrame {

    private final SimpleDateFormat datef = new SimpleDateFormat("dd-MM-yyyy");
    DefaultListModel lm = new DefaultListModel<>();
    Object[] columns = new Object[8]; // For individual table row
    private int receiptID; // Current booking ID of receipt

    /**
     * Creates new form AdminFrame
     */
    public AdminFrame() {
        initComponents();
        loadUsers();
        loadCars();
        loadBookings();
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
    
        public void addBookingTableRow(DefaultTableModel model, Booking booking) 
    {
        columns[0] = booking.getBookingId();
        columns[1] = booking.getCustomer();
        columns[2] = booking.getCar();
        columns[3] = booking.getBookingDate();
        columns[4] = booking.getStartDate();
        columns[5] = booking.getEndDate();
        columns[6] = booking.getBookingFee();
        model.addRow(columns);
        
    }

    // Load cars in table
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
            addBookingTableRow(tableModel, bookings.get(i));
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
            if ((bookDate.isAfter(startDate) || bookDate.isEqual(startDate)) && 
                (bookDate.isBefore(endDate) || bookDate.isEqual(endDate)))
            {
                bookings.add(booking);
            }
        }
        for (Booking booking : bookings) {
            System.out.println(booking);
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
            {
                System.out.printf("Carbrand does not contain key %s. Initiializing key...\n", carBrand);
                carBrands.put(carBrand, bookingFee);
            }

            else
            {
                System.out.printf("Carbrand contains key %s. Replacing value...\n", carBrand);
                carBrands.replace(carBrand, carBrands.get(carBrand) + bookingFee);
            }

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
            txtAreaReport.setText(txtAreaReport.getText() + String.format(" %s \t\t %.2f\n\n", m.getKey(), m.getValue()));   
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

        bg = new javax.swing.JPanel();
        pnlHeader = new javax.swing.JPanel();
        lblRapidCar = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnlBookRoom = new javax.swing.JPanel();
        tabBookRoom = new javax.swing.JTabbedPane();
        pnlBookRoom1 = new javax.swing.JPanel();
        jCalendar1 = new com.toedter.calendar.JCalendar();
        btnChkIn = new javax.swing.JButton();
        txtChkIn = new javax.swing.JTextField();
        txtChkOut = new javax.swing.JTextField();
        btnChkOut = new javax.swing.JButton();
        btnSearchRoom = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listAvailableRooms = new javax.swing.JList<>();
        btnProceedGuest = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        pnlBookRoom2 = new javax.swing.JPanel();
        formBook1 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtGuestName = new javax.swing.JTextField();
        btnSearchGuest = new javax.swing.JButton();
        btnConfirmBook = new javax.swing.JButton();
        lblChkIn = new javax.swing.JLabel();
        lblChkOut = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        lblNights = new javax.swing.JLabel();
        lblSubtotal = new javax.swing.JLabel();
        lblServiceTax = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        lblTourismTax = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblTotal = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        lblRoomID = new javax.swing.JLabel();
        btnSelectGuest = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableBookRoomGuest = new javax.swing.JTable();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bg.setBackground(new java.awt.Color(240, 255, 240));

        pnlHeader.setBackground(new java.awt.Color(102, 255, 204));

        lblRapidCar.setBackground(new java.awt.Color(0, 0, 0));
        lblRapidCar.setFont(new java.awt.Font("Poppins Medium", 3, 24)); // NOI18N
        lblRapidCar.setText("RapidCar");

        btnLogout.setBackground(new java.awt.Color(0, 153, 153));
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
                .addGap(55, 55, 55))
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

        jTabbedPane1.setBackground(new java.awt.Color(204, 255, 204));
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        pnlBookRoom.setBackground(new java.awt.Color(240, 240, 240));

        pnlBookRoom1.setBackground(new java.awt.Color(235, 235, 235));

        jCalendar1.setBackground(new java.awt.Color(153, 255, 204));

        btnChkIn.setBackground(new java.awt.Color(0, 153, 153));
        btnChkIn.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnChkIn.setForeground(new java.awt.Color(255, 255, 255));
        btnChkIn.setText("Check In Date");
        btnChkIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChkInActionPerformed(evt);
            }
        });

        txtChkIn.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        txtChkIn.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtChkIn.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtChkIn.setPreferredSize(new java.awt.Dimension(21, 28));
        txtChkIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtChkInActionPerformed(evt);
            }
        });

        txtChkOut.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        txtChkOut.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtChkOut.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtChkOut.setPreferredSize(new java.awt.Dimension(21, 28));
        txtChkOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtChkOutActionPerformed(evt);
            }
        });

        btnChkOut.setBackground(new java.awt.Color(0, 153, 153));
        btnChkOut.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnChkOut.setForeground(new java.awt.Color(255, 255, 255));
        btnChkOut.setText("Check Out Date");
        btnChkOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChkOutActionPerformed(evt);
            }
        });

        btnSearchRoom.setBackground(new java.awt.Color(0, 153, 153));
        btnSearchRoom.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnSearchRoom.setForeground(new java.awt.Color(255, 255, 255));
        btnSearchRoom.setText("Search");
        btnSearchRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchRoomActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Available Rooms");

        listAvailableRooms.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        listAvailableRooms.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "N/A", " " };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listAvailableRooms.setLayoutOrientation(javax.swing.JList.VERTICAL_WRAP);
        listAvailableRooms.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listAvailableRoomsValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(listAvailableRooms);

        btnProceedGuest.setBackground(new java.awt.Color(0, 153, 153));
        btnProceedGuest.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnProceedGuest.setForeground(new java.awt.Color(255, 255, 255));
        btnProceedGuest.setText("Proceed >>");
        btnProceedGuest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProceedGuestActionPerformed(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(153, 255, 204));

        jLabel35.setFont(new java.awt.Font("Poppins Light", 0, 12)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(51, 51, 51));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("* Note: S000 - Sea View,");

        jLabel36.setFont(new java.awt.Font("Poppins Light", 0, 12)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(51, 51, 51));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("J000 - Jungle View");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel35)
                .addGap(0, 0, 0)
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlBookRoom1Layout = new javax.swing.GroupLayout(pnlBookRoom1);
        pnlBookRoom1.setLayout(pnlBookRoom1Layout);
        pnlBookRoom1Layout.setHorizontalGroup(
            pnlBookRoom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBookRoom1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(pnlBookRoom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jCalendar1, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlBookRoom1Layout.createSequentialGroup()
                        .addGroup(pnlBookRoom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnChkIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnChkOut, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtChkIn, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlBookRoom1Layout.createSequentialGroup()
                        .addGap(217, 217, 217)
                        .addGroup(pnlBookRoom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSearchRoom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtChkOut, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 141, Short.MAX_VALUE)
                .addGroup(pnlBookRoom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnProceedGuest, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlBookRoom1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(57, 57, 57))
        );
        pnlBookRoom1Layout.setVerticalGroup(
            pnlBookRoom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBookRoom1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pnlBookRoom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlBookRoom1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnProceedGuest, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlBookRoom1Layout.createSequentialGroup()
                        .addComponent(jCalendar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlBookRoom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnChkIn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtChkIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlBookRoom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnChkOut, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtChkOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSearchRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        tabBookRoom.addTab("1 - Select Date", pnlBookRoom1);

        pnlBookRoom2.setBackground(new java.awt.Color(235, 235, 235));

        formBook1.setBackground(new java.awt.Color(235, 235, 235));

        jLabel16.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Summary");

        jLabel17.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(51, 51, 51));
        jLabel17.setText("Check In Date");

        jLabel18.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 51, 51));
        jLabel18.setText("Check Out Date");

        jLabel20.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(51, 51, 51));
        jLabel20.setText("Service Tax (10%)");

        jLabel21.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(51, 51, 51));
        jLabel21.setText("Total");

        jLabel12.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Guest Name");

        txtGuestName.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        txtGuestName.setForeground(new java.awt.Color(102, 102, 102));
        txtGuestName.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtGuestName.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtGuestName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGuestNameActionPerformed(evt);
            }
        });

        btnSearchGuest.setBackground(new java.awt.Color(0, 153, 153));
        btnSearchGuest.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnSearchGuest.setForeground(new java.awt.Color(255, 255, 255));
        btnSearchGuest.setText("Search");
        btnSearchGuest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchGuestActionPerformed(evt);
            }
        });

        btnConfirmBook.setBackground(new java.awt.Color(0, 153, 153));
        btnConfirmBook.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnConfirmBook.setForeground(new java.awt.Color(255, 255, 255));
        btnConfirmBook.setText("Confirm Booking >>");
        btnConfirmBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmBookActionPerformed(evt);
            }
        });

        lblChkIn.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        lblChkIn.setForeground(new java.awt.Color(102, 102, 102));
        lblChkIn.setText("N/A");

        lblChkOut.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        lblChkOut.setForeground(new java.awt.Color(102, 102, 102));
        lblChkOut.setText("N/A");
        lblChkOut.setToolTipText("");

        jLabel24.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(51, 51, 51));
        jLabel24.setText("Nights");

        jLabel25.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(51, 51, 51));
        jLabel25.setText("Subtotal");

        lblNights.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        lblNights.setForeground(new java.awt.Color(102, 102, 102));
        lblNights.setText("0");

        lblSubtotal.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        lblSubtotal.setForeground(new java.awt.Color(102, 102, 102));
        lblSubtotal.setText("RM0.00");

        lblServiceTax.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        lblServiceTax.setForeground(new java.awt.Color(102, 102, 102));
        lblServiceTax.setText("RM0.00");

        jLabel29.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(51, 51, 51));
        jLabel29.setText("Tourism Tax");

        lblTourismTax.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        lblTourismTax.setForeground(new java.awt.Color(102, 102, 102));
        lblTourismTax.setText("RM0.00");

        lblTotal.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(102, 102, 102));
        lblTotal.setText("RM0.00");

        jPanel6.setBackground(new java.awt.Color(153, 255, 204));

        jLabel33.setFont(new java.awt.Font("Poppins Light", 0, 12)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(51, 51, 51));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("* Note: Please select guest and proceed.");

        jLabel34.setFont(new java.awt.Font("Poppins Light", 0, 12)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(51, 51, 51));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("   (Register guest first if haven't already)");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel33)
                .addGap(0, 0, 0)
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jLabel26.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(51, 51, 51));
        jLabel26.setText("Room ID");

        lblRoomID.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        lblRoomID.setForeground(new java.awt.Color(102, 102, 102));
        lblRoomID.setText("N/A");

        btnSelectGuest.setBackground(new java.awt.Color(0, 153, 153));
        btnSelectGuest.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnSelectGuest.setForeground(new java.awt.Color(255, 255, 255));
        btnSelectGuest.setText("Select as Guest");
        btnSelectGuest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectGuestActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout formBook1Layout = new javax.swing.GroupLayout(formBook1);
        formBook1.setLayout(formBook1Layout);
        formBook1Layout.setHorizontalGroup(
            formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formBook1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, formBook1Layout.createSequentialGroup()
                                    .addGap(37, 37, 37)
                                    .addComponent(jLabel29)
                                    .addGap(18, 18, 18)
                                    .addComponent(lblTourismTax)))
                            .addGroup(formBook1Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addGap(18, 18, 18)
                                .addComponent(lblServiceTax))
                            .addGroup(formBook1Layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addGroup(formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel24)
                                    .addComponent(jLabel25))
                                .addGap(18, 18, 18)
                                .addGroup(formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNights)
                                    .addComponent(lblSubtotal)))
                            .addGroup(formBook1Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel26)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel18))
                                .addGap(18, 18, 18)
                                .addGroup(formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblChkIn)
                                    .addComponent(lblChkOut)
                                    .addComponent(lblRoomID, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(formBook1Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTotal)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnConfirmBook, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnSearchGuest, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtGuestName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSelectGuest, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(56, 56, 56))
        );
        formBook1Layout.setVerticalGroup(
            formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formBook1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtGuestName, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSearchGuest)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSelectGuest)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnConfirmBook)
                .addGap(18, 18, 18))
            .addGroup(formBook1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(lblChkIn))
                .addGap(2, 2, 2)
                .addGroup(formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(lblChkOut))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(lblRoomID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(lblNights))
                .addGap(1, 1, 1)
                .addGroup(formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(lblSubtotal))
                .addGap(4, 4, 4)
                .addGroup(formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(lblServiceTax))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(lblTourismTax))
                .addGap(2, 2, 2)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(lblTotal))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tableBookRoomGuest.setBackground(new java.awt.Color(204, 255, 255));
        tableBookRoomGuest.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        tableBookRoomGuest.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User ID", "Name", "Gender", "Contact No.", "Email", "NRIC"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableBookRoomGuest.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableBookRoomGuestMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tableBookRoomGuest);

        javax.swing.GroupLayout pnlBookRoom2Layout = new javax.swing.GroupLayout(pnlBookRoom2);
        pnlBookRoom2.setLayout(pnlBookRoom2Layout);
        pnlBookRoom2Layout.setHorizontalGroup(
            pnlBookRoom2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBookRoom2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(formBook1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(pnlBookRoom2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 701, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(96, Short.MAX_VALUE))
        );
        pnlBookRoom2Layout.setVerticalGroup(
            pnlBookRoom2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBookRoom2Layout.createSequentialGroup()
                .addComponent(formBook1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        tabBookRoom.addTab("2 - Select Guest", pnlBookRoom2);

        javax.swing.GroupLayout pnlBookRoomLayout = new javax.swing.GroupLayout(pnlBookRoom);
        pnlBookRoom.setLayout(pnlBookRoomLayout);
        pnlBookRoomLayout.setHorizontalGroup(
            pnlBookRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBookRoomLayout.createSequentialGroup()
                .addComponent(tabBookRoom)
                .addContainerGap())
        );
        pnlBookRoomLayout.setVerticalGroup(
            pnlBookRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBookRoomLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabBookRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Book Car", pnlBookRoom);

        tabManageBookings.setBackground(new java.awt.Color(240, 240, 240));

        formBook2.setBackground(new java.awt.Color(204, 255, 204));

        btnBookingsSearch.setBackground(new java.awt.Color(0, 153, 153));
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

        btnBookingsEdit.setBackground(new java.awt.Color(0, 153, 153));
        btnBookingsEdit.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnBookingsEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnBookingsEdit.setText("Edit");
        btnBookingsEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBookingsEditActionPerformed(evt);
            }
        });

        btnBookingsDelete.setBackground(new java.awt.Color(0, 153, 153));
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
        jLabel51.setText("Car Plate");

        jLabel52.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
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
        jLabel53.setText("Pick-Up");

        jLabel54.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel54.setText("Drop-Off");

        jLabel55.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel55.setText("Amount");

        javax.swing.GroupLayout formBook2Layout = new javax.swing.GroupLayout(formBook2);
        formBook2.setLayout(formBook2Layout);
        formBook2Layout.setHorizontalGroup(
            formBook2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formBook2Layout.createSequentialGroup()
                .addGap(95, 95, 95)
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                        .addGroup(formBook2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formBook2Layout.createSequentialGroup()
                                .addGroup(formBook2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel54)
                                    .addComponent(jLabel52))
                                .addGap(3, 3, 3))
                            .addGroup(formBook2Layout.createSequentialGroup()
                                .addGroup(formBook2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel51)
                                    .addComponent(btnBookingsSearch))
                                .addGap(18, 18, 18)))
                        .addGroup(formBook2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(formBook2Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(formBook2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtBookingDate, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtBookingCP, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDropOff, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(formBook2Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(btnBookingsEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(btnBookingsDelete)))
                        .addGap(64, 64, 64))))
        );
        formBook2Layout.setVerticalGroup(
            formBook2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formBook2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(formBook2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(txtBookingID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBookingCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel51))
                .addGap(14, 14, 14)
                .addGroup(formBook2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(txtCustomerID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBookingDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel52))
                .addGap(17, 17, 17)
                .addGroup(formBook2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPickUp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel53)
                    .addComponent(jLabel54)
                    .addComponent(txtDropOff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(formBook2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formBook2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(formBook2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel55)))
                    .addGroup(formBook2Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(formBook2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnBookingsDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBookingsEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBookingsSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        tableBookings.setBackground(new java.awt.Color(204, 255, 255));
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
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Manage Bookings", tabManageBookings);

        tabManageCars.setBackground(new java.awt.Color(240, 240, 240));

        formBook3.setBackground(new java.awt.Color(204, 255, 204));

        jLabel22.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel22.setText("Car Plate");

        jLabel23.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel23.setText("Car Brand");

        jLabel30.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel30.setText("Car Model");

        jLabel31.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
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

        btnAddCar.setBackground(new java.awt.Color(0, 153, 153));
        btnAddCar.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnAddCar.setForeground(new java.awt.Color(255, 255, 255));
        btnAddCar.setText("Add");
        btnAddCar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCarActionPerformed(evt);
            }
        });

        btnSearchCar.setBackground(new java.awt.Color(0, 153, 153));
        btnSearchCar.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnSearchCar.setForeground(new java.awt.Color(255, 255, 255));
        btnSearchCar.setText("Search");
        btnSearchCar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchCarActionPerformed(evt);
            }
        });

        btnEditCar.setBackground(new java.awt.Color(0, 153, 153));
        btnEditCar.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnEditCar.setForeground(new java.awt.Color(255, 255, 255));
        btnEditCar.setText("Edit");
        btnEditCar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditCarActionPerformed(evt);
            }
        });

        btnDeleteCar.setBackground(new java.awt.Color(0, 153, 153));
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
        jLabel45.setText("Required fields");

        jLabel44.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 51, 51));
        jLabel44.setText("*");

        jLabel47.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(255, 51, 51));
        jLabel47.setText("*");

        javax.swing.GroupLayout formBook3Layout = new javax.swing.GroupLayout(formBook3);
        formBook3.setLayout(formBook3Layout);
        formBook3Layout.setHorizontalGroup(
            formBook3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formBook3Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(formBook3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formBook3Layout.createSequentialGroup()
                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel45))
                    .addGroup(formBook3Layout.createSequentialGroup()
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
                        .addGap(60, 60, 60)
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
                                    .addComponent(txtCarDailyRate, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        formBook3Layout.setVerticalGroup(
            formBook3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formBook3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(formBook3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formBook3Layout.createSequentialGroup()
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
                                .addComponent(txtCarDailyRate, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(formBook3Layout.createSequentialGroup()
                        .addGroup(formBook3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(jLabel40)
                            .addComponent(txtCarPlate, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(formBook3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(jLabel39)
                            .addComponent(txtCarBrand, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addGroup(formBook3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(jLabel46))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(formBook3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSearchCar)
                    .addComponent(btnAddCar)
                    .addComponent(btnEditCar)
                    .addComponent(btnDeleteCar))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        tableCars.setBackground(new java.awt.Color(204, 255, 255));
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
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 803, Short.MAX_VALUE))
                .addContainerGap())
        );
        tabManageCarsLayout.setVerticalGroup(
            tabManageCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabManageCarsLayout.createSequentialGroup()
                .addComponent(formBook3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Manage Cars", tabManageCars);

        tabManageUsers.setBackground(new java.awt.Color(240, 240, 240));

        formBook.setBackground(new java.awt.Color(204, 255, 204));

        jLabel2.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel2.setText("Full Name");

        jLabel3.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
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
        jLabel4.setText("Contact no.");

        txtContact.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        txtContact.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtContact.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtContact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContactActionPerformed(evt);
            }
        });

        btnUserSearch.setBackground(new java.awt.Color(0, 153, 153));
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
        jLabel6.setText("Gender");

        comGender.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        comGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));

        jLabel7.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
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
        jLabel10.setText("Password");

        btnUserAdd.setBackground(new java.awt.Color(0, 153, 153));
        btnUserAdd.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnUserAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnUserAdd.setText("Add");
        btnUserAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserAddActionPerformed(evt);
            }
        });

        btnUserEdit.setBackground(new java.awt.Color(0, 153, 153));
        btnUserEdit.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnUserEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnUserEdit.setText("Edit");
        btnUserEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserEditActionPerformed(evt);
            }
        });

        btnUserDelete.setBackground(new java.awt.Color(0, 153, 153));
        btnUserDelete.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnUserDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnUserDelete.setText("Delete");
        btnUserDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserDeleteActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Poppins Light", 0, 12)); // NOI18N
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

        javax.swing.GroupLayout formBookLayout = new javax.swing.GroupLayout(formBook);
        formBook.setLayout(formBookLayout);
        formBookLayout.setHorizontalGroup(
            formBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formBookLayout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(formBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formBookLayout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11))
                    .addGroup(formBookLayout.createSequentialGroup()
                        .addGroup(formBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(formBookLayout.createSequentialGroup()
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(332, 332, 332)
                                .addComponent(jLabel13))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, formBookLayout.createSequentialGroup()
                                .addGroup(formBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(formBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(formBookLayout.createSequentialGroup()
                                        .addGroup(formBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel4))
                                        .addGroup(formBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(formBookLayout.createSequentialGroup()
                                                .addGap(16, 16, 16)
                                                .addComponent(comGender, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formBookLayout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(txtContact, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(formBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel49, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel50, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(formBookLayout.createSequentialGroup()
                                .addGroup(formBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(comRole, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(formBookLayout.createSequentialGroup()
                                        .addComponent(btnUserAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(26, 26, 26)
                                        .addComponent(btnUserSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtUserFullName, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(58, 58, 58)
                                .addComponent(jLabel14)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(formBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(formBookLayout.createSequentialGroup()
                                .addGroup(formBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel10))
                                .addGap(18, 18, 18)
                                .addGroup(formBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnUserDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(formBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtNric, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btnUserEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addGap(12, 12, 12)
                        .addGroup(formBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtNric, javax.swing.GroupLayout.PREFERRED_SIZE, 28, Short.MAX_VALUE)
                            .addComponent(jLabel50))
                        .addGap(15, 15, 15)
                        .addGroup(formBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel14)
                            .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(formBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel10))
                        .addGap(44, 44, 44))
                    .addGroup(formBookLayout.createSequentialGroup()
                        .addGroup(formBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel48)
                            .addComponent(txtUserFullName, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(formBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(comRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32))
                        .addGap(18, 18, 18)
                        .addGroup(formBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(formBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(jLabel28))
                            .addComponent(comGender, javax.swing.GroupLayout.PREFERRED_SIZE, 28, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(formBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(formBookLayout.createSequentialGroup()
                                .addGroup(formBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel27))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(formBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel15))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(formBookLayout.createSequentialGroup()
                                .addComponent(txtContact, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addGap(43, 43, 43)))))
                .addGroup(formBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUserSearch)
                    .addComponent(btnUserAdd)
                    .addComponent(btnUserEdit)
                    .addComponent(btnUserDelete))
                .addGap(10, 10, 10))
        );

        tableUsers.setBackground(new java.awt.Color(204, 255, 255));
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
                    .addComponent(formBook, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 803, Short.MAX_VALUE))
                .addContainerGap())
        );
        tabManageUsersLayout.setVerticalGroup(
            tabManageUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabManageUsersLayout.createSequentialGroup()
                .addComponent(formBook, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Manage Users", tabManageUsers);

        tabReceipts.setBackground(new java.awt.Color(204, 255, 204));

        txtAreaReceipt.setEditable(false);
        txtAreaReceipt.setColumns(20);
        txtAreaReceipt.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtAreaReceipt.setRows(5);
        jScrollPane5.setViewportView(txtAreaReceipt);

        jLabel43.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
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

        btnNextReceipt.setBackground(new java.awt.Color(0, 153, 153));
        btnNextReceipt.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnNextReceipt.setForeground(new java.awt.Color(255, 255, 255));
        btnNextReceipt.setText(">");
        btnNextReceipt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextReceiptActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Poppins Medium", 1, 20)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Receipt");

        btnPrintReceipt.setBackground(new java.awt.Color(0, 153, 153));
        btnPrintReceipt.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnPrintReceipt.setForeground(new java.awt.Color(255, 255, 255));
        btnPrintReceipt.setText("Print Receipt");
        btnPrintReceipt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintReceiptActionPerformed(evt);
            }
        });

        btnSearchReceipt.setBackground(new java.awt.Color(0, 153, 153));
        btnSearchReceipt.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnSearchReceipt.setForeground(new java.awt.Color(255, 255, 255));
        btnSearchReceipt.setText("Search");
        btnSearchReceipt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchReceiptActionPerformed(evt);
            }
        });

        btnFirstReceipt.setBackground(new java.awt.Color(0, 153, 153));
        btnFirstReceipt.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnFirstReceipt.setForeground(new java.awt.Color(255, 255, 255));
        btnFirstReceipt.setText("<< First");
        btnFirstReceipt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstReceiptActionPerformed(evt);
            }
        });

        btnPreviousReceipt.setBackground(new java.awt.Color(0, 153, 153));
        btnPreviousReceipt.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnPreviousReceipt.setForeground(new java.awt.Color(255, 255, 255));
        btnPreviousReceipt.setText("<");
        btnPreviousReceipt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousReceiptActionPerformed(evt);
            }
        });

        btnLastReceipt.setBackground(new java.awt.Color(0, 153, 153));
        btnLastReceipt.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnLastReceipt.setForeground(new java.awt.Color(255, 255, 255));
        btnLastReceipt.setText("Last >>");
        btnLastReceipt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastReceiptActionPerformed(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(153, 255, 204));

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
                    .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
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

        tabReport.setBackground(new java.awt.Color(204, 255, 204));

        txtAreaReport.setEditable(false);
        txtAreaReport.setColumns(20);
        txtAreaReport.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtAreaReport.setRows(5);
        jScrollPane7.setViewportView(txtAreaReport);

        jLabel57.setFont(new java.awt.Font("Poppins Medium", 1, 20)); // NOI18N
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel57.setText("Report");

        jCalendar2.setBackground(new java.awt.Color(153, 255, 204));

        btnReportStartDate.setBackground(new java.awt.Color(0, 153, 153));
        btnReportStartDate.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnReportStartDate.setForeground(new java.awt.Color(255, 255, 255));
        btnReportStartDate.setText("Start Date");
        btnReportStartDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportStartDateActionPerformed(evt);
            }
        });

        btnReportEndDate.setBackground(new java.awt.Color(0, 153, 153));
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

        btnGenerateReport.setBackground(new java.awt.Color(0, 153, 153));
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
                .addContainerGap(42, Short.MAX_VALUE)
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
                .addGap(26, 26, 26)
                .addGroup(tabReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );
        tabReportLayout.setVerticalGroup(
            tabReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabReportLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(tabReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabReportLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
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
                        .addComponent(btnGenerateReport, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))
                    .addGroup(tabReportLayout.createSequentialGroup()
                        .addComponent(jLabel57)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(18, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("Reports", tabReport);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlHeader, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
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

    private void btnChkInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChkInActionPerformed
        // chkInDate = datef.format(jCalendar1.getDate());
        // txtChkIn.setText(chkInDate);
        // lblChkIn.setText(chkInDate);
        // validateToday();
        // if (!chkOutDate.isEmpty()) {
        //     validateBookingDates();
        // }
        // bookRoomID = "";
        // lblRoomID.setText("N/A");
    }//GEN-LAST:event_btnChkInActionPerformed

    private void txtChkInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtChkInActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtChkInActionPerformed

    private void txtChkOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtChkOutActionPerformed

    }//GEN-LAST:event_txtChkOutActionPerformed

    private void btnChkOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChkOutActionPerformed
        // chkOutDate = datef.format(jCalendar1.getDate());
        // txtChkOut.setText(chkOutDate);
        // lblChkOut.setText(chkOutDate);
        // if (!chkInDate.isEmpty()) {
        //     validateBookingDates();
        // }
        // bookRoomID = "";
        // lblRoomID.setText("N/A");
    }//GEN-LAST:event_btnChkOutActionPerformed

    private void btnSearchRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchRoomActionPerformed
        // if (chkInDate.isEmpty() || chkOutDate.isEmpty()) { // Validation
        //     JOptionPane.showMessageDialog(this, "Please fill in the dates first.");
        // } else if (validateBookingDates()) { // Add all available rooms to list model
        //     ArrayList<String> availableRooms = getAvailableRooms();
        //     lm.removeAllElements();
        //     availableRooms.forEach((room) -> lm.addElement(room));
        // }
    }//GEN-LAST:event_btnSearchRoomActionPerformed

    private void listAvailableRoomsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listAvailableRoomsValueChanged

    }//GEN-LAST:event_listAvailableRoomsValueChanged

    private void btnProceedGuestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProceedGuestActionPerformed
        // if (listAvailableRooms.getSelectedValue() == null) {
        //     JOptionPane.showMessageDialog(this, "Please select a room before proceeding.");
        // } else {
        //     bookRoomID = listAvailableRooms.getSelectedValue();
        //     lblRoomID.setText(bookRoomID);
        //     tabBookRoom.setSelectedIndex(1);
        // }
    }//GEN-LAST:event_btnProceedGuestActionPerformed

    private void txtGuestNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGuestNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGuestNameActionPerformed

    private void btnSearchGuestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchGuestActionPerformed
        // String name = txtGuestName.getText().trim().toLowerCase();
        // if (name.isEmpty()) {
        //     loadGuest();
        //     JOptionPane.showMessageDialog(this, "Please insert guest name to search.");
        // } else {
        //     ArrayList<User> customers = ResortBooking.getCusts();
        //     DefaultTableModel tableModel = (DefaultTableModel) tableBookRoomGuest.getModel();
        //     tableModel.setRowCount(0); // Delete all previous rows

        //     // Adding all customers who hv the matched name to table
        //     for (User cust : customers) {
        //         if (cust.getName().toLowerCase().contains(name)) {
        //             row[0] = cust.getUserID();
        //             row[1] = cust.getName();
        //             row[2] = cust.getGender();
        //             row[3] = cust.getContact();
        //             row[4] = cust.getEmail();
        //             row[5] = cust.getIC();
        //             tableModel.addRow(row);
        //         }
        //     }
        // }
    }//GEN-LAST:event_btnSearchGuestActionPerformed

    private void btnConfirmBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmBookActionPerformed
        // if (chkInDate.isEmpty() || chkOutDate.isEmpty() || guestID == -1 || bookRoomID.isEmpty())
        // JOptionPane.showMessageDialog(this, "Please fill in all necessary details before proceeding.");
        // else {
        //     ArrayList<String> bookingInfo = new ArrayList<String>();
        //     Calendar calendar = Calendar.getInstance();
        //     String today = datef.format(calendar.getTime());
        //     Collections.addAll(bookingInfo, "-1", String.valueOf(guestID), bookRoomID, chkInDate, chkOutDate, today);
        //     Booking booking = new Booking(bookingInfo);
        //     if (!booking.isDuplicate()) {
        //         if (booking.addToFile())
        //         JOptionPane.showMessageDialog(this, "Booking successful.");
        //         else {
        //             booking = null;
        //             JOptionPane.showMessageDialog(this, "Booking failed - Something went wrong.");
        //         }
        //     } else {
        //         booking = null; // Deleting it (by making it eligible for garbage collection)
        //         JOptionPane.showMessageDialog(this, "Booking denied - Duplicate booking detected.");
        //     }
        // }
    }//GEN-LAST:event_btnConfirmBookActionPerformed

    private void btnSelectGuestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectGuestActionPerformed
        // int rowSelected = tableBookRoomGuest.getSelectedRow();
        // if (rowSelected < 0)
        // JOptionPane.showMessageDialog(this, "Please select a guest first.");
        // else {
        //     guestID = (int) tableBookRoomGuest.getValueAt(rowSelected, 0); // UserID col
        //     txtGuestName.setText((String) tableBookRoomGuest.getValueAt(rowSelected, 1));
        // }
    }//GEN-LAST:event_btnSelectGuestActionPerformed

    private void tableBookRoomGuestMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableBookRoomGuestMouseClicked
        DefaultTableModel tableModel = (DefaultTableModel) tableBookRoomGuest.getModel();
        int row = tableBookRoomGuest.getSelectedRow();

        if (row >= 0)
        txtGuestName.setText((String) tableModel.getValueAt(row, 1));
    }//GEN-LAST:event_tableBookRoomGuestMouseClicked
    
    private void btnBookingsSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBookingsSearchActionPerformed
        
    }//GEN-LAST:event_btnBookingsSearchActionPerformed

    private void btnBookingsEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBookingsEditActionPerformed
        // DefaultTableModel tableModel = (DefaultTableModel) tableBookings.getModel();
        // int row = tableBookings.getSelectedRow();

        // if (row < 0) {
        //     JOptionPane.showMessageDialog(this, "Please select a row to edit.");
        // } else if (chkInDate.isEmpty() || chkOutDate.isEmpty()) {
        //     JOptionPane.showMessageDialog(this, "Please fill in the dates first.");
        // } else {
        //     if (validateBookingDates()) {
        //         String room = (String) tableModel.getValueAt(row, 1);
        //         Booking currentBooking = Booking.getBooking((Integer) tableModel.getValueAt(row, 0));
        //         ArrayList<String> availableRooms = getAvailableRooms(currentBooking);
        //         if (availableRooms.contains(room)) {
        //             // passed all validations - approve edit
        //             currentBooking.updateInfo(chkInDate, chkOutDate);
        //             JOptionPane.showMessageDialog(this, "Booking info successfully updated.");
        //         } else {
        //             JOptionPane.showMessageDialog(this, String.format("Room %s is not available for the given dates.", room));
        //         }
        //     }
        // }
    }//GEN-LAST:event_btnBookingsEditActionPerformed

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
            } else {
                JOptionPane.showMessageDialog(this, "Booking deletion failed - something went wrong.");
            }
        }
    }//GEN-LAST:event_btnBookingsDeleteActionPerformed

    private void txtBookingCPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBookingCPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBookingCPActionPerformed

    private void txtBookingDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBookingDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBookingDateActionPerformed

    private void txtCustomerIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCustomerIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCustomerIDActionPerformed

    private void tableBookingsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableBookingsMouseClicked
        
        DefaultTableModel tableModel = (DefaultTableModel) tableBookings.getModel();
        int row = tableBookings.getSelectedRow();
        
        if (row >= 0) {
            txtBookingID.setText((String) tableModel.getValueAt(row, 0));
            txtCustomerID.setText((String) tableModel.getValueAt(row, 1));
            txtBookingCP.setText((String) tableModel.getValueAt(row, 2));
            txtBookingDate.setText((String) tableModel.getValueAt(row, 3));
            txtPickUp.setText((String) tableModel.getValueAt(row, 4));
            txtDropOff.setText((String) tableModel.getValueAt(row, 5));
            txtAmount.setText((String) tableModel.getValueAt(row, 6));
    }
    }//GEN-LAST:event_tableBookingsMouseClicked

    private void txtContactActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    }                                          


    private void btnUserSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserSearchActionPerformed
        
        String role = comRole.getSelectedItem().toString().toLowerCase();
        String gender = comGender.getSelectedItem().toString().toLowerCase();
        String name = txtUserFullName.getText().trim().toLowerCase();
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
            if (user.getName().toLowerCase().contains(name) && gender.equals(user.getGender().toLowerCase())
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

    private void txtUserFullNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserFullNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUserFullNameActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void txtNricActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNricActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNricActionPerformed

    private void txtUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsernameActionPerformed

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

    private void txtBookIdReceiptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBookIdReceiptActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBookIdReceiptActionPerformed

    private void btnNextReceiptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextReceiptActionPerformed
        Booking booking = null;

        while (booking == null) {
            // ReceiptID starts from 1
            receiptID++;
            // TODO - Change CarRental.getBookings().size() to Highest ID
            if (receiptID > 0 && receiptID <= CarRental.getBookings().size()) 
            {
                String requestedBookingId = String.format("B%05d", receiptID);
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

    private void btnPrintReceiptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintReceiptActionPerformed
        try {
            txtAreaReceipt.print();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Could not print, check your printer.");
        }
    }//GEN-LAST:event_btnPrintReceiptActionPerformed

    private void btnSearchReceiptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchReceiptActionPerformed
        Booking booking = validateBookingID();

        if (booking == null)
        JOptionPane.showMessageDialog(this, "Invalid Booking ID.");

        else
        loadReceipt(booking); // populate receipt
    }//GEN-LAST:event_btnSearchReceiptActionPerformed

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

    private void btnPreviousReceiptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousReceiptActionPerformed
        Booking booking = null;

        while (booking == null) {
            // ReceiptID starts from 1
            receiptID--;
            // TODO - Change CarRental.getBookings().size() to Highest ID
            if (receiptID > 0 && receiptID <= CarRental.getBookings().size()) 
            {
                String requestedBookingId = String.format("B%05d", receiptID);
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
        // int last = Booking.getHighestId();
        // Booking booking = Booking.getBooking(last);
        // boolean hasRecord = true;

        // while (booking == null) {
        //     last--;
        //     if (last < 0) {
        //         JOptionPane.showMessageDialog(this, "No booking receipts to show.");
        //         hasRecord = false;
        //         break;
        //     } else {
        //         booking = Booking.getBooking(last);
        //     }
        // }

        // if (hasRecord) {
        //     loadReceipt(booking);
        //     txtBookIdReceipt.setText(Integer.toString(booking.getBookingID()));
        // }
    }//GEN-LAST:event_btnLastReceiptActionPerformed

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

    private void txtCarBrandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCarBrandActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCarBrandActionPerformed

    private void txtCarModelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCarModelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCarModelActionPerformed

    private void txtCarDailyRateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCarDailyRateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCarDailyRateActionPerformed

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

    private void txtCarPlateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCarPlateActionPerformed
        
    }//GEN-LAST:event_txtCarPlateActionPerformed

    private void btnBookingsSearchMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBookingsSearchMouseExited

    }//GEN-LAST:event_btnBookingsSearchMouseExited

//    boolean displayed = false;
    
    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
//        if (displayed) return;
//        displayed = true;
//        File file = new File(CarRental.getBookingFile());
//        
//        try {
//            FileReader fr = new FileReader(file);
//            BufferedReader br = new BufferedReader(fr);
//            
//            DefaultTableModel model = (DefaultTableModel)tableBookings.getModel();
//            Object[] lines = br.lines().toArray();
//            
//            for(int i = 0; i < lines.length; i++){
//                String[] row = lines[i].toString().split(",");
//                model.addRow(row);
//            }
//            
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(AdminFrame.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void txtBookingIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBookingIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBookingIDActionPerformed

    private void txtPickUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPickUpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPickUpActionPerformed

    private void txtAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAmountActionPerformed

    private void txtDropOffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDropOffActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDropOffActionPerformed

    private void comRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comRoleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comRoleActionPerformed

    private void btnReportStartDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportStartDateActionPerformed
        
        // Extracting text box values
        String inputStartDate = datef.format(jCalendar2.getDate());
        String inputEndDate = txtReportEndDate.getText().trim();
        txtReportStartDate.setText(inputStartDate);

        // Do nothing if end date is not provided
        if (inputEndDate.isEmpty()) return;

        // Date formatting
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate startDate = LocalDate.parse(inputStartDate, dateFormat);
        LocalDate endDate = LocalDate.parse(inputEndDate, dateFormat);
        long duration = ChronoUnit.DAYS.between(startDate, endDate);

        if ((int) duration <= 0)
        {
            JOptionPane.showMessageDialog(this, "Start date must be before end date.");
            txtReportStartDate.setText("");
        }
        
        // lblChkIn.setText(chkInDate);
        // validateToday();
        // if (!chkOutDate.isEmpty()) {
        //     validateBookingDates();
        // }
        // bookRoomID = "";
        // lblRoomID.setText("N/A");
    }//GEN-LAST:event_btnReportStartDateActionPerformed

    private void btnReportEndDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportEndDateActionPerformed
        
        // Extracting text box values
        String inputEndDate = datef.format(jCalendar2.getDate());
        String inputStartDate = txtReportStartDate.getText().trim();
        txtReportEndDate.setText(inputEndDate);

        // Do nothing if end date is not provided
        if (inputStartDate.isEmpty()) return;

        // Date formatting
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate startDate = LocalDate.parse(inputStartDate, dateFormat);
        LocalDate endDate = LocalDate.parse(inputEndDate, dateFormat);
        long duration = ChronoUnit.DAYS.between(startDate, endDate);

        if ((int) duration <= 0)
        {
            JOptionPane.showMessageDialog(this, "Start date must be before end date.");
            txtReportEndDate.setText("");
        }

    }//GEN-LAST:event_btnReportEndDateActionPerformed

    private void txtReportStartDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtReportStartDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtReportStartDateActionPerformed

    private void txtReportEndDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtReportEndDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtReportEndDateActionPerformed

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
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
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
                new AdminFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JButton btnAddCar;
    private javax.swing.JButton btnBookingsDelete;
    private javax.swing.JButton btnBookingsEdit;
    private javax.swing.JButton btnBookingsSearch;
    private javax.swing.JButton btnChkIn;
    private javax.swing.JButton btnChkOut;
    private javax.swing.JButton btnConfirmBook;
    private javax.swing.JButton btnDeleteCar;
    private javax.swing.JButton btnEditCar;
    private javax.swing.JButton btnFirstReceipt;
    private javax.swing.JButton btnGenerateReport;
    private javax.swing.JButton btnLastReceipt;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnNextReceipt;
    private javax.swing.JButton btnPreviousReceipt;
    private javax.swing.JButton btnPrintReceipt;
    private javax.swing.JButton btnProceedGuest;
    private javax.swing.JButton btnReportEndDate;
    private javax.swing.JButton btnReportStartDate;
    private javax.swing.JButton btnSearchCar;
    private javax.swing.JButton btnSearchGuest;
    private javax.swing.JButton btnSearchReceipt;
    private javax.swing.JButton btnSearchRoom;
    private javax.swing.JButton btnSelectGuest;
    private javax.swing.JButton btnUserAdd;
    private javax.swing.JButton btnUserDelete;
    private javax.swing.JButton btnUserEdit;
    private javax.swing.JButton btnUserSearch;
    private javax.swing.JComboBox<String> comGender;
    private javax.swing.JComboBox<String> comRole;
    private javax.swing.JPanel formBook;
    private javax.swing.JPanel formBook1;
    private javax.swing.JPanel formBook2;
    private javax.swing.JPanel formBook3;
    private com.toedter.calendar.JCalendar jCalendar1;
    private com.toedter.calendar.JCalendar jCalendar2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblChkIn;
    private javax.swing.JLabel lblChkOut;
    private javax.swing.JLabel lblNights;
    private javax.swing.JLabel lblRapidCar;
    private javax.swing.JLabel lblRoomID;
    private javax.swing.JLabel lblServiceTax;
    private javax.swing.JLabel lblSubtotal;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTourismTax;
    private javax.swing.JList<String> listAvailableRooms;
    private javax.swing.JPasswordField pass;
    private javax.swing.JPanel pnlBookRoom;
    private javax.swing.JPanel pnlBookRoom1;
    private javax.swing.JPanel pnlBookRoom2;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JTabbedPane tabBookRoom;
    private javax.swing.JPanel tabManageBookings;
    private javax.swing.JPanel tabManageCars;
    private javax.swing.JPanel tabManageUsers;
    private javax.swing.JPanel tabReceipts;
    private javax.swing.JPanel tabReport;
    private javax.swing.JTable tableBookRoomGuest;
    private javax.swing.JTable tableBookings;
    private javax.swing.JTable tableCars;
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
    private javax.swing.JTextField txtChkIn;
    private javax.swing.JTextField txtChkOut;
    private javax.swing.JTextField txtContact;
    private javax.swing.JTextField txtCustomerID;
    private javax.swing.JTextField txtDropOff;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtGuestName;
    private javax.swing.JTextField txtNric;
    private javax.swing.JTextField txtPickUp;
    private javax.swing.JTextField txtReportEndDate;
    private javax.swing.JTextField txtReportStartDate;
    private javax.swing.JTextField txtUserFullName;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
