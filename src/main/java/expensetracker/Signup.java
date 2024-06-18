/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package expensetracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author anushagonal
 */
public class Signup extends javax.swing.JFrame {

    /**
     * Creates new form Signup
     */
    public Signup() {
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

        name_label = new javax.swing.JLabel();
        enter_username = new javax.swing.JTextField();
        name_label2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Enter_btn = new javax.swing.JButton();
        enter_password = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        name_label.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        name_label.setText("Username : ");

        enter_username.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        enter_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enter_usernameActionPerformed(evt);
            }
        });

        name_label2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        name_label2.setText("Password : ");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("SIGN UP PANEL");

        Enter_btn.setText("Submit");
        Enter_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Enter_btnActionPerformed(evt);
            }
        });

        enter_password.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        enter_password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enter_passwordActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(name_label2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(name_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(enter_username, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(enter_password, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(92, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Enter_btn)
                .addGap(186, 186, 186))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(enter_username)
                    .addComponent(name_label, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(name_label2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(enter_password))
                .addGap(51, 51, 51)
                .addComponent(Enter_btn)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void enter_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enter_usernameActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_enter_usernameActionPerformed

    private void Enter_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Enter_btnActionPerformed
        // TODO add your handling code here:
        if(enter_username.getText().trim().isEmpty() || enter_password.getText().trim().isEmpty() ){
            JOptionPane.showMessageDialog(this, "ALL CREDENTIALS IMPORTANT FILL THEM!!");
        }
        else{
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String db = "jdbc:mysql://localhost:3306/expensetracker";
                String username = "root";
                String password = "";
                
                Connection con = DriverManager.getConnection(db, username, password); //here also exception handling will be done
                // Check if the username already exists
                String checkQuery = "SELECT COUNT(*) FROM user_detail WHERE username = ?";
                PreparedStatement checkPst = con.prepareStatement(checkQuery);
                checkPst.setString(1, enter_username.getText());
                ResultSet rs = checkPst.executeQuery();
                
                if (rs.next() && rs.getInt(1) > 0) {
                    JOptionPane.showMessageDialog(this, "Username already exists!");
                } else {
                    // Insert the new user if username does not exist
                    String query = "INSERT INTO user_detail (username, password) VALUES (?, ?)";
                    PreparedStatement pst = con.prepareStatement(query);
                    pst.setString(1, enter_username.getText());
                    pst.setString(2, enter_password.getText());

                    int count = pst.executeUpdate();
                    if (count > 0) {
                        JOptionPane.showMessageDialog(this, "Data inserted successfully!!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Data insertion FAILED!!");
                    }
                }
                //following SQL query for entering name and gender
                
                con.close();//connection needs to be close
                
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Signup.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Signup.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_Enter_btnActionPerformed

    private void enter_passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enter_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_enter_passwordActionPerformed

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
            java.util.logging.Logger.getLogger(Signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Signup().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Enter_btn;
    private javax.swing.JTextField enter_password;
    private javax.swing.JTextField enter_username;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel name_label;
    private javax.swing.JLabel name_label2;
    // End of variables declaration//GEN-END:variables
}
