package Backend;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class Controller {

	public static Connection connection;
    private static PreparedStatement psAddUser;
    private static PreparedStatement psUpdateUser;
    private static PreparedStatement psGetUserName;
    private static PreparedStatement psCheckCredentials;
    private static PreparedStatement psUsers;
    private static ResultSet resultSet;
    private static ArrayList<String> userList = new ArrayList<String>();

    // handler(): Add User along with a Role
    public static void addUser(String username, String role, String type, String passwd, String dte) {   	
        try {
            psAddUser = connection.prepareStatement("Insert into USER (USERNAME, USER_ROLE, USER_TYPE, "
            									+ "PASSWORD, DATE_CREATED) " + "values (?,?,?,?,?) ");
            psAddUser.setString(1, username);
            psAddUser.setString(2, role);
            psAddUser.setString(3, type);
            psAddUser.setString(4, passwd);
            psAddUser.setString(5, dte);           
            psAddUser.executeUpdate();
            
        } catch(SQLException se) {
            System.out.println("Could not add User. " + se.getMessage());
        }     
    }

    // handler(): Update user-Password 
    public static void updatePassword(String usrname, String newPassword) {   	
        try {
            psUpdateUser = connection.prepareStatement("Update USER set PASSWORD = '" + newPassword 
            										+ "' where USERNAME = '" + usrname + "'");
            psUpdateUser.executeUpdate();
            
        } catch(SQLException se) {
            System.out.println("Could not update UserPassword. " + se.getMessage());
        }     
    }
    
    // check if Username exists?
    public static Boolean checkUserName(String usrname) {     
        try {
        	psGetUserName = connection.prepareStatement("Select USERNAME from USER where USERNAME = ? order by USERNAME");
        	psGetUserName.setString(1, usrname);
            resultSet = psGetUserName.executeQuery();          
            while(resultSet.next()) {
                return true;
            }
        } catch(SQLException se) {
        	System.out.println("Could not fetch the Username. " + se.getMessage());
        }
        
        return false;        
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

    public static Boolean getUserInfo(String userName) {
        try {
            psCheckCredentials = connection.prepareStatement("SELECT * FROM USER WHERE USERNAME = ?");
            psCheckCredentials.setString(1, userName);
            resultSet = psCheckCredentials.executeQuery();
            
            while(resultSet.next()) {
                return true; // Username and password match found
            }
        } catch(SQLException se) {
            System.out.println("Error checking credentials: " + se.getMessage());
        }
        
        return false; // No matching username and password found
    }
    
    
    // handler(): to get the list of Users (info may be required by an Admin)
    public static ArrayList<String> getAllUsers() {   	
        ArrayList<String> resultsUsersList = null;       
        try {
        	psUsers = connection.prepareStatement("Select USERNAME from USER order by USERNAME");
            resultSet = psUsers.executeQuery();

            resultsUsersList = new ArrayList<>();        
            while(resultSet.next()) {
            	resultsUsersList.add(resultSet.getString("USERNAME") );
            	
            }
        } catch(SQLException se) {
            System.out.println("Could not get User List. " + se.getMessage());
        }  
        
        return resultsUsersList;
    }

    public static void setConnection(Connection connection){
        Controller.connection = connection;
    }

    
    
    ////////////////////////////////////////
    //// Main method(): To Test the handlers
    ////////////////////////////////////////
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
    	////// Call to test DB handler()... 
		
		// getConnection()
    	//connection = DBConnection.getConnection();
    	//System.out.println("DB Connected is : " + connection); 
    	
    	
    	////// Call to test Function handlers()...     	
        
    	// checkUserName() and display result
        Boolean result = Controller.checkUserName("user03");
        if (result == true)
        	System.out.println("Username already exist. Please try another Username");
        
    	// addUser()
        Controller.addUser("user03", "Admin", "A", "passwd3", "2024-04-28");
        
        // updatePassword()
        Controller.updatePassword("user03", "new-Pwd3");
		
        // getAllUsers() and display the Users List
        userList = Controller.getAllUsers();        
        for (int i=0; i<userList.size(); i++) {
        	System.out.println("Username in the userList : " + userList.get(i));
        }
    }
	
	
    
}
