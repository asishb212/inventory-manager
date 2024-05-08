package frontend;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Map;

import Backend.*;

public class admin_inventory_controller {
    @FXML
    private GridPane detailsGrid;

    @FXML
    private ScrollPane scrollPane;

    private String[] headers = {"Name", "Description", "Unit Price", "Discount", "Stock Status", "Units available" , "Units sold","Category","          ","           ","           ","        ","        ","        ","        ","        "};

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
        Main.AdminUserDashboardSceneSwitch();
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
        Main.AdminUserDashboardSceneSwitch();
    }

    public void initialize() {
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);  
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); 
        
        ArrayList<Map<String, Object>> itemsList = Queries.getAllItemDetails();

        for (int i = 0; i < headers.length; i++) {
            Label headerLabel = new Label(headers[i]);
            headerLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
            detailsGrid.add(headerLabel, i, 0);
        }
                                           
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

            Label total_quantity_Label = new Label(item.get("total_qty_purchased").toString());
            total_quantity_Label.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
            detailsGrid.add(total_quantity_Label, 4, i + 1);

            Label sold_quantity_Label = new Label(item.get("total_qty_sold").toString());
            sold_quantity_Label.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
            detailsGrid.add(sold_quantity_Label, 5, i + 1);

            Label statusLabel = new Label((String) item.get("stock_status"));
            statusLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
            detailsGrid.add(statusLabel, 6, i + 1);

            Label categoryLabel = new Label((String) item.get("Category"));
            categoryLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
            detailsGrid.add(categoryLabel, 7, i + 1);

        }

        for (int j = itemsList.size(); j < 30; j++){
            Label statusLabel = new Label("              ");
            statusLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
            detailsGrid.add(statusLabel, 4, j + 1);
        }
    }
    
}
