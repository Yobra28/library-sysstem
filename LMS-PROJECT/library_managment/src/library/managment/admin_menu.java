package library.managment;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class admin_menu extends javax.swing.JFrame {

    private JPanel jPanelMenu;
    private JPanel jPanelContent;
    private JSplitPane splitPane;

    public admin_menu() {
        initComponents();
        setLocationRelativeTo(null); // Center the window
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the window
    }

    private void initComponents() {
        jPanelMenu = new JPanel();
        jPanelContent = new JPanel();
        splitPane = new JSplitPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        // Menu Panel (Left Side)
        jPanelMenu.setBackground(new Color(44, 62, 80)); // Dark background
        jPanelMenu.setLayout(new GridLayout(8, 1, 10, 10)); // Grid layout with 8 rows, 1 column, and spacing

        // Add menu buttons
        addMenuButton("Add Book", evt -> create_book());
        addMenuButton("Generate User Report", evt -> generateUserReport());
        addMenuButton("Generate Study Space Report", evt -> generateStudySpaceReport());
        addMenuButton("Generate Borrowed Books Report", evt -> generateBorrowedBooksReport());
        addMenuButton("Generate Fine Report", evt -> generateFineReport());
        addMenuButton("Delete Damaged Book", evt -> deleteDamagedBook());
        addMenuButton("Download Report", evt -> downloadReport());
        addMenuButton("Close", evt -> {
            new login().setVisible(true);
            this.dispose();
        });

        // Content Panel (Right Side)
        jPanelContent.setBackground(new Color(255, 255, 255)); // Light background
        jPanelContent.setLayout(new CardLayout());

        // Add default content to the content panel
        JLabel defaultLabel = new JLabel("Welcome to the Admin Menu!");
        defaultLabel.setFont(new Font("Arial", Font.BOLD, 24));
        defaultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        jPanelContent.add(defaultLabel, "default");

        // Split Pane
        splitPane.setDividerLocation(400); // Set the initial divider location (left panel width)
        splitPane.setLeftComponent(jPanelMenu);
        splitPane.setRightComponent(jPanelContent);

        // Add the split pane to the frame
        getContentPane().add(splitPane);

        pack();
    }

    private Object create_book() {
       
        new create_book().setVisible(true);
        return jPanelContent;
    }

    private void addMenuButton(String text, java.awt.event.ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setForeground(new Color(255, 255, 255)); // White text
        button.setBackground(new Color(0, 102, 204)); // Blue background
        button.setFocusPainted(false); // Remove focus border
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Add padding
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Change cursor to hand
        button.addActionListener(actionListener);

        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 153, 255)); // Lighter blue on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 102, 204)); // Original blue
            }
        });

        jPanelMenu.add(button);
    }

    private void generateUserReport() {
        displayTable("SELECT userName, regNo, email FROM student", new String[]{"Username", "Registration Number", "Email"}, "User Report");
    }

    private void generateStudySpaceReport() {
        displayTable("SELECT userName, seatNumber, timePeriod FROM study_space_booking", new String[]{"Username", "Seat Number", "Time Period"}, "Study Space Report");
    }

    private void generateBorrowedBooksReport() {
        displayTable("SELECT bookId, userName, dueDate FROM borrowing_history", new String[]{"Book ID", "Username", "Due Date"}, "Borrowed Books Report");
    }

    private void generateFineReport() {
        // Updated query to join fines and student tables to get regNo from the student table
        String query = "SELECT f.userName, s.regNo, f.fine " +
                       "FROM fines f " +
                       "JOIN student s ON f.userName = s.userName";
        String[] columnNames = {"Username", "Registration Number", "Fine"};
        displayTable(query, columnNames, "Fine Report");
    }

    private void deleteDamagedBook() {
        String bookId = JOptionPane.showInputDialog(this, "Enter Book ID to delete:");
        if (bookId != null && !bookId.trim().isEmpty()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBMS_Project", "root", "12345678");
                String sql = "DELETE FROM lib_book WHERE bookId = ?";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, bookId);
                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Book with ID " + bookId + " deleted successfully.");
                } else {
                    JOptionPane.showMessageDialog(this, "No book found with ID " + bookId + ".");
                }
                con.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Book ID cannot be empty.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void downloadReport() {
        // Present a dialog to select the report type
        String[] reportOptions = {"User Report", "Study Space Report", "Borrowed Books Report", "Fine Report"};
        String selectedReport = (String) JOptionPane.showInputDialog(
                this,
                "Select the report you want to download:",
                "Download Report",
                JOptionPane.QUESTION_MESSAGE,
                null,
                reportOptions,
                reportOptions[0]);

        if (selectedReport == null) {
            // If the admin cancels the dialog, do nothing
            return;
        }

        // Determine the query and column names based on the selected report
        String query = "";
        String[] columnNames = {};
        String fileName = "";

        switch (selectedReport) {
            case "User Report":
                query = "SELECT userName, regNo, email FROM student";
                columnNames = new String[]{"Username", "Registration Number", "Email"};
                fileName = "User_Report.csv";
                break;
            case "Study Space Report":
                query = "SELECT userName, seatNumber, timePeriod FROM study_space_booking";
                columnNames = new String[]{"Username", "Seat Number", "Time Period"};
                fileName = "Study_Space_Report.csv";
                break;
            case "Borrowed Books Report":
                query = "SELECT bookId, userName, dueDate FROM borrowing_history";
                columnNames = new String[]{"Book ID", "Username", "Due Date"};
                fileName = "Borrowed_Books_Report.csv";
                break;
            case "Fine Report":
                query = "SELECT f.userName, s.regNo, f.fine " +
                        "FROM fines f " +
                        "JOIN student s ON f.userName = s.userName";
                columnNames = new String[]{"Username", "Registration Number", "Fine"};
                fileName = "Fine_Report.csv";
                break;
            default:
                JOptionPane.showMessageDialog(this, "Invalid report selection.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
        }

        // Fetch the data and save it as a CSV file
        saveReportAsCSV(query, columnNames, fileName);
    }

    private void saveReportAsCSV(String query, String[] columnNames, String fileName) {
        try {
            // Prompt the user to select a file location
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save Report");
            fileChooser.setSelectedFile(new java.io.File(fileName)); // Default file name
            int userSelection = fileChooser.showSaveDialog(this);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                java.io.File fileToSave = fileChooser.getSelectedFile();

                // Fetch data from the database
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBMS_Project", "root", "12345678");
                PreparedStatement pst = con.prepareStatement(query);
                ResultSet rs = pst.executeQuery();

                // Write data to the CSV file
                try (java.io.FileWriter writer = new java.io.FileWriter(fileToSave)) {
                    // Write column headers
                    for (int i = 0; i < columnNames.length; i++) {
                        writer.write(columnNames[i]);
                        if (i < columnNames.length - 1) {
                            writer.write(",");
                        }
                    }
                    writer.write("\n");

                    // Write rows
                    while (rs.next()) {
                        for (int i = 0; i < columnNames.length; i++) {
                            writer.write(String.valueOf(rs.getObject(i + 1)));
                            if (i < columnNames.length - 1) {
                                writer.write(",");
                            }
                        }
                        writer.write("\n");
                    }

                    JOptionPane.showMessageDialog(this, "Report saved successfully to " + fileToSave.getAbsolutePath(), "Success", JOptionPane.INFORMATION_MESSAGE);
                }

                // Close the database connection
                rs.close();
                pst.close();
                con.close();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error saving report: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayTable(String query, String[] columnNames, String title) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBMS_Project", "root", "12345678");
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
            while (rs.next()) {
                Object[] row = new Object[columnNames.length];
                for (int i = 0; i < columnNames.length; i++) {
                    row[i] = rs.getObject(i + 1);
                }
                tableModel.addRow(row);
            }

            JTable table = new JTable(tableModel);
            table.setFont(new Font("Arial", Font.PLAIN, 14));
            table.setRowHeight(25);

            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setPreferredSize(new Dimension(600, 400));

            JOptionPane.showMessageDialog(null, scrollPane, title, JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new admin_menu().setVisible(true));
    }
}