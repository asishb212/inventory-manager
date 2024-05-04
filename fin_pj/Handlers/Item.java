// Item Class that represents the ITEM table in the repository inventory_db

public class Item {

    private long itemId;
    private String itemName;
    private String itemDescription;
    private double itemUnitPrice;
    private double itemDiscountPercent;
    private long supplierId;

	// constructor
	public Item() {		
		
	}

	// New Constructor
	public Item(long itemId, String itemName, String itemDescription, double itemUnitPrice, 
			double itemDiscountPercent, long supplierId) {	
		
		setItemId(itemId);
		setItemName(itemName);
		setItemDescription(itemDescription);		
		setItemUnitPrice(itemUnitPrice);
		setItemDiscountPercent(itemDiscountPercent);
		setSupplierId(supplierId);
	}

	///////////////////////
	//// Getter Methods
	///////////////////////	
    public long getItemId() {
        return this.itemId;
    }

    public String getItemName() {
        return this.itemName;
    }

    public String getItemDescription() {
        return this.itemDescription;
    }

    public double getItemUnitPrice() {
        return this.itemUnitPrice;
    }

    public double getItemDiscountPercent() {
        return this.itemDiscountPercent;
    }

    public long getSupplierId() {
        return this.supplierId;
    }

    ///////////////////////
	//// Setter Methods
	///////////////////////	
    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public void setItemName(String itemName) {
    	this.itemName = itemName;
    }

    public void setItemDescription(String itemDescription) {
    	this.itemDescription = itemDescription;
    }

    public void setItemUnitPrice(double itemUnitPrice) {
    	this.itemUnitPrice = itemUnitPrice;
    }

    public void setItemDiscountPercent(double itemDiscountPercent) {
    	this.itemDiscountPercent = itemDiscountPercent;
    }
    
    public void setSupplierId(long supplierId) {
        this.supplierId = supplierId;
    }
    
    
}
