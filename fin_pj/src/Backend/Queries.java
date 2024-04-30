package Backend;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.HashMap;
import java.util.Map;

public class Queries {

    public static Connection connection;
    private static PreparedStatement psCheckCredentials;
    private static ResultSet resultSet;

    public static void setConnection(Connection connection){
        Queries.connection = connection;
    }

    public static Boolean checkCredentials(String userName, String Password) {
        try {
            psCheckCredentials = connection.prepareStatement("SELECT USERNAME FROM USER WHERE USERNAME = ? AND PASSWORD = ?");
            psCheckCredentials.setString(1, userName);
            psCheckCredentials.setString(2, Password);
            resultSet = psCheckCredentials.executeQuery();
            
            while(resultSet.next()) {
                return true; // Username and password match found
            }
        } catch(SQLException se) {
            System.out.println("Error checking credentials: " + se.getMessage());
        }

        return false; // No matching username and password found
    }

    public static Map<String,Object> getUserInfo(String userName) {
        try {
            psCheckCredentials = connection.prepareStatement("SELECT * FROM USER WHERE USERNAME = ?");
            psCheckCredentials.setString(1, userName);
            resultSet = psCheckCredentials.executeQuery();

            Map<String,Object> userData = new HashMap<>();
            
            while(resultSet.next()) {
                int user_id = resultSet.getInt("user_id");
                String username = resultSet.getString("username");
                String user_role = resultSet.getString("user_role");
                String user_type = resultSet.getString("user_type");

                userData.put("user_id", user_id);
                userData.put("username", username);
                userData.put("user_role", user_role);
                userData.put("user_type", user_type);

                break;
            }

        return userData;
        
        } catch(SQLException se) {
            System.out.println(se.getMessage());
            return new HashMap<>();

        }
        
    }
}
