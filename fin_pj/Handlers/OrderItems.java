// OrderItems Class that represents the ORDER_ITEMS table in the repository inventory_db

public class OrderItems {

    private long orderItemId;
	private long itemOrderedQty;
	private double itemOrderSubTotal;
	private double itemDiscountAmount;
	private double itemOrderTotal;
	private String itemOrderType;
    private long orderId;	// FK
    private long itemId;	// FK
    private long stockId;	// FK
	
	// constructor
	public OrderItems() {		
		
	}

	///////////////////////
	//// Setter Methods
	///////////////////////	
	public void setOrderItemID(long orderItemId) {
		 this.orderItemId = orderItemId;
	}
	 
	public void setItemOrderedQty(long itemOrderedQty) {
		 this.itemOrderedQty = itemOrderedQty;
	}

	public void setItemOrderSubTotal(double itemOrderSubTotal) {
	   	this.itemOrderSubTotal = itemOrderSubTotal;
	}

	public void setItemDiscountAmount(double itemDiscountAmount) {
	   	this.itemDiscountAmount = itemDiscountAmount;
	}
	
	public void setItemOrderTotal(double itemOrderTotal) {
	   	this.itemOrderTotal = itemOrderTotal;
	}

	public void setItemOrderType(String itemOrderType) {
		 this.itemOrderType = itemOrderType;
	}
	
	public void setOrderId(long orderId) {
		 this.orderId = orderId;
	}
	 
	public void setItemId(long itemId) {
		 this.itemId = itemId;
	}
	 
	public void setStockId(long stockId) {
		 this.stockId = stockId;
	}
	 
	///////////////////////
	//// Getter Methods
	///////////////////////	
	public long getOrderItemID() {
		 return this.orderItemId;
	}
	 
	public long getItemOrderedQty() {
		return this.itemOrderedQty;
	}

	public double getItemOrderSubTotal() {
		return this.itemOrderSubTotal;
	}

	public double getItemDiscountAmount() {
		return this.itemDiscountAmount;
	}
	
	public double getItemOrderTotal() {
		return this.itemOrderTotal;
	}

	public String getItemOrderType() {
		return this.itemOrderType;
	}
	
	public long getOrderId() {
		return this.orderId;
	}
	 
	public long getItemId() {
		return this.itemId;
	}
	 
	public long getStockId() {
		return this.stockId;
	}
	
}
