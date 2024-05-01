package frontend;

public class SupplierAddress {

    private static String supplierAddrStreet;
    private static String supplierAddrCity;
    private static String supplierAddrState;
    private static String supplierAddrCountry;
    private static String supplierAddrZipcode;
       
    // constructor
    public SupplierAddress() {		 		 
    }
    
    // constructor
    public SupplierAddress(String supplierStreet, String supplierCity, String supplierState, 
            String supplierCountry, String supplierZipcode) {   
        setSupplierStreet(supplierStreet);
        setSupplierCity(supplierCity);
        setSupplierState(supplierState);
        setSupplierCountry(supplierCountry);
        setSupplierZipcode(supplierZipcode);
    }

    ///////////////////////
    //// setter Methods
    ///////////////////////
    public static void setSupplierStreet(String supplierStreet) {
        SupplierAddress.supplierAddrStreet = supplierStreet;
    }
    
    public static void setSupplierCity(String supplierCity) {
        SupplierAddress.supplierAddrCity = supplierCity;
    }
    
    public static void setSupplierState(String supplierState) {
        SupplierAddress.supplierAddrState = supplierState;
    }
    
    public static void setSupplierCountry(String supplierCountry) {
        SupplierAddress.supplierAddrCountry = supplierCountry;
    }
    
    public static void setSupplierZipcode(String supplierZipcode) {
        SupplierAddress.supplierAddrZipcode = supplierZipcode;
    }

    ///////////////////////
    //// getter Methods
    ///////////////////////
    public static String getSupplierStreet() {
        return SupplierAddress.supplierAddrStreet;
    }
   
    public static String getSupplierCity() {
            return SupplierAddress.supplierAddrCity;
    }
    
    public static String getSupplierState() {
            return SupplierAddress.supplierAddrState;
    }
    
    public static String getSupplierCountry() {
            return SupplierAddress.supplierAddrCountry;
    }
    
    public static String getSupplierZipcode() {
            return SupplierAddress.supplierAddrZipcode;
    }
    
}
