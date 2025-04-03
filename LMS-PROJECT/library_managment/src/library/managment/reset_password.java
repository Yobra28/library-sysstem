package library.managment;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class reset_password extends JFrame {

    private JTextField usernameField;
    private JPasswordField newPasswordField;

    public reset_password() {
        initComponents();
        setLocationRelativeTo(null); // Center the window
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the window
        setResizable(false); // Prevent resizing
        setTitle("Reset Password");
    }

    private void initComponents() {
        // Main panel with GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding
        mainPanel.setBackground(new Color(30, 30, 30)); // Smooth black background

        // GridBagConstraints for layout control
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Add spacing between components

        // Header label
        JLabel headerLabel = new JLabel("Reset Password");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Larger font for the header
        headerLabel.setForeground(new Color(255, 255, 255)); // White text
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span across two columns
        gbc.anchor = GridBagConstraints.CENTER; // Center align the header
        mainPanel.add(headerLabel, gbc);

        // Username label
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        usernameLabel.setForeground(new Color(200, 200, 200)); // Light gray text
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // Reset to one column
        gbc.anchor = GridBagConstraints.LINE_START; // Align to the start
        mainPanel.add(usernameLabel, gbc);

        // Username field
        usernameField = new JTextField(15);
        usernameField.setMaximumSize(new Dimension(300, 30));
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(usernameField, gbc);

        // New password label
        JLabel newPasswordLabel = new JLabel("New Password:");
        newPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        newPasswordLabel.setForeground(new Color(200, 200, 200)); // Light gray text
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(newPasswordLabel, gbc);

        // New password field
        newPasswordField = new JPasswordField(15);
        newPasswordField.setMaximumSize(new Dimension(300, 30));
        gbc.gridx = 1;
        gbc.gridy = 2;
        mainPanel.add(newPasswordField, gbc);

        // Reset button
        JButton resetButton = new JButton("Reset Password");
        resetButton.setFont(new Font("Arial", Font.BOLD, 14));
        resetButton.setBackground(new Color(0, 102, 204)); // Blue background
        resetButton.setForeground(Color.WHITE); // White text
        resetButton.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // Span across two columns
        gbc.anchor = GridBagConstraints.CENTER; // Center align the button
        mainPanel.add(resetButton, gbc);

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String newPassword = new String(newPasswordField.getPassword());
                resetPassword(username, newPassword);
            }
        });

        // Back button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(new Color(192, 57, 43)); // Red background
        backButton.setForeground(Color.WHITE); // White text
        backButton.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2; // Span across two columns
        gbc.anchor = GridBagConstraints.CENTER; // Center align the button
        mainPanel.add(backButton, gbc);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new user_login().setVisible(true); // Redirect to user_login
                dispose(); // Close the current window
            }
        });

        // Add main panel to the frame
        add(mainPanel);
    }

    private void resetPassword(String username, String newPassword) {
        if (username.isEmpty() || newPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Fields cannot be empty");
            return;
        }

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBMS_Project", "root", "");
            String sql = "UPDATE student SET password=? WHERE userName=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, newPassword);
            pst.setString(2, username);
            int rowsUpdated = pst.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Password reset successful!");
                new user_login().setVisible(true); // Redirect to user_login
                dispose(); // Close the current window
            } else {
                JOptionPane.showMessageDialog(this, "Username not found");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new reset_password().setVisible(true);
    }
}
