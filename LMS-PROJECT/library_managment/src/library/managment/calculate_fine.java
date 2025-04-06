package library.managment;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class calculate_fine extends javax.swing.JFrame {

    private String uName;

    public calculate_fine(String userName) {
        initComponents();
        uName = userName;
        setLocationRelativeTo(null); // Center the window
    }

    private void initComponents() {

        jLabelHeader = new javax.swing.JLabel(); // New label for header
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton(); // New close button
        jLabelImage = new javax.swing.JLabel(); // New label for image
        jPanel1 = new javax.swing.JPanel(); // New JPanel

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new Color(44, 62, 80)); // Set background color to [44, 62, 80]

        jLabelHeader.setFont(new java.awt.Font("Arial", Font.BOLD, 28)); // NOI18N
        jLabelHeader.setText("CALCULATE FINE");
        jLabelHeader.setForeground(new Color(255, 255, 255)); // Set text color to white
        jLabelHeader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER); // Center the text

        jLabel1.setFont(new java.awt.Font("Arial", Font.BOLD, 18)); // NOI18N
        jLabel1.setText("Calculate Fine");
        jLabel1.setForeground(new Color(255, 255, 255)); // Set text color to white

        jLabel2.setFont(new java.awt.Font("Arial", Font.PLAIN, 14)); // NOI18N
        jLabel2.setText("User Name:");
        jLabel2.setForeground(new Color(255, 255, 255)); // Set text color to white

        jLabel3.setFont(new java.awt.Font("Arial", Font.PLAIN, 14)); // NOI18N
        jLabel3.setText("Book ID:");
        jLabel3.setForeground(new Color(255, 255, 255)); // Set text color to white

        jTextField1.setFont(new java.awt.Font("Arial", Font.PLAIN, 14)); // NOI18N
        jTextField1.setBackground(new Color(240, 240, 240)); // Set background color
        jTextField1.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0, 102, 204))); // Set border color

        jTextField2.setFont(new java.awt.Font("Arial", Font.PLAIN, 14)); // NOI18N
        jTextField2.setBackground(new Color(240, 240, 240)); // Set background color
        jTextField2.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0, 102, 204))); // Set border color

        jButton1.setText("Calculate");
        jButton1.setFont(new java.awt.Font("Arial", Font.BOLD, 14)); // NOI18N
        jButton1.setBackground(new Color(0, 102, 204)); // Set background color
        jButton1.setForeground(new Color(255, 255, 255)); // Set text color
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Close");
        jButton2.setFont(new java.awt.Font("Arial", Font.BOLD, 14)); // NOI18N
        jButton2.setBackground(new Color(0, 102, 204)); // Set background color
        jButton2.setForeground(new Color(255, 255, 255)); // Set text color
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        // Set image icon
        ImageIcon icon = new ImageIcon("path/to/your/image.png");
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        jLabelImage.setIcon(new ImageIcon(scaledImg));
        jLabelImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER); // Center the image

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelImage, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE) // Add image label
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE) // Add header label
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField1)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2)))))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE) // Add header label
                .addGap(10, 10, 10)
                .addComponent(jLabelImage, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE) // Add image label
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(50, Short.MAX_VALUE))
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
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        String userName = jTextField1.getText();
        String bookId = jTextField2.getText();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBMS_Project", "root", "12345678");

            // Query to calculate overdue days and fetch due date and regNo
            String sql = "SELECT DATEDIFF(CURDATE(), bh.dueDate) AS daysOverdue, bh.dueDate, s.regNo " +
                         "FROM borrowing_history bh " +
                         "JOIN student s ON bh.userName = s.userName " +
                         "WHERE bh.userName = ? AND bh.bookId = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, userName);
            pst.setString(2, bookId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                int daysOverdue = rs.getInt("daysOverdue");
                String dueDate = rs.getDate("dueDate").toString();
                String regNo = rs.getString("regNo");

                if (daysOverdue > 0) {
                    int fine = daysOverdue * 10; // Assuming fine is 10 units per day
                    int option = JOptionPane.showConfirmDialog(null, "Fine for " + userName + " (Reg No: " + regNo + ") is " + fine + " Kshs. Do you accept the fine?", "Accept Fine", JOptionPane.YES_NO_OPTION);

                    if (option == JOptionPane.YES_OPTION) {
                        // Insert fine details into the fines table
                        String insertFineSql = "INSERT INTO fines (userName, bookId, dueDate, daysOverdue, fine) VALUES (?, ?, ?, ?, ?)";
                        PreparedStatement insertPst = con.prepareStatement(insertFineSql);
                        insertPst.setString(1, userName);
                        insertPst.setString(2, bookId);
                        insertPst.setString(3, dueDate);
                        insertPst.setInt(4, daysOverdue);
                        insertPst.setInt(5, fine);

                        int rowsInserted = insertPst.executeUpdate();
                        if (rowsInserted > 0) {
                            JOptionPane.showMessageDialog(null, "Fine accepted and recorded successfully.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Failed to record the fine.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Fine not accepted.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No fine for " + userName);
                }
            } else {
                JOptionPane.showMessageDialog(null, "No record found for " + userName + " and Book ID " + bookId);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        // Close the current frame and return to user_menu
        new user_menu(uName).setVisible(true);
        this.dispose();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new calculate_fine("defaultUser").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2; // New close button
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelHeader; // New label for header
    private javax.swing.JLabel jLabelImage; // New label for image
    private javax.swing.JPanel jPanel1; // New JPanel
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration
}
