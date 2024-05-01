import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Controller {

	private static Connection connection;

	private static PreparedStatement psUsers;
    private static PreparedStatement psAddUser;
    private static PreparedStatement psUpdateUser;
    private static PreparedStatement psGetUserName;

    private static PreparedStatement psCustomer;
    private static PreparedStatement psSupplier;
    private static PreparedStatement psAddress;

    private static ResultSet resultSet;
    private static ArrayList<User> userList = new ArrayList<>();
    private static ArrayList<Supplier> supplierList = new ArrayList<>();
    private static ArrayList<Customer> customerList = new ArrayList<>();

    //// Handlers w.r.t. User object  
    /*
    * addUser()		 
	* updatePassword()	 	
	* checkUserName()		 
	* getUser()		 	
	* getAllUsers() 
    */
    
    // handler(): Add User along with a Role; such as Admin, Supplier, Customer
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
    
    // check if User-name exists
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
    
    // handler(): gets a User info. (retrieved Object info can be Viewed)
    public static User getUser(String usrname) {   	
    	User userObj = null;
    	
        try {
        	psGetUserName = connection.prepareStatement("Select USERNAME, USER_ROLE, USER_TYPE from USER where USERNAME = ? order by USERNAME");
        	psGetUserName.setString(1, usrname);
        	resultSet = psGetUserName.executeQuery();

            while(resultSet.next() == true) {
            	userObj = new User(resultSet.getString("USERNAME"), 
            			resultSet.getString("USER_ROLE"), resultSet.getString("USER_TYPE"));                        
            }
        } catch(SQLException se) {
            System.out.println("Could not get the specified User. " + se.getMessage());
        }  
        
        return userObj;
    }

    // handler(): to get the list of Users (info may be required by an Admin)
    public static ArrayList<User> getAllUsers() {   	
        ArrayList<User> resultsUsersList = null;       
        try {
        	psUsers = connection.prepareStatement("Select USERNAME, USER_ROLE, USER_TYPE from USER order by USERNAME");
            resultSet = psUsers.executeQuery();

            resultsUsersList = new ArrayList<User>();        
            while(resultSet.next() == true) {
            	resultsUsersList.add(new User(resultSet.getString("USERNAME"), 
            			resultSet.getString("USER_ROLE"), resultSet.getString("USER_TYPE")) );                        
            }
        } catch(SQLException se) {
            System.out.println("Could not get User List. " + se.getMessage());
        }  
        
        return resultsUsersList;
    }

    //// Handlers w.r.t. Supplier object
	/*
	addSupplier() 		 
	addSupplierAddress()	 
	checkSupplierName()		 
	getSupplier() 		 
	getSupplierAddress()	 
	getAllSuppliers()	
	*/
    
    // handler(): Add Supplier along-with their Address
    public static void addSupplier(String supplierName, String contactFname, String contactLname, 
    								String contactPhone, Long userid) 
    {   	
        try {
        	psSupplier = connection.prepareStatement("Insert into SUPPLIER (SUPPLIER_NAME, "
        			+ "CONTACT_FIRSTNAME, CONTACT_LASTNAME, CONTACT_PHONE, USER_ID) " + "values (?,?,?,?,?) ");
        	psSupplier.setString(1, supplierName);
        	psSupplier.setString(2, contactFname);
        	psSupplier.setString(3, contactLname);
        	psSupplier.setString(4, contactPhone);
        	psSupplier.setLong(5, userid);
        	psSupplier.executeUpdate();
            
        } catch(SQLException se) {
            System.out.println("Could not add Supplier. " + se.getMessage());
        }     
    }
    
    // handler(): Add Supplier Address
    public static void addSupplierAddress(String supplierName, String street, String city, String state, String country, String zip) 
    {   	
       	Long supplierID = null;
        try {
        	// get the SUPPLIER_ID
        	psSupplier = connection.prepareStatement("Select SUPPLIER_ID from SUPPLIER where SUPPLIER_NAME = ?");
        	psSupplier.setString(1, supplierName);
        	resultSet = psSupplier.executeQuery();
            while(resultSet.next() == true) {
            	supplierID = resultSet.getLong("SUPPLIER_ID");                        
            }
            
        	// Add the Address for that SUPPLIER_ID
        	psAddress = connection.prepareStatement("Insert into SUPPLIER_ADDR (ADDR_STREET, ADDR_CITY, "
        			+ "ADDR_STATE, ADDR_COUNTRY, ADDR_ZIPCODE, SUPPLIER_ID) " + "values (?,?,?,?,?,?) ");
        	psAddress.setString(1, street);
        	psAddress.setString(2, city);
        	psAddress.setString(3, state);
        	psAddress.setString(4, country);
        	psAddress.setString(5, zip);
        	psAddress.setLong(6, supplierID);
        	psAddress.executeUpdate();
        } catch(SQLException se) {
            System.out.println("Could not add Address for the specified Supplier. " + se.getMessage());
        }     
    }
    
    // check if Supplier-Name exists
    public static Boolean checkSupplierName(String supplierName) {     
        try {
        	psSupplier = connection.prepareStatement("Select SUPPLIER_NAME from SUPPLIER where SUPPLIER_NAME = ?");
        	psSupplier.setString(1, supplierName);
            resultSet = psSupplier.executeQuery();          
            while(resultSet.next() == true) {
                return true;
            }
        } catch(SQLException se) {
        	System.out.println("Could not fetch the Supplier name. " + se.getMessage());
        }
        
        return false;        
    }    
    
    // handler(): gets a Supplier info. (retrieved Object info can be Viewed)
    public static Supplier getSupplier(String supplierName) {   	
    	Supplier supplierObj = null;
    	
        try {
        	// get Supplier details
        	psSupplier = connection.prepareStatement("Select SUPPLIER_ID, SUPPLIER_NAME, CONTACT_FIRSTNAME, "
        			+ "CONTACT_LASTNAME, CONTACT_PHONE from SUPPLIER where SUPPLIER_NAME = ?");
        	psSupplier.setString(1, supplierName);
        	resultSet = psSupplier.executeQuery();

            while(resultSet.next() == true) {
            	supplierObj = new Supplier(resultSet.getLong("SUPPLIER_ID"), resultSet.getString("SUPPLIER_NAME"), 
            			resultSet.getString("CONTACT_FIRSTNAME"), resultSet.getString("CONTACT_LASTNAME"), 
            			resultSet.getString("CONTACT_PHONE"));                        
            }
        } catch(SQLException se) {
            System.out.println("Could not get the specified Supplier info. " + se.getMessage());
        }  
        
        return supplierObj;
    }
    
    // handler(): gets a SupplierAddress info. (retrieved Object info can be Viewed)
    public static SupplierAddress getSupplierAddress(String supplierName) {   	
    	SupplierAddress supplierAddressObj = null;
        Long supplierID = 0L;
    	
        try {
        	// get the SUPPLIER_ID
        	psSupplier = connection.prepareStatement("Select SUPPLIER_ID from SUPPLIER where SUPPLIER_NAME = ?");
        	psSupplier.setString(1, supplierName);
        	resultSet = psSupplier.executeQuery();
            while(resultSet.next() == true) {
            	supplierID = resultSet.getLong("SUPPLIER_ID");                        
            }
            
            // get Supplier-Address details            
            psAddress = connection.prepareStatement("Select ADDR_STREET, ADDR_CITY, ADDR_STATE, ADDR_COUNTRY, ADDR_ZIPCODE from SUPPLIER_ADDR where SUPPLIER_ID = ?");
            psAddress.setLong(1, supplierID);
        	resultSet = psAddress.executeQuery();

            while(resultSet.next() == true) {
            	supplierAddressObj = new SupplierAddress(resultSet.getString("ADDR_STREET"), 
            			resultSet.getString("ADDR_CITY"), resultSet.getString("ADDR_STATE"), 
            			resultSet.getString("ADDR_COUNTRY"), resultSet.getString("ADDR_ZIPCODE") );                        
            }
            
        } catch(SQLException se) {
            System.out.println("Could not get the specified Supplier info. " + se.getMessage());
        }  
        
        return supplierAddressObj;
    }
    
    // handler(): to get the list of Suppliers (info may be required by an Admin)
    public static ArrayList<Supplier> getAllSuppliers() {   	
        ArrayList<Supplier> resultsSuppliersList = null;       
        try {
        	psSupplier = connection.prepareStatement("Select SUPPLIER_ID, SUPPLIER_NAME, CONTACT_FIRSTNAME, "
        										+ "CONTACT_LASTNAME, CONTACT_PHONE from SUPPLIER");
            resultSet = psSupplier.executeQuery();

            resultsSuppliersList = new ArrayList<Supplier>();        
            while(resultSet.next() == true) {
            	resultsSuppliersList.add(new Supplier(resultSet.getLong("SUPPLIER_ID"), 
            			resultSet.getString("SUPPLIER_NAME"), resultSet.getString("CONTACT_FIRSTNAME"),
            			resultSet.getString("CONTACT_LASTNAME"), resultSet.getString("CONTACT_PHONE") ));
            }
        } catch(SQLException se) {
            System.out.println("Could not get Supplier List. " + se.getMessage());
        }  
        
        return resultsSuppliersList;
    }

    //// Handlers w.r.t. Customer object
	/*
	addCustomer() 		 
	addCustomerAddress()	 
	checkCustomerPhone()		 
	getCustomer() 		 
	getCustomerAddress()	 
	getAllCustomers()	
	*/
    
    // handler(): Add Customer along-with their Address
    public static void addCustomer(String custFname, String custLname, 
    								String custPhone, String custEmail, Long userid) 
    {   	
        try {
        	psCustomer = connection.prepareStatement("Insert into CUSTOMER (FIRST_NAME, "
        			+ "LAST_NAME, PHONE_NUMBER, EMAIL, USER_ID) " + "values (?,?,?,?,?) ");
        	psCustomer.setString(1, custFname);
        	psCustomer.setString(2, custLname);
        	psCustomer.setString(3, custPhone);
        	psCustomer.setString(4, custEmail);
        	psCustomer.setLong(5, userid);
        	psCustomer.executeUpdate();
            
        } catch(SQLException se) {
            System.out.println("Could not add Customer. " + se.getMessage());
        }     
    }
    
    // handler(): Add Customer Address
    public static void addCustomerAddress(String custPhone, String street, String city, String state, String country, String zip) 
    {   	
       	Long customerID = null;
        try {
        	// get the CUST_ID
        	psCustomer = connection.prepareStatement("Select CUST_ID from CUSTOMER where PHONE_NUMBER = ?");
        	psCustomer.setString(1, custPhone);
        	resultSet = psCustomer.executeQuery();
            while(resultSet.next() == true) {
            	customerID = resultSet.getLong("CUST_ID");                        
            }
            
        	// Add the Address for that CUST_ID
        	psAddress = connection.prepareStatement("Insert into CUST_ADDR (ADDR_STREET, ADDR_CITY, "
        			+ "ADDR_STATE, ADDR_COUNTRY, ADDR_ZIPCODE, CUST_ID) " + "values (?,?,?,?,?,?) ");
        	psAddress.setString(1, street);
        	psAddress.setString(2, city);
        	psAddress.setString(3, state);
        	psAddress.setString(4, country);
        	psAddress.setString(5, zip);
        	psAddress.setLong(6, customerID);
        	psAddress.executeUpdate();
        } catch(SQLException se) {
            System.out.println("Could not add Address for the specified Customer. " + se.getMessage());
        }     
    }
    
    // check if Customer-Phone Number exists
    public static Boolean checkCustomerPhone(String custPhoneNumber) {     
        try {
        	psCustomer = connection.prepareStatement("Select PHONE_NUMBER from CUSTOMER where PHONE_NUMBER = ?");
        	psCustomer.setString(1, custPhoneNumber);
            resultSet = psCustomer.executeQuery();          
            while(resultSet.next() == true) {
                return true;
            }
        } catch(SQLException se) {
        	System.out.println("Could not fetch the Customer Phone number. " + se.getMessage());
        }
        
        return false;        
    }    
    
    // handler(): gets a Customer info. (retrieved Object info can be Viewed)
    public static Customer getCustomer(String custPhoneNumber) {   	
    	Customer customerObj = null;
    	
        try {
        	// get Customer details
        	psCustomer = connection.prepareStatement("Select CUST_ID, FIRST_NAME, LAST_NAME, "
        			+ "PHONE_NUMBER, EMAIL from CUSTOMER where PHONE_NUMBER = ?");
        	psCustomer.setString(1, custPhoneNumber);
        	resultSet = psCustomer.executeQuery();

            while(resultSet.next() == true) {
            	customerObj = new Customer(resultSet.getLong("CUST_ID"), resultSet.getString("FIRST_NAME"), 
            			resultSet.getString("LAST_NAME"), resultSet.getString("PHONE_NUMBER"), 
            			resultSet.getString("EMAIL"));                        
            }
        } catch(SQLException se) {
            System.out.println("Could not get the specified Customer info. " + se.getMessage());
        }  
        
        return customerObj;
    }
    
    // handler(): gets a CustomerAddress info. (retrieved Object info can be Viewed)
    public static CustomerAddress getCustomerAddress(String custPhoneNumber) {   	
    	CustomerAddress customerAddressObj = null;
        Long customerID = 0L;
    	
        try {
        	// get the CUST_ID
        	psCustomer = connection.prepareStatement("Select CUST_ID from CUSTOMER where PHONE_NUMBER = ?");
        	psCustomer.setString(1, custPhoneNumber);
        	resultSet = psCustomer.executeQuery();
            while(resultSet.next() == true) {
            	customerID = resultSet.getLong("CUST_ID");                        
            }
            
            // get Customer-Address details            
            psAddress = connection.prepareStatement("Select ADDR_STREET, ADDR_CITY, ADDR_STATE, ADDR_COUNTRY, ADDR_ZIPCODE from CUST_ADDR where CUST_ID = ?");
            psAddress.setLong(1, customerID);
        	resultSet = psAddress.executeQuery();

            while(resultSet.next() == true) {
            	customerAddressObj = new CustomerAddress(resultSet.getString("ADDR_STREET"), 
            			resultSet.getString("ADDR_CITY"), resultSet.getString("ADDR_STATE"), 
            			resultSet.getString("ADDR_COUNTRY"), resultSet.getString("ADDR_ZIPCODE") );                        
            }
            
        } catch(SQLException se) {
            System.out.println("Could not get the specified Customer info. " + se.getMessage());
        }  
        
        return customerAddressObj;
    }
    
    // handler(): to get the list of Customers (info may be required by an Admin)
    public static ArrayList<Customer> getAllCustomers() {   	
        ArrayList<Customer> resultsCustomersList = null;       
        try {
        	psCustomer = connection.prepareStatement("Select CUST_ID, FIRST_NAME, LAST_NAME, "
        										+ "PHONE_NUMBER, EMAIL from CUSTOMER");
            resultSet = psCustomer.executeQuery();

            resultsCustomersList = new ArrayList<Customer>();        
            while(resultSet.next() == true) {
            	resultsCustomersList.add(new Customer(resultSet.getLong("CUST_ID"), 
            			resultSet.getString("FIRST_NAME"), resultSet.getString("LAST_NAME"),
            			resultSet.getString("PHONE_NUMBER"), resultSet.getString("EMAIL") ));
            }
        } catch(SQLException se) {
            System.out.println("Could not get Supplier List. " + se.getMessage());
        }  
        
        return resultsCustomersList;
    }
    
    ////
    //// Handlers w.r.t. Stock, Item, Order objects
    ////
    
    
    
    
    
////////////////////////////////////////////

    ////////////////////////////////////////
    //// Main method(): To Test the handlers
    ////////////////////////////////////////
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
    ////// Call to test DB handler()... 
		
		// getConnection()
    	connection = DBConnection.getConnection();
    	System.out.println("DB Connected is : " + connection); 
    	
    //////
    ////// Test the function handlers()...     	
    ////// 
    	Boolean result;
   	
    	// Call checkUserName() and display result
        result = Controller.checkUserName("user10");
        if (result == true)
        	System.out.println("Username already exist. Please try another Username");
        
    	// Call addUser()
        Controller.addUser("user10", "Supplier", "S", "passwd10", "2024-05-01");
        
        // Call updatePassword()
        Controller.updatePassword("user10", "new-Pwd10");
		
    	// Call getUser()
        User usrObj = null;
        usrObj = Controller.getUser("user10");
        System.out.println("The Role of Username " + usrObj.getUserName() + " is : " + usrObj.getUserRole());
        
        // Call getAllUsers() and display the Users List
        userList = Controller.getAllUsers();        
        for (int i=0; i<userList.size(); i++) {
        	System.out.println("Username in the userList : " + userList.get(i).getUserName());
        }

       
	////// Call and Test : Supplier handlers().....        
        String suppName = "Clorax";
        
    	// Call checkSupplier() and display result
        result = Controller.checkSupplierName(suppName);
        if (result == true)
        	System.out.println("Supplier name already exist. Please try another Supplier name");
        
// Manually, fetch userid    
        // Call addSupplier()
        Controller.addSupplier(suppName, "Mark", "Jonas", "7021110000", 6L); // say userid = 38
        
        // addSupplierAddress()
        Controller.addSupplierAddress(suppName, "801 TiffanyStreet", "Brooklyn", "NewYork", "USA", "11202");

    	// Call getSupplier() 		
        Supplier supplierObj = null;
        supplierObj = Controller.getSupplier(suppName);
        System.out.println("The Contact name of Supplier " + supplierObj.getContactFirstname() + " " + supplierObj.getContactLastname() );
        
    	// Call getSupplierAddress()
        SupplierAddress supplierAddressObj = null;
        supplierAddressObj = Controller.getSupplierAddress(suppName);
        System.out.println("The Supplier's City address is " + supplierAddressObj.getSupplierCity() );
        
        // Call getAllSuppliers() and display the Suppliers List
        supplierList = Controller.getAllSuppliers();        
        for (int i=0; i<supplierList.size(); i++) {
        	System.out.println("Supplier name in the supplierList : " + supplierList.get(i).getSupplierName());
        }


   	////// Call and Test : Customer handlers().....        
        String custPhone = "7321119999";

    	// Call checkCustomerPhone() and display result
        result = Controller.checkCustomerPhone(custPhone);
        if (result == true)
        	System.out.println("Customer already exist. Please try another Customer Phone number.");
        
// Manually, fetch userid
        // Call addCustomer()
        Controller.addCustomer("Smith", "Scott", custPhone, "ss@ggmail.com", 6L); // say userid = 6        
        // addCustomerAddress()
        Controller.addCustomerAddress(custPhone, "111 Bouldyard", "Manhattan", "NewYork", "USA", "11401");

    	// Call getCustomer() 		
        Customer customerObj = null;
        customerObj = Controller.getCustomer(custPhone);
        System.out.println("The name of Customer is : " + customerObj.getFirstname() + " " + customerObj.getLastname() );
        
    	// Call getCustomerAddress()
        CustomerAddress customerAddressObj = null;
        customerAddressObj = Controller.getCustomerAddress(custPhone);
        System.out.println("The Customer's City address is " + customerAddressObj.getCustomerCity() );
        
        // Call getAllCustomers() and display the Customers List
        customerList = Controller.getAllCustomers();        
        for (int i=0; i<customerList.size(); i++) {
        	System.out.println("Customers in the customerList : " + customerList.get(i).getFirstname());
        }

    ////// Call and Test : Stock, Item, Order related handlers().....      
    //////     
    
        
	}
    
}
