package Backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {

    private static Connection connection;
    private static final String database = "jdbc:mysql://localhost:3306/sys";
    private static final String user = "root";
    private static final String password = "asdfghjkl";

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(database, user, password);
            } catch (SQLException se) {              
                System.out.println("Could not open database. " + se.getMessage());
                System.exit(1);
            }
        }
        return connection;
    }

    
/*    
    //// Main Method: To test the MySql DB connection
    public static void main(String args[]) {
    	connection = DBConnection.getConnection();
    	System.out.println("Connected to : " + connection);    	
    }
*/
    
}

