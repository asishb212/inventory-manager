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
        Main.NormUserDashboardSceneSwitch(User.userName);
    }

    @FXML
    private void handleInventory() {
        // Handle Save action
        showAlert("Save clicked");
    }

    @FXML
    private void handleInsights() {
        Main.NormUserInsightsSceneSwitch();
    }

    @FXML
    private void handleInfo() {
        Main.UserInfoSceneSwitch();
    }

    @FXML
    private void handlePwd() {
        // Handle Copy action
        showAlert("Copy clicked");
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Action");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void initialize() {

        String labelsText = "Username\nUser Role\nUser Type\nUser ID\nSupplier Name\nContact First Name\nContact Last Name\nContact Phone\nSupplier ID\nSupplier Street\nSupplier City\nSupplier State\nSupplier Country\nSupplier Zipcode";
        
        String infoText = User.getUserName() + "\n" +
                          User.getUserRole() + "\n" +
                          User.getUserType() + "\n" +
                          User.getUserId() + "\n" +
                          Supplier.getSupplierName() + "\n" +
                          Supplier.getContactFirstname() + "\n" +
                          Supplier.getContactLastname() + "\n" +
                          Supplier.getContactPhone() + "\n" +
                          Supplier.getSupplierID() + "\n" +
                          SupplierAddress.getSupplierStreet() + "\n" +
                          SupplierAddress.getSupplierCity() + "\n" +
                          SupplierAddress.getSupplierState() + "\n" +
                          SupplierAddress.getSupplierCountry() + "\n" +
                          SupplierAddress.getSupplierZipcode();


        attr_label.setText(labelsText);
        info_label.setText(infoText);
    }
}



