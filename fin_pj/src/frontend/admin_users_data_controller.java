package frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Map;

import Backend.*;

public class admin_users_data_controller {

    String[] supplierLabels = {"User Id","Username","user_role","user_type","Supplier ID", "Supplier Name", "First Name", "Last Name", "Phone", "street","city","state","country","zip"};

    String[] customerLabels = {"User Id","Username","user_role","user_type","Customer ID", "First Name", "Last Name:", "Phone Number", "Email", "street","city","state","country","zip"};

    String[] adminLabels = {"User Id","Username","user_role","user_type","            ","            ","            ","            ","            ","            ","            ","            "};

    String[] noneLabels = {"            ","            ","            ","            ","            ","            ","            ","            ","            ","            ","            ","            ","            "};


    @FXML
    private GridPane detailsGrid;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private ComboBox<String> comboBox;

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

    private void showUserData(String chosen){

        detailsGrid.getChildren().clear();

        if (chosen.equals("Supplier")){
            for (int i = 0; i < supplierLabels.length; i++) {
                Label headerLabel = new Label(supplierLabels[i]);
                headerLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
                detailsGrid.add(headerLabel, i, 0);
            }
        }

        else if (chosen.equals("Customer")){
            for (int i = 0; i < customerLabels.length; i++) {
                Label headerLabel = new Label(customerLabels[i]);
                headerLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
                detailsGrid.add(headerLabel, i, 0);
            }
        }

        else if (chosen.equals("Admin")){
            for (int i = 0; i < adminLabels.length; i++) {
                Label headerLabel = new Label(adminLabels[i]);
                headerLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
                detailsGrid.add(headerLabel, i, 0);
            }
        }
        else {
            for (int i = 0; i < adminLabels.length; i++) {
                Label headerLabel = new Label(adminLabels[i]);
                headerLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
                detailsGrid.add(headerLabel, i, 0);
            }
        }

        ArrayList<Map<String,Object>> allUserData = Queries.getAllusers();

        int i = 0;
        for (Map<String, Object> user : allUserData){

            String user_type = (String) user.get("user_type");

            if (user_type.equals("S") && chosen.equals("Supplier")){
                Label useridLabel = new Label(user.get("user_id").toString());
                useridLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
                detailsGrid.add(useridLabel, 0, i + 1);

                Label userNameLabel = new Label((String) user.get("username"));
                userNameLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
                detailsGrid.add(userNameLabel, 1, i + 1);

                Label userRoleLabel = new Label((String) user.get("user_role"));
                userRoleLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
                detailsGrid.add(userRoleLabel, 2, i + 1);

                Label userTypeLabel = new Label((String) user.get("user_type"));
                userTypeLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
                detailsGrid.add(userTypeLabel, 3, i + 1);

                Label supplierIDLabel = new Label(user.get("SUPPLIER_ID").toString());
                supplierIDLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
                detailsGrid.add(supplierIDLabel, 4, i + 1);

                Label suppliernamLabel = new Label((String) user.get("SUPPLIER_NAME"));
                suppliernamLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
                detailsGrid.add(suppliernamLabel, 5, i + 1);

                Label contactFirstnameLabel = new Label((String) user.get("CONTACT_FIRSTNAME"));
                contactFirstnameLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
                detailsGrid.add(contactFirstnameLabel, 6, i + 1);

                Label contactLastnameLabel = new Label((String) user.get("CONTACT_LASTNAME"));
                contactLastnameLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
                detailsGrid.add(contactLastnameLabel, 7, i + 1);

                Label contactPhoneLabel = new Label(user.get("CONTACT_PHONE").toString());
                contactPhoneLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
                detailsGrid.add(contactPhoneLabel, 8, i + 1);

                Label addrStreetLabel = new Label((String) user.get("ADDR_STREET"));
                addrStreetLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
                detailsGrid.add(addrStreetLabel, 9, i + 1);

                Label addrCityLabel = new Label((String) user.get("ADDR_CITY"));
                addrCityLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
                detailsGrid.add(addrCityLabel, 10, i + 1);

                Label addrStateLabel = new Label((String) user.get("ADDR_STATE"));
                addrStateLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
                detailsGrid.add(addrStateLabel, 11, i + 1);

                Label addrCountryLabel = new Label((String) user.get("ADDR_COUNTRY"));
                addrCountryLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
                detailsGrid.add(addrCountryLabel, 12, i + 1);

                Label addrZipcodeLabel = new Label(user.get("ADDR_ZIPCODE").toString());
                addrZipcodeLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
                detailsGrid.add(addrZipcodeLabel, 13, i + 1);

                Button actionBtn = new Button("    Delete    ");
                actionBtn.setOnAction(event -> handleDeleteAction(event, (long) user.get("user_id")));

                detailsGrid.add(actionBtn, 14, i + 1);

                i++;
                i++;
            }

            else if (user_type.equals("C") && chosen.equals("Customer")) {
                Label useridLabel = new Label(user.get("user_id").toString());
                useridLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
                detailsGrid.add(useridLabel, 0, i + 1);

                Label userNameLabel = new Label((String) user.get("username"));
                userNameLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
                detailsGrid.add(userNameLabel, 1, i + 1);

                Label userRoleLabel = new Label((String) user.get("user_role"));
                userRoleLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
                detailsGrid.add(userRoleLabel, 2, i + 1);

                Label userTypeLabel = new Label((String) user.get("user_type"));
                userTypeLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
                detailsGrid.add(userTypeLabel, 3, i + 1);

                Label custIdLabel = new Label(String.valueOf(user.get("CUST_ID")));
                custIdLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
                detailsGrid.add(custIdLabel, 4, i + 1);

                Label firstNameLabel = new Label((String) user.get("FIRST_NAME"));
                firstNameLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
                detailsGrid.add(firstNameLabel, 5, i + 1);

                Label lastNameLabel = new Label((String) user.get("LAST_NAME"));
                lastNameLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
                detailsGrid.add(lastNameLabel, 6, i + 1);

                Label phoneNumberLabel = new Label((String) user.get("PHONE_NUMBER"));
                phoneNumberLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
                detailsGrid.add(phoneNumberLabel, 7, i + 1);

                Label emailLabel = new Label((String) user.get("EMAIL"));
                emailLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
                detailsGrid.add(emailLabel, 8, i + 1);

                Label addrStreetLabel = new Label((String) user.get("ADDR_STREET"));
                addrStreetLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
                detailsGrid.add(addrStreetLabel, 9, i + 1);

                Label addrCityLabel = new Label((String) user.get("ADDR_CITY"));
                addrCityLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
                detailsGrid.add(addrCityLabel, 10, i + 1);

                Label addrStateLabel = new Label((String) user.get("ADDR_STATE"));
                addrStateLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
                detailsGrid.add(addrStateLabel, 11, i + 1);

                Label addrCountryLabel = new Label((String) user.get("ADDR_COUNTRY"));
                addrCountryLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
                detailsGrid.add(addrCountryLabel, 12, i + 1);

                Label addrZipcodeLabel = new Label(user.get("ADDR_ZIPCODE").toString());
                addrZipcodeLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
                detailsGrid.add(addrZipcodeLabel, 13, i + 1);

                Button actionBtn = new Button("    Delete    ");
                actionBtn.setOnAction(event -> handleDeleteAction(event, (long) user.get("user_id")));

                detailsGrid.add(actionBtn, 14, i + 1);

                i++;
                i++;
            }

            else if (user_type.equals("A") && chosen.equals("Admin")){
                Label useridLabel = new Label(user.get("user_id").toString());
                useridLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
                detailsGrid.add(useridLabel, 0, i + 1);

                Label userNameLabel = new Label((String) user.get("username"));
                userNameLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
                detailsGrid.add(userNameLabel, 1, i + 1);

                Label userRoleLabel = new Label((String) user.get("user_role"));
                userRoleLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
                detailsGrid.add(userRoleLabel, 2, i + 1);

                Label userTypeLabel = new Label((String) user.get("user_type"));
                userTypeLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
                detailsGrid.add(userTypeLabel, 3, i + 1);

                i++;
                i++;
            }  
        }

        for (int j = cart_items.items.size(); j < 20; j++){
            Label statusLabel = new Label("              ");
            statusLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 25;");
            detailsGrid.add(statusLabel, 4, j + 1);
        }
    }

    private void handleDeleteAction(ActionEvent event,long user_id) {
        Queries.deleteUser(user_id);

        String value = comboBox.getValue();

        if (!value.equals(null)){
            showUserData(value);
        }
        else{
            showAlert("select an option");
        }
    }
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Action");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void handleShowButton(){
        String value = comboBox.getValue();

        if (!value.equals(null)){
            showUserData(value);
        }
        else{
            showAlert("select an option");
        }
    }

    public void initialize(){
        showUserData("none");
        
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);  
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); 
    }
}

