// SupplierAddress Class that represents the SUPPLIER_ADDR table in the repository inventory_db

public class SupplierAddress {

	 private String supplierAddrStreet;
	 private String supplierAddrCity;
	 private String supplierAddrState;
	 private String supplierAddrCountry;
	 private String supplierAddrZipcode;
		
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
	 public void setSupplierStreet(String supplierStreet) {
		 this.supplierAddrStreet = supplierStreet;
	 }
	 
	 public void setSupplierCity(String supplierCity) {
		 this.supplierAddrCity = supplierCity;
	 }
	 
	 public void setSupplierState(String supplierState) {
		 this.supplierAddrState = supplierState;
	 }
	 
	 public void setSupplierCountry(String supplierCountry) {
		 this.supplierAddrCountry = supplierCountry;
	 }
	 
	 public void setSupplierZipcode(String supplierZipcode) {
		 this.supplierAddrZipcode = supplierZipcode;
	 }

	 ///////////////////////
	 //// getter Methods
	 ///////////////////////
	 public String getSupplierStreet() {
	 	return this.supplierAddrStreet;
	 }
	
	 public String getSupplierCity() {
		 	return this.supplierAddrCity;
	 }
	 
	 public String getSupplierState() {
		 	return this.supplierAddrState;
	 }
	 
	 public String getSupplierCountry() {
		 	return this.supplierAddrCountry;
	 }
	 
	 public String getSupplierZipcode() {
		 	return this.supplierAddrZipcode;
	 }
	 
}
