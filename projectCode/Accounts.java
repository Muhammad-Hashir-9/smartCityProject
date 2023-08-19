package OOP.src;

// All the imports needed

import java.util.logging.Level;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Logger;

//Start of the account class
public class Accounts {
    Connection con;
    CategoryClass cc = new CategoryClass();

    // This method contains all of the Gui of Account class
    void accGui() {

        JFrame frame = new JFrame("Smart City");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setBackground(new Color(0, 102, 102));

        JPanel panel2 = new JPanel();
        panel2.setLayout(null);

        JPanel accPanel = new JPanel();
        accPanel.setLayout(new GridLayout(8, 1));

        accPanel.setVisible(false);

        JPanel logPanel = new JPanel();
        logPanel.setLayout(null);

        logPanel.setBackground(Color.WHITE);
        logPanel.setSize(500, 600);

        JLabel logLabel1 = new JLabel("Email/Phone Number");
        JLabel logLabel2 = new JLabel("Password");
        JLabel logLabel3 = new JLabel("Dont have an account?");
        JLabel logLabel4 = new JLabel("LOGIN");
        logLabel4.setFont(new Font("Calibiri", 0, 30));
        logLabel4.setBounds(1100, 80, 200, 30);
        logLabel4.setForeground(new Color(0, 102, 102));

        JTextField logField1 = new JTextField();
        JPasswordField logField2 = new JPasswordField();

        JButton logButton1 = new JButton("Log in");
        JButton logButton2 = new JButton("Register");

        logButton1.setBackground(new Color(0, 102, 102));
        logButton1.setForeground(Color.WHITE);
        logButton2.setBackground(new Color(0, 102, 102));
        logButton2.setForeground(Color.WHITE);

        // Registration Button
        logButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accPanel.setVisible(true);
                logPanel.setVisible(false);
            }
        });

        // Login button
        logButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean check = false;
                try {

                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM Accounts");
                    while (rs.next()) {
                        String email = rs.getString("Email");
                        String password = rs.getString("Password");
                        if (logField1.getText().equals(email) && logField2.getText().equals(password)) {
                            check = true;

                            // The following two lines will open the category class and hides the accounts
                            // frame
                            cc.catGUI();
                            frame.setVisible(false);

                        }

                    }

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                if (!check) {
                    String disp = String.format("Credentials do not match");
                    JOptionPane.showMessageDialog(null, disp);
                }
            }
        });

        // Putting image on the panel in Accounts Frame
        try {
            BufferedImage image = ImageIO.read(new File("OOP\\src\\Image.png"));
            Icon icon = new ImageIcon(image.getScaledInstance(800, 600, Image.SCALE_SMOOTH));
            JLabel pic = new JLabel(icon);
            pic.setBounds(0, 200, 800, 600);
            panel1.add(pic);

        } catch (Exception e) {
            e.printStackTrace();

        }
        // Setting the text above image
        JLabel text = new JLabel("Welcome To Smart City");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Times New Roman", 0, 50));
        text.setBounds(150, 70, 500, 50);
        panel1.add(text);

        // Setting bounds for widgets of logPanel

        logLabel1.setBounds(1000, 140, 200, 30);
        logField1.setBounds(1200, 140, 200, 30);
        logLabel2.setBounds(1000, 210, 200, 30);
        logField2.setBounds(1200, 210, 200, 30);
        logButton1.setBounds(1000, 270, 70, 30);
        logButton2.setBounds(1200, 340, 150, 30);
        logLabel3.setBounds(1000, 340, 150, 30);

        // For sign-up panel
        JLabel fnLabel = new JLabel("First name");
        JLabel lnLabel = new JLabel("Last name");
        JLabel eLabel = new JLabel("Email");
        JLabel pnLabel = new JLabel("Phone number");
        JLabel pLabel = new JLabel("Password");
        JLabel sLabel = new JLabel("SIGN UP");
        sLabel.setFont(new Font("Calibiri", 0, 30));
        sLabel.setBounds(1300, 200, 200, 30);
        sLabel.setForeground(new Color(0, 102, 102));

        JTextField fnField = new JTextField();
        JTextField lnField = new JTextField();
        JTextField eField = new JTextField();
        JTextField pnField = new JTextField();
        JPasswordField pField = new JPasswordField();

        JButton accButton1 = new JButton("Sign up");
        JButton accButton2 = new JButton("Back to login");

        accButton1.setBackground(new Color(0, 102, 102));
        accButton1.setForeground(Color.WHITE);
        accButton2.setBackground(new Color(0, 102, 102));
        accButton2.setForeground(Color.WHITE);

        // Back to login button
        accButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logPanel.setVisible(true);
                accPanel.setVisible(false);

            }
        });

        // Sign-up button
        accButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent exp) {
                try {

                    String fn = fnField.getText();
                    String ln = lnField.getText();
                    String e = eField.getText();
                    String p = pField.getText();
                    String pn = pnField.getText();

                    PreparedStatement stmt = con.prepareStatement("INSERT INTO ACCOUNTS VALUES(?,?,?,?,?)");
                    stmt.setString(3, e);
                    stmt.setString(4, p);

                    // Ensuring that name contains letters only
                    if (fn.matches("[a-zA-Z]+") && ln.matches("[a-zA-Z]+")) {
                        stmt.setString(1, fn);
                        stmt.setString(2, ln);

                    } else {
                        String disp = String.format("Name must contain letters only");
                        JOptionPane.showMessageDialog(null, disp);
                    }

                    // Ensuring that length of phone number is < 11 and it contains digits only
                    if (pn.length() <= 11 && pn.matches("[0-9]+")) {
                        stmt.setString(5, pn);

                    } else {
                        String disp = String.format(
                                "Wrong information enterd in Phone Number\n Phone number must contain only digits and must not exceed size of 11");
                        JOptionPane.showMessageDialog(null, disp);
                    }
                    stmt.execute();
                    String disp = String.format("Registeration successful");
                    JOptionPane.showMessageDialog(null, disp);
                    // System.out.println("Insertion Completed");
                    stmt.close();
                } catch (SQLException evt) {
                    evt.printStackTrace();
                }
            }
        });

        // Adding widgets to the panel

        accPanel.add(fnLabel);
        accPanel.add(fnField);
        accPanel.add(lnLabel);
        accPanel.add(lnField);
        accPanel.add(eLabel);
        accPanel.add(eField);
        accPanel.add(pnLabel);
        accPanel.add(pnField);
        accPanel.add(pLabel);
        accPanel.add(pField);
        accPanel.add(accButton1);
        accPanel.add(accButton2);
        accPanel.add(sLabel);

        logPanel.add(logLabel1);
        logPanel.add(logField1);
        logPanel.add(logLabel2);
        logPanel.add(logField2);
        logPanel.add(logButton1);
        logPanel.add(logButton2);
        logPanel.add(logLabel3);
        logPanel.add(logLabel4);

        // Setting up panels bound
        accPanel.setBounds(900, 0, 650, 400);
        logPanel.setBounds(900, 0, 500, 600);
        panel1.setBounds(0, 0, 800, 1080);

        // Adding panels to the frame
        frame.add(panel1);
        frame.add(accPanel);
        frame.add(logPanel);

        // Setting up frame
        frame.setSize(1980, 1080);
        frame.setVisible(true);

    }

    // Creating table in database through code
    void createTable() {
        try {
            String tableName = "Accounts";
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SHOW TABLES LIKE '" + tableName + "'");
            boolean tableExists = rs.next();
            rs.close();

            // If table exists, then dont create another
            if (!tableExists) {
                String q = "CREATE TABLE " + tableName + "(" +
                        " FirstName varchar(100)," +
                        " LastName varchar(100)," +
                        " Email varchar(100)," +
                        "Password varchar(100)," +
                        "PhoneNumber varchar(50)" +
                        ");";

                stmt.execute(q);
                System.out.println("Table created successfully");
            } else {
                System.out.println("Table already exists");
            }

            stmt.close();
        } catch (SQLException e) {
            Logger.getLogger(Accounts.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    // Setting up Accounts class connection with the database
    void createConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
            System.out.println("Database Connection Success:");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Accounts.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}