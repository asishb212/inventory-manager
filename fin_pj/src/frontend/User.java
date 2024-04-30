package frontend;

// User Class that represents the USER table in the repository inventory_db
public class User {

	 public static String userName;
	 public static String userRole;
	 public static String userType;
	 public static int userId;
	 //private String password;

	 // constructor
	 public User() {		 
	 }

	 // constructor
	 public User(String userName) {   
		 setUserName(userName);
	 }
	 
	 // constructor
	 public User(String userName, String userRole, String userType) {   
        setUserName(userName);
        setUserRole(userRole);
        setUserType(userType);
     }

    ///////////////////////
    //// setter Methods
	///////////////////////
	public static void setUserId(int userId) {
        User.userId = userId;
    }

    public static void setUserName(String userName) {
        User.userName = userName;
    }
        
    public static void setUserRole(String userRole) {
        User.userRole = userRole;
    }

    public static void setUserType(String userType) {
        User.userType = userType;
    }
	 
	
	///////////////////////
	//// getter Methods
    ///////////////////////
	public static String getUserName() {
		return User.userName;
	}
	
	public static String getUserRole() {
		return User.userRole;
	}

	public static String getUserType() {
		return User.userType;
	}
	
}