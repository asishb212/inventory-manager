// ItemStock Class that represents the ITEM_STOCK table in the repository inventory_db

import java.time.LocalDateTime;

public class ItemStock {

    private long stockId;
    private long totalQtyPurchased;
    private long totalQtySold;
    private long totalQtyAvailable;
    private String stockStatus;
    private long itemId;
    private LocalDateTime dateCreated;
    private LocalDateTime dateModified;

	// constructor
	public ItemStock() {		
		
	}

	// New Constructor
	public ItemStock(long stockId, long totalQtyPurchased, long totalQtySold, long totalQtyAvailable, 
			String stockStatus, long itemId) {		
		
		setStockId(stockId);
		setTotalQtyPurchased(totalQtyPurchased);
		setTotalQtySold(totalQtySold);		
		setTotalQtyAvailable(totalQtyAvailable);
		setStockStatus(stockStatus);
		setItemId(itemId);
	}

	
	///////////////////////
	//// Getter Methods
	///////////////////////	
    public long getStockId() {
        return this.stockId;
    }

    public long getTotalQtyPurchased() {
        return this.totalQtyPurchased;
    }

    public long getTotalQtySold() {
        return this.totalQtySold;
    }

    public long getTotalQtyAvailable() {
        return this.totalQtyAvailable;
    }

    public String getStockStatus() {
        return this.stockStatus;
    }

    public long getItemId() {
        return this.itemId;
    }

    public LocalDateTime getDateCreated() {
        return this.dateCreated;
    }

    public LocalDateTime getDateModified() {
        return this.dateModified;
    }

	///////////////////////
	//// Setter Methods
	///////////////////////	
    public void setStockId(long stockId) {
        this.stockId = stockId;
    }

    public void setTotalQtyPurchased(long totalQtyPurchased) {
    	this.totalQtyPurchased = totalQtyPurchased;
    }

    public void setTotalQtySold(long totalQtySold) {
    	this.totalQtySold = totalQtySold;
    }

    public void setTotalQtyAvailable(long totalQtyAvailable) {
    	this.totalQtyAvailable = totalQtyAvailable;
    }

    public void setStockStatus(String stockStatus) {
    	this.stockStatus = stockStatus;
    }

    public void setItemId(long itemId) {
    	this.itemId = itemId;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
    	this.dateCreated = dateCreated;
    }

    public void setDateModified(LocalDateTime dateModified) {
    	this.dateModified = dateModified;
    }

}
