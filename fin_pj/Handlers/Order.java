// Order Class that represents the ORDER table in the repository inventory_db

import java.time.LocalDateTime;

public class Order {

    private long orderId;
	private String orderType;
	private String orderStatus;
	private double subTotal;
	private double taxPercent;
	private double totalAmount;
    private LocalDateTime dateCreated;
    private LocalDateTime dateModified;
    private long userId;	// FK
	
	// constructor
	public Order() {		
		
	}

	///////////////////////
	//// Setter Methods
	///////////////////////	
	public void setOrderID(long orderId) {
		 this.orderId = orderId;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	
	public void setOrderStatus(String orderStatus) {
		 this.orderStatus = orderStatus;
	}
	
	public void setSubTotal(double subTotal) {
	   	this.subTotal = subTotal;
	}
	 
	public void setTaxPercent(double taxPercent) {
	   	this.taxPercent = taxPercent;
	}
	
	public void setTotalAmount(double totalAmount) {
	   	this.totalAmount = totalAmount;
	}
	
	public void setDateCreated(LocalDateTime dateCreated) {
	   	this.dateCreated = dateCreated;
	}
	
	public void setDateModified(LocalDateTime dateModified) {
		this.dateModified = dateModified;
	}

	public void setUserId(long userId) {
		 this.userId = userId;
	}

	///////////////////////
	//// Getter Methods
	///////////////////////	

	public long getOrderID() {
		 return this.orderId;
	}

	public String getOrderType() {
		return this.orderType;
	}
	
	public String getOrderStatus() {
		return this.orderStatus;
	}
	
	public double getSubTotal() {
		return this.subTotal;
	}
	 
	public double getTaxPercent() {
		return this.taxPercent;
	}
	
	public double getTotalAmount() {
		return this.totalAmount;
	}
	
	public LocalDateTime getDateCreated() {
		return this.dateCreated;
	}
	
	public LocalDateTime getDateModified() {
		return this.dateModified;
	}

	public long getUserId() {
		return this.userId;
	}
	
}
