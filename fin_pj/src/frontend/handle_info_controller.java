package frontend;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

public class handle_info_controller {

    @FXML
    private Label attr_label;

    @FXML
    private Label info_label;

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

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Action");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void initialize() {

        String labelsText_supplier = "Username\nUser Role\nUser Type\nUser ID\nSupplier Name\nContact First Name\nContact Last Name\nContact Phone\n ID\nStreet\nCity\nState\nCountry\nZipcode";
        
        String infoText_supplier = User.getUserName() + "\n" +
                          User.getUserRole() + "\n" +
                          User.getUserType() + "\n" +
                          User.getUserId() + "\n" +
                          Supplier.getSupplierName() + "\n" +
                          Supplier.getContactFirstname() + "\n" +
                          Supplier.getContactLastname() + "\n" +
                          Supplier.getContactPhone() + "\n" +
                          Supplier.getSupplierID() + "\n" +
                          address.getSupplierStreet() + "\n" +
                          address.getSupplierCity() + "\n" +
                          address.getSupplierState() + "\n" +
                          address.getSupplierCountry() + "\n" +
                          address.getSupplierZipcode();

        String labelsText_cust = "Username\nUser Role\nUser Type\nUser ID\nFirst Name\nLast Name\nPhone\nEmail\nStreet\nCity\nState\nCountry\nZipcode";
        
        String infoText_cust = User.getUserName() + "\n" +
                                   User.getUserRole() + "\n" +
                                   User.getUserType() + "\n" +
                                   User.getUserId() + "\n" +
                                   Customer.getFirstname() + "\n" +
                                   Customer.getLastname() + "\n" +
                                   Customer.getPhoneNumber() + "\n" +
                                   Customer.getEmail() + "\n" +
                                   address.getSupplierStreet() + "\n" +
                                   address.getSupplierCity() + "\n" +
                                   address.getSupplierState() + "\n" +
                                   address.getSupplierCountry() + "\n" +
                                   address.getSupplierZipcode();

        if (User.getUserType().equals("S")){
            attr_label.setText(labelsText_supplier);
            info_label.setText(infoText_supplier);
        }
        else{
            attr_label.setText(labelsText_cust);
            info_label.setText(infoText_cust);
        }
        
    }
}



