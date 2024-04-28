
// User Class that represents the USER table in the repository inventory_db
public class User {

	 private String usrName;
	 private String usrRole;
	 private String usrType;
	 private String password;

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
    public void setUserName(String userName) {
        this.usrName = userName;
    }
        
    public void setUserRole(String userRole) {
        this.usrRole = userRole;
    }

    public void setUserType(String userType) {
        this.usrType = userType;
    }
	 
    public void setPassword(String passwrd) {
        this.password = passwrd;
    }
	
	///////////////////////
	//// getter Methods
    ///////////////////////
	public String getUserName() {
		return this.usrName;
	}
	
	public String getUserRole() {
		return this.usrRole;
	}

	public String getUserType() {
		return this.usrType;
	}
	
	public String getPassword() {
		return this.password;
	}
	
}