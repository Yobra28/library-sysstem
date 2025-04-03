package library.managment;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.CubicCurve2D;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.AbstractBorder;

public class user_login extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public user_login() {
        initComponents();
        setLocationRelativeTo(null); // Center the window
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the window to cover the whole screen
    }

    private void initComponents() {
        // Main panel with split layout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Left panel for login form
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(new Color(44, 62, 80)); // Dark background color

        // Title label
        JLabel titleLabel = new JLabel("LIBRARY MANAGEMENT SYSTEM");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36)); // Larger font for better visibility
        titleLabel.setForeground(new Color(255, 255, 255));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // Add spacing around the title

        // User image
        JLabel userImage = new JLabel(new ImageIcon("user_icon.png")); // Replace with the path to your user image
        userImage.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Username field with label
        usernameField = new JTextField(15);
        JLayeredPane usernamePanel = createLabeledField(usernameField, "Username");

        // Password field with label
        passwordField = new JPasswordField(15);
        JLayeredPane passwordPanel = createLabeledField(passwordField, "Password");

        // Buttons panel (horizontal arrangement)
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Horizontal layout with spacing
        buttonsPanel.setBackground(new Color(44, 62, 80)); // Match the background color

        // Login button
        JButton loginButton = createMenuButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                userLoginActionPerformed(evt);
            }
        });

        // Create Account button
        JButton createAccountButton = createMenuButton("Create Account");
        createAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                new registration().setVisible(true);
                dispose(); // Close the current window
            }
        });

        // Forgot Password button
        JButton forgotPasswordButton = createMenuButton("Forgot Password?");
        forgotPasswordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                new reset_password().setVisible(true);
                dispose(); // Close the current window
            }
        });

        // Back button
        JButton backButton = createMenuButton("Back");
        backButton.setBackground(new Color(192, 57, 43)); // Red background for back button
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                new login().setVisible(true);
                dispose(); // Close the current window
            }
        });

        // Add buttons to the horizontal panel
        buttonsPanel.add(loginButton);
        buttonsPanel.add(createAccountButton);
        buttonsPanel.add(forgotPasswordButton);
        buttonsPanel.add(backButton);

        // Add components to the left panel
        leftPanel.add(titleLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add vertical spacing
        leftPanel.add(userImage);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add vertical spacing
        leftPanel.add(usernamePanel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add vertical spacing
        leftPanel.add(passwordPanel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add vertical spacing
        leftPanel.add(buttonsPanel); // Add the horizontal buttons panel

        // Right panel with background image and S-shape
        JPanel rightPanel = new SShapeBackgroundPanel("C:\\Users\\user\\OneDrive\\Desktop\\LMS-PROJECT JAVA\\HKSLibrary_SilentStudy_7_1MB.jpg"); // Replace with the path to your background image

        // Add panels to the main panel
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.CENTER);

        // Add main panel to the frame
        add(mainPanel);
        setTitle("User Login");
        setSize(800, 500); // Adjust the size of the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(0, 102, 204)); // Blue background
        button.setForeground(Color.WHITE); // White text
        button.setFocusPainted(false);
        button.setBorder(new RoundedBorder(3)); // Set rounded border
        return button;
    }

    private void userLoginActionPerformed(ActionEvent evt) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Fields are empty");
            return;
        }

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBMS_Project", "root", "12345678");
            String sql = "SELECT * FROM student WHERE userName=? AND password=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                new user_menu(username).setVisible(true); // Redirect to UserMenu
                dispose(); // Close the current window
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new user_login().setVisible(true);
    }

    // Add username and password labels behind the fields
    private JLayeredPane createLabeledField(JTextField field, String labelText) {
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(300, 40));

        // Label (background text)
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setForeground(new Color(150, 150, 150)); // Light gray color for the label
        label.setBounds(10, 10, 280, 20); // Position the label inside the layered pane

        // Text field
        field.setBounds(0, 0, 300, 40);
        field.setOpaque(false); // Make the field transparent to show the label behind
        field.setBorder(new RoundedBorder(3)); // Rounded border for the field
        field.setForeground(Color.WHITE); // Set text color to white
        field.setCaretColor(Color.WHITE); // Set caret (cursor) color to white

        // Add a DocumentListener to monitor text changes
        field.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                updateLabelVisibility();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                updateLabelVisibility();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                updateLabelVisibility();
            }

            private void updateLabelVisibility() {
                label.setVisible(field.getText().isEmpty()); // Show label if the field is empty
            }
        });

        // Add components to the layered pane
        layeredPane.add(label, Integer.valueOf(1)); // Add label at the bottom layer
        layeredPane.add(field, Integer.valueOf(2)); // Add field on top of the label

        return layeredPane;
    }

    // Custom JPanel for background image and S-shape
    private static class SShapeBackgroundPanel extends JPanel {
        private final Image backgroundImage;

        public SShapeBackgroundPanel(String imagePath) {
            this.backgroundImage = new ImageIcon(imagePath).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;

            // Draw the background image
            g2.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

            // Draw the S-shape
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(255, 255, 255, 150)); // Semi-transparent white
            CubicCurve2D sShape = new CubicCurve2D.Double(0, 0, getWidth() / 2.0, getHeight() / 3.0, getWidth() / 2.0,
                    2 * getHeight() / 3.0, getWidth(), getHeight());
            g2.fill(sShape);
        }
    }

    // Custom border class for rounded corners
    private static class RoundedBorder extends AbstractBorder {
        private final int radius;

        public RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.GRAY);
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(4, 4, 4, 4);
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.left = insets.right = insets.top = insets.bottom = 4;
            return insets;
        }
    }
}
