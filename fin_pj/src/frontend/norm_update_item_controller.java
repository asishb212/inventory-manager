package frontend;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class norm_update_item_controller {

    private static String userName;
    
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

    @FXML 
    private void handleConfirm() {
        showAlert("add item clicked");
    }

    @FXML 
    private void handleCancel() {
        Main.NormUserDashboardSceneSwitch(norm_update_item_controller.userName);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Action");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void setUsername(String text) {
        norm_update_item_controller.userName = text;
    }
}
