package library.managment;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class AdminLogin extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public AdminLogin() {
        initComponents();
        setLocationRelativeTo(null); // Center the window
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the window
    }

    private void initComponents() {
        // Main panel with GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(44, 62, 80)); // Dark background color
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding

        // GridBagConstraints for layout control
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Add spacing between components

        // Title label
        JLabel titleLabel = new JLabel("LIBRARY MANAGEMENT SYSTEM");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(255, 255, 255));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span across two columns
        gbc.anchor = GridBagConstraints.CENTER; // Center align the title
        panel.add(titleLabel, gbc);

        // Admin image
        JLabel adminImage = new JLabel(new ImageIcon("admin_icon.png")); // Replace with the path to your admin image
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2; // Span across two columns
        gbc.anchor = GridBagConstraints.CENTER; // Center align the image
        panel.add(adminImage, gbc);

        // Username label
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        usernameLabel.setForeground(new Color(255, 255, 255));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1; // Reset to one column
        gbc.anchor = GridBagConstraints.LINE_START; // Align to the start
        panel.add(usernameLabel, gbc);

        // Username field
        usernameField = new JTextField(15);
        usernameField.setMaximumSize(new Dimension(300, 30));
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(usernameField, gbc);

        // Password label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordLabel.setForeground(new Color(255, 255, 255));
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(passwordLabel, gbc);

        // Password field
        passwordField = new JPasswordField(15);
        passwordField.setMaximumSize(new Dimension(300, 30));
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(passwordField, gbc);

        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setBackground(new Color(0, 102, 204)); // Blue background
        loginButton.setForeground(Color.WHITE); // White text
        loginButton.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2; // Span across two columns
        gbc.anchor = GridBagConstraints.CENTER; // Center align the button
        panel.add(loginButton, gbc);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                adminLoginActionPerformed(evt);
            }
        });

        // Back button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBackground(new Color(192, 57, 43)); // Red background
        backButton.setForeground(Color.WHITE); // White text
        backButton.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2; // Span across two columns
        gbc.anchor = GridBagConstraints.CENTER; // Center align the button
        panel.add(backButton, gbc);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                new login().setVisible(true);
                dispose(); // Close the current window
            }
        });

        // Add panel to the frame
        add(panel);
        setTitle("Admin Login");
        setSize(500, 600); // Set the size of the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void adminLoginActionPerformed(ActionEvent evt) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Fields are empty");
            return;
        }

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBMS_Project", "root", "12345678");
            String sql = "SELECT * FROM admin WHERE userName=? AND password=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                new admin_menu().setVisible(true); // Redirect to AdminMenu
                dispose(); // Close the current window
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new AdminLogin().setVisible(true);
    }
}
