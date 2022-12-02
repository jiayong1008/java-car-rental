/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package carrental;

import static carrental.AdminFrame.getDateDiff;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import javax.lang.model.util.ElementScanner14;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CustomerFrame extends javax.swing.JFrame {

    private static User user;
    Object[] columns = new Object[8]; // For individual table row

    /**
     * Creates new form CustomerFrame
     */
    public CustomerFrame (User cust) {
        user = cust;
        initComponents();
        loadCars();
//        loadCustomers();
        loadBookings();
        loadNotifications();
    }

// ================     HELPER FUNCTIONS        ========================
    
    
    // Load cars in table
    private void loadCars() 
    {
        ArrayList<Car> cars = CarRental.getCars();
        DefaultTableModel tableModel = (DefaultTableModel) tableAllCars.getModel();
        tableModel.setRowCount(0);
        int index = cars.size();
        
        for (int i = 0; i < index; i++) {
            addTableRow(tableModel, cars.get(i));
        }
    }
    
    private void loadBookings() 
    {
        String customerID = user.getUserID();
        DefaultTableModel tableHistoryModel = (DefaultTableModel) tableHistory.getModel();
        tableHistoryModel.setRowCount(0); // Delete all previous rows
        for (Booking booking : CarRental.getBookings())
        {
            if (booking.getCustID().toUpperCase().contains(customerID))//print history of specific customer
            {
                for (Car car : CarRental.getCars())
                {
                if (car.getCarPlate().contains(booking.getCarNo()))
                    {
//                        System.out.println("the carplate in car file"+car.getCarPlate());
//                        System.out.println("the carno that we search for "+booking.getCarNo());
//                        System.out.println("brand is"+car.getCarBrand());
//                        System.out.println("model is "+car.getCarModel());
                        columns[3] = car.getCarBrand();
                        columns[4] = car.getCarModel();
                    }
                }
                columns[0] = booking.getBookingId();
                columns[1] = booking.getBookingDate().toString();
                columns[2] = booking.getCarNo();
//                columns[3] = booking.getCarNo();
//                columns[4] = booking.getCarNo();
                columns[5] = booking.getStartDate().toString();
                columns[6] = booking.getEndDate().toString();
                columns[7] = String.format("%.2f", booking.getBookingFee());
                tableHistoryModel.addRow(columns); 
            }
        }
    }
    
    private void loadNotifications() 
    {
        String customerID = user.getUserID();
        DefaultTableModel tableNotificationModel = (DefaultTableModel) tblNotification.getModel();
        tableNotificationModel.setRowCount(0); // Delete all previous rows
        for (Booking booking : CarRental.getBookings())
        {
            if (booking.getCustID().toUpperCase().contains(customerID))//print history of specific customer
            {
                if(booking.getStatus().equals("CONFIRMED")){
                    columns[0] = "Your booking "+booking.getBookingId()+" is confirmed!";
                    tableNotificationModel.addRow(columns);
                }
        }
    }
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
    
    private void bookingConfirmation() {
        System.out.println("send booking confirmation");
//        jListConfirmationLetter.add
    }
    
//    public void addHistoryTableRow(DefaultTableModel model, Booking booking) 
//    {
////        columns[0] = booking.getBookingId();
////        columns[2] = booking.getCarNo();
////        columns[1] = booking.getBookingDate();
////        columns[5] = booking.getStartDate();
////        columns[6] = booking.getEndDate();
////        columns[7] = booking.getBookingFee();
////        model.addRow(columns);
////        for (Car car : CarRental.getCars())
////                {
//////                    System.out.println("brand is"+car.getCarBrand());
//////                    System.out.println("model is "+car.getCarModel());
////                    System.out.println("the search"+ booking.getCarNo());
////                if (car.getCarPlate().contains(booking.getCarNo()))
////                    {
////                        System.out.println("the carplate in car file"+car.getCarPlate());
////                        System.out.println("the carno that we search for "+booking.getCarNo());
////                        System.out.println("brand is"+car.getCarBrand());
////                        System.out.println("model is "+car.getCarModel());
////                        columns[3] = car.getCarBrand();
////                        columns[4] = car.getCarModel();
////                    }
////                }
//        
//    }

// ================     END OF HELPER FUNCTIONS        ========================
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane5 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        pnlHeader = new javax.swing.JPanel();
        lblRapidCar = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        jtpCustomer = new javax.swing.JTabbedPane();
        tabCars = new javax.swing.JPanel();
        tabHistory1 = new javax.swing.JPanel();
        btnFilterCar = new javax.swing.JButton();
        txtMinPrice = new javax.swing.JTextField();
        txtCarBrand = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCarModel = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtMaxPrice = new javax.swing.JTextField();
        btnResetCar = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tableAllCars = new javax.swing.JTable();
        pnlBookRoom = new javax.swing.JPanel();
        tabBooking = new javax.swing.JTabbedPane();
        pnlBookRoom1 = new javax.swing.JPanel();
        jCldBooking = new com.toedter.calendar.JCalendar();
        btnPUD = new javax.swing.JButton();
        txtPUD = new javax.swing.JTextField();
        txtDOD = new javax.swing.JTextField();
        btnDOD = new javax.swing.JButton();
        btnSearchCar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btnProceedCar = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        btnSearchCar1 = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblAvailableCars = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblACar = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jBtnSelect = new javax.swing.JButton();
        pnlBookRoom2 = new javax.swing.JPanel();
        formBook1 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        btnConfirmBook = new javax.swing.JButton();
        lblPUD = new javax.swing.JLabel();
        lblCP = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        lblDuration = new javax.swing.JLabel();
        lblSubtotal = new javax.swing.JLabel();
        lblServiceTax = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblTotal = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        lblBookingID = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel19 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        lblDOD = new javax.swing.JLabel();
        lblCB = new javax.swing.JLabel();
        lblCM = new javax.swing.JLabel();
        lblRR = new javax.swing.JLabel();
        tabReceipts = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblNotification = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblMessages = new javax.swing.JTable();
        tabBookingHistory = new javax.swing.JPanel();
        tabHistory = new javax.swing.JPanel();
        btnSearchHistory = new javax.swing.JButton();
        txtSearchHistory = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableHistory = new javax.swing.JTable();

        jTable2.setBackground(new java.awt.Color(255, 204, 102));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Car Plate", "Car Brand", "Car Model", "Rental Rate (Daily)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable2.setSelectionBackground(new java.awt.Color(255, 204, 153));
        jScrollPane5.setViewportView(jTable2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 204, 102));

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 692, Short.MAX_VALUE)
                .addComponent(btnLogout)
                .addGap(71, 71, 71))
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

        jtpCustomer.setBackground(new java.awt.Color(0, 0, 0));
        jtpCustomer.setForeground(new java.awt.Color(255, 255, 255));
        jtpCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtpCustomerMouseClicked(evt);
            }
        });

        tabCars.setBackground(new java.awt.Color(255, 204, 102));

        tabHistory1.setBackground(new java.awt.Color(0, 0, 0));

        btnFilterCar.setBackground(new java.awt.Color(255, 204, 102));
        btnFilterCar.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnFilterCar.setForeground(new java.awt.Color(255, 255, 255));
        btnFilterCar.setText("Search");
        btnFilterCar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilterCarActionPerformed(evt);
            }
        });

        txtMinPrice.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        txtMinPrice.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtMinPrice.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtMinPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMinPriceActionPerformed(evt);
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

        jLabel6.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 204, 102));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Car Brand");

        jLabel7.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 204, 102));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Car Model");

        txtCarModel.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        txtCarModel.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtCarModel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtCarModel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCarModelActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 204, 102));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Minimum Price");

        jLabel9.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 204, 102));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Maximum Price");

        txtMaxPrice.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        txtMaxPrice.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtMaxPrice.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtMaxPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaxPriceActionPerformed(evt);
            }
        });

        btnResetCar.setBackground(new java.awt.Color(255, 204, 102));
        btnResetCar.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnResetCar.setForeground(new java.awt.Color(255, 255, 255));
        btnResetCar.setText("Reset");
        btnResetCar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetCarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabHistory1Layout = new javax.swing.GroupLayout(tabHistory1);
        tabHistory1.setLayout(tabHistory1Layout);
        tabHistory1Layout.setHorizontalGroup(
            tabHistory1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabHistory1Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(tabHistory1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabHistory1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtCarBrand, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tabHistory1Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtCarModel, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(tabHistory1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabHistory1Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtMinPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tabHistory1Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtMaxPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(55, 55, 55))
            .addGroup(tabHistory1Layout.createSequentialGroup()
                .addGap(209, 209, 209)
                .addComponent(btnFilterCar, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72)
                .addComponent(btnResetCar, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tabHistory1Layout.setVerticalGroup(
            tabHistory1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabHistory1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(tabHistory1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMinPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCarBrand, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(tabHistory1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabHistory1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(txtCarModel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tabHistory1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(txtMaxPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(tabHistory1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnFilterCar)
                    .addComponent(btnResetCar))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        tableAllCars.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        tableAllCars.setModel(new javax.swing.table.DefaultTableModel(
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
                false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableAllCars.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableAllCarsMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tableAllCarsMouseExited(evt);
            }
        });
        jScrollPane6.setViewportView(tableAllCars);

        javax.swing.GroupLayout tabCarsLayout = new javax.swing.GroupLayout(tabCars);
        tabCars.setLayout(tabCarsLayout);
        tabCarsLayout.setHorizontalGroup(
            tabCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabCarsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabHistory1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 963, Short.MAX_VALUE))
                .addContainerGap())
        );
        tabCarsLayout.setVerticalGroup(
            tabCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabCarsLayout.createSequentialGroup()
                .addComponent(tabHistory1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                .addContainerGap())
        );

        jtpCustomer.addTab("Cars", tabCars);

        pnlBookRoom.setBackground(new java.awt.Color(0, 0, 0));

        tabBooking.setBackground(new java.awt.Color(0, 0, 0));
        tabBooking.setForeground(new java.awt.Color(255, 255, 255));

        pnlBookRoom1.setBackground(new java.awt.Color(0, 0, 0));

        jCldBooking.setBackground(new java.awt.Color(153, 255, 204));

        btnPUD.setBackground(new java.awt.Color(255, 204, 102));
        btnPUD.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnPUD.setForeground(new java.awt.Color(255, 255, 255));
        btnPUD.setText("Pick-up Date");
        btnPUD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPUDActionPerformed(evt);
            }
        });

        txtPUD.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        txtPUD.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPUD.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtPUD.setPreferredSize(new java.awt.Dimension(21, 28));
        txtPUD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPUDActionPerformed(evt);
            }
        });

        txtDOD.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        txtDOD.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtDOD.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtDOD.setPreferredSize(new java.awt.Dimension(21, 28));
        txtDOD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDODActionPerformed(evt);
            }
        });

        btnDOD.setBackground(new java.awt.Color(255, 204, 102));
        btnDOD.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnDOD.setForeground(new java.awt.Color(255, 255, 255));
        btnDOD.setText("Drop-off Date");
        btnDOD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDODActionPerformed(evt);
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

        jLabel5.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 204, 102));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Available Cars");

        btnProceedCar.setBackground(new java.awt.Color(255, 204, 102));
        btnProceedCar.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnProceedCar.setForeground(new java.awt.Color(255, 255, 255));
        btnProceedCar.setText("Proceed >>");
        btnProceedCar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProceedCarActionPerformed(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(255, 204, 204));

        jLabel35.setFont(new java.awt.Font("Poppins Light", 0, 12)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(51, 51, 51));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("*Please choose a car to");

        jLabel36.setFont(new java.awt.Font("Poppins Light", 0, 12)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(51, 51, 51));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("proceed");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnSearchCar1.setBackground(new java.awt.Color(255, 204, 102));
        btnSearchCar1.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnSearchCar1.setForeground(new java.awt.Color(255, 255, 255));
        btnSearchCar1.setText("Reset");
        btnSearchCar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchCar1ActionPerformed(evt);
            }
        });

        tblAvailableCars.setBackground(new java.awt.Color(255, 204, 102));
        tblAvailableCars.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Car Plate", "Car Brand", "Car Model", "Rental Rate (Daily)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblAvailableCars.setSelectionBackground(new java.awt.Color(255, 204, 153));
        jScrollPane8.setViewportView(tblAvailableCars);

        javax.swing.GroupLayout pnlBookRoom1Layout = new javax.swing.GroupLayout(pnlBookRoom1);
        pnlBookRoom1.setLayout(pnlBookRoom1Layout);
        pnlBookRoom1Layout.setHorizontalGroup(
            pnlBookRoom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBookRoom1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(pnlBookRoom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jCldBooking, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlBookRoom1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(pnlBookRoom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnSearchCar, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPUD, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDOD, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlBookRoom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPUD, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDOD, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearchCar1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
                .addGroup(pnlBookRoom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnProceedCar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBookRoom1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66))
        );
        pnlBookRoom1Layout.setVerticalGroup(
            pnlBookRoom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBookRoom1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(pnlBookRoom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBookRoom1Layout.createSequentialGroup()
                        .addComponent(jCldBooking, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlBookRoom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnPUD, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPUD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlBookRoom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDOD, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDOD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlBookRoom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSearchCar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearchCar1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlBookRoom1Layout.createSequentialGroup()
                        .addGroup(pnlBookRoom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlBookRoom1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel5))
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnProceedCar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(93, Short.MAX_VALUE))
        );

        tabBooking.addTab("1 - Select Date", pnlBookRoom1);

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jPanel3.setBackground(new java.awt.Color(255, 204, 102));

        tblACar.setBackground(new java.awt.Color(255, 204, 102));
        tblACar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Car Plate", "Car Brand", "Car Model", "Rental Rate (Daily)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblACar.setSelectionBackground(new java.awt.Color(255, 204, 153));
        jScrollPane4.setViewportView(tblACar);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 746, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Palatino", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 204, 102));
        jLabel1.setText("Available Cars");

        jBtnSelect.setBackground(new java.awt.Color(255, 204, 102));
        jBtnSelect.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jBtnSelect.setForeground(new java.awt.Color(255, 255, 255));
        jBtnSelect.setText("Select");
        jBtnSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSelectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(389, 389, 389)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jBtnSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 116, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBtnSelect)
                .addGap(63, 63, 63))
        );

        tabBooking.addTab("2 - Select Car", jPanel2);

        pnlBookRoom2.setBackground(new java.awt.Color(0, 0, 0));

        formBook1.setBackground(new java.awt.Color(0, 0, 0));

        jLabel16.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 204, 102));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Booking Summary");

        jLabel17.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Pick-up Date");

        jLabel18.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Drop-off Date");

        jLabel20.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Service Tax (10%)");

        jLabel21.setFont(new java.awt.Font("Poppins Medium", 3, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Total");

        btnConfirmBook.setBackground(new java.awt.Color(255, 204, 102));
        btnConfirmBook.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnConfirmBook.setForeground(new java.awt.Color(255, 255, 255));
        btnConfirmBook.setText("Confirm Booking >>");
        btnConfirmBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmBookActionPerformed(evt);
            }
        });

        lblPUD.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        lblPUD.setForeground(new java.awt.Color(255, 255, 255));
        lblPUD.setText("N/A");

        lblCP.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        lblCP.setForeground(new java.awt.Color(255, 255, 255));
        lblCP.setText("N/A");
        lblCP.setToolTipText("");

        jLabel24.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Period(Days)");

        jLabel25.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Subtotal");

        lblDuration.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        lblDuration.setForeground(new java.awt.Color(255, 255, 255));
        lblDuration.setText("0");

        lblSubtotal.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        lblSubtotal.setForeground(new java.awt.Color(255, 255, 255));
        lblSubtotal.setText("RM0.00");

        lblServiceTax.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        lblServiceTax.setForeground(new java.awt.Color(255, 255, 255));
        lblServiceTax.setText("RM0.00");

        jSeparator1.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));

        lblTotal.setFont(new java.awt.Font("Poppins Medium", 2, 18)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(255, 255, 255));
        lblTotal.setText("RM0.00");

        jPanel6.setBackground(new java.awt.Color(255, 204, 204));

        jLabel33.setFont(new java.awt.Font("Poppins Light", 0, 12)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(51, 51, 51));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("* Note: If you fail to arrive, no refund");

        jLabel34.setFont(new java.awt.Font("Poppins Light", 0, 12)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(51, 51, 51));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("will be given.");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
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
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Booking ID");

        lblBookingID.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        lblBookingID.setForeground(new java.awt.Color(255, 255, 255));
        lblBookingID.setText("N/A");

        jSeparator2.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));

        jLabel19.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Car Brand");

        jLabel22.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Car Plate");

        jLabel23.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Car Model");

        jLabel27.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Rental Rate");

        lblDOD.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        lblDOD.setForeground(new java.awt.Color(255, 255, 255));
        lblDOD.setText("N/A");

        lblCB.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        lblCB.setForeground(new java.awt.Color(255, 255, 255));
        lblCB.setText("N/A");
        lblCB.setToolTipText("");

        lblCM.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        lblCM.setForeground(new java.awt.Color(255, 255, 255));
        lblCM.setText("N/A");
        lblCM.setToolTipText("");

        lblRR.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        lblRR.setForeground(new java.awt.Color(255, 255, 255));
        lblRR.setText("N/A");
        lblRR.setToolTipText("");

        javax.swing.GroupLayout formBook1Layout = new javax.swing.GroupLayout(formBook1);
        formBook1.setLayout(formBook1Layout);
        formBook1Layout.setHorizontalGroup(
            formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formBook1Layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addGroup(formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(formBook1Layout.createSequentialGroup()
                            .addGap(25, 25, 25)
                            .addGroup(formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(formBook1Layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addComponent(jLabel24)
                                    .addGap(18, 18, 18)
                                    .addComponent(lblDuration))
                                .addGroup(formBook1Layout.createSequentialGroup()
                                    .addGroup(formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel17)
                                        .addComponent(jLabel18)
                                        .addComponent(jLabel26))
                                    .addGap(18, 18, 18)
                                    .addGroup(formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblPUD)
                                        .addComponent(lblBookingID, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblDOD)))))
                        .addGroup(formBook1Layout.createSequentialGroup()
                            .addGroup(formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel20)
                                .addComponent(jLabel25))
                            .addGap(18, 18, 18)
                            .addGroup(formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblSubtotal)
                                .addComponent(lblServiceTax)))
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formBook1Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(formBook1Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jLabel21)
                        .addGap(18, 18, 18)
                        .addComponent(lblTotal)))
                .addGap(70, 70, 70)
                .addGroup(formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnConfirmBook, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(formBook1Layout.createSequentialGroup()
                            .addComponent(jLabel23)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblCM))
                        .addGroup(formBook1Layout.createSequentialGroup()
                            .addComponent(jLabel27)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblRR))
                        .addGroup(formBook1Layout.createSequentialGroup()
                            .addGroup(formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel22)
                                .addComponent(jLabel19))
                            .addGap(55, 55, 55)
                            .addGroup(formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblCP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblCB)))))
                .addContainerGap(270, Short.MAX_VALUE))
        );
        formBook1Layout.setVerticalGroup(
            formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formBook1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formBook1Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addGroup(formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(lblPUD))
                        .addGap(2, 2, 2)
                        .addGroup(formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(lblDOD))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formBook1Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)))
                .addGroup(formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formBook1Layout.createSequentialGroup()
                        .addGroup(formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(lblBookingID))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(lblDuration))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addGroup(formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(lblSubtotal))
                        .addGap(4, 4, 4)
                        .addGroup(formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(lblServiceTax))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(formBook1Layout.createSequentialGroup()
                        .addGroup(formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(lblCP))
                        .addGap(2, 2, 2)
                        .addGroup(formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addGroup(formBook1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(lblCB, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(lblCM))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(lblRR))
                        .addGap(26, 26, 26)))
                .addGroup(formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formBook1Layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(lblTotal)))
                    .addComponent(btnConfirmBook))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlBookRoom2Layout = new javax.swing.GroupLayout(pnlBookRoom2);
        pnlBookRoom2.setLayout(pnlBookRoom2Layout);
        pnlBookRoom2Layout.setHorizontalGroup(
            pnlBookRoom2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBookRoom2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(formBook1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlBookRoom2Layout.setVerticalGroup(
            pnlBookRoom2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBookRoom2Layout.createSequentialGroup()
                .addComponent(formBook1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(94, 94, 94))
        );

        tabBooking.addTab("3 - Payment", pnlBookRoom2);

        javax.swing.GroupLayout pnlBookRoomLayout = new javax.swing.GroupLayout(pnlBookRoom);
        pnlBookRoom.setLayout(pnlBookRoomLayout);
        pnlBookRoomLayout.setHorizontalGroup(
            pnlBookRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBookRoomLayout.createSequentialGroup()
                .addComponent(tabBooking)
                .addContainerGap())
        );
        pnlBookRoomLayout.setVerticalGroup(
            pnlBookRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBookRoomLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabBooking, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jtpCustomer.addTab("Booking", pnlBookRoom);

        tabReceipts.setBackground(new java.awt.Color(255, 255, 255));

        tblNotification.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Your booking B00000 is confirmed!"},
                {"Your booking B00000 is confirmed!"},
                {"Your booking B00000 is confirmed!"},
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Notification"
            }
        ));
        tblNotification.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNotificationMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblNotification);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(43, Short.MAX_VALUE)))
        );

        tblMessages.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Message"
            }
        ));
        jScrollPane7.setViewportView(tblMessages);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 651, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout tabReceiptsLayout = new javax.swing.GroupLayout(tabReceipts);
        tabReceipts.setLayout(tabReceiptsLayout);
        tabReceiptsLayout.setHorizontalGroup(
            tabReceiptsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabReceiptsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        tabReceiptsLayout.setVerticalGroup(
            tabReceiptsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabReceiptsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabReceiptsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jtpCustomer.addTab("Notification", tabReceipts);

        tabBookingHistory.setBackground(new java.awt.Color(255, 204, 102));

        tabHistory.setBackground(new java.awt.Color(0, 0, 0));

        btnSearchHistory.setBackground(new java.awt.Color(255, 204, 102));
        btnSearchHistory.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnSearchHistory.setForeground(new java.awt.Color(255, 255, 255));
        btnSearchHistory.setText("Search");
        btnSearchHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchHistoryActionPerformed(evt);
            }
        });

        txtSearchHistory.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        txtSearchHistory.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtSearchHistory.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtSearchHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchHistoryActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Palatino", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 204, 102));
        jLabel2.setText("Car Brand: ");

        javax.swing.GroupLayout tabHistoryLayout = new javax.swing.GroupLayout(tabHistory);
        tabHistory.setLayout(tabHistoryLayout);
        tabHistoryLayout.setHorizontalGroup(
            tabHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabHistoryLayout.createSequentialGroup()
                .addContainerGap(495, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(txtSearchHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearchHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        tabHistoryLayout.setVerticalGroup(
            tabHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabHistoryLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(tabHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSearchHistory)
                    .addComponent(txtSearchHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        tableHistory.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        tableHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Booking ID", "Booking Date", "Car Plate", "Car Brand", "Car Model", "Pick-up", "Drop-off", "Amount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableHistory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableHistoryMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tableHistoryMouseExited(evt);
            }
        });
        jScrollPane2.setViewportView(tableHistory);

        javax.swing.GroupLayout tabBookingHistoryLayout = new javax.swing.GroupLayout(tabBookingHistory);
        tabBookingHistory.setLayout(tabBookingHistoryLayout);
        tabBookingHistoryLayout.setHorizontalGroup(
            tabBookingHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabBookingHistoryLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabBookingHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabHistory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 963, Short.MAX_VALUE))
                .addContainerGap())
        );
        tabBookingHistoryLayout.setVerticalGroup(
            tabBookingHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabBookingHistoryLayout.createSequentialGroup()
                .addComponent(tabHistory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jtpCustomer.addTab("All Booking History", tabBookingHistory);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlHeader, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jtpCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(pnlHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jtpCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        this.setVisible(false);
        new LoginFrame().setVisible(true);
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void jtpCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtpCustomerMouseClicked
        tabBooking.setEnabledAt(1, false);
        tabBooking.setEnabledAt(2, false);
    }//GEN-LAST:event_jtpCustomerMouseClicked

    private void tableHistoryMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableHistoryMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tableHistoryMouseExited

    private void tableHistoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableHistoryMouseClicked

    }//GEN-LAST:event_tableHistoryMouseClicked

    private void txtSearchHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchHistoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchHistoryActionPerformed

    private void btnSearchHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchHistoryActionPerformed
       String search = txtSearchHistory.getText().trim().toUpperCase(); //get search(car brand) from user
       String carNo = null;
       if (search.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Please enter car brand to search bookings.");
        }

        DefaultTableModel tableModelSearch = (DefaultTableModel) tableHistory.getModel();
        tableModelSearch.setRowCount(0); // Delete all previous rows

        for (Car car : CarRental.getCars())
        {
//            System.out.println(car.getCarBrand());
//            System.out.println("the search"+ search);
            if (car.getCarBrand().contains(search))
            {
                System.out.println("search: "+search);
                carNo = car.getCarPlate();//through car brand get carPlate(s) from car file
//            }
        
                String customerID = user.getUserID();//get login's userid
                System.out.println("customerid: "+customerID);
    //            DefaultTableModel tableModelSearch = (DefaultTableModel) tableHistory.getModel();
                for (Booking booking : CarRental.getBookings())
                {
                    if (carNo != null)
                        if (booking.getCustID().toUpperCase().contains(customerID) && booking.getCarNo().contains(carNo))//print history of specific customer
                        {
                            if (car.getCarPlate().toUpperCase().contains(booking.getCarNo().toUpperCase()))
                            {
                                System.out.println("the carplate in car file"+car.getCarPlate());
                                System.out.println("the carno that we search for "+booking.getCarNo());
                                System.out.println("the customer in customer file"+booking.getCustID());
                                System.out.println("brand is"+car.getCarBrand());
                                System.out.println("model is "+car.getCarModel());
                                columns[3] = car.getCarBrand();
                                columns[4] = car.getCarModel();
                            }
                            columns[0] = booking.getBookingId();
    //                        columns[3] = "dabian";
    //                        columns[4] = "dabian";        
                            columns[1] = booking.getBookingDate().toString();
                            columns[2] = booking.getCarNo();
                            columns[5] = booking.getStartDate().toString();
                            columns[6] = booking.getEndDate().toString();
                            columns[7] = String.format("%.2f", booking.getBookingFee());
                            tableModelSearch.addRow(columns);
                            System.out.println("im here");
                        }
                }
            }
        }
        txtSearchHistory.setText("");
    }//GEN-LAST:event_btnSearchHistoryActionPerformed

    private void displayBookingSummary(){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String spickUp = txtPUD.getText().trim();
        String sdropOff = txtDOD.getText().trim();
        LocalDate pickUp = LocalDate.parse(spickUp, format);
        LocalDate dropOff= LocalDate.parse(sdropOff, format);
        DefaultTableModel tableCarModel = (DefaultTableModel) tblACar.getModel();
        int row = tblACar.getSelectedRow();
        double rr = (double) tableCarModel.getValueAt(row, 3); 
        
        long d = 0;
        int duration;
        d = getDateDiff(pickUp,dropOff,TimeUnit.MILLISECONDS);
        duration = (int)d;
        int ID = Booking.id;
        String bookingID = "B" + String.format("%04d", ID);
        System.out.println("bookingID: "+bookingID);
        double subTotal = rr * d;
        double serviceTax = subTotal * 10 / 100;
        double total = subTotal + serviceTax;
        if (row >= 0){
            lblCP.setText((String) tableCarModel.getValueAt(row, 0));
            lblCB.setText((String) tableCarModel.getValueAt(row, 1));
            lblCM.setText((String) tableCarModel.getValueAt(row, 2));
            lblRR.setText(String.valueOf(rr));
            lblPUD.setText(spickUp);
            lblDOD.setText(sdropOff);
            lblBookingID.setText(bookingID) ;
            lblDuration.setText(String.valueOf(duration));
            lblSubtotal.setText(String.valueOf(subTotal));
            lblServiceTax.setText(String.valueOf(serviceTax));
            lblTotal.setText(String.valueOf(total));
        }
        
    }
    
    private void jBtnSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSelectActionPerformed
        if(tblACar.getSelectionModel().isSelectionEmpty()){
            JOptionPane.showMessageDialog(this, "Please choose a car to book. ");
        }
        else{
            displayBookingSummary();
            tabBooking.setSelectedIndex(2); 
            tabBooking.setEnabledAt(0, false);
            tabBooking.setEnabledAt(1, false);
        }
    }//GEN-LAST:event_jBtnSelectActionPerformed

    private void btnProceedCarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProceedCarActionPerformed
        if (tblACar.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "No car available, please choose another date.");
        } else {
            tabBooking.setSelectedIndex(1);
            tabBooking.setEnabledAt(0, false);
            tabBooking.setEnabledAt(2, false);
        }
    }//GEN-LAST:event_btnProceedCarActionPerformed

    private void btnSearchCarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchCarActionPerformed
        String sPickUp = txtPUD.getText();
        String sDropOff = txtDOD.getText();
        LocalDate pickUp = null;
        LocalDate dropOff = null;
        if(sDropOff.isEmpty() || sPickUp.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please choose pick up and drop off date.");
        }
        else{
            if(!sPickUp.isEmpty() || !sDropOff.isEmpty()){
                DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                pickUp = LocalDate.parse(sPickUp, format);
                dropOff = LocalDate.parse(sDropOff, format);
                LocalDate now = LocalDate.now();
                if(pickUp.getDayOfMonth()==dropOff.getDayOfMonth() && pickUp.getMonthValue()==dropOff.getMonthValue() && pickUp.getYear()==dropOff.getYear()){
                    JOptionPane.showMessageDialog(this, "Fail to add booking, invalid pick up and drop off date.");
                }//same day rejected
                
                else if(pickUp.getYear() < now.getYear()){
                    JOptionPane.showMessageDialog(this, "Fail to add booking, invalid pick up and drop off date.");
                }
                
                else if(pickUp.getMonthValue() < now.getMonthValue()){
                    JOptionPane.showMessageDialog(this, "Fail to add booking, invalid pick up and drop off date.");
                }
                
                else if(pickUp.getDayOfMonth() < now.getDayOfMonth()){
                    JOptionPane.showMessageDialog(this, "Fail to add booking, invalid pick up and drop off date.");
                }

                else if(pickUp==dropOff || dropOff.getYear() < pickUp.getYear() || dropOff.getYear() - pickUp.getYear() > 1){
                    JOptionPane.showMessageDialog(this, "Fail to add booking, invalid pick up and drop off date.");
                }//drop off year smaller pickup year / more than one year

                else if(dropOff.getMonthValue() < pickUp.getMonthValue()){
                    JOptionPane.showMessageDialog(this, "Fail to add booking, invalid pick up and drop off date.");
                }//drop off month smaller pickup month

                else if(dropOff.getDayOfMonth() < pickUp.getDayOfMonth()){
                    JOptionPane.showMessageDialog(this, "Fail to add booking, invalid pick up and drop off date.");
                }//drop off day smaller pickup day
                else{
                    displayAvailableCars(pickUp, dropOff);
                }
            }
            
        }
        // if (chkInDate.isEmpty() || chkOutDate.isEmpty()) { // Validation
            //     JOptionPane.showMessageDialog(this, "Please fill in the dates first.");
            // } else if (validateBookingDates()) { // Add all available rooms to list model
            //     ArrayList<String> availableRooms = getAvailableRooms();
            //     lm.removeAllElements();
            //     availableRooms.forEach((room) -> lm.addElement(room));
            // }
    }//GEN-LAST:event_btnSearchCarActionPerformed

    public static long getDateDiff(LocalDate date1, LocalDate date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getDayOfYear() - date1.getDayOfYear();
        return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);

    }
    
    private void displayAvailableCars(LocalDate pickUp, LocalDate dropOff) {
        DefaultTableModel tableModel = (DefaultTableModel) tblAvailableCars.getModel();
        DefaultTableModel tableCarModel = (DefaultTableModel) tblACar.getModel();
        tableModel.setRowCount(0); // Delete all previous rows
        tableCarModel.setRowCount(0);
        long d = 0;
        long dInFile = 0;
        int duration, dInFileI;
        d = getDateDiff(pickUp,dropOff,TimeUnit.MILLISECONDS);
        duration = (int)d;
        ArrayList<String> carInTable=new ArrayList<String>();
        ArrayList<String> carNotAvailable=new ArrayList<String>();
        ArrayList<String> carAvailable=new ArrayList<String>();
        for (Car car : CarRental.getCars()){
            for (Booking booking : CarRental.getBookings()){    
                dInFile = getDateDiff(booking.getStartDate(),booking.getEndDate(),TimeUnit.MILLISECONDS);
                dInFileI =  (int)dInFile;
                LocalDate dateInDuration = booking.getStartDate();
                for (int i = 0; i <= dInFileI; i++) {
                    LocalDate pickUpTemp = pickUp;
                    for (int j = 0; j <= duration; j++){
                        if (pickUpTemp.equals(dateInDuration)){
                            if (!carNotAvailable.contains(booking.getCarNo())){
                                carNotAvailable.add(booking.getCarNo());
                            }
                        }
                        pickUpTemp = pickUpTemp.plusDays(1);
                    }
                    
                    dateInDuration = dateInDuration.plusDays(1);
                }
                if(!carNotAvailable.contains(car.getCarPlate())){
                    if(!carAvailable.contains(car.getCarPlate())){
                        carAvailable.add(car.getCarPlate());
                    }
                }    
            }
            if(carAvailable.contains(car.getCarPlate())){
                    columns[0] = car.getCarPlate();
                    columns[1] = car.getCarBrand();
                    columns[2] = car.getCarModel();
                    columns[3] = car.getDailyRentalRate();
                    if(!carInTable.contains(car.getCarPlate())){
                        carInTable.add(car.getCarPlate());
                        tableModel.addRow(columns);
                        tableCarModel.addRow(columns);
                    }
            }     
        }
    }

    
    private void btnDODActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDODActionPerformed
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String bDropOff = sdf.format(jCldBooking.getDate());
        txtDOD.setText(bDropOff);
        
        // bookRoomID = "";
        // lblRoomID.setText("N/A");
    }//GEN-LAST:event_btnDODActionPerformed

    private void txtDODActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDODActionPerformed

    }//GEN-LAST:event_txtDODActionPerformed

    private void txtPUDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPUDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPUDActionPerformed

    private void btnPUDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPUDActionPerformed
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String bPickUp = sdf.format(jCldBooking.getDate());
        txtPUD.setText(bPickUp);
        
// chkInDate = datef.format(jCalendar1.getDate());
        // txtChkIn.setText(chkInDate);
        // lblChkIn.setText(chkInDate);
        // validateToday();
        // if (!chkOutDate.isEmpty()) {
            //     validateBookingDates();
            // }
        // bookRoomID = "";
        // lblRoomID.setText("N/A");
    }//GEN-LAST:event_btnPUDActionPerformed

    private void btnFilterCarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterCarActionPerformed
        
        String carBrand = txtCarBrand.getText().trim().toLowerCase();
        String carModel = txtCarModel.getText().trim().toLowerCase();
        String inputMinPrice = txtMinPrice.getText().trim();
        String inputMaxPrice = txtMaxPrice.getText().trim();
        Double minPrice = 0.0; // Dummy minimum price just for the sake of comparison
        Double maxPrice = 999999.9; // Dummy maximum price just for the sake of comparison
        
        // If minimum price field is not empty, ensure that it is a numeric (double) value
        if (!inputMinPrice.isEmpty())
        {
            try {
                minPrice = Double.parseDouble(inputMinPrice);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Minimum price must be a numeric value.");
                return;
            }
        }
        
        // If maximum price field is not empty, ensure that it is a numeric (double) value
        if (!inputMaxPrice.isEmpty())
        {
            try {
                maxPrice = Double.parseDouble(inputMaxPrice);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Maximum price must be a numeric value.");
                return;
            }
        }

        DefaultTableModel tableModel = (DefaultTableModel) tableAllCars.getModel();
        tableModel.setRowCount(0); // Delete all previous rows

        for (Car car : CarRental.getCars())
        {
            Boolean withinPriceRange;

            if (inputMinPrice.isEmpty() && inputMaxPrice.isEmpty()) 
                withinPriceRange = true;
            else if (!inputMinPrice.isEmpty() && !inputMaxPrice.isEmpty())
                withinPriceRange = car.getDailyRentalRate() >= minPrice && car.getDailyRentalRate() <= maxPrice;
            else if (!inputMinPrice.isEmpty())
                withinPriceRange = car.getDailyRentalRate() >= minPrice;
            else
                withinPriceRange = car.getDailyRentalRate() <= maxPrice;


            if ((car.getCarBrand().toLowerCase().contains(carBrand) &&
                car.getCarModel().toLowerCase().contains(carModel)) && withinPriceRange)
            {
                columns[0] = car.getCarID();
                columns[1] = car.getCarPlate();
                columns[2] = car.getCarBrand();
                columns[3] = car.getCarModel();
                columns[4] = String.format("%.2f", car.getDailyRentalRate());
                tableModel.addRow(columns);
            }
        }
    }//GEN-LAST:event_btnFilterCarActionPerformed

    private void txtMinPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMinPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMinPriceActionPerformed

    private void tableAllCarsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableAllCarsMouseClicked

        DefaultTableModel tableModel = (DefaultTableModel) tableAllCars.getModel();
        int row = tableAllCars.getSelectedRow();

        if (row >= 0) {
            txtCarBrand.setText((String) tableModel.getValueAt(row, 2));
            txtCarModel.setText((String) tableModel.getValueAt(row, 3));
        }
    }//GEN-LAST:event_tableAllCarsMouseClicked

    private void tableAllCarsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableAllCarsMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tableAllCarsMouseExited

    private void txtCarBrandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCarBrandActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCarBrandActionPerformed

    private void txtCarModelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCarModelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCarModelActionPerformed

    private void txtMaxPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaxPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaxPriceActionPerformed

    private void btnResetCarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetCarActionPerformed
        txtCarBrand.setText("");
        txtCarModel.setText("");
        txtMinPrice.setText("");
        txtMaxPrice.setText("");
        loadCars();
    }//GEN-LAST:event_btnResetCarActionPerformed

    private void tblNotificationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNotificationMouseClicked
        DefaultTableModel tableMessagesModel = (DefaultTableModel) tblMessages.getModel();
        DefaultTableModel tableNotificationsModel = (DefaultTableModel) tblNotification.getModel();
        int row = tblNotification.getSelectedRow();

        if (row >= 0) {
            String notification = (String) tableNotificationsModel.getValueAt(row, 0);
            String bookingID = notification.substring(13, 18);
            String customerID = user.getUserID();
            String username = user.getUsername();
            tableMessagesModel.setRowCount(0); // Delete all previous rows
            for (Booking booking : CarRental.getBookings())
            {
                if (booking.getBookingId().toUpperCase().contains(bookingID))//print history of specific customer
                {
                    columns[0] = "Yay, your booking is confirmed!";
                    tableMessagesModel.addRow(columns);
                    columns[0] = " ";
                    tableMessagesModel.addRow(columns);
                    columns[0] = "Dear " + username + ",";
                    tableMessagesModel.addRow(columns);
                    columns[0] = " ";
                    tableMessagesModel.addRow(columns);
                    columns[0] = "Thank you for booking with RapidCar.";
                    tableMessagesModel.addRow(columns);
                    columns[0] = "We have received your Order " + booking.getBookingId() + " on " + booking.getBookingDate() + ".  ";
                    tableMessagesModel.addRow(columns);
                    columns[0] = "We hope you had a pleasant experience. Stay safe!";
                    tableMessagesModel.addRow(columns);
                    columns[0] = " ";
                    tableMessagesModel.addRow(columns);
                    columns[0] = "Yours sincerely,";
                    tableMessagesModel.addRow(columns);
                    columns[0] = "RapidCar.";
                    tableMessagesModel.addRow(columns);
            }
        }
        }
    }//GEN-LAST:event_tblNotificationMouseClicked

    private void btnSearchCar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchCar1ActionPerformed
        txtPUD.setText("");
        txtDOD.setText("");
    }//GEN-LAST:event_btnSearchCar1ActionPerformed

    private void btnConfirmBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmBookActionPerformed
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        bookingConfirmation();
        String customerID = user.getUserID();
        String bookingCP = lblCP.getText().toUpperCase();
        String sBookingDate = format.format(LocalDate.now());
        String sPickUp = lblPUD.getText();
        String sDropOff = lblDOD.getText();
        String status = "WAITING FOR CONFIRMATION";
        LocalDate pickUp = LocalDate.parse(sPickUp, format);
        LocalDate dropOff = LocalDate.parse(sDropOff, format);
        String sAmount = lblTotal.getText();

        if (customerID.isEmpty() || bookingCP.isEmpty() || sBookingDate.isEmpty() || sPickUp.isEmpty() || sDropOff.isEmpty() || sAmount.isEmpty())
        JOptionPane.showMessageDialog(this, "Please fill in all necessary information to add booking.");
        
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
                    JOptionPane.showMessageDialog(this, "Payment Successful");
                }
                else
                JOptionPane.showMessageDialog(this, "Payment Unsucessful - Something went wrong.");

            } else {
                booking = null; // Deleting it (by making it eligible for garbage collection)
                JOptionPane.showMessageDialog(this, "Booking not added - Duplication detected.");
            }
            tabBooking.setSelectedIndex(0); 
        }
             
    }//GEN-LAST:event_btnConfirmBookActionPerformed
    
    boolean displayed = false;
    
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
            java.util.logging.Logger.getLogger(CustomerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CustomerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CustomerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CustomerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CustomerFrame(user).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfirmBook;
    private javax.swing.JButton btnDOD;
    private javax.swing.JButton btnFilterCar;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnPUD;
    private javax.swing.JButton btnProceedCar;
    private javax.swing.JButton btnResetCar;
    private javax.swing.JButton btnSearchCar;
    private javax.swing.JButton btnSearchCar1;
    private javax.swing.JButton btnSearchHistory;
    private javax.swing.JPanel formBook1;
    private javax.swing.JButton jBtnSelect;
    private com.toedter.calendar.JCalendar jCldBooking;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable2;
    private javax.swing.JTabbedPane jtpCustomer;
    private javax.swing.JLabel lblBookingID;
    private javax.swing.JLabel lblCB;
    private javax.swing.JLabel lblCM;
    private javax.swing.JLabel lblCP;
    private javax.swing.JLabel lblDOD;
    private javax.swing.JLabel lblDuration;
    private javax.swing.JLabel lblPUD;
    private javax.swing.JLabel lblRR;
    private javax.swing.JLabel lblRapidCar;
    private javax.swing.JLabel lblServiceTax;
    private javax.swing.JLabel lblSubtotal;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JPanel pnlBookRoom;
    private javax.swing.JPanel pnlBookRoom1;
    private javax.swing.JPanel pnlBookRoom2;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JTabbedPane tabBooking;
    private javax.swing.JPanel tabBookingHistory;
    private javax.swing.JPanel tabCars;
    private javax.swing.JPanel tabHistory;
    private javax.swing.JPanel tabHistory1;
    private javax.swing.JPanel tabReceipts;
    private javax.swing.JTable tableAllCars;
    private javax.swing.JTable tableHistory;
    private javax.swing.JTable tblACar;
    private javax.swing.JTable tblAvailableCars;
    private javax.swing.JTable tblMessages;
    private javax.swing.JTable tblNotification;
    private javax.swing.JTextField txtCarBrand;
    private javax.swing.JTextField txtCarModel;
    private javax.swing.JTextField txtDOD;
    private javax.swing.JTextField txtMaxPrice;
    private javax.swing.JTextField txtMinPrice;
    private javax.swing.JTextField txtPUD;
    private javax.swing.JTextField txtSearchHistory;
    // End of variables declaration//GEN-END:variables
}
