/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package carrental;

/**
 *
 * @author vinie
 */
public class RegistrationFrame extends javax.swing.JFrame {

    /**
     * Creates new form RegistrationFrame
     */
    public RegistrationFrame() {
        initComponents();
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
        txtRgtUsername = new javax.swing.JPasswordField();
        txtRgtPass = new javax.swing.JTextField();
        txtConfirmPass = new javax.swing.JTextField();
        txtContact = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        btnMale = new javax.swing.JRadioButton();
        btnFemale = new javax.swing.JRadioButton();
        lblContact = new javax.swing.JLabel();
        lblGender = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblRgtPass = new javax.swing.JLabel();
        lblConfirmPass = new javax.swing.JLabel();
        txtNRIC = new javax.swing.JPasswordField();
        lblNRIC = new javax.swing.JLabel();
        lblRegistration1 = new javax.swing.JLabel();

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

        txtRgtUsername.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        txtRgtUsername.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));

        txtRgtPass.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        txtRgtPass.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtRgtPass.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtRgtPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRgtPassActionPerformed(evt);
            }
        });

        txtConfirmPass.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        txtConfirmPass.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtConfirmPass.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtConfirmPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtConfirmPassActionPerformed(evt);
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

        buttonGroup1.add(btnMale);
        btnMale.setForeground(new java.awt.Color(255, 255, 255));
        btnMale.setText("Male");

        buttonGroup1.add(btnFemale);
        btnFemale.setForeground(new java.awt.Color(255, 255, 255));
        btnFemale.setText("Female");

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

        txtNRIC.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        txtNRIC.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));

        lblNRIC.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        lblNRIC.setForeground(new java.awt.Color(255, 255, 255));
        lblNRIC.setText("NRIC");

        lblRegistration1.setBackground(new java.awt.Color(245, 245, 245));
        lblRegistration1.setFont(new java.awt.Font("Poppins", 3, 24)); // NOI18N
        lblRegistration1.setForeground(new java.awt.Color(255, 204, 102));
        lblRegistration1.setText("RapidCar");

        javax.swing.GroupLayout panelRegistrationLayout = new javax.swing.GroupLayout(panelRegistration);
        panelRegistration.setLayout(panelRegistrationLayout);
        panelRegistrationLayout.setHorizontalGroup(
            panelRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRegistrationLayout.createSequentialGroup()
                .addGap(239, 239, 239)
                .addComponent(lblRegistration)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panelRegistrationLayout.createSequentialGroup()
                .addGroup(panelRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRegistrationLayout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addGroup(panelRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelRegistrationLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(panelRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelRegistrationLayout.createSequentialGroup()
                                        .addComponent(txtNRIC, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnMale)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnFemale))
                                    .addGroup(panelRegistrationLayout.createSequentialGroup()
                                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(panelRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtContact, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblContact))
                                        .addGap(18, 18, 18)
                                        .addGroup(panelRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblEmail)
                                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(lblName)
                                    .addGroup(panelRegistrationLayout.createSequentialGroup()
                                        .addGroup(panelRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtRgtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblRgtUsername)
                                            .addComponent(lblNRIC))
                                        .addGap(18, 18, 18)
                                        .addGroup(panelRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblGender)
                                            .addComponent(txtRgtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblRgtPass))
                                        .addGap(18, 18, 18)
                                        .addGroup(panelRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblConfirmPass)
                                            .addComponent(txtConfirmPass, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(lblLoginAlert, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelRegistrationLayout.createSequentialGroup()
                        .addGap(181, 181, 181)
                        .addComponent(btnConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(99, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRegistrationLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblRegistration1)
                .addGap(15, 15, 15))
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
                    .addComponent(lblEmail))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(panelRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRgtUsername)
                    .addComponent(lblRgtPass)
                    .addComponent(lblConfirmPass))
                .addGap(18, 18, 18)
                .addGroup(panelRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRgtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRgtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtConfirmPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNRIC)
                    .addComponent(lblGender))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNRIC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnMale)
                        .addComponent(btnFemale)))
                .addGap(34, 34, 34)
                .addComponent(btnConfirm)
                .addGap(18, 18, 18)
                .addComponent(lblRegistration1)
                .addContainerGap(19, Short.MAX_VALUE))
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

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        String username = txtName.getText().trim().toLowerCase();
        String password = new String(txtRgtUsername.getPassword());

        // Check staff credentials
        // if (checkCredentials(username, password)) {
        //     MainFrame mainf = new MainFrame(); // Call main page frame
        //     mainf.setVisible(true);
        //     this.setVisible(false); // Close login frame
        // } else { // Wrong credentials
        //     lblLoginAlert.setOpaque(true);
        //     lblLoginAlert.setText("Invalid staff credentials.");
        // }
    }//GEN-LAST:event_btnConfirmActionPerformed

    private void txtRgtPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRgtPassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRgtPassActionPerformed

    private void txtConfirmPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtConfirmPassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtConfirmPassActionPerformed

    private void txtContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContactActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

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
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
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
    private javax.swing.JTextField txtConfirmPass;
    private javax.swing.JTextField txtContact;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JPasswordField txtNRIC;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtRgtPass;
    private javax.swing.JPasswordField txtRgtUsername;
    // End of variables declaration//GEN-END:variables
}
