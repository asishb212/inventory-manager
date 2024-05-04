import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Controller {

	private static Connection connection;

	private static PreparedStatement psUsers;
    private static PreparedStatement psAddUser;
    private static PreparedStatement psUpdateUser;
    private static PreparedStatement psGetUserName;

    private static PreparedStatement psCustomer;
    private static PreparedStatement psSupplier;
    private static PreparedStatement psAddress;
    private static PreparedStatement psItem;
    private static PreparedStatement psItemStock;
    private static PreparedStatement psOrder;
    private static PreparedStatement psOrderItems;

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
    								String contactPhone, long userid) 
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
       	long supplierID = 0L;
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
        long supplierID = 0L;
    	
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
    								String custPhone, String custEmail, long userid) 
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
       	long customerID = 0L;
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
        long customerID = 0L;
    	
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
    
    //// Handlers w.r.t. Item and ItemStock objects
	/*
	addItem() 		 
	addItemStock()	 	
	getItem()
	getItemStock()	 
	updateItemStock()
	*/

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

            PreparedStatement statement = connection.prepareStatement("INSERT INTO item_stock (total_qty_purchased, "
            		+ "total_qty_sold, total_qty_available, stock_status, item_id, date_created) "
            		+ "VALUES (?, ?, ?, ?, ?, ?)");

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
    
////////// Get Item based on selected itemName and its supplierName i.e, BrandName
    // handler(): gets a Item info. (retrieved Object info can be Viewed)
    public static Item getItem(String itemName, String supplierName) {   	
    	Item itemObj = null;
    	
        try {
        	// get Item details
        	psItem = connection.prepareStatement("Select ITEM_ID, ITEM_NAME, ITEM_DESCRIPTION, "
        			+ "ITEM_UNIT_PRICE, ITEM_DISCOUNT_PERCENT, SUPPLIER_ID "
        			+ "from ITEM where ITEM_NAME = ? and SUPPLIER_ID = select SUPPLIER_ID from SUPPLIER"
        			+ "where SUPPLIER_NAME = ?");
        	psItem.setString(1, itemName);
        	psItem.setString(2, supplierName);
        	resultSet = psItem.executeQuery();

            while(resultSet.next() == true) {
            	itemObj = new Item(resultSet.getLong("ITEM_ID"), resultSet.getString("ITEM_NAME"), 
            			resultSet.getString("ITEM_DESCRIPTION"), resultSet.getDouble("ITEM_UNIT_PRICE"), 
            			resultSet.getDouble("ITEM_DISCOUNT_PERCENT"), resultSet.getLong("SUPPLIER_ID"));                        
            }
        } catch(SQLException se) {
            System.out.println("Could not get the specified Item info. " + se.getMessage());
        }  
        
        return itemObj;
    }
////////// Get ItemStock()
    // handler(): gets a ItemStock info. (retrieved Object info can be Viewed)
    public static ItemStock getItemStock(long itemId) {   	
    	ItemStock itemStockObj = null;
    	
        try {
        	// get ItemStock details
        	psItemStock = connection.prepareStatement("Select STOCK_ID, TOTAL_QTY_PURCHASED, "
							        			+ "TOTAL_QTY_SOLD, TOTAL_QTY_AVAILABLE, STOCK_STATUS "
							        			+ "from ITEM_STOCK where ITEM_ID = ?");
        	psItemStock.setLong(1, itemId);
        	resultSet = psItemStock.executeQuery();

            while(resultSet.next() == true) {
            	itemStockObj = new ItemStock(resultSet.getLong("STOCK_ID"), resultSet.getLong("TOTAL_QTY_PURCHASED"), 
            			resultSet.getLong("TOTAL_QTY_SOLD"), resultSet.getLong("TOTAL_QTY_AVAILABLE"), 
            			resultSet.getString("STOCK_STATUS"), resultSet.getLong("ITEM_ID"));                        
            }
        } catch(SQLException se) {
            System.out.println("Could not get the specified ItemStock info. " + se.getMessage());
        }  
        
        return itemStockObj;
    }

////////// Update ItemStock()
    public static void updateItemStock(long totalQtyPurchased, long totalQtySold, long item_id) {
        try {
            LocalDate currentDate = LocalDate.now();
            // Format the date to match the MySQL DATE format (YYYY-MM-DD)
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = currentDate.format(formatter);

            double totalQtyAvailable;
            String stockStatus;
            ItemStock itemStockObj = null;
            
            itemStockObj = getItemStock(item_id);
        	totalQtyAvailable = itemStockObj.getTotalQtyAvailable();
            
            // Purchase Order (Means, the Order is made by Supplier)
            if (totalQtySold == 0.00) {          	
            	totalQtyAvailable = totalQtyAvailable + totalQtyPurchased;
            }

            // Sales Order (Means, the Order is made by Customer)
            if (totalQtyPurchased == 0.00) {
            	totalQtyAvailable = totalQtyAvailable - totalQtySold;
            }

            if (totalQtyAvailable > 0)
            	stockStatus = "A";		// Items Stock is Available
            else 
            	stockStatus = "NA";	    // Items Stock is Not Available        
	            
            psUpdateUser = connection.prepareStatement("Update ITEM_STOCK set "
								            		+ "TOTAL_QTY_PURCHASED = " + totalQtyPurchased 
								            		+ "TOTAL_QTY_SOLD = " + totalQtySold 
								            		+ "TOTAL_QTY_AVAILABLE = " + totalQtyAvailable
								            		+ "STOCK_STATUS = " + stockStatus
								            		+ "DATE_MODIFIED = " + formattedDate 
													+ " where ITEM_ID = " + item_id );

            psUpdateUser.executeUpdate();

        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
    }    

////////// Add Order, OrderItems and Update the ItemStock (Note: OrderType is "PURCHASE" or "SALES")
//// Handlers w.r.t. Item, Order, OrderItems, and ItemStock objects
	/*
	addOrder()
	getOrder()
	updateOrder()
	addOrderItems
	getAllOrderItems	
	updateInventoryOnOrder()	// method call : On click of Purchase Order or Sales Order	 

	viewItemStockList()	 
	*/
    public static void addOrder(long userid, String orderType, LocalDate currentDate) {
       
        // Format the date to match the MySQL DATE format (YYYY-MM-DD)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);

    	String orderStatus = "PAID"; 
    	double taxPercent = 10.0;
    	double orderSubtotal = 0.00;
    	double orderTotalAmount = 0.00;
    	
		//// Insert into ORDER table
    	try {
			psOrder = connection.prepareStatement("Insert into ORDER (ORDER_TYPE, ORDER_STATUS, "
					+ "SUB_TOTAL, TAX_PERCENT, TOTAL_AMOUNT, USER_ID, DATE_CREATED) " 
		    		+ "values (?,?,?,?,?,?,?) ");
			psOrder.setString(1, orderType); 
			psOrder.setString(2, orderStatus); 
			psOrder.setDouble(3, orderSubtotal);
			psOrder.setDouble(4, taxPercent);
			psOrder.setDouble(5, orderTotalAmount);
			psOrder.setLong(6, userid);
			psOrder.setString(7, formattedDate);  
		
			psOrder.executeUpdate();

    	} catch(SQLException se) {
			System.out.println("Could not place the Order and update item Stock. " + se.getMessage());
		}      	
    	
    }
    
    public static Order getOrder(long userid, String orderType, LocalDate createdDate) {
    	
    	Order orderObj = null;
    	
        // Format the date to match the MySQL DATE format (YYYY-MM-DD)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = createdDate.format(formatter);
    	
        try {
        	// get Order details
        	psOrder = connection.prepareStatement("Select ORDER_ID, ORDER_TYPE, ORDER_STATUS,"
							        			+ "SUB_TOTAL, TAX_PERCENT, TOTAL_AMOUNT, USER_ID, DATE_CREATED "
							        			+ "from ITEM_STOCK where USER_ID = ? and "
							        			+ "ORDER_TYPE = ? and DATE_CREATED = ? order by DATE_CREATED desc");
        	psOrder.setLong(1, userid);
        	psOrder.setString(2, orderType);
        	psOrder.setString(3, formattedDate);
        	resultSet = psOrder.executeQuery();

            while(resultSet.next() == true) {
            	orderObj = new Order(resultSet.getLong("ORDER_ID"), resultSet.getString("ORDER_TYPE"), 
            			resultSet.getString("ORDER_STATUS"), resultSet.getDouble("SUB_TOTAL"), 
            			resultSet.getDouble("TAX_PERCENT"), resultSet.getDouble("TOTAL_AMOUNT"),
            			resultSet.getLong("USER_ID") );                        
            }
        } catch(SQLException se) {
            System.out.println("Could not get the specified ItemStock info. " + se.getMessage());
        }  
        
        return orderObj;
    }
    
    public static void updateOrder(long orderId, double itemOrderTotal) {
        try {
        	double orderSubtotal = itemOrderTotal;
        	double taxPercent = 10.0; 		// arbitrary value
        	double orderTotalAmount = orderSubtotal + (orderSubtotal * (taxPercent/100));
        	
       		// Update into ORDER table with count of all items' Total
            psUpdateUser = connection.prepareStatement("Update ORDER set "
								            		+ " SUB_TOTAL = " + orderSubtotal 
								            		+ " TAX_PERCENT = " + taxPercent 
								            		+ " TOTAL_AMOUNT = " + orderTotalAmount
													+ " where ORDER_ID = " + orderId );
            psUpdateUser.executeUpdate();

        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
    	
    }

    public static void addOrderItems(long userid, String orderType, 
								String itemName, String supplierName, long itemOrderedQty) 
    {

        // call getItem()
		Item itemObj = getItem(itemName, supplierName);    		
		long itemId = itemObj.getItemId();
		
		double itemOrderSubtotal = itemObj.getItemUnitPrice() * itemOrderedQty;
		double itemDiscountAmount = itemOrderSubtotal * (itemObj.getItemDiscountPercent() / 100);
		double itemOrderTotal = itemOrderSubtotal - itemDiscountAmount;
		
		// call getItemStock()
        ItemStock itemStockObj = getItemStock(itemId);
    	long stockId = itemStockObj.getStockId();
   	
    	// call getOrder()
        LocalDate currentDate = LocalDate.now();
    	Order orderobj = getOrder(userid, orderType, currentDate);
    	long orderId = orderobj.getOrderID();
    	
		//// Insert into ORDER_ITEMS table
    	try {
			psOrderItems = connection.prepareStatement("Insert into ORDER_ITEMS (ITEM_ORDERED_QTY, "
		    		+ "ITEM_ORDER_SUBTOTAL, ITEM_DISCOUNT_AMOUNT, ITEM_ORDER_TOTAL, ITEM_ORDER_TYPE, "
		    		+ "ORDER_ID, ITEM_ID, STOCK_ID) " + "values (?,?,?,?,?, ?,?,?) ");
			psOrderItems.setLong(1, itemOrderedQty);
			psOrderItems.setDouble(2, itemOrderSubtotal);
			psOrderItems.setDouble(3, itemDiscountAmount);
			psOrderItems.setDouble(4, itemOrderTotal);
			if (orderType.equalsIgnoreCase("PURCHASE"))
				psOrderItems.setString(5, "IN");  
			else if (orderType.equalsIgnoreCase("SALES"))
				psOrderItems.setString(5, "OUT");  
			psOrderItems.setLong(6, orderId);
			psOrderItems.setLong(7, itemId);
			psOrderItems.setLong(8, stockId);
			
			psOrderItems.executeUpdate();
		
    	} catch(SQLException se) {
			System.out.println("Could not place the Order and update item Stock. " + se.getMessage());
		}      	
    	
    }
    public static OrderItems[] getAllOrderItems(long orderId) {
    	OrderItems orderItemsObj[] = null;
    	
        try {
        	// get OrderItems details
        	psOrderItems = connection.prepareStatement("Select ORDER_ITEM_ID, ITEM_ORDERED_QTY, "
        								+ "ITEM_ORDER_SUBTOTAL, ITEM_DISCOUNT_AMOUNT, "
        								+ "ITEM_ORDER_TOTAL, ITEM_ORDER_TYPE, "
							        	+ "ORDER_ID, ITEM_ID, STOCK_ID "
							        	+ "from ORDER_ITEMS where ORDER__ID = ? "
							        	+ "order by DATE_CREATED desc");
        	psOrderItems.setLong(1, orderId);
        	resultSet = psOrderItems.executeQuery();
        	
        	int i=0;
            while(resultSet.next() == true) {
            	orderItemsObj[i] = new OrderItems(resultSet.getLong("ORDER_ITEM_ID"), resultSet.getLong("ITEM_ORDERED_QTY"), 
            			resultSet.getDouble("ITEM_ORDER_SUBTOTAL"),resultSet.getDouble("ITEM_DISCOUNT_AMOUNT"), 
            			resultSet.getDouble("ITEM_ORDER_TOTAL"), 
            			resultSet.getString("ITEM_ORDER_TYPE"), resultSet.getLong("ORDER_ID"),
            			resultSet.getLong("ITEM_ID"), resultSet.getLong("STOCK_ID") );  
            	i++;
            }
        } catch(SQLException se) {
            System.out.println("Could not get the specified ItemStock info. " + se.getMessage());
        }  
        
    	
    	return orderItemsObj;
    }
    
    // updateInventoryStockOnOrder() : For PURCHASE Order or SALES Order, call this method() 
    public static void updateInventoryStockOnOrder(long userid, String orderType, 
    										String itemName, String supplierName, long itemOrderedQty ) {
    	try {
            LocalDate currentDate = LocalDate.now();
            
            // call addOrder()
    		addOrder(userid, orderType, currentDate);
    		
    		// call getOrder()
    		Order orderObj = getOrder(userid, orderType, currentDate);
    		long orderId = orderObj.getOrderID();
    		
    		// call addOrderItems() - Every time an OrderItem is saved in cart for this OrderId
    		addOrderItems(orderId, orderType, itemName, supplierName, itemOrderedQty);
    		
    		// call getAllOrderItems()
    		OrderItems orderItemsArray[];
    		orderItemsArray = getAllOrderItems(orderId);
    		
    		double itemOrderTotal=0.00;
    		String itemOrderType;
    		long itemId;
    		
    		for (int i=0; i<orderItemsArray.length; i++) {
    			
    			itemOrderTotal = itemOrderTotal + orderItemsArray[i].getItemOrderTotal();
    			
    			itemOrderType = orderItemsArray[i].getItemOrderType(); // IN or OUT
    			itemId = orderItemsArray[i].getItemId();
    			
    			//// call updateItemStock()  : To update ITEM_STOCK table 
        	    if (itemOrderType.equalsIgnoreCase("IN")) {
        	    	long totalQtyPurchased = itemOrderedQty;
        	    	long totalQtySold = 0;
        	    	updateItemStock(totalQtyPurchased, totalQtySold, itemId);
        	    }     
        	    else if (itemOrderType.equalsIgnoreCase("OUT")) {
        	    	long totalQtyPurchased = 0;
        	    	long totalQtySold = itemOrderedQty;
        	    	updateItemStock(totalQtyPurchased, totalQtySold, itemId);
        	    }     
        	    
    		}
    		
    		// call updateOrder()
    		updateOrder(orderId, itemOrderTotal);
  	 	    
		} catch(Exception e) {
			System.out.println("Could not place the Order and update item Stock. " + e.getMessage());
		}      	
    }
    
    ////////// View Inventory Stock items 
    public static void viewItemStockList() {
    
    	
    }

   
    
    ////////////////////////////////////////
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
        
        // Call getAllUsers() and display the Users List along with their Roles
        userList = Controller.getAllUsers();        
        for (int i=0; i<userList.size(); i++) {
        	System.out.println("Username in the userList : " + userList.get(i).getUserName() + "\t" + userList.get(i).getUserRole());
        }

       
	////// Call and Test : Supplier handlers().....        
        String suppName = "Clorax";
        
    	// Call checkSupplier() and display result
        result = Controller.checkSupplierName(suppName);
        if (result == true)
        	System.out.println("Supplier name already exist. Please try another Supplier name");
        
// Manually, fetch userid    
        // Call addSupplier()
        Controller.addSupplier(suppName, "Mark", "Jonas", "7021110000", 38L); // say userid = 38
        
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
        Controller.addCustomer("Smith", "Scott", custPhone, "ss@ggmail.com", 37L); // say userid = 37
        
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
