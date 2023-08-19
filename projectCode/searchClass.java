package OOP.src;

//All the imports needed
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

//Start of search class
public class searchClass {

    // Declaring global variable
    Connection con;

    // Method that contains all gui of search class
    void searchGui() {
        JFrame frame = new JFrame("Smart City");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setBackground(new Color(0, 102, 102));
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        // Widgets for panel
        JLabel label1 = new JLabel("Enter your search");
        JLabel label2 = new JLabel();
        JLabel label3 = new JLabel();
        JLabel label4 = new JLabel();
        JLabel label5 = new JLabel();
        JLabel label6 = new JLabel();
        JLabel label7 = new JLabel();
        JLabel label8 = new JLabel();
        JLabel label9 = new JLabel();
        JLabel label10 = new JLabel();

        JLabel label13 = new JLabel("Search Class");
        label13.setFont(new Font("Calibiri", 0, 30));
        label13.setBounds(1100, 80, 200, 30);
        label13.setForeground(new Color(0, 102, 102));

        label5.setVisible(false);

        // Array of labels created for displaying results on the panel
        JLabel[] labels = { label2, label3, label4, label6, label7, label8, label9, label10 };

        JTextField textField = new JTextField();
        JButton button = new JButton("Search");
        button.setBackground(new Color(0, 102, 102));
        button.setForeground(Color.WHITE);

        // A helping string array that contain column names of database table in the
        // same format as in database
        String[] categories = { "Clinics", "Educational institutes", "Hospitals", "Hotels", "Hostels", "Restaurants",
                "Tourist spots" };

        // A string array that will store the contents of columns of table
        String[] print = new String[labels.length];

        // Search button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String search = textField.getText();

                // Capitalizae first letter if user enters it small. Because database has
                // capital first letter and store it in cap variable
                String cap = search.substring(0, 1).toUpperCase() + search.substring(1);
                // A check for when user enter wrong spelling during search
                boolean matchFound = false;

                for (int i = 0; i < categories.length; i++) {
                    if (categories[i].equals(cap)) {
                        matchFound = true;
                        label5.setVisible(false);
                        label6.setVisible(false);
                        label7.setVisible(false);
                        label8.setVisible(false);
                        label9.setVisible(false);
                        label10.setVisible(false);

                        // A variable that stores the entered words without spaces
                        String concat = cap.replaceAll("\\s+", "");
                        try {
                            Statement stmt = con.createStatement();
                            ResultSet rs = stmt.executeQuery("SELECT " + concat + " FROM information");
                            int j = 0;
                            while (rs.next()) {
                                print[j] = rs.getString(concat);
                                j++;
                            }
                            for (int x = 0; x < j; x++) {
                                labels[x].setText(print[x]);
                            }

                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                        break;
                    }
                }

                if (!matchFound) {
                    int lenghtStr = cap.length() / 2;
                    System.out.println(lenghtStr);
                    String[] str = new String[lenghtStr];
                    int k = 0;

                    // Dividing the entered string after 2 characters and storing it in str arrray
                    for (int i = 0; i < cap.length(); i += 2) {
                        str[k] = cap.substring(i, i + 2);
                        k++;
                    }
                    // for (int i = 0; i < str.length; i++) {
                    // System.out.println(str[i]);
                    // }

                    StringBuilder likeConditions = new StringBuilder();
                    // looping the str array
                    for (String s : str) {
                        likeConditions.append(" OR Data LIKE '%" + s + "%'");
                    }

                    label5.setVisible(true);
                    label6.setVisible(true);
                    label7.setVisible(true);
                    label8.setVisible(true);
                    label9.setVisible(true);
                    label10.setVisible(true);
                    label5.setText("Sorry no search found. Relevent searches to the entered one might be:");
                    try {
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery("SELECT * FROM helperdb WHERE Data LIKE '%" + cap
                                + "%'" + likeConditions.toString());
                        int j = 0;
                        while (rs.next()) {
                            print[j] = rs.getString("Data");
                            j++;
                        }
                        System.out.println(j);
                        for (int x = 0; x < j; x++) {
                            labels[x].setText(print[x]);
                        }
                        // for clearing the text fields
                        for (int x = j; x < j; x++) {
                            labels[x].setText("");
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        // Putting image on the frame
        try {
            BufferedImage image = ImageIO.read(new File("OOP\\src\\Image.png"));
            Icon icon = new ImageIcon(image.getScaledInstance(800, 600, Image.SCALE_SMOOTH));
            JLabel pic = new JLabel(icon);
            pic.setBounds(0, 200, 800, 600);
            panel1.add(pic);

        } catch (Exception e) {
            e.printStackTrace();

        }
        // Setting text above the image
        JLabel text = new JLabel("Welcome To Smart City");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Times New Roman", 0, 50));
        text.setBounds(150, 70, 500, 50);
        panel1.add(text);

        label1.setBounds(950, 150, 200, 30);
        textField.setBounds(1070, 150, 200, 30);
        button.setBounds(1290, 150, 100, 30);
        label5.setBounds(1070, 190, 450, 30);
        label2.setBounds(1070, 210, 300, 30);
        label3.setBounds(1070, 230, 300, 30);
        label4.setBounds(1070, 250, 300, 30);
        label6.setBounds(1070, 270, 300, 30);
        label7.setBounds(1070, 290, 300, 30);
        label8.setBounds(1070, 310, 300, 30);
        label9.setBounds(1070, 330, 300, 30);
        label10.setBounds(1070, 350, 300, 30);

        panel.add(label1);
        panel.add(textField);
        panel.add(button);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);
        panel.add(label5);
        panel.add(label6);
        panel.add(label7);
        panel.add(label8);
        panel.add(label9);
        panel.add(label10);
        panel.add(label13);
        panel.setBounds(800, 0, 1180, 1080);
        panel1.setBounds(0, 0, 800, 1080);

        frame.add(panel1);
        frame.add(panel);

        frame.setSize(1980, 1080);
        frame.setVisible(true);

    }

    void createTableSC() {
        try {
            String tableName = "Information";
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SHOW TABLES LIKE '" + tableName + "'");
            boolean tableExists = rs.next();
            rs.close();

            if (!tableExists) {
                String q = "CREATE TABLE " + tableName + "(" +
                        " Clinics varchar(250)," +
                        " Educationalinstitutes varchar(250)," +
                        " Hospitals varchar(250)," +
                        " Hotels varchar(250)," +
                        " Hostels varchar(250)," +
                        " Restaurants varchar(250)," +
                        " Touristspots varchar(250)" +
                        ");";

                stmt.execute(q);
                System.out.println("Table created successfully");
            } else {
                System.out.println("Table already exists");
            }

            stmt.close();
        } catch (SQLException e) {
            Logger.getLogger(searchClass.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    // For storing the column names of our database table
    // Used for searching purpose in case user enters wrong spelling
    void helpingDBSC() {
        try {
            String tableName = "helperDB";
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SHOW TABLES LIKE '" + tableName + "'");
            boolean tableExists = rs.next();
            rs.close();

            if (!tableExists) {
                String q = "CREATE TABLE " + tableName + " (" +
                        "`Data` varchar(250)" +
                        ");";
                stmt.execute(q);
                System.out.println("Table created successfully");
            } else {
                System.out.println("Table already exists");
            }

            stmt.close();
        } catch (SQLException e) {
            Logger.getLogger(searchClass.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    // Setting up connection with the data base
    void createConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
            System.out.println("Database Connection Success:");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(searchClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
