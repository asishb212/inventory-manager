package frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Map;

import Backend.*;

public class Cart_controller {
    @FXML
    private GridPane detailsGrid;

    @FXML
    private ScrollPane scrollPane;

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
    private void handleCancel() {
        Main.DashboardSceneSwitch(User.userName);
    }

    private String[] headers = {"Name", "Description", "Unit Price", "Discount", "Stock Status", "Category","Quantity","          ","           ","           ","        ","        ","        ","        ","        ","        "};

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Action");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void initialize() {
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);  
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); 

        updateGrid();
    }

    public void updateGrid(){
        detailsGrid.getChildren().clear();  // Clear the grid before adding new elements

        for (int i = 0; i < headers.length; i++) {
            Label headerLabel = new Label(headers[i]);
            headerLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
            detailsGrid.add(headerLabel, i, 0);
        }

        int i = 0;
        for (ArrayList<Long> item : cart_items.get_cart_items()){
            Map<String, Object> itemsDetails = Queries.getItemDetailsBy_SId_IId(item.get(0),item.get(1));

            Label namelabel = new Label((String) itemsDetails.get("item_name"));
            namelabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
            detailsGrid.add(namelabel, 0, i + 1);

            Label descriptionLabel = new Label((String) itemsDetails.get("item_description"));
            descriptionLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
            detailsGrid.add(descriptionLabel, 1, i + 1);

            Label priceLabel = new Label(itemsDetails.get("item_unit_price").toString());
            priceLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
            detailsGrid.add(priceLabel, 2, i + 1);

            Label discountLabel = new Label(itemsDetails.get("item_discount_percent").toString());
            discountLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
            detailsGrid.add(discountLabel, 3, i + 1);

            Label statusLabel = new Label((String) itemsDetails.get("stock_status"));
            statusLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
            detailsGrid.add(statusLabel, 4, i + 1);

            Label categoryLabel = new Label((String) itemsDetails.get("Category"));
            categoryLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
            detailsGrid.add(categoryLabel, 5, i + 1);

            Label qunatityLabel = new Label(cart_items.get_cart_items().get(i).get(2).toString());
            qunatityLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
            detailsGrid.add(qunatityLabel, 6, i + 1);

            String ss = (String) itemsDetails.get("stock_status");

            if (ss.equals("A")){
                Button actionBtn = new Button("     Delete     ");

                actionBtn.setOnAction(event -> handleDeleteAction(event, item));

                detailsGrid.add(actionBtn, 7, i+1);
            }

            i++;
        }

        for (int j = cart_items.items.size(); j < 20; j++){
            Label statusLabel = new Label("              ");
            statusLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
            detailsGrid.add(statusLabel, 4, j + 1);
        }
    }

    private void handleDeleteAction(ActionEvent event, ArrayList<Long> item) {
        cart_items.remove_cart_item(item);
        updateGrid();
    }

    @FXML
    private void handleOrder(){
        double totalPrice = 0.0;
        String quantities = "";
        String item_ids = "";

        int i = 0;

        if (cart_items.get_cart_items().size() < 1){
            showAlert("Cart is empty");
            Main.SearchSceneSwitch();
        }
        else {

        for (ArrayList<Long> item : cart_items.get_cart_items()) {
            Map<String, Object> itemsDetails = Queries.getItemDetailsBy_SId_IId(item.get(0), item.get(1));

            long quantity = cart_items.get_cart_items().get(i).get(2);
            double itemPrice = quantity * Double.parseDouble(itemsDetails.get("item_unit_price").toString());
            double discountPercent = Double.parseDouble(itemsDetails.get("item_discount_percent").toString());
            double discountedPrice = itemPrice * (1.0 - discountPercent / 100.0);

            totalPrice += discountedPrice;

            item_ids += itemsDetails.get("item_id").toString();
            item_ids += ";";

            quantities += cart_items.get_cart_items().get(i).get(2).toString();
            quantities += ";";

            Queries.updateQuantity((long) itemsDetails.get("item_id"),(long) cart_items.get_cart_items().get(i).get(2));

            i++;
        }

        Queries.addOrder(User.getUserId(), User.getUserType(),item_ids,quantities);

        String formattedTotalPrice = String.format("%.2f", totalPrice);
        showAlert("Total Price after Discounts: $" + formattedTotalPrice);

        cart_items.items.clear();
        updateGrid();
        Main.DashboardSceneSwitch(User.userName);
    }
    }

    public void handleEmpty(){
        cart_items.items.clear();
        updateGrid();
    }
}
