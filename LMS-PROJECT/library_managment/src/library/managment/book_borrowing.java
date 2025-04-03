package library.managment;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.JOptionPane;

public class book_borrowing extends javax.swing.JFrame {

    private String uName;

    public book_borrowing(String userName) {
        initComponents();
        uName = userName;
        setLocationRelativeTo(null); // Center the window
    }

    private void initComponents() {

        jPanel1 = new javax.swing.JPanel(); // New JPanel
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField(); // New JTextField for publisher

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new Color(44, 62, 80)); // Set background color to [44, 62, 80]

        jLabel1.setFont(new java.awt.Font("Arial", Font.BOLD, 24)); // NOI18N
        jLabel1.setText("Book Borrowing");
        jLabel1.setForeground(new Color(255, 255, 255)); // Set text color

        jLabel2.setFont(new java.awt.Font("Arial", Font.PLAIN, 14)); // NOI18N
        jLabel2.setText("Search by bookName, bookId, authorName, or publisherName:");
        jLabel2.setForeground(new Color(255, 255, 255)); // Set text color

        jTextField1.setFont(new java.awt.Font("Arial", Font.PLAIN, 14)); // NOI18N
        jTextField1.setBackground(new Color(240, 240, 240)); // Set background color
        jTextField1.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0, 102, 204))); // Set border color

        jButton1.setText("Search");
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

        jButton3.setText("Borrow");
        jButton3.setFont(new java.awt.Font("Arial", Font.BOLD, 14)); // NOI18N
        jButton3.setBackground(new Color(0, 102, 204)); // Set background color
        jButton3.setForeground(new Color(255, 255, 255)); // Set text color
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", Font.PLAIN, 14)); // NOI18N
        jLabel3.setText("Borrowing Period:");
        jLabel3.setForeground(new Color(255, 255, 255)); // Set text color

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1 week", "2 weeks" }));
        jComboBox1.setFont(new java.awt.Font("Arial", Font.PLAIN, 14)); // NOI18N
        jComboBox1.setBackground(new Color(240, 240, 240)); // Set background color
        jComboBox1.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0, 102, 204))); // Set border color

        jLabel4.setFont(new java.awt.Font("Arial", Font.PLAIN, 14)); // NOI18N
        jLabel4.setText("bookName:");
        jLabel4.setForeground(new Color(255, 255, 255)); // Set text color

        jLabel5.setFont(new java.awt.Font("Arial", Font.PLAIN, 14)); // NOI18N
        jLabel5.setText("bookId:");
        jLabel5.setForeground(new Color(255, 255, 255)); // Set text color

        jLabel6.setFont(new java.awt.Font("Arial", Font.PLAIN, 14)); // NOI18N
        jLabel6.setText("authorName:");
        jLabel6.setForeground(new Color(255, 255, 255)); // Set text color

        jLabel7.setFont(new java.awt.Font("Arial", Font.PLAIN, 14)); // NOI18N
        jLabel7.setText("PublisherName:");
        jLabel7.setForeground(new Color(255, 255, 255)); // Set text color

        jTextField2.setFont(new java.awt.Font("Arial", Font.PLAIN, 14)); // NOI18N
        jTextField2.setBackground(new Color(240, 240, 240)); // Set background color
        jTextField2.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0, 102, 204))); // Set border color

        jTextField3.setFont(new java.awt.Font("Arial", Font.PLAIN, 14)); // NOI18N
        jTextField3.setBackground(new Color(240, 240, 240)); // Set background color
        jTextField3.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0, 102, 204))); // Set border color

        jTextField4.setFont(new java.awt.Font("Arial", Font.PLAIN, 14)); // NOI18N
        jTextField4.setBackground(new Color(240, 240, 240)); // Set background color
        jTextField4.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0, 102, 204))); // Set border color

        jTextField5.setFont(new java.awt.Font("Arial", Font.PLAIN, 14)); // NOI18N
        jTextField5.setBackground(new Color(240, 240, 240)); // Set background color
        jTextField5.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0, 102, 204))); // Set border color

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField2)
                            .addComponent(jTextField3)
                            .addComponent(jTextField4)
                            .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3)
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
        String searchQuery = jTextField1.getText();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBMS_Project", "root", "12345678");
            String sql = "SELECT * FROM lib_book WHERE bookName LIKE ? OR bookId LIKE ? OR authorName LIKE ? OR publisherName LIKE ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, "%" + searchQuery + "%");
            pst.setString(2, "%" + searchQuery + "%");
            pst.setString(3, "%" + searchQuery + "%");
            pst.setString(4, "%" + searchQuery + "%");
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                jTextField2.setText(rs.getString("bookName"));
                jTextField3.setText(rs.getString("bookId"));
                jTextField4.setText(rs.getString("authorName"));
                jTextField5.setText(rs.getString("publisherName"));
            } else {
                JOptionPane.showMessageDialog(null, "No book found.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        // Close the current frame and return to user_menu
        new user_menu(uName).setVisible(true);
        this.dispose();
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        String selectedBook = jTextField3.getText();
        if (selectedBook != null && !selectedBook.isEmpty()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBMS_Project", "root", "12345678");
                
                // Check if the book is already borrowed
                String checkSql = "SELECT * FROM borrowing_history WHERE bookId = ? ";
                PreparedStatement checkPst = con.prepareStatement(checkSql);
                checkPst.setString(1, selectedBook);
                ResultSet checkRs = checkPst.executeQuery();

                if (checkRs.next()) {
                    JOptionPane.showMessageDialog(null, "Book is already borrowed by another user.");
                    return;
                }

                String period = (String) jComboBox1.getSelectedItem();
                int days = period.equals("1 week") ? 7 : 14;
                String sql = "INSERT INTO borrowing_history (userName, bookId, dueDate) VALUES (?, ?, DATE_ADD(CURDATE(), INTERVAL ? DAY))";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, uName);
                pst.setString(2, selectedBook);
                pst.setInt(3, days);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Book borrowed successfully. Due date is in " + days + " days.");

                // Send email notification
                sendEmailNotification(uName, selectedBook, days);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please enter a valid book ID.");
        }
    }

    private void sendEmailNotification(String userName, String bookId, int days) {
        String to = getUserEmail(userName); // Get the user's email address
        String from = "briankuruui3768@gmail.com"; // Your library's email address
        String smtpHost = System.getenv("SMTP_HOST"); // Your SMTP server

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", smtpHost);
        properties.setProperty("mail.smtp.port", "587");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");

        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("briankuruui3768@gmail.com", " ");
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Book Borrowed Confirmation");
            message.setText("Dear " + userName + ",\n\nYou have successfully borrowed the book with ID: " + bookId + ".\nThe due date is in " + days + " days.\n\nThank you,\nYour Library");

            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    private String getUserEmail(String userName) {
        // Implement this method to get the user's email address from the student table
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBMS_Project", "root", "12345678");
            String sql = "SELECT email FROM student WHERE userName = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, userName);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getString("email");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new book_borrowing("defaultUser").setVisible(true);
            }
        });
    }

    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1; // New JPanel
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5; // New JTextField for publisher
}
