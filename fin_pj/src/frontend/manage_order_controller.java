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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import Backend.*;

public class manage_order_controller {
    
    private String[] headers = {"order_type", "order_status", "tax_percent", "items", "date_created","          ","           ","           ","        ","        ","        ","        ","        "};
    private String[] headers2 = {"Name", "Description", "Unit Price", "Discount", "Stock Status", "Category","Quantity","          ","           ","           ","        ","        ","        ","        ","        ","        "};

    private int i = 0;
    private long[] item_ids;
    private long[] quantities;

    @FXML
    private ScrollPane scrollPane, scrollPane2;

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
            headerLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
            detailsGrid.add(headerLabel, i, 0);
        }

        ArrayList<Map<String,Object>> orderDetailsList = Queries.getOrders_by_uID(User.getUserId());

        for (int i = 0; i < orderDetailsList.size(); i++) {
            Map<String, Object> order = orderDetailsList.get(i);

            String items_st = (String) order.get("items");
            int items_count = items_st.split(";").length;

            Label orderStatusLable = new Label((String) order.get("order_type"));
            orderStatusLable.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
            detailsGrid.add(orderStatusLable, 0, i + 1);

            Label order_statusLable = new Label((String) order.get("order_status"));
            order_statusLable.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
            detailsGrid.add(order_statusLable, 1, i + 1);

            Label tax_percent_lable = new Label(order.get("tax_percent").toString());
            tax_percent_lable.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
            detailsGrid.add(tax_percent_lable, 2, i + 1);

            Label items_count_Label = new Label(Integer.toString(items_count));
            items_count_Label.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
            detailsGrid.add(items_count_Label, 3, i + 1);

            Label date_label = new Label(order.get("date_created").toString().substring(0, 10));
            date_label.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
            detailsGrid.add(date_label, 4, i + 1);

            Button actionBtn = new Button("    Details    ");
            actionBtn.setOnAction(event -> handleEditAction(event, order));

            detailsGrid.add(actionBtn, 5, i+1);
            }
        
        for (int j = orderDetailsList.size(); j < 20; j++){
            Label statusLabel = new Label("              ");
            statusLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
            detailsGrid.add(statusLabel, 4, j + 1);
        }
    }

    private void handleEditAction(ActionEvent event, Map<String,Object> order){

        String item_ids_str = (String) order.get("items");
        item_ids = Arrays.stream(item_ids_str.split(";")).mapToLong(Long::parseLong).toArray();

        String quantity_str = (String) order.get("quantities");
        quantities = Arrays.stream(quantity_str.split(";")).mapToLong(Long::parseLong).toArray();

        updatepanel();
    }

    public void initialize(){

        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);  
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); 

        scrollPane2.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);  
        scrollPane2.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); 

        getOrders();
    }

    @FXML
    private void handleOrder(){
        showAlert("hi");
    }

    private void updatepanel(){
        detailsGrid.getChildren().clear();

        for (i = 0; i < item_ids.length; i++) {

            for (int i = 0; i < headers2.length; i++) {
                Label headerLabel = new Label(headers2[i]);
                headerLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
                detailsGrid.add(headerLabel, i, 0);
            }

            Map<String, Object> item = Queries.getItems_by_uID_orderID(item_ids[i]);
            
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

            Label quantityLabel = new Label(String.valueOf(quantities[i]));
            quantityLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
            detailsGrid.add(quantityLabel, 6, i + 1);
        }

        for (int j = item_ids.length; j < 20; j++){
            Label statusLabel = new Label("              ");
            statusLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
            detailsGrid.add(statusLabel, 4, j + 1);
        }
    }
    
}
