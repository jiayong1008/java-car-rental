/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package carrental;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;


public class RegistrationFrame extends javax.swing.JFrame {   
    
    public RegistrationFrame() {
        initComponents();
        CarRental.loadUsers();
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        panelRegistration = new javax.swing.JPanel();
        lblLoginAlert = new javax.swing.JLabel();
        lblRegistration = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        lblRgtUsername = new javax.swing.JLabel();
        btnConfirm = new javax.swing.JButton();
        passRgt = new javax.swing.JPasswordField();
        txtRgtUsername = new javax.swing.JTextField();
        txtNRIC = new javax.swing.JTextField();
        txtContact = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        lblContact = new javax.swing.JLabel();
        lblGender = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblRgtPass = new javax.swing.JLabel();
        lblConfirmPass = new javax.swing.JLabel();
        passcRgt = new javax.swing.JPasswordField();
        lblNRIC = new javax.swing.JLabel();
        lblRegistration1 = new javax.swing.JLabel();
        btnMale = new javax.swing.JRadioButton();
        btnFemale = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnSignUp = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelRegistration.setBackground(new java.awt.Color(0, 0, 0));

        lblLoginAlert.setBackground(new java.awt.Color(255, 204, 204));
        lblLoginAlert.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        lblLoginAlert.setForeground(new java.awt.Color(102, 102, 102));
        lblLoginAlert.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLoginAlert.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));

        lblRegistration.setBackground(new java.awt.Color(245, 245, 245));
        lblRegistration.setFont(new java.awt.Font("Poppins", 1, 36)); // NOI18N
        lblRegistration.setForeground(new java.awt.Color(255, 204, 102));
        lblRegistration.setText("Registration");

        lblName.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        lblName.setForeground(new java.awt.Color(255, 255, 255));
        lblName.setText("Name as per IC");

        txtName.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        txtName.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtName.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });

        lblRgtUsername.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        lblRgtUsername.setForeground(new java.awt.Color(255, 255, 255));
        lblRgtUsername.setText("Username");

        btnConfirm.setBackground(new java.awt.Color(255, 204, 102));
        btnConfirm.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        btnConfirm.setForeground(new java.awt.Color(255, 255, 255));
        btnConfirm.setText("Confirm");
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });

        passRgt.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        passRgt.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));

        txtRgtUsername.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        txtRgtUsername.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtRgtUsername.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtRgtUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRgtUsernameActionPerformed(evt);
            }
        });

        txtNRIC.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        txtNRIC.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtNRIC.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtNRIC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNRICActionPerformed(evt);
            }
        });

        txtContact.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        txtContact.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtContact.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtContact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContactActionPerformed(evt);
            }
        });

        txtEmail.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        txtEmail.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtEmail.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        lblContact.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        lblContact.setForeground(new java.awt.Color(255, 255, 255));
        lblContact.setText("Contact No.");

        lblGender.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        lblGender.setForeground(new java.awt.Color(255, 255, 255));
        lblGender.setText("Gender");

        lblEmail.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        lblEmail.setForeground(new java.awt.Color(255, 255, 255));
        lblEmail.setText("Email");

        lblRgtPass.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        lblRgtPass.setForeground(new java.awt.Color(255, 255, 255));
        lblRgtPass.setText("Password");

        lblConfirmPass.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        lblConfirmPass.setForeground(new java.awt.Color(255, 255, 255));
        lblConfirmPass.setText("Confirm Password");

        passcRgt.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        passcRgt.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));

        lblNRIC.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        lblNRIC.setForeground(new java.awt.Color(255, 255, 255));
        lblNRIC.setText("NRIC");

        lblRegistration1.setBackground(new java.awt.Color(245, 245, 245));
        lblRegistration1.setFont(new java.awt.Font("Poppins", 3, 24)); // NOI18N
        lblRegistration1.setForeground(new java.awt.Color(255, 204, 102));
        lblRegistration1.setText("RapidCar");

        btnMale.setBackground(new java.awt.Color(0, 0, 0));
        buttonGroup1.add(btnMale);
        btnMale.setForeground(new java.awt.Color(255, 255, 255));
        btnMale.setText("Male");

        btnFemale.setBackground(new java.awt.Color(0, 0, 0));
        buttonGroup1.add(btnFemale);
        btnFemale.setForeground(new java.awt.Color(255, 255, 255));
        btnFemale.setText("Female");
        btnFemale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFemaleActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 10)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("EG: 000-0000000");

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 0, 10)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("EG: 000000-00-0000");

        btnSignUp.setBackground(new java.awt.Color(255, 204, 102));
        btnSignUp.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        btnSignUp.setForeground(new java.awt.Color(255, 255, 255));
        btnSignUp.setText("Login");
        btnSignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignUpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRegistrationLayout = new javax.swing.GroupLayout(panelRegistration);
        panelRegistration.setLayout(panelRegistrationLayout);
        panelRegistrationLayout.setHorizontalGroup(
            panelRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRegistrationLayout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(panelRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRegistrationLayout.createSequentialGroup()
                        .addComponent(txtRgtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(passRgt, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRegistrationLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(panelRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblName))
                        .addGap(37, 37, 37)
                        .addGroup(panelRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtContact, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelRegistrationLayout.createSequentialGroup()
                                .addComponent(lblContact)
                                .addGap(4, 4, 4)
                                .addComponent(jLabel1)))
                        .addGap(18, 18, 18)
                        .addGroup(panelRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEmail)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblConfirmPass)))
                    .addGroup(panelRegistrationLayout.createSequentialGroup()
                        .addGroup(panelRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelRegistrationLayout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(lblLoginAlert, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelRegistrationLayout.createSequentialGroup()
                                .addGroup(panelRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNRIC, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panelRegistrationLayout.createSequentialGroup()
                                        .addComponent(lblNRIC)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel2))
                                    .addComponent(lblRgtUsername))
                                .addGap(37, 37, 37)
                                .addGroup(panelRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblGender)
                                    .addComponent(lblRgtPass)
                                    .addGroup(panelRegistrationLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(btnMale)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnFemale))
                                    .addComponent(passcRgt, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(82, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRegistrationLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblRegistration1)
                .addGap(15, 15, 15))
            .addGroup(panelRegistrationLayout.createSequentialGroup()
                .addGap(239, 239, 239)
                .addComponent(lblRegistration)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panelRegistrationLayout.createSequentialGroup()
                .addGap(190, 190, 190)
                .addComponent(btnConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRegistrationLayout.setVerticalGroup(
            panelRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRegistrationLayout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(lblRegistration)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblLoginAlert, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblName)
                    .addComponent(lblContact)
                    .addComponent(lblEmail)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(panelRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRgtUsername)
                    .addComponent(lblRgtPass)
                    .addComponent(lblConfirmPass))
                .addGap(18, 18, 18)
                .addGroup(panelRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRgtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passRgt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passcRgt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNRIC)
                    .addComponent(lblGender)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNRIC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMale)
                    .addComponent(btnFemale))
                .addGap(42, 42, 42)
                .addGroup(panelRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConfirm)
                    .addComponent(btnSignUp))
                .addGap(46, 46, 46)
                .addComponent(lblRegistration1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRegistration, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelRegistration, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed
    
    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {                                           
        String name = txtName.getText().trim().toUpperCase();
        String contactNo = txtContact.getText().trim();
        String email = txtEmail.getText().trim().toLowerCase();
        String ic = txtNRIC.getText().trim();
        String username = txtRgtUsername.getText().trim().toLowerCase();
        String password = new String(passRgt.getPassword());
        String cpassword = new String(passcRgt.getPassword());
        String role = "customer";
        String gender="";

        if(btnMale.isSelected()){
            gender = "male";
        }
        else if(btnFemale.isSelected()){
            gender = "female";
        }
        else{
            JOptionPane.showMessageDialog(this, "Please choose gender.");
        }

        double dContact = 0.0;
        double dIC = 0.0;
        
        if (name.isEmpty() || contactNo.isEmpty() || email.isEmpty() || ic.isEmpty() || username.isEmpty() || password.isEmpty() || cpassword.isEmpty())
            JOptionPane.showMessageDialog(this, "Please fill in all necessary information to add user.");
            
        else if(name.length() <= 3 || username.length() <= 3){
            JOptionPane.showMessageDialog(this, "Name and username must be more than 3 characters. ");
        }
        
        else if(contactNo.length() < 11 || contactNo.length() > 12){
            JOptionPane.showMessageDialog(this, "Invalid contact number. ");
        }
        
        else if(!contactNo.contains("-")){
            JOptionPane.showMessageDialog(this, "Please follow the contact number format '000-0000000' ");
        }
        
        else if(!email.contains("@") || !email.contains(".com")){
            JOptionPane.showMessageDialog(this, "Invalid email. ");
        }
        
        else if(!password.equals(cpassword)){
            JOptionPane.showMessageDialog(this, "The password confirmation does not match.");
        }
        else if (password.length() < 9){
                JOptionPane.showMessageDialog(this, "Password must be more than 8 characters. ");
        }
        
        else if(ic.length() != 14){
            JOptionPane.showMessageDialog(this, "Invalid NRIC. ");
        }
        
        else if(!ic.contains("-")){
            JOptionPane.showMessageDialog(this, "Please follow the contact number format '000000-00-0000' ");
        }
        
        else {
            
            try {
                dContact = Double.parseDouble(contactNo.replace("-",""));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Contact number must be a numeric value.");
                return;
            }
            
            try {
                dIC = Double.parseDouble(ic.replace("-",""));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "NRIC must be a numeric value.");
                return;
            }
            ArrayList<String> userInfo = new ArrayList<String>();

            Collections.addAll(
                userInfo, "T-1", role.toLowerCase(), name, 
                gender, contactNo, email, ic, username, password
            );
            User user = role.equals("customer") ? new Customer(userInfo) : new Customer(userInfo);

            if (!user.isDuplicate()) {
                if (user.addToFile()) {
                    CarRental.loadUsers();
                    JOptionPane.showMessageDialog(this, "Registration successful");
                } else {
                    JOptionPane.showMessageDialog(this, "Registration unsuccessful - Something went wrong.");
                }
            } else {
                user = null; // Deleting it (by making it eligible for garbage collection)
                JOptionPane.showMessageDialog(this, "Registration unsuccessful - Duplication detected.");
            }
        }
    }
    
    private void txtRgtUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRgtUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRgtUsernameActionPerformed

    private void txtNRICActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNRICActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNRICActionPerformed

    private void txtContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContactActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void btnFemaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFemaleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFemaleActionPerformed

    private void btnSignUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignUpActionPerformed
        LoginFrame loginf = new LoginFrame();
        loginf.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnSignUpActionPerformed

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
            java.util.logging.Logger.getLogger(RegistrationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistrationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistrationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistrationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistrationFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfirm;
    private javax.swing.JRadioButton btnFemale;
    private javax.swing.JRadioButton btnMale;
    private javax.swing.JButton btnSignUp;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblConfirmPass;
    private javax.swing.JLabel lblContact;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblGender;
    private javax.swing.JLabel lblLoginAlert;
    private javax.swing.JLabel lblNRIC;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblRegistration;
    private javax.swing.JLabel lblRegistration1;
    private javax.swing.JLabel lblRgtPass;
    private javax.swing.JLabel lblRgtUsername;
    private javax.swing.JPanel panelRegistration;
    private javax.swing.JPasswordField passRgt;
    private javax.swing.JPasswordField passcRgt;
    private javax.swing.JTextField txtContact;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNRIC;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtRgtUsername;
    // End of variables declaration//GEN-END:variables
}
