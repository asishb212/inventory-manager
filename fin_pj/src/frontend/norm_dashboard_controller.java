package frontend;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;


public class norm_dashboard_controller {

    @FXML
    private Label welcomeText;


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
    private void handleAddItem(){
        showAlert("Copy clicked");
    }

    @FXML 
    private void handleUpdateItem(){
        showAlert("Copy clicked");
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Action");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void setText(String text) {
        welcomeText.setText(text);    
    }
}
