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
        panelLogin = new javax.swing.JPanel();
        lblLoginAlert = new javax.swing.JLabel();
        lblLogin = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        lblPass = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        passLogin = new javax.swing.JPasswordField();
        txtUsername1 = new javax.swing.JTextField();
        txtUsername2 = new javax.swing.JTextField();
        txtUsername3 = new javax.swing.JTextField();
        txtUsername4 = new javax.swing.JTextField();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        lblUsername1 = new javax.swing.JLabel();
        lblUsername2 = new javax.swing.JLabel();
        lblUsername3 = new javax.swing.JLabel();
        lblUsername4 = new javax.swing.JLabel();
        lblUsername5 = new javax.swing.JLabel();
        passLogin1 = new javax.swing.JPasswordField();
        lblPass1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelLogin.setBackground(new java.awt.Color(153, 255, 204));

        lblLoginAlert.setBackground(new java.awt.Color(255, 204, 204));
        lblLoginAlert.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        lblLoginAlert.setForeground(new java.awt.Color(102, 102, 102));
        lblLoginAlert.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLoginAlert.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));

        lblLogin.setBackground(new java.awt.Color(245, 245, 245));
        lblLogin.setFont(new java.awt.Font("Poppins", 1, 36)); // NOI18N
        lblLogin.setForeground(new java.awt.Color(51, 51, 51));
        lblLogin.setText("Registration");

        lblUsername.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(102, 102, 102));
        lblUsername.setText("Name as per IC");

        txtUsername.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        txtUsername.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtUsername.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsernameActionPerformed(evt);
            }
        });

        lblPass.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        lblPass.setForeground(new java.awt.Color(102, 102, 102));
        lblPass.setText("Username");

        btnLogin.setBackground(new java.awt.Color(0, 153, 153));
        btnLogin.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("Confirm");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        passLogin.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        passLogin.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));

        txtUsername1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        txtUsername1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtUsername1.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtUsername1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsername1ActionPerformed(evt);
            }
        });

        txtUsername2.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        txtUsername2.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtUsername2.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtUsername2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsername2ActionPerformed(evt);
            }
        });

        txtUsername3.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        txtUsername3.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtUsername3.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtUsername3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsername3ActionPerformed(evt);
            }
        });

        txtUsername4.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        txtUsername4.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtUsername4.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtUsername4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsername4ActionPerformed(evt);
            }
        });

        jRadioButton1.setText("Male");

        jRadioButton2.setText("Female");

        lblUsername1.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        lblUsername1.setForeground(new java.awt.Color(102, 102, 102));
        lblUsername1.setText("Contact No.");

        lblUsername2.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        lblUsername2.setForeground(new java.awt.Color(102, 102, 102));
        lblUsername2.setText("Gender");

        lblUsername3.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        lblUsername3.setForeground(new java.awt.Color(102, 102, 102));
        lblUsername3.setText("Email");

        lblUsername4.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        lblUsername4.setForeground(new java.awt.Color(102, 102, 102));
        lblUsername4.setText("Password");

        lblUsername5.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        lblUsername5.setForeground(new java.awt.Color(102, 102, 102));
        lblUsername5.setText("Confirm Password");

        passLogin1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        passLogin1.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));

        lblPass1.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        lblPass1.setForeground(new java.awt.Color(102, 102, 102));
        lblPass1.setText("NRIC");

        javax.swing.GroupLayout panelLoginLayout = new javax.swing.GroupLayout(panelLogin);
        panelLogin.setLayout(panelLoginLayout);
        panelLoginLayout.setHorizontalGroup(
            panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoginLayout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLoginLayout.createSequentialGroup()
                        .addComponent(passLogin1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelLoginLayout.createSequentialGroup()
                        .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelLoginLayout.createSequentialGroup()
                                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(panelLoginLayout.createSequentialGroup()
                                        .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtUsername3, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblUsername1))
                                        .addGap(18, 18, 18)
                                        .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblUsername3)
                                            .addComponent(txtUsername4, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(lblUsername)
                                    .addGroup(panelLoginLayout.createSequentialGroup()
                                        .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(passLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblPass)
                                            .addComponent(lblPass1))
                                        .addGap(18, 18, 18)
                                        .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblUsername2)
                                            .addComponent(txtUsername1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblUsername4))
                                        .addGap(18, 18, 18)
                                        .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblUsername5)
                                            .addComponent(txtUsername2, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(lblLoginAlert, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(110, 110, 110))))
            .addGroup(panelLoginLayout.createSequentialGroup()
                .addGap(238, 238, 238)
                .addComponent(lblLogin)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelLoginLayout.setVerticalGroup(
            panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoginLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(lblLogin)
                .addGap(18, 18, 18)
                .addComponent(lblLoginAlert, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsername)
                    .addComponent(lblUsername1)
                    .addComponent(lblUsername3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsername3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsername4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPass)
                    .addComponent(lblUsername4)
                    .addComponent(lblUsername5))
                .addGap(18, 18, 18)
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsername1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsername2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPass1)
                    .addComponent(lblUsername2))
                .addGap(18, 18, 18)
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(passLogin1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jRadioButton1)
                        .addComponent(jRadioButton2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(btnLogin)
                .addGap(40, 40, 40))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 720, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsernameActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        String username = txtUsername.getText().trim().toLowerCase();
        String password = new String(passLogin.getPassword());

        // Check staff credentials
        if (checkCredentials(username, password)) {
            MainFrame mainf = new MainFrame(); // Call main page frame
            mainf.setVisible(true);
            this.setVisible(false); // Close login frame
        } else { // Wrong credentials
            lblLoginAlert.setOpaque(true);
            lblLoginAlert.setText("Invalid staff credentials.");
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void txtUsername1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsername1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsername1ActionPerformed

    private void txtUsername2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsername2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsername2ActionPerformed

    private void txtUsername3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsername3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsername3ActionPerformed

    private void txtUsername4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsername4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsername4ActionPerformed

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
    private javax.swing.JButton btnLogin;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JLabel lblLogin;
    private javax.swing.JLabel lblLoginAlert;
    private javax.swing.JLabel lblPass;
    private javax.swing.JLabel lblPass1;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JLabel lblUsername1;
    private javax.swing.JLabel lblUsername2;
    private javax.swing.JLabel lblUsername3;
    private javax.swing.JLabel lblUsername4;
    private javax.swing.JLabel lblUsername5;
    private javax.swing.JPanel panelLogin;
    private javax.swing.JPasswordField passLogin;
    private javax.swing.JPasswordField passLogin1;
    private javax.swing.JTextField txtUsername;
    private javax.swing.JTextField txtUsername1;
    private javax.swing.JTextField txtUsername2;
    private javax.swing.JTextField txtUsername3;
    private javax.swing.JTextField txtUsername4;
    // End of variables declaration//GEN-END:variables
}
