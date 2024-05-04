package frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Map;

import Backend.*;

public class add_order_controller {

    private String[] headers = {"Name", "Description", "Unit Price", "Discount", "Stock Status", "Category"};

    @FXML
    private GridPane detailsGrid;
    
    @FXML
    private RadioButton radioButton;

    @FXML
    private TextField itemName;

    @FXML
    private ComboBox<String> category;

    @FXML
    private TextField Discount;

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
        // Handle New action
        Main.DashboardSceneSwitch(User.userName);
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

    @FXML
    private void handleSearch() {
        
        detailsGrid.getChildren().clear();

        for (int i = 0; i < headers.length; i++) {
            Label headerLabel = new Label(headers[i]);
            detailsGrid.add(headerLabel, i, 0);
        }

        boolean isSelected = radioButton.isSelected();
        String itemNamegotText = itemName.getText();
        String itemDiscountPercentText = Discount.getText();
        String categoryGotText = category.getValue();

        Double discountDouble = (itemDiscountPercentText.isEmpty()) ? 0 : Double.parseDouble(itemDiscountPercentText);
        String itemNameText = (itemNamegotText == null) ? "" : itemNamegotText;
        String categoryText = (categoryGotText == null) ? "" : categoryGotText;

        ArrayList<Map<String, Object>> itemsList = Queries.getItemDetails(itemNameText,discountDouble,
                                                                        categoryText,isSelected);

        for (int i = 0; i < itemsList.size(); i++) {
            Map<String, Object> item = itemsList.get(i);
            
            detailsGrid.add(new Label((String) item.get("item_name")), 0, i + 1);
            detailsGrid.add(new Label((String) item.get("item_description")), 1, i + 1);
            detailsGrid.add(new Label(item.get("item_unit_price").toString()), 2, i + 1);
            detailsGrid.add(new Label(item.get("item_discount_percent").toString()), 3, i + 1);
            detailsGrid.add(new Label((String) item.get("stock_status")), 4, i + 1);
            detailsGrid.add(new Label((String) item.get("Category")), 5, i + 1);

            String ss = (String) item.get("stock_status");

            if (ss.equals("A")){
                Button actionBtn = new Button("Add");
                actionBtn.setOnAction(event -> handleUpdateAction(event, item));

                detailsGrid.add(actionBtn, 8, i+1);
            }
        }
    }

    private void handleUpdateAction(ActionEvent event, Map<String, Object> itemDetails) {
        System.out.println("hello");
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

}
