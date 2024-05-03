package frontend;

import Backend.Queries;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;


public class add_item_controller {

    private static String userName;

    @FXML
    private TextField itemName;

    @FXML
    private TextField itemDescription;

    @FXML
    private TextField itemUnitPrice;

    @FXML
    private TextField itemDiscountPercent;

    @FXML
    private TextField totalQtyPurchased;

    @FXML
    private TextField totalQtySold;

    @FXML
    private TextField totalQtyAvailable;
    
    @FXML
    private void handleLogout() {
        // Handle New action
        Main.LoginSceneSwitch();
    }

    @FXML
    private void handleExit() {
        // Handle Open action
        System.exit(0);
    }

    @FXML
    private void handleHome() {
        // Handle Open action
        Main.DashboardSceneSwitch(userName);
    }

    @FXML
    private void handleInventory() {
        // Handle Save action
        showAlert("Save clicked");
    }

    @FXML
    private void handleInsights() {
        Main.InsightsSceneSwitch();
    }

    @FXML
    private void handleInfo() {
        Main.UserInfoSceneSwitch();
    }

    @FXML
    private void handlePwd() {
        Main.UpdatePwdSceneSwitch();
    }

    private boolean validateItemName(String itemName) {
        return itemName != null && itemName.length() <= 30;
    }
    
    private boolean validateItemDescription(String itemDescription) {
        return itemDescription != null && itemDescription.length() <= 30;
    }
    
    private boolean validateItemUnitPrice(String itemUnitPrice) {
        try {
            double price = Double.parseDouble(itemUnitPrice);
            return String.valueOf(price).matches("^\\d{0,8}(\\.\\d{0,2})?$");
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    private boolean validateItemDiscountPercent(String itemDiscountPercent) {
        try {
            double discount = Double.parseDouble(itemDiscountPercent);
            return String.valueOf(discount).matches("^\\d{0,8}(\\.\\d{0,2})?$");
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    private boolean validateTotalQtyPurchased(String totalQtyPurchased) {
        try {
            return totalQtyPurchased.matches("\\d{1,10}");
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    private boolean validateTotalQtySold(String totalQtySold) {
        try {
            return totalQtySold.matches("\\d{1,10}");
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    private boolean validateTotalQtyAvailable(String totalQtyAvailable) {
        try {
            return totalQtyAvailable.matches("\\d{1,10}");
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    private boolean validateItemnumbs(long totalQtyPurchased, long total_qty_sold) {
        if (totalQtyPurchased > total_qty_sold){
            showAlert("Purchase quantity is more than sold quantity");
            return false;
        }
        return true;
    }

    @FXML 
    private void handleConfirm() {
        String itemNameText = itemName.getText();
        String itemDescriptionText = itemDescription.getText();
        String itemUnitPriceText = itemUnitPrice.getText();
        String itemDiscountPercentText = itemDiscountPercent.getText();
        String totalQtyPurchasedText = totalQtyPurchased.getText();
        String totalQtySoldText = totalQtySold.getText();
        String totalQtyAvailableText = totalQtyAvailable.getText();

        if (validateItemName(itemNameText) &&
            validateItemDescription(itemDescriptionText) &&
            validateItemUnitPrice(itemUnitPriceText) &&
            validateItemDiscountPercent(itemDiscountPercentText) &&
            validateTotalQtyPurchased(totalQtyPurchasedText) &&
            validateTotalQtySold(totalQtySoldText) &&
            validateTotalQtyAvailable(totalQtyAvailableText)) {
            
            Queries.addItem(itemNameText, itemDescriptionText, 
                            Double.parseDouble(itemUnitPriceText),
                            Double.parseDouble(itemDiscountPercentText), 
                            Supplier.getSupplierID());
            
            if (validateItemnumbs(Long.parseLong(totalQtyPurchasedText), Long.parseLong(totalQtySoldText))) {
                Queries.addItemStock(Long.parseLong(totalQtyPurchasedText), Long.parseLong(totalQtySoldText), Item.getItemId());
            }
            else{
                return;
            }

            showAlert("Item added!");
            Main.DashboardSceneSwitch(User.userName);
        } 
        else {
            // If any validation fails, notify the user
            System.out.println("Validation failed. Please check your inputs.");
        }        
    }

    @FXML 
    private void handleCancel() {
        Main.DashboardSceneSwitch(User.userName);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Action");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void setUsername(String text) {
        add_item_controller.userName = text;
    }
}
