package frontend;

public class Customer {

    private static String custFirstname;
    private static String custLastname;
    private static String custPhoneNumber;	// Unique
    private static String custEmail;
    private static Long customerID;

    // constructor
    public Customer() {		 
    }
    
    // constructor
    public Customer(String phoneNum) {   
        setPhoneNumber(phoneNum);
    }

    // constructor
    public Customer(Long custID, String firstname, String lastname, String phoneNum, String email) {	 
        setCustomerID(custID);
        setFirstname(firstname);
        setLastname(lastname);
        setPhoneNumber(phoneNum);
        setEmail(email);
    }

    ///////////////////////
    //// setter Methods
    ///////////////////////
    public static void setPhoneNumber(String phoneNum) {
        Customer.custPhoneNumber = phoneNum;
    }

    public static void setFirstname(String firstname) {
        Customer.custFirstname = firstname;
    }

    public static void setLastname(String lastname) {
        Customer.custLastname = lastname;
    }

    public static void setEmail(String email) {
        Customer.custEmail = email;
    }
    
    public static void setCustomerID(Long custID) {
        Customer.customerID = custID;
    }

    ///////////////////////
    //// getter Methods
    ///////////////////////
    public static String getPhoneNumber() {
        return Customer.custPhoneNumber;
    }

    public static String getFirstname() {
        return Customer.custFirstname;
    }

    public static String getLastname() {
        return Customer.custLastname;
    }

    public static String getEmail() {
        return Customer.custEmail;
    }
    
    public static Long getCustomerID() {
        return Customer.customerID;
    }

}