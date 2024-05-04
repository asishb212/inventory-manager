package frontend;

import java.util.ArrayList;
import java.util.Map;

import Backend.Queries;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class update_item_controller {

    @FXML
    private TextField itemName;

    @FXML
    private Label label;

    @FXML
    private Button ConfirmButton;

    @FXML
    private Button SearchButton;

    @FXML
    private GridPane detailsGrid;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private AnchorPane searchOP;

    @FXML
    private AnchorPane updateOP;

    @FXML
    private TextField itemName2;

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

    private long item_id;


    private String[] headers = {"Name", "Description", "Unit Price", "Discount", "Qty Purchased", "Qty Sold", "Stock Status", "Category"};
    
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
    private void handleInventory() {
        // Handle Save action
        showAlert("Save clicked");
    }

    @FXML
    private void handleInsights() {
        // Handle Exit action
        showAlert("Exit clicked");
    }

    @FXML
    private void handleInfo() {
        // Handle Cut action
        showAlert("Cut clicked");
    }

    @FXML
    private void handlePwd() {
        // Handle Copy action
        showAlert("Copy clicked");
    }

    private boolean validateItemName(String itemName) {
        if (itemName != null && itemName.length() <= 30) {
            return true;
        } else {
            showAlert("Item name must not be empty and should be 30 characters or less.");
            return false;
        }
    }
    
    private boolean validateItemDescription(String itemDescription) {
        if (itemDescription != null && itemDescription.length() <= 30) {
            return true;
        } else {
            showAlert("Item description must not be empty and should be 30 characters or less.");
            return false;
        }
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
        String itemNameText = itemName2.getText();
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

            Queries.updateItem(itemNameText, itemDescriptionText, 
                           Double.parseDouble(itemUnitPriceText),
                           Double.parseDouble(itemDiscountPercentText), 
                           Long.parseLong(totalQtyPurchasedText), Long.parseLong(totalQtySoldText),
                           categoryText,item_id);
            
            showAlert("Item updated!");
            Main.DashboardSceneSwitch(User.userName);
        }
        else{
            System.out.println("Validation failed. Please check your inputs.");
        }
    }

    @FXML 
    private void handleSearch() {
        String itemNameText = itemName.getText();
        
        ArrayList<Map<String, Object>> itemsList = Queries.getItemDetailsByName_supp(itemNameText, Supplier.getSupplierID());

        for (int i = 0; i < itemsList.size(); i++) {
            Map<String, Object> item = itemsList.get(i);
            
            detailsGrid.add(new Label((String) item.get("item_name")), 0, i + 1);
            detailsGrid.add(new Label((String) item.get("item_description")), 1, i + 1);
            detailsGrid.add(new Label(item.get("item_unit_price").toString()), 2, i + 1);
            detailsGrid.add(new Label(item.get("item_discount_percent").toString()), 3, i + 1);
            detailsGrid.add(new Label(item.get("total_qty_purchased").toString()), 4, i + 1);
            detailsGrid.add(new Label(item.get("total_qty_sold").toString()), 5, i + 1);
            detailsGrid.add(new Label((String) item.get("stock_status")), 6, i + 1);
            detailsGrid.add(new Label((String) item.get("Category")), 7, i + 1);

            Button actionBtn = new Button("Update");
            actionBtn.setOnAction(event -> handleUpdateAction(event, item));

            detailsGrid.add(actionBtn, 8, i+1);
        }

        SearchButton.setVisible(false);
        SearchButton.setDisable(true);
        scrollPane.setVisible(true);

    }

    private void handleUpdateAction(ActionEvent event, Map<String, Object> itemDetails) {

        searchOP.setVisible(false);
        searchOP.setDisable(true);

        updateOP.setVisible(true);
        updateOP.setDisable(false);

        ConfirmButton.setDisable(false);
        ConfirmButton.setVisible(true);

        itemName2.setText((String) itemDetails.get("item_name"));
        itemDescription.setText((String) itemDetails.get("item_description"));
        itemUnitPrice.setText(itemDetails.get("item_unit_price").toString());
        itemDiscountPercent.setText(itemDetails.get("item_discount_percent").toString());
        totalQtyPurchased.setText( itemDetails.get("total_qty_purchased").toString());
        totalQtySold.setText(itemDetails.get("total_qty_sold").toString());
        category.setValue((String) itemDetails.get("Category"));

        item_id = (long) itemDetails.get("item_id");
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

    public void initialize(){

        ConfirmButton.setDisable(true);
        updateOP.setDisable(true);
        updateOP.setVisible(false);


        for (int i = 0; i < headers.length; i++) {
            Label headerLabel = new Label(headers[i]);
            detailsGrid.add(headerLabel, i, 0);
        }
    }
}
