package frontend;

public class Supplier {

    private static String supplierName;
    private static String contactFirstname;
    private static String contactLastname;
    private static String contactPhone;
    private static Long supplierID;
   
    // constructor
    public Supplier() {		 
    }
    
    // constructor
    public Supplier(String suppName) {   
        setSupplierName(suppName);
    }
    
    // constructor
    public Supplier(Long supplierID, String suppName, String contactFname, String contactLname, String contactPhone) {   
        setSupplierID(supplierID);
        setSupplierName(suppName);
        setContactFirstname(contactFname);
        setContactLastname(contactLname);
        setContactPhone(contactPhone);
    }

    ///////////////////////
    //// setter Methods
    ///////////////////////
    public static void setSupplierName(String suppName) {
        Supplier.supplierName = suppName;
    }

    public static void setContactFirstname(String contactFname) {
        Supplier.contactFirstname = contactFname;
    }

    public static void setContactLastname(String contactLname) {
        Supplier.contactLastname = contactLname;
    }

    public static void setContactPhone(String contactPhoneNumber) {
        Supplier.contactPhone = contactPhoneNumber;
    }

    public static void setSupplierID(Long supplierID) {
        Supplier.supplierID = supplierID;
    }

    ///////////////////////
    //// getter Methods
    ///////////////////////
    public static String getSupplierName() {
        return Supplier.supplierName;
    }
       
    public static String getContactFirstname() {
            return Supplier.contactFirstname;
        }
           
    public static String getContactLastname() {
            return Supplier.contactLastname;
        }

    public static String getContactPhone() {
            return Supplier.contactPhone;
        }
   
    public static Long getSupplierID() {
            return Supplier.supplierID;
        }
}
