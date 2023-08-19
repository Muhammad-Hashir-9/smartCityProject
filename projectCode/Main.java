package OOP.src;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Accounts acc = new Accounts();

        try {

            acc.createConnection();
            acc.accGui();
            acc.createTable();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
