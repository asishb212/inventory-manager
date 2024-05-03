// Item Class that represents the ITEM table in the repository inventory_db

public class Item {

    private long itemId;
    private String itemName;
    private String itemDescription;
    private double itemUnitPrice;
    private double itemDiscountPercent;

	// constructor
	public Item() {		
		
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
    
}
