package frontend;

public class address {

    private static String supplierAddrStreet;
    private static String supplierAddrCity;
    private static String supplierAddrState;
    private static String supplierAddrCountry;
    private static String supplierAddrZipcode;
       
    // constructor
    public address() {		 		 
    }
    
    // constructor
    public address(String supplierStreet, String supplierCity, String supplierState, 
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
        address.supplierAddrStreet = supplierStreet;
    }
    
    public static void setSupplierCity(String supplierCity) {
        address.supplierAddrCity = supplierCity;
    }
    
    public static void setSupplierState(String supplierState) {
        address.supplierAddrState = supplierState;
    }
    
    public static void setSupplierCountry(String supplierCountry) {
        address.supplierAddrCountry = supplierCountry;
    }
    
    public static void setSupplierZipcode(String supplierZipcode) {
        address.supplierAddrZipcode = supplierZipcode;
    }

    ///////////////////////
    //// getter Methods
    ///////////////////////
    public static String getSupplierStreet() {
        return address.supplierAddrStreet;
    }
   
    public static String getSupplierCity() {
            return address.supplierAddrCity;
    }
    
    public static String getSupplierState() {
            return address.supplierAddrState;
    }
    
    public static String getSupplierCountry() {
            return address.supplierAddrCountry;
    }
    
    public static String getSupplierZipcode() {
            return address.supplierAddrZipcode;
    }
    
}
