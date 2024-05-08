package frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Map;

import Backend.*;

public class add_order_controller {

    private String[] headers = {"Name", "Description", "Unit Price", "Discount", "Stock Status", "Category","Quantity","          ","           ","           ","        ","        ","        ","        ","        ","        "};

    @FXML
    private GridPane detailsGrid;

    @FXML
    private ScrollPane scrollPane;
    
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
            headerLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
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

            Label namelabel = new Label((String) item.get("item_name"));
            namelabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
            detailsGrid.add(namelabel, 0, i + 1);

            Label descriptionLabel = new Label((String) item.get("item_description"));
            descriptionLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
            detailsGrid.add(descriptionLabel, 1, i + 1);

            Label priceLabel = new Label(item.get("item_unit_price").toString());
            priceLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
            detailsGrid.add(priceLabel, 2, i + 1);

            Label discountLabel = new Label(item.get("item_discount_percent").toString());
            discountLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
            detailsGrid.add(discountLabel, 3, i + 1);

            Label statusLabel = new Label((String) item.get("stock_status"));
            statusLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
            detailsGrid.add(statusLabel, 4, i + 1);

            Label categoryLabel = new Label((String) item.get("Category"));
            categoryLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
            detailsGrid.add(categoryLabel, 5, i + 1);

            TextField quantityField = new TextField("1");
            quantityField.setPrefWidth(40);

            Button increment = new Button("+");
            increment.setOnAction(e -> quantityField.setText(String.valueOf(Integer.parseInt(quantityField.getText()) + 1)));
    
            Button decrement = new Button("-");
            decrement.setOnAction(e -> {
                int current = Integer.parseInt(quantityField.getText());
                quantityField.setText(String.valueOf(Math.max(1, current - 1)));  // Prevent negative quantities
            });

            GridPane quantityControl = new GridPane();
            quantityControl.setHgap(2);
            quantityControl.add(decrement, 0, 0);
            quantityControl.add(quantityField, 1, 0);
            quantityControl.add(increment, 2, 0);

            VBox quantityBox = new VBox();  // Spacing parameter can be adjusted for more vertical space
            Label quantityLabel = new Label("     ");
            quantityLabel.setStyle("-fx-font-size: 3px; -fx-padding: 2 0;"); // Smaller font size and padding
            quantityBox.getChildren().add(quantityLabel);            
            quantityBox.getChildren().add(quantityControl);

            detailsGrid.add(quantityBox, 6, i + 1);

            String ss = (String) item.get("stock_status");

            if (ss.equals("A")){
                Button actionBtn = new Button("    Add    ");
                actionBtn.setOnAction(event -> handleUpdateAction(event, item, quantityField));

                detailsGrid.add(actionBtn, 7, i + 1);
            }
        }

        for (int j = itemsList.size(); j < 20; j++){
            Label statusLabel = new Label("              ");
            statusLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
            detailsGrid.add(statusLabel, 4, j + 1);
        }
    }

    private void handleUpdateAction(ActionEvent event, Map<String, Object> itemDetails, TextField quantityField) {

        String quantityText = quantityField.getText();
        long quantity = (quantityText.isEmpty()) ? 0 : Long.parseLong(quantityText);

        ArrayList<Long> deets = new ArrayList<>();
        deets.add((long) itemDetails.get("item_id"));
        deets.add((long) itemDetails.get("supplier_id"));
        deets.add(quantity);

        cart_items.addItem(deets);
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
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);  
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); 

        handleSearch();
    }

}
