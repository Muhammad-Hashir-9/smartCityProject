package OOP.src;

//All the imports needed
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.SQLException;

//Start of CategoryClass
public class CategoryClass {
    // Creating object of search class in Category Class
    searchClass sc = new searchClass();

    // Method for Gui of Category Class
    void catGUI() {
        JFrame frame = new JFrame("Smart City");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setBackground(new Color(0, 102, 102));

        // Post category panel
        JPanel postCatPanel = new JPanel();
        postCatPanel.setLayout(new GridLayout(12, 0));
        postCatPanel.setPreferredSize(new Dimension(1920, 300));
        postCatPanel.setBackground(Color.WHITE);

        JLabel pcStdLabel1 = new JLabel("Some relevent search suggestions based on your category/profession");
        JLabel pcStdLabel2 = new JLabel("Education Institutes");
        JLabel pcStdLabel3 = new JLabel("Hostels");
        JLabel pcStdLabel5 = new JLabel("Restaurants");
        JLabel pcStdLabel6 = new JLabel("Hospitals");
        JLabel pcStdLabel7 = new JLabel("Entertainment");

        /* ........................................................ */

        JLabel pcTraLabel1 = new JLabel("Some relevent suggestions based on your category/profession");
        JLabel pcTraLabel2 = new JLabel("Hotels");
        JLabel pcTraLabel3 = new JLabel("Tourist spots");
        JLabel pcTraLabel4 = new JLabel("Restaurants");
        JLabel pcTraLabel5 = new JLabel("Hospitals");
        JLabel pcTraLabel6 = new JLabel("Entertainment");

        /* ........................................................ */

        JLabel pcDocLabel1 = new JLabel("Some relevent search suggestions based on your category/profession");
        JLabel pcDocLabel2 = new JLabel("Hospitals");
        JLabel pcDocLabel3 = new JLabel("Educational Institutes");
        JLabel pcDocLabel5 = new JLabel("Hotels");
        JLabel pcDocLabel6 = new JLabel("Clinics");

        JButton pcButton = new JButton("Move to search");// Button for moving to search class
        pcButton.setBackground(new Color(0, 102, 102));
        pcButton.setForeground(Color.WHITE);
        /* ........................................................ */

        // Initially setting it to false
        postCatPanel.setVisible(false);

        // Category panel
        JPanel catPanel = new JPanel();
        catPanel.setLayout(null);
        catPanel.setPreferredSize(new Dimension(400, 400));
        catPanel.setBackground(Color.WHITE);

        JLabel catLabel = new JLabel("Select your profession:");
        JButton catButton = new JButton("Submit");
        catButton.setBackground(new Color(0, 102, 102));
        catButton.setForeground(Color.WHITE);

        JButton catButton1 = new JButton("Undo");// action performed is below catButton
        catButton1.setBackground(new Color(0, 102, 102));
        catButton1.setForeground(Color.WHITE);

        JLabel catLabel2 = new JLabel("Category Class");
        catLabel2.setFont(new Font("Calibiri", 0, 30));
        catLabel2.setBounds(1100, 80, 300, 50);
        catLabel2.setForeground(new Color(0, 102, 102));

        // For comboBox
        String[] categories = { "Choose Your category", "Student", "Traveler", "Doctor" };
        JComboBox<String> comboBox = new JComboBox<>(categories);
        JLabel comboLabel = new JLabel("Selected category: None");

        // Adding action to comboBox button
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object ob = comboBox.getSelectedItem();
                if (ob.equals(categories[0])) {
                    comboLabel.setText("Selected category: None");
                } else {
                    comboLabel.setText("Selected category: " + ob);
                }
            }
        });

        // Submit button
        catButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object ob = comboBox.getSelectedItem();
                if (ob.equals(categories[1])) {
                    postCatPanel.setVisible(true);
                    postCatPanel.add(pcStdLabel1);
                    postCatPanel.add(pcStdLabel2);
                    postCatPanel.add(pcStdLabel3);
                    postCatPanel.add(pcStdLabel5);
                    postCatPanel.add(pcStdLabel6);
                    postCatPanel.add(pcStdLabel7);

                } else if (ob.equals(categories[2])) {
                    postCatPanel.setVisible(true);
                    postCatPanel.add(pcTraLabel1);
                    postCatPanel.add(pcTraLabel2);
                    postCatPanel.add(pcTraLabel3);
                    postCatPanel.add(pcTraLabel4);
                    postCatPanel.add(pcTraLabel5);
                    postCatPanel.add(pcTraLabel6);
                } else if (ob.equals(categories[3])) {
                    postCatPanel.setVisible(true);
                    postCatPanel.add(pcDocLabel1);
                    postCatPanel.add(pcDocLabel2);
                    postCatPanel.add(pcDocLabel3);
                    postCatPanel.add(pcDocLabel5);
                    postCatPanel.add(pcDocLabel6);
                }

            }
        });

        // Undo button, if user wants to change his category
        catButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object ob = comboBox.getSelectedItem();
                if (ob.equals(categories[1])) {
                    postCatPanel.setVisible(false);
                    postCatPanel.remove(pcStdLabel1);
                    postCatPanel.remove(pcStdLabel2);
                    postCatPanel.remove(pcStdLabel3);
                    postCatPanel.remove(pcStdLabel5);
                    postCatPanel.remove(pcStdLabel6);
                    postCatPanel.remove(pcStdLabel7);
                }
                if (ob.equals(categories[2])) {
                    postCatPanel.setVisible(false);
                    postCatPanel.remove(pcTraLabel1);
                    postCatPanel.remove(pcTraLabel2);
                    postCatPanel.remove(pcTraLabel3);
                    postCatPanel.remove(pcTraLabel4);
                    postCatPanel.remove(pcTraLabel5);
                    postCatPanel.remove(pcTraLabel6);
                }
                if (ob.equals(categories[3])) {
                    postCatPanel.setVisible(false);
                    postCatPanel.remove(pcDocLabel1);
                    postCatPanel.remove(pcDocLabel2);
                    postCatPanel.remove(pcDocLabel3);
                    postCatPanel.remove(pcDocLabel5);
                    postCatPanel.remove(pcDocLabel6);
                }

            }

        });
        // Move to search button
        pcButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // First a connection is created of searc class
                try {
                    sc.createConnection();
                } catch (SQLException e1) {

                    e1.printStackTrace();
                }
                // These two lines will make the search class visible and hides the category
                // class frame
                sc.searchGui();
                frame.setVisible(false);
            }
        });

        // Putting image on the panel in Category Class Frame
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

        // Setting up bounds for category panel widgets
        catLabel.setBounds(1200, 130, 200, 20);
        comboBox.setBounds(1200, 160, 200, 20);
        comboLabel.setBounds(1200, 190, 300, 20);
        catButton.setBounds(1200, 220, 100, 20);

        // Setting bound for undo button
        catButton1.setBounds(1350, 220, 100, 20);

        // Adding widgets to category panel
        catPanel.add(catLabel);
        catPanel.add(catButton);
        catPanel.add(comboLabel);
        catPanel.add(comboBox);
        catPanel.add(catButton1);
        catPanel.add(catLabel2);

        postCatPanel.add(pcButton);

        catPanel.setBounds(800, 100, 1180, 400);
        postCatPanel.setBounds(800, 500, 1180, 480);
        panel1.setBounds(0, 0, 800, 1080);

        // Adding panels to frame
        frame.add(panel1);
        frame.add(postCatPanel);
        frame.add(catPanel);

        // Setting up the frame
        frame.setSize(1980, 1080);
        frame.setVisible(true);

    }
}
