package frontend;

import Backend.Queries;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
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
    private ComboBox<String> category;
    
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
        Main.SearchSceneSwitch();
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
        if (itemName == null || itemName.trim().isEmpty()) {
            showAlert("Item name must not be empty.");
            return false;
        } 
        else if (itemName.length()>30) {
            showAlert("Item name cannot be longer than 30 characters.");
            return false;
        }
        return true;
    }
    
    private boolean validateItemDescription(String itemDescription) {
        if (itemDescription == null || itemDescription.trim().isEmpty()) {
            showAlert("Item name must not be empty.");
            return false;
        } 
        else if (itemDescription.length() > 30){
            showAlert("Item name cannot be longer than 30 characters.");
            return false;
        }
        return true;
    }
    
    private boolean validateItemUnitPrice(String itemUnitPrice) {
        try {
            double price = Double.parseDouble(itemUnitPrice);
            if (String.valueOf(price).matches("^\\d{0,8}(\\.\\d{0,2})?$")) {
                return true;
            } else {
                showAlert("Invalid item unit price format.");
                return false;
            }
        } catch (NumberFormatException e) {
            showAlert("Item unit price must be a valid number.");
            return false;
        }
    }
    
    private boolean validateItemDiscountPercent(String itemDiscountPercent) {
        try {
            double discount = Double.parseDouble(itemDiscountPercent);
            if (String.valueOf(discount).matches("^\\d{0,8}(\\.\\d{0,2})?$")) {
                return true;
            } else {
                showAlert("Invalid item discount percent format.");
                return false;
            }
        } catch (NumberFormatException e) {
            showAlert("Item discount percent must be a valid number.");
            return false;
        }
    }
    
    private boolean validateTotalQtyPurchased(String totalQtyPurchased) {
        try {
            if (totalQtyPurchased.matches("\\d{1,10}")) {
                return true;
            } else {
                showAlert("Total quantity purchased must be a valid positive integer.");
                return false;
            }
        } catch (NumberFormatException e) {
            showAlert("Total quantity purchased must be a valid positive integer.");
            return false;
        }
    }
    
    private boolean validateTotalQtySold(String totalQtySold) {
        try {
            if (totalQtySold.matches("\\d{1,10}")) {
                return true;
            } else {
                showAlert("Total quantity sold must be a valid positive integer.");
                return false;
            }
        } catch (NumberFormatException e) {
            showAlert("Total quantity sold must be a valid positive integer.");
            return false;
        }
    }
    
    private boolean validateItemnumbs(long totalQtyPurchased, long total_qty_sold) {
        if (totalQtyPurchased < total_qty_sold){
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
        String categoryText = category.getValue();

        if (validateItemName(itemNameText) &&
            validateItemDescription(itemDescriptionText) &&
            validateItemUnitPrice(itemUnitPriceText) &&
            validateItemDiscountPercent(itemDiscountPercentText) &&
            validateTotalQtyPurchased(totalQtyPurchasedText) &&
            validateTotalQtySold(totalQtySoldText) &&
            validateItemnumbs(Long.parseLong(totalQtyPurchasedText), Long.parseLong(totalQtySoldText))) {
            
            Queries.addItem(itemNameText, itemDescriptionText, 
                            Double.parseDouble(itemUnitPriceText),
                            Double.parseDouble(itemDiscountPercentText), 
                            Supplier.getSupplierID(),
                            Long.parseLong(totalQtyPurchasedText), Long.parseLong(totalQtySoldText),
                            categoryText);
            
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
