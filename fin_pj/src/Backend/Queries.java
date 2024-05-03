package Backend;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.HashMap;
import java.util.Map;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Queries {

    public static Connection connection;
    private static ResultSet resultSet;

    private static PreparedStatement psCheckCredentials;
    private static PreparedStatement psAddUser;
    private static PreparedStatement psSupplier;
    private static PreparedStatement psgetUserId;
    private static PreparedStatement psCheckUsername;
    private static PreparedStatement psAddress;
    private static PreparedStatement psUpdateUser;
    private static PreparedStatement psCustomer;


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

    public static void updatePassword(String usrname, String newPassword) {   	
        try {
            psUpdateUser = connection.prepareStatement("Update USER set PASSWORD = '" + newPassword 
            										+ "' where USERNAME = '" + usrname + "'");
            psUpdateUser.executeUpdate();
            
        } catch(SQLException se) {
            System.out.println("Could not update UserPassword. " + se.getMessage());
        }     
    }


    public static void addUser(String username, String type, String passwd) {   	
        try {
            LocalDate currentDate = LocalDate.now();
        
            // Format the date to match the MySQL DATE format (YYYY-MM-DD)
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = currentDate.format(formatter);

            psAddUser = connection.prepareStatement("Insert into USER (USERNAME, USER_ROLE, USER_TYPE, "
            									+ "PASSWORD, DATE_CREATED) " + "values (?,?,?,?,?) ");
            psAddUser.setString(1, username);
            psAddUser.setString(2, "Norm");
            psAddUser.setString(3, type);
            psAddUser.setString(4, passwd);
            psAddUser.setString(5, formattedDate);           
            psAddUser.executeUpdate();
            
        } catch(SQLException se) {
            System.out.println("Could not add User. " + se.getMessage());
        }     
    }

    public static long getUserId(String username){
        try {
            psgetUserId = connection.prepareStatement("SELECT user_id FROM USER WHERE USERNAME = ?");
            psgetUserId.setString(1, username);
            resultSet = psgetUserId.executeQuery();

            while(resultSet.next()) {
                return resultSet.getLong("user_id");
            }
        } catch(SQLException se) {
            System.out.println("Could not get userId. " + se.getMessage());
        }  
        return 0;
    }

    public static boolean CheckUsername(String username){
        try {
            psCheckUsername = connection.prepareStatement("SELECT username FROM USER WHERE username = ?");
            psCheckUsername.setString(1, username);
            resultSet = psCheckUsername.executeQuery();

            while(resultSet.next()) {
                return true;
            }
        } catch(SQLException se) {
            System.out.println("Could not get userId. " + se.getMessage());
        }
        return false;  
    }

    public static void addSupplier(String username,String supplierName, String contactFname, String contactLname, 
    								String contactPhone) 
    {   	
        try {
            long userid = 0;
            psgetUserId = connection.prepareStatement("SELECT user_id FROM USER WHERE USERNAME = ?");
            psgetUserId.setString(1, username);
            resultSet = psgetUserId.executeQuery();

            while(resultSet.next()) {
                userid = resultSet.getLong("user_id");
            }

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

    public static Map<String,Object> getSupplier(long userId) {   	    	
        try {
        	// get Supplier details
        	psSupplier = connection.prepareStatement("Select SUPPLIER_ID, SUPPLIER_NAME, CONTACT_FIRSTNAME, "
        			+ "CONTACT_LASTNAME, CONTACT_PHONE from SUPPLIER where user_id = ?");
        	psSupplier.setLong(1, userId);
        	resultSet = psSupplier.executeQuery();

            Map<String,Object> supplierInfo = new HashMap<>();

            while(resultSet.next() == true) {
                long supplierId = resultSet.getLong("SUPPLIER_ID");
                String supplierName = resultSet.getString("SUPPLIER_NAME");
                String contactFirstName = resultSet.getString("CONTACT_FIRSTNAME");
                String contactLastName = resultSet.getString("CONTACT_LASTNAME");
                String contactPhone = resultSet.getString("CONTACT_PHONE");

                // Put the data into the HashMap
                supplierInfo.put("SUPPLIER_ID", supplierId);
                supplierInfo.put("SUPPLIER_NAME", supplierName);
                supplierInfo.put("CONTACT_FIRSTNAME", contactFirstName);
                supplierInfo.put("CONTACT_LASTNAME", contactLastName);
                supplierInfo.put("CONTACT_PHONE", contactPhone);
                
                break;
            }

            return supplierInfo;

        } catch(SQLException se) {
            System.out.println("Could not get the specified Supplier info. " + se.getMessage());
            return new HashMap<>();
        }  
        
    }

    public static Map<String,Object> getSupplierAddress(long supplierID) {   	
    	
        try {
            
            // get Supplier-Address details            
            psAddress = connection.prepareStatement("Select ADDR_STREET, ADDR_CITY, ADDR_STATE, ADDR_COUNTRY, ADDR_ZIPCODE from SUPPLIER_ADDR where SUPPLIER_ID = ?");
            psAddress.setLong(1, supplierID);
        	resultSet = psAddress.executeQuery();

            Map<String,Object> supplierInfo = new HashMap<>();

            while(resultSet.next() == true) {
            	String street = resultSet.getString("ADDR_STREET");
                String city = resultSet.getString("ADDR_CITY");
                String state = resultSet.getString("ADDR_STATE");
                String country = resultSet.getString("ADDR_COUNTRY");
                String zipcode = resultSet.getString("ADDR_ZIPCODE");

                System.out.println(street);

                supplierInfo.put("ADDR_STREET", street);
                supplierInfo.put("ADDR_CITY", city);
                supplierInfo.put("ADDR_STATE", state);
                supplierInfo.put("ADDR_COUNTRY", country);
                supplierInfo.put("ADDR_ZIPCODE", zipcode);   
                
                return supplierInfo;
            }
            
        } catch(SQLException se) {
            System.out.println("Could not get the specified Supplier info. " + se.getMessage());
        }  
        
        return new HashMap<>();
    }

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

    public static Map<String,Object> getCustomer(long user_id) {   	
    	
        try {
        	// get Customer details
        	psCustomer = connection.prepareStatement("Select CUST_ID, FIRST_NAME, LAST_NAME, "
        			+ "PHONE_NUMBER, EMAIL from CUSTOMER where user_id = ?");
        	psCustomer.setLong(1, user_id);
        	resultSet = psCustomer.executeQuery();

            Map<String,Object> customerInfo = new HashMap<>();

            while(resultSet.next() == true) {
            	customerInfo.put("CUST_ID", resultSet.getLong("CUST_ID"));
                customerInfo.put("FIRST_NAME", resultSet.getString("FIRST_NAME"));
                customerInfo.put("LAST_NAME", resultSet.getString("LAST_NAME"));
                customerInfo.put("PHONE_NUMBER", resultSet.getString("PHONE_NUMBER"));
                customerInfo.put("EMAIL", resultSet.getString("EMAIL"));

                return customerInfo;
            }
        } catch(SQLException se) {
            System.out.println("Could not get the specified Customer info. " + se.getMessage());
        }  

        return new HashMap<>();
    }
    
    // handler(): gets a CustomerAddress info. (retrieved Object info can be Viewed)
    public static Map<String,Object> getCustomerAddress(String custPhoneNumber) {   	
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

            Map<String,Object> customerAddr = new HashMap<>();

            while(resultSet.next() == true) {
            	customerAddr.put("ADDR_STREET", resultSet.getString("ADDR_STREET"));
                customerAddr.put("ADDR_CITY", resultSet.getString("ADDR_CITY"));
                customerAddr.put("ADDR_STATE", resultSet.getString("ADDR_STATE"));
                customerAddr.put("ADDR_COUNTRY", resultSet.getString("ADDR_COUNTRY"));
                customerAddr.put("ADDR_ZIPCODE", resultSet.getString("ADDR_ZIPCODE"));

                return customerAddr;
            }
            
        } catch(SQLException se) {
            System.out.println("Could not get the specified Customer info. " + se.getMessage());
        }  
        
        return new HashMap<>();
    }


    ////////////////////////////////////////////////
    ////////////  ITEM Queries  ////////////////////
    ////////////////////////////////////////////////

    public static void addItem(String itemName, String itemDescription, double unitPrice, double discountPercent, long supplierId) {
        try  {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO item (item_name, item_description, item_unit_price, item_discount_percent, supplier_id) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, itemName);
            statement.setString(2, itemDescription);
            statement.setDouble(3, unitPrice);
            statement.setDouble(4, discountPercent);
            statement.setLong(5, supplierId);
            
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addItemStock(long totalQtyPurchased, long totalQtySold, long item_id) {
        try {
            LocalDate currentDate = LocalDate.now();
        
            // Format the date to match the MySQL DATE format (YYYY-MM-DD)
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = currentDate.format(formatter);


            PreparedStatement statement = connection.prepareStatement("INSERT INTO item_stock (total_qty_purchased, total_qty_sold, total_qty_available, stock_status,item_id, date_created) VALUES (?, ?, ?, ?, ?, ?)");

            statement.setLong(1, totalQtyPurchased);
            statement.setLong(2, totalQtySold);
            statement.setLong(3, totalQtyPurchased-totalQtySold);
            if (totalQtyPurchased-totalQtySold > 0) {
            statement.setString(4, "A");
            }
            else{
                statement.setString(4, "NA");
            }
            statement.setLong(5, item_id);
            statement.setString(6, formattedDate);           


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
