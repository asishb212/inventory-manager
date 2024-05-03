// Customer Class that represents the CUSTOMER table in the repository inventory_db

public class Customer {

	 private String custFirstname;
	 private String custLastname;
	 private String custPhoneNumber;	// Unique
	 private String custEmail;
	 private long 	customerID;

	 // constructor
	 public Customer() {		 
	 }
	 
	 // constructor
	 public Customer(String phoneNum) {   
		 setPhoneNumber(phoneNum);
	 }

	 // constructor
	 public Customer(long custID, String firstname, String lastname, String phoneNum, String email) {	 
		 setCustomerID(custID);
		 setFirstname(firstname);
		 setLastname(lastname);
		 setPhoneNumber(phoneNum);
		 setEmail(email);
	 }

	 ///////////////////////
	 //// setter Methods
	 ///////////////////////
	 public void setPhoneNumber(String phoneNum) {
		 this.custPhoneNumber = phoneNum;
	 }

	 public void setFirstname(String firstname) {
		 this.custFirstname = firstname;
	 }

	 public void setLastname(String lastname) {
		 this.custLastname = lastname;
	 }

	 public void setEmail(String email) {
		 this.custEmail = email;
	 }
	 
	 public void setCustomerID(long custID) {
		 this.customerID = custID;
	 }

	 ///////////////////////
	 //// getter Methods
	 ///////////////////////
	 public String getPhoneNumber() {
	 	return this.custPhoneNumber;
	 }

	 public String getFirstname() {
		 return this.custFirstname;
	 }

	 public String getLastname() {
		 return this.custLastname;
	 }

	 public String getEmail() {
		 return this.custEmail;
	 }
	 
	 public long getCustomerID() {
		 return this.customerID;
	 }

}
