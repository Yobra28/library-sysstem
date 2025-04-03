package library.managment;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class login extends JFrame {

    private JLabel footerLabel;
    private int footerTextPosition;

    public login() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the window to cover the whole screen
        setLocationRelativeTo(null); // Center the window
        startFooterAnimation(); // Start the footer animation
    }

    private void initComponents() {
        // Main panel with background image
        JPanel panel = new BackgroundPanel("C:\\Users\\user\\Downloads\\images.png"); // Replace with the path to your background image
        panel.setLayout(new BorderLayout());

        // Title label
        JLabel titleLabel = new JLabel("LIBRARY MANAGEMENT SYSTEM");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 60)); // Larger font for better visibility
        titleLabel.setForeground(new Color(255, 255, 255));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Admin section (image + button)
        JPanel adminPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)); // FlowLayout for image and button
        adminPanel.setOpaque(false); // Make the panel transparent
        JLabel adminImageLabel = new JLabel(resizeIcon("C:\\Users\\user\\OneDrive\\Desktop\\LMS-PROJECT JAVA\\download2.png", 80, 80)); // Replace with the path to your admin image
        RoundedButton adminLoginButton = new RoundedButton("Admin Login");
        adminLoginButton.setFont(new Font("Arial", Font.BOLD, 20));
        adminLoginButton.setBackground(new Color(0, 102, 204));
        adminLoginButton.setForeground(Color.WHITE);
        adminLoginButton.setFocusPainted(false);
        adminLoginButton.setPreferredSize(new Dimension(200, 50)); // Set button size
        adminLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                new AdminLogin().setVisible(true);
                dispose(); // Close the current window
            }
        });
        adminPanel.add(adminImageLabel);
        adminPanel.add(adminLoginButton);

        // User section (image + button)
        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)); // FlowLayout for image and button
        userPanel.setOpaque(false); // Make the panel transparent
        JLabel userImageLabel = new JLabel(resizeIcon("C:\\Users\\user\\OneDrive\\Desktop\\LMS-PROJECT JAVA\\download.jpeg", 80, 80)); // Replace with the path to your user image
        RoundedButton userLoginButton = new RoundedButton("User Login");
        userLoginButton.setFont(new Font("Arial", Font.BOLD, 20));
        userLoginButton.setBackground(new Color(0, 102, 204));
        userLoginButton.setForeground(Color.WHITE);
        userLoginButton.setFocusPainted(false);
        userLoginButton.setPreferredSize(new Dimension(200, 50)); // Set button size
        userLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                new user_login().setVisible(true);
                dispose(); // Close the current window
            }
        });
        userPanel.add(userImageLabel);
        userPanel.add(userLoginButton);

        // Center panel for admin and user sections
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 20));
        centerPanel.setOpaque(false);
        centerPanel.add(adminPanel);
        centerPanel.add(userPanel);

        // Footer label
        footerLabel = new JLabel("Welcome to Chuka Online Library Reservation System");
        footerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        footerLabel.setForeground(new Color(255, 255, 255));
        footerLabel.setHorizontalAlignment(SwingConstants.LEFT); // Align text to the left
        footerLabel.setBounds(0, 0, 1000, 30); // Set initial bounds for animation

        // Footer panel
        JPanel footerPanel = new JPanel(null); // Use null layout for custom positioning
        footerPanel.setPreferredSize(new Dimension(getWidth(), 40));
        footerPanel.setOpaque(false); // Transparent background
        footerPanel.add(footerLabel);

        // Add components to the panel
        panel.add(titleLabel, BorderLayout.NORTH); // Add title at the top
        panel.add(centerPanel, BorderLayout.CENTER); // Add admin and user sections in the center
        panel.add(footerPanel, BorderLayout.SOUTH); // Add footer at the bottom

        // Add panel to the frame
        add(panel);
        setTitle("Library Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void startFooterAnimation() {
        footerTextPosition = getWidth(); // Start the text from the right edge of the frame
        Timer timer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                footerTextPosition -= 5; // Move the text to the left
                if (footerTextPosition + footerLabel.getPreferredSize().width < 0) {
                    footerTextPosition = getWidth(); // Reset the position when the text goes off-screen
                }
                footerLabel.setLocation(footerTextPosition, footerLabel.getY());
            }
        });
        timer.start();
    }

    // Custom JPanel to draw the background image
    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            try {
                backgroundImage = new ImageIcon(imagePath).getImage();
            } catch (Exception e) {
                System.err.println("Error loading background image: " + e.getMessage());
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    // Custom JButton with rounded corners
    class RoundedButton extends JButton {
        public RoundedButton(String text) {
            super(text);
            setContentAreaFilled(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // Rounded corners with 20-pixel radius
            super.paintComponent(g);
            g2.dispose();
        }

        @Override
        protected void paintBorder(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getForeground());
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20); // Rounded corners with 20-pixel radius
            g2.dispose();
        }

        @Override
        public Insets getInsets() {
            return new Insets(10, 20, 10, 20); // Add padding inside the button
        }
    }

    // Method to resize an icon
    private ImageIcon resizeIcon(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

    public static void main(String[] args) {
        new login().setVisible(true);
    }
}
