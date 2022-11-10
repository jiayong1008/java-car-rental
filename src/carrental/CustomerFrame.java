/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package carrental;

import java.util.ArrayList;

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
        jCalendar1 = new com.toedter.calendar.JCalendar();
        btnPUD = new javax.swing.JButton();
        txtPUD = new javax.swing.JTextField();
        txtDOD = new javax.swing.JTextField();
        btnDOD = new javax.swing.JButton();
        btnSearchCar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listAvailableRooms = new javax.swing.JList<>();
        btnProceedCar = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
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
        jSeparator2 = new javax.swing.JSeparator();
        tabReceipts = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblNotification = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jListConfirmationLetter = new javax.swing.JList<>();
        tabBookingHistory = new javax.swing.JPanel();
        tabHistory = new javax.swing.JPanel();
        btnSearchHistory = new javax.swing.JButton();
        txtSearchHistory = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableHistory = new javax.swing.JTable();

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
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 952, Short.MAX_VALUE))
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

        jCalendar1.setBackground(new java.awt.Color(153, 255, 204));

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
        jLabel35.setText("* Note: Car Brand - Car Model");

        jLabel36.setFont(new java.awt.Font("Poppins Light", 0, 12)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(51, 51, 51));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("BMW - M3");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel36, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlBookRoom1Layout = new javax.swing.GroupLayout(pnlBookRoom1);
        pnlBookRoom1.setLayout(pnlBookRoom1Layout);
        pnlBookRoom1Layout.setHorizontalGroup(
            pnlBookRoom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBookRoom1Layout.createSequentialGroup()
                .addGroup(pnlBookRoom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBookRoom1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jCalendar1, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlBookRoom1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(pnlBookRoom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnDOD, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPUD, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlBookRoom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPUD, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDOD, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearchCar, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 172, Short.MAX_VALUE)
                .addGroup(pnlBookRoom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBookRoom1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(btnProceedCar, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlBookRoom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(164, 164, 164))
        );
        pnlBookRoom1Layout.setVerticalGroup(
            pnlBookRoom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBookRoom1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jCalendar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlBookRoom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPUD, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPUD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlBookRoom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDOD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDOD, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearchCar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(82, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBookRoom1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnProceedCar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabBooking.addTab("1 - Select Date", pnlBookRoom1);

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jPanel3.setBackground(new java.awt.Color(255, 204, 102));

        jTable1.setBackground(new java.awt.Color(255, 204, 102));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.setSelectionBackground(new java.awt.Color(255, 204, 153));
        jScrollPane4.setViewportView(jTable1);

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
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel1.setFont(new java.awt.Font("Palatino", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 204, 102));
        jLabel1.setText("Available Cars");

        jButton1.setBackground(new java.awt.Color(255, 204, 102));
        jButton1.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Select");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 104, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
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
        lblChkIn.setForeground(new java.awt.Color(255, 255, 255));
        lblChkIn.setText("N/A");

        lblChkOut.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        lblChkOut.setForeground(new java.awt.Color(255, 255, 255));
        lblChkOut.setText("N/A");
        lblChkOut.setToolTipText("");

        jLabel24.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Period(Days)");

        jLabel25.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Subtotal");

        lblNights.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        lblNights.setForeground(new java.awt.Color(255, 255, 255));
        lblNights.setText("0");

        lblSubtotal.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        lblSubtotal.setForeground(new java.awt.Color(255, 255, 255));
        lblSubtotal.setText("RM0.00");

        lblServiceTax.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        lblServiceTax.setForeground(new java.awt.Color(255, 255, 255));
        lblServiceTax.setText("RM0.00");

        jLabel29.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Tourism Tax");

        lblTourismTax.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        lblTourismTax.setForeground(new java.awt.Color(255, 255, 255));
        lblTourismTax.setText("RM0.00");

        jSeparator1.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));

        lblTotal.setFont(new java.awt.Font("Poppins Medium", 2, 18)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(255, 255, 255));
        lblTotal.setText("RM0.00");

        jPanel6.setBackground(new java.awt.Color(255, 204, 204));

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
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Booking ID");

        lblRoomID.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        lblRoomID.setForeground(new java.awt.Color(255, 255, 255));
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

        jSeparator2.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout formBook1Layout = new javax.swing.GroupLayout(formBook1);
        formBook1.setLayout(formBook1Layout);
        formBook1Layout.setHorizontalGroup(
            formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formBook1Layout.createSequentialGroup()
                .addGroup(formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formBook1Layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(jLabel21)
                        .addGap(18, 18, 18)
                        .addComponent(lblTotal))
                    .addGroup(formBook1Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addGroup(formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(formBook1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel24)
                                .addGap(18, 18, 18)
                                .addComponent(lblNights))
                            .addGroup(formBook1Layout.createSequentialGroup()
                                .addGroup(formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel26))
                                .addGap(18, 18, 18)
                                .addGroup(formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblChkIn)
                                    .addComponent(lblChkOut)
                                    .addComponent(lblRoomID, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(formBook1Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(formBook1Layout.createSequentialGroup()
                                .addGroup(formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel29)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel25))
                                .addGap(18, 18, 18)
                                .addGroup(formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblSubtotal)
                                    .addComponent(lblServiceTax)
                                    .addComponent(lblTourismTax)))
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formBook1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 325, Short.MAX_VALUE)
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
                .addGap(18, 18, 18)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(lblSubtotal))
                .addGap(4, 4, 4)
                .addGroup(formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(lblServiceTax))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTourismTax, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(formBook1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotal)
                    .addComponent(jLabel21)))
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
                .addComponent(formBook1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, Short.MAX_VALUE)
                .addGap(113, 113, 113))
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
                {"Your booking on 12-10-2022 is confirmed!"},
                {"Your booking on 12-10-2022 is confirmed!"},
                {"Your booking on 11-10-2022 is confirmed!"},
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Title 1"
            }
        ));
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
            .addGap(0, 402, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(43, Short.MAX_VALUE)))
        );

        jListConfirmationLetter.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Yay, your booking is confirmed!", " ", "Dear Username,", " ", "Thank you for booking with RapidCar.", "We have received your Order B00001 on DATE TIME.  ", "We hope you had a pleasant experience. Stay safe!", " ", "Yours sincerely,", "RapidCar.", " ", " ", " " };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane5.setViewportView(jListConfirmationLetter);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 633, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5))
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
                .addContainerGap(484, Short.MAX_VALUE)
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
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 951, Short.MAX_VALUE))
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
//        if (displayed) return;
//        displayed = true;
//        File file = new File(CarRental.getBookingFile());
//        try {
//            FileReader fr = new FileReader(file);
//            BufferedReader br = new BufferedReader(fr);
//
//            DefaultTableModel model = (DefaultTableModel)tableHistory.getModel();
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
            }
        
            String customerID = user.getUserID();//get login's userid
            System.out.println("customerid: "+customerID);
//            DefaultTableModel tableModelSearch = (DefaultTableModel) tableHistory.getModel();
            tableModelSearch.setRowCount(0); // Delete all previous rows
            for (Booking booking : CarRental.getBookings())
            {
                if (carNo != null)
                    if (booking.getCustID().toUpperCase().contains(customerID) && booking.getCarNo().contains(carNo))//print history of specific customer
                    {
                        if (car.getCarPlate().contains(booking.getCarNo()))
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
        txtSearchHistory.setText("");
    }//GEN-LAST:event_btnSearchHistoryActionPerformed

    private void btnSelectGuestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectGuestActionPerformed
        // int rowSelected = tableBookRoomGuest.getSelectedRow();
        // if (rowSelected < 0)
        // JOptionPane.showMessageDialog(this, "Please select a guest first.");
        // else {
            //     guestID = (int) tableBookRoomGuest.getValueAt(rowSelected, 0); // UserID col
            //     txtGuestName.setText((String) tableBookRoomGuest.getValueAt(rowSelected, 1));
            // }
    }//GEN-LAST:event_btnSelectGuestActionPerformed

    private void btnConfirmBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmBookActionPerformed
        bookingConfirmation();
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

    private void txtGuestNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGuestNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGuestNameActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnProceedCarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProceedCarActionPerformed
        // if (listAvailableRooms.getSelectedValue() == null) {
            //     JOptionPane.showMessageDialog(this, "Please select a room before proceeding.");
            // } else {
            //     bookRoomID = listAvailableRooms.getSelectedValue();
            //     lblRoomID.setText(bookRoomID);
            //     tabBookRoom.setSelectedIndex(1);
            // }
    }//GEN-LAST:event_btnProceedCarActionPerformed

    private void listAvailableRoomsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listAvailableRoomsValueChanged

    }//GEN-LAST:event_listAvailableRoomsValueChanged

    private void btnSearchCarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchCarActionPerformed
        // if (chkInDate.isEmpty() || chkOutDate.isEmpty()) { // Validation
            //     JOptionPane.showMessageDialog(this, "Please fill in the dates first.");
            // } else if (validateBookingDates()) { // Add all available rooms to list model
            //     ArrayList<String> availableRooms = getAvailableRooms();
            //     lm.removeAllElements();
            //     availableRooms.forEach((room) -> lm.addElement(room));
            // }
    }//GEN-LAST:event_btnSearchCarActionPerformed

    private void btnDODActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDODActionPerformed
        // chkOutDate = datef.format(jCalendar1.getDate());
        // txtChkOut.setText(chkOutDate);
        // lblChkOut.setText(chkOutDate);
        // if (!chkInDate.isEmpty()) {
            //     validateBookingDates();
            // }
        // bookRoomID = "";
        // lblRoomID.setText("N/A");
    }//GEN-LAST:event_btnDODActionPerformed

    private void txtDODActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDODActionPerformed

    }//GEN-LAST:event_txtDODActionPerformed

    private void txtPUDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPUDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPUDActionPerformed

    private void btnPUDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPUDActionPerformed
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
            System.out.println(car.getCarBrand().toLowerCase());
            System.out.println(carBrand);
            System.out.println(car.getCarBrand().toLowerCase().contains(carBrand));
            System.out.println();
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
    private javax.swing.JButton btnSearchGuest;
    private javax.swing.JButton btnSearchHistory;
    private javax.swing.JButton btnSelectGuest;
    private javax.swing.JPanel formBook1;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jListConfirmationLetter;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTabbedPane jtpCustomer;
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
    private javax.swing.JTable tblNotification;
    private javax.swing.JTextField txtCarBrand;
    private javax.swing.JTextField txtCarModel;
    private javax.swing.JTextField txtDOD;
    private javax.swing.JTextField txtGuestName;
    private javax.swing.JTextField txtMaxPrice;
    private javax.swing.JTextField txtMinPrice;
    private javax.swing.JTextField txtPUD;
    private javax.swing.JTextField txtSearchHistory;
    // End of variables declaration//GEN-END:variables
}
