// Supplier Class that represents the SUPPLIER table in the repository inventory_db

public class Supplier {

	 private String supplierName;
	 private String contactFirstname;
	 private String contactLastname;
	 private String contactPhone;
	 private Long supplierID;
	
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
	 public void setSupplierName(String suppName) {
		 this.supplierName = suppName;
	 }

	 public void setContactFirstname(String contactFname) {
		 this.contactFirstname = contactFname;
	 }

	 public void setContactLastname(String contactLname) {
		 this.contactLastname = contactLname;
	 }

	 public void setContactPhone(String contactPhoneNumber) {
		 this.contactPhone = contactPhoneNumber;
	 }

	 public void setSupplierID(Long supplierID) {
		 this.supplierID = supplierID;
	 }

	 ///////////////////////
	 //// getter Methods
	 ///////////////////////
	 public String getSupplierName() {
	 	return this.supplierName;
	 }
		
	 public String getContactFirstname() {
		 	return this.contactFirstname;
		 }
			
	 public String getContactLastname() {
		 	return this.contactLastname;
		 }

	 public String getContactPhone() {
		 	return this.contactPhone;
		 }
	
	 public Long getSupplierID() {
		 	return this.supplierID;
		 }
}
