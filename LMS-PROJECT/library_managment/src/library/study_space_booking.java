package library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

import library.managment.user_menu;

import java.awt.Color;
import java.awt.Font;

public class study_space_booking extends javax.swing.JFrame {

    private String uName;

    public study_space_booking(String userName) {
        initComponents();
        uName = userName;
        setLocationRelativeTo(null); // Center the window
        loadAvailableSeats();
        autoVacateSeats(); // Automatically vacate expired seats
    }

    private void initComponents() {
        jPanel1 = new javax.swing.JPanel(); // Main panel
        jScrollPane = new javax.swing.JScrollPane(); // Scroll pane
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel(); // Label for day selection
        jLabel5 = new javax.swing.JLabel(); // Label for date selection
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>(); // Combo box for day selection
        jComboBoxYear = new javax.swing.JComboBox<>();
        jComboBoxMonth = new javax.swing.JComboBox<>();
        jComboBoxDay = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton(); // Close button

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        // Configure jPanel1
        jPanel1.setBackground(new Color(44, 62, 80)); // Set background color
        jPanel1.setLayout(new javax.swing.GroupLayout(jPanel1)); // Use GroupLayout for layout

        // Configure labels
        jLabel1.setFont(new java.awt.Font("Arial", Font.BOLD, 24)); // Set font size
        jLabel1.setText("Study Space Booking");
        jLabel1.setForeground(new Color(255, 255, 255)); // Set text color

        jLabel2.setFont(new java.awt.Font("Arial", Font.PLAIN, 14));
        jLabel2.setText("Select Seat Number:");
        jLabel2.setForeground(new Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Arial", Font.PLAIN, 14));
        jLabel3.setText("Select Time Period:");
        jLabel3.setForeground(new Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Arial", Font.PLAIN, 14));
        jLabel4.setText("Selected Day:");
        jLabel4.setForeground(new Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Arial", Font.PLAIN, 14));
        jLabel5.setText("Select Date:");
        jLabel5.setForeground(new Color(255, 255, 255));

        // Configure combo boxes
        jComboBox1.setFont(new java.awt.Font("Arial", Font.PLAIN, 14)); // Set font
        jComboBox1.setBackground(new Color(240, 240, 240)); // Set background color
        jComboBox1.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0, 102, 204))); // Set border color
        jComboBox1.setPreferredSize(new java.awt.Dimension(200, 25)); // Set width to 200 and height to 25

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "8-11 AM", "11 AM-2 PM", "2-5 PM", "5-8 PM", "8-11 PM" }));
        jComboBox2.setFont(new java.awt.Font("Arial", Font.PLAIN, 14)); // Set font
        jComboBox2.setBackground(new Color(240, 240, 240)); // Set background color
        jComboBox2.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0, 102, 204))); // Set border color
        jComboBox2.setPreferredSize(new java.awt.Dimension(200, 25)); // Set width to 200 and height to 25

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" }));
        jComboBox3.setFont(new java.awt.Font("Arial", Font.PLAIN, 14)); // Set font
        jComboBox3.setBackground(new Color(240, 240, 240)); // Set background color
        jComboBox3.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0, 102, 204))); // Set border color
        jComboBox3.setPreferredSize(new java.awt.Dimension(200, 25)); // Set width to 200 and height to 25

        int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        jComboBoxYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{String.valueOf(currentYear), String.valueOf(currentYear + 1)}));
        jComboBoxMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}));
        jComboBoxMonth.addActionListener(evt -> updateDaysInMonth());
        jComboBoxYear.addActionListener(evt -> updateDaysInMonth());
        updateDaysInMonth();

        // Configure buttons
        jButton1.setText("Book");
        jButton1.setFont(new java.awt.Font("Arial", Font.BOLD, 14));
        jButton1.setBackground(new Color(0, 102, 204));
        jButton1.setForeground(new Color(255, 255, 255));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Close");
        jButton2.setFont(new java.awt.Font("Arial", Font.BOLD, 14));
        jButton2.setBackground(new Color(0, 102, 204));
        jButton2.setForeground(new Color(255, 255, 255));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        // Add components to jPanel1
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(50, 50, 50)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3)
                                .addComponent(jLabel5) // "Select Date" label
                                .addComponent(jLabel4)) // "Select Day" label
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jComboBoxYear, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jComboBoxMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jComboBoxDay, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jButton1)
                            .addGap(18, 18, 18)
                            .addComponent(jButton2)))
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
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(30, 30, 30)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(30, 30, 30)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5) // "Select Date" label
                        .addComponent(jComboBoxYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBoxMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBoxDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(30, 30, 30)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4) // "Select Day" label
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(30, 30, 30)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jButton2))
                    .addContainerGap(50, Short.MAX_VALUE))
        );

        // Wrap jPanel1 in a JScrollPane
        jScrollPane.setViewportView(jPanel1);

        // Add the scroll pane to the frame
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    private void updateDaysInMonth() {
        // Get the selected year and month
        int year = Integer.parseInt((String) jComboBoxYear.getSelectedItem());
        int month = jComboBoxMonth.getSelectedIndex() + 1; // Month is 0-based in Calendar

        // Calculate the number of days in the selected month and year
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.set(year, month - 1, 1); // Set to the first day of the selected month
        int daysInMonth = calendar.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);

        // Populate the day combo box
        jComboBoxDay.removeAllItems();
        for (int i = 1; i <= daysInMonth; i++) {
            jComboBoxDay.addItem(String.valueOf(i));
        }

        // Add an action listener to automatically select the day of the week
        jComboBoxDay.addActionListener(evt -> updateDayOfWeek());
    }

    private void updateDayOfWeek() {
        try {
            // Ensure a day is selected
            if (jComboBoxDay.getSelectedItem() == null) {
                return; // Exit the method if no day is selected
            }

            // Get the selected year, month, and day
            int year = Integer.parseInt((String) jComboBoxYear.getSelectedItem());
            int month = jComboBoxMonth.getSelectedIndex() + 1; // Month is 0-based in Calendar
            int day = Integer.parseInt((String) jComboBoxDay.getSelectedItem());

            // Use Calendar to calculate the day of the week
            java.util.Calendar calendar = java.util.Calendar.getInstance();
            calendar.set(year, month - 1, day); // Set the selected date

            // Get the day of the week (1 = Sunday, 2 = Monday, ..., 7 = Saturday)
            int dayOfWeek = calendar.get(java.util.Calendar.DAY_OF_WEEK);

            // Map the day of the week to its name
            String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
            String selectedDay = days[dayOfWeek - 1];

            // Set the selected day in jComboBox3
            jComboBox3.setSelectedItem(selectedDay);
        } catch (Exception e) {
            // Handle any exceptions (e.g., if no day is selected yet)
            System.out.println("Error updating day of the week: " + e.getMessage());
        }
    }

    private void loadAvailableSeats() {
        try {
            jComboBox1.removeAllItems();
            for (int i = 1; i <= 50; i++) {
                jComboBox1.addItem("Seat " + i);
            }
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBMS_Project", "root", "12345678");
            String sql = "SELECT seatNumber FROM study_space_booking";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String bookedSeat = "Seat " + rs.getString("seatNumber");
                jComboBox1.removeItem(bookedSeat);
            }
            rs.close();
            pst.close();
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        String seatNumber = (String) jComboBox1.getSelectedItem();
        String timePeriod = (String) jComboBox2.getSelectedItem();
        int year = Integer.parseInt((String) jComboBoxYear.getSelectedItem());
        int month = jComboBoxMonth.getSelectedIndex() + 1;
        int day = Integer.parseInt((String) jComboBoxDay.getSelectedItem());

        if (seatNumber == null || timePeriod == null) {
            JOptionPane.showMessageDialog(null, "Fields are empty");
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBMS_Project", "root", "12345678");
            String checkSql = "SELECT * FROM study_space_booking WHERE seatNumber = ? AND timePeriod = ? AND year = ? AND month = ? AND day = ?";
            PreparedStatement checkPst = con.prepareStatement(checkSql);
            checkPst.setString(1, seatNumber);
            checkPst.setString(2, timePeriod);
            checkPst.setInt(3, year);
            checkPst.setInt(4, month);
            checkPst.setInt(5, day);
            ResultSet checkRs = checkPst.executeQuery();

            if (checkRs.next()) {
                JOptionPane.showMessageDialog(null, "Seat " + seatNumber + " is already booked for the selected time period and date.");
                return;
            }

            String sql = "INSERT INTO study_space_booking (userName, seatNumber, timePeriod, year, month, day) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, uName);
            pst.setString(2, seatNumber);
            pst.setString(3, timePeriod);
            pst.setInt(4, year);
            pst.setInt(5, month);
            pst.setInt(6, day);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Study space booked: " + seatNumber + " for " + timePeriod + " on " + day + "/" + month + "/" + year);

            loadAvailableSeats();

            checkRs.close();
            checkPst.close();
            pst.close();
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void autoVacateSeats() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBMS_Project", "root", "12345678");
            String sql = "DELETE FROM study_space_booking " +
                         "WHERE STR_TO_DATE(CONCAT(year, '-', month, '-', day, ' ', SUBSTRING_INDEX(timePeriod, '-', 1)), '%Y-%m-%d %l %p') < NOW() - INTERVAL 3 HOUR";
            PreparedStatement pst = con.prepareStatement(sql);
            int rowsDeleted = pst.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println(rowsDeleted + " expired bookings removed.");
            }
            pst.close();
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in autoVacateSeats: " + e.getMessage());
        }
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        new user_menu(uName).setVisible(true);
        this.dispose();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new study_space_booking("defaultUser").setVisible(true);
            }
        });
    }

    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2; // Close button
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3; // Combo box for day selection
    private javax.swing.JComboBox<String> jComboBoxYear;
    private javax.swing.JComboBox<String> jComboBoxMonth;
    private javax.swing.JComboBox<String> jComboBoxDay;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4; // Label for day selection
    private javax.swing.JLabel jLabel5; // Label for date selection
    private javax.swing.JPanel jPanel1; // Main panel
    private javax.swing.JScrollPane jScrollPane; // Scroll pane
}
