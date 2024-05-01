// CustomerAddress Class that represents the CUST_ADDR table in the repository inventory_db

public class CustomerAddress {

	 private String custAddrStreet;
	 private String custAddrCity;
	 private String custAddrState;
	 private String custAddrCountry;
	 private String custAddrZipcode;

	 // constructor
	 public CustomerAddress() {		 		 
	 }
	 
	 // constructor
	 public CustomerAddress(String custStreet, String custCity, String custState, 
			 				String custCountry, String custZipcode) {   
		 setCustomerStreet(custStreet);
		 setCustomerCity(custCity);
		 setCustomerState(custState);
		 setCustomerCountry(custCountry);
		 setCustomerZipcode(custZipcode);
	 }

	 ///////////////////////
	 //// setter Methods
	 ///////////////////////

	 public void setCustomerStreet(String custStreet) {
		 this.custAddrStreet = custStreet;
	 }
	 
	 public void setCustomerCity(String custCity) {
		 this.custAddrCity = custCity;
	 }
	 
	 public void setCustomerState(String custState) {
		 this.custAddrState = custState;
	 }
	 
	 public void setCustomerCountry(String custCountry) {
		 this.custAddrCountry = custCountry;
	 }
	 
	 public void setCustomerZipcode(String custZipcode) {
		 this.custAddrZipcode = custZipcode;
	 }

	 ///////////////////////
	 //// getter Methods
	 ///////////////////////
	 public String getCustomerStreet() {
	 	return this.custAddrStreet;
	 }
	
	 public String getCustomerCity() {
		 	return this.custAddrCity;
	 }
	 
	 public String getCustomerState() {
		 	return this.custAddrState;
	 }
	 
	 public String getCustomerCountry() {
		 	return this.custAddrCountry;
	 }
	 
	 public String getCustomerZipcode() {
		 	return this.custAddrZipcode;
	 }
	 

}
