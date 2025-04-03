package library.managment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class RoundedTextField extends JTextField {
    private int radius;

    public RoundedTextField(int radius) {
        super();
        this.radius = radius;
        setOpaque(false); // Make the background transparent
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the rounded background
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

        // Draw the border
        g2.setColor(getForeground());
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);

        g2.dispose();
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Do nothing (border is handled in paintComponent)
    }
}

class RoundedPasswordField extends JPasswordField {
    private int radius;

    public RoundedPasswordField(int radius) {
        super();
        this.radius = radius;
        setOpaque(false); // Make the background transparent
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the rounded background
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

        // Draw the border
        g2.setColor(getForeground());
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);

        g2.dispose();
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Do nothing (border is handled in paintComponent)
    }
}

public class registration extends javax.swing.JFrame {

    private javax.swing.JLabel animatedLabel; // Label for animation

    public registration() {
        initComponents();
        setLocationRelativeTo(null); // Center the window
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the window to cover the whole screen
        startTextAnimation(); // Start text animation
    }

    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel(); // Email label
        jTextField1 = new RoundedTextField(15);
        jTextField2 = new RoundedTextField(15);
        jTextField3 = new RoundedTextField(15);
        jTextField4 = new RoundedTextField(15);
        jTextField5 = new RoundedTextField(15); // Email text field
        jPasswordField1 = new RoundedPasswordField(15);
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        // Set panel background color
        jPanel1.setBackground(new Color(44, 62, 80));

        // Set labels
        jLabel1.setFont(new java.awt.Font("Arial", Font.BOLD, 18)); // Increase font size
        jLabel1.setText("First Name");
        jLabel1.setForeground(new Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Arial", Font.BOLD, 18));
        jLabel2.setText("Last Name");
        jLabel2.setForeground(new Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Arial", Font.BOLD, 18));
        jLabel3.setText("Reg No");
        jLabel3.setForeground(new Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Arial", Font.BOLD, 18));
        jLabel4.setText("Username");
        jLabel4.setForeground(new Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Arial", Font.BOLD, 18));
        jLabel5.setText("Password");
        jLabel5.setForeground(new Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Arial", Font.BOLD, 18));
        jLabel6.setText("Email");
        jLabel6.setForeground(new Color(255, 255, 255));

        // Set buttons
        jButton1.setText("Register");
        jButton1.setFont(new java.awt.Font("Arial", Font.BOLD, 18)); // Increase font size
        jButton1.setBackground(new Color(0, 102, 204));
        jButton1.setForeground(new Color(255, 255, 255));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Back to Login");
        jButton2.setFont(new java.awt.Font("Arial", Font.BOLD, 15)); // Increase font size
        jButton2.setBackground(new Color(0, 102, 204));
        jButton2.setForeground(new Color(255, 255, 255));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        // Use GroupLayout for alignment
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                    .addGap(50, 50, 50)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(50, 50, 50)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(50, 50, 50)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(50, 50, 50)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(50, 50, 50)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(50, 50, 50)
                            .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(50, 50, 50)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(100, 100, 100)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(50, 50, 50)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1);
        pack();
    }

    private void startTextAnimation() {
        Timer timer = new Timer(100, new ActionListener() { // Update every 100ms
            private int red = 0;
            private int green = 102;
            private int blue = 204;
            private boolean increasing = true;

            @Override
            public void actionPerformed(ActionEvent e) {
                // Change the color of the text
                if (increasing) {
                    red += 5;
                    green += 5;
                    if (red >= 255 || green >= 255) {
                        increasing = false;
                    }
                } else {
                    red -= 5;
                    green -= 5;
                    if (red <= 0 || green <= 102) {
                        increasing = true;
                    }
                }
                animatedLabel.setForeground(new Color(red, green, blue));
            }
        });
        timer.start(); // Start the timer
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        String firstName = jTextField1.getText();
        String lastName = jTextField2.getText();
        String regNo = jTextField3.getText();
        String userName = jTextField4.getText();
        String password = new String(jPasswordField1.getPassword());
        String email = jTextField5.getText(); // Get email from text field

        // Validate input fields
        if (firstName.isEmpty() || lastName.isEmpty() || regNo.isEmpty() || userName.isEmpty() || password.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Fields are empty");
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBMS_Project", "root", "");
            String sql = "INSERT INTO student (firstName, lastName, regNo, userName, password, email) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, firstName);
            pst.setString(2, lastName);
            pst.setString(3, regNo);
            pst.setString(4, userName);
            pst.setString(5, password);
            pst.setString(6, email); // Set email in prepared statement
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registration Successful");
            new login().setVisible(true);
            this.setVisible(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        new login().setVisible(true);
        this.setVisible(false);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new registration().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6; // Email label
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5; // Email text field
    private javax.swing.JPanel jPanel1;
}