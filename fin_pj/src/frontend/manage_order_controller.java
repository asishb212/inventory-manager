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
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import Backend.*;

public class manage_order_controller {
    
    private String[] headers = {"order_type", "order_status", "tax_percent", "items", "date_created"};

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
    public void handleCancel() {
        Main.DashboardSceneSwitch(User.getUserName());
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Action");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void getOrders() {
        detailsGrid.getChildren().clear();

        for (int i = 0; i < headers.length; i++) {
            Label headerLabel = new Label(headers[i]);
            detailsGrid.add(headerLabel, i, 0);
        }

        ArrayList<Map<String,Object>> orderDetailsList = Queries.getOrders_by_uID(User.getUserId());

        for (int i = 0; i < orderDetailsList.size(); i++) {
            Map<String, Object> order = orderDetailsList.get(i);

            String items_st = (String) order.get("items");
            int items_count = items_st.split(";").length;
            
            detailsGrid.add(new Label((String) order.get("order_type")), 0, i + 1);
            detailsGrid.add(new Label((String) order.get("order_status")), 1, i + 1);
            detailsGrid.add(new Label(order.get("tax_percent").toString()), 2, i + 1);
            detailsGrid.add(new Label(Integer.toString(items_count)), 3, i + 1);
            detailsGrid.add(new Label(order.get("date_created").toString().substring(0, 10)), 4, i + 1);

            Button actionBtn = new Button("    Edit    ");
            actionBtn.setOnAction(event -> handleEditAction(event, order));

            detailsGrid.add(actionBtn, 5, i+1);
            }
    }

    private void handleEditAction(ActionEvent event, Map<String,Object> order){

        long order_id = (long) order.get("order_id");

        String item_ids_str = (String) order.get("items");
        long item_ids[] = Arrays.stream(item_ids_str.split(";")).mapToLong(Long::parseLong).toArray();

        String quantity_str = (String) order.get("quantities");
        long quantities[] = Arrays.stream(quantity_str.split(";")).mapToLong(Long::parseLong).toArray();

        Map<String,Object> itemsList = Queries.getItems_by_uID_orderID(order_id);
        System.out.println(itemsList.size());

        detailsGrid.getChildren().clear();

        for (int i = 0; i < item_ids.length; i++) {
            Map<String, Object> item = Queries.getItems_by_uID_orderID(item_ids[i]);
            
            detailsGrid.add(new Label((String) item.get("item_name")), 0, i + 1);
            detailsGrid.add(new Label((String) item.get("item_description")), 1, i + 1);
            detailsGrid.add(new Label(item.get("item_unit_price").toString()), 2, i + 1);
            detailsGrid.add(new Label(item.get("item_discount_percent").toString()), 3, i + 1);
            detailsGrid.add(new Label((String) item.get("stock_status")), 4, i + 1);
            detailsGrid.add(new Label((String) item.get("Category")), 5, i + 1);

            TextField quantityField = new TextField(String.valueOf(quantities[i]));
            quantityField.setPrefWidth(40);

            Button increment = new Button("+");
            increment.setOnAction(e -> quantityField.setText(String.valueOf(Integer.parseInt(quantityField.getText()) + 1)));
            
            Button decrement = new Button("-");
            decrement.setOnAction(e -> {
                int current = Integer.parseInt(quantityField.getText());
                quantityField.setText(String.valueOf(Math.max(0, current - 1)));  // Prevent negative quantities
            });

            GridPane quantityControl = new GridPane();
            quantityControl.setHgap(2);
            quantityControl.add(decrement, 0, 0);
            quantityControl.add(quantityField, 1, 0);
            quantityControl.add(increment, 2, 0);

            VBox quantityBox = new VBox(); 
            Label quantityLabel = new Label("     ");
            quantityLabel.setStyle("-fx-font-size: 3px; -fx-padding: 2 0;"); 
            quantityBox.getChildren().add(quantityLabel);            
            quantityBox.getChildren().add(quantityControl);

            detailsGrid.add(quantityBox, 6, i + 1);
        }
        
    }

    public void initialize(){
        getOrders();
    }
}
