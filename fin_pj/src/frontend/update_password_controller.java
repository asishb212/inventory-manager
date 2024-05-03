package frontend;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;

import Backend.*;


public class update_password_controller {

    @FXML
    private Label label;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

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

    public boolean validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            showAlert("Password cannot be empty.");
            return false;
        }
        else if (password.length() < 8) {
            showAlert("Password must be at least 8 characters long.");
            return false;
        }
        else if (password.length() > 30) {
            showAlert("Password cannot be longer than 30 characters.");
            return false;
        }
        else if (!password.matches(".*[A-Z].*")) {
            showAlert("Password must contain at least one uppercase letter.");
            return false;
        }
        else if (!password.matches(".*[a-z].*")) {
            showAlert("Password must contain at least one lowercase letter.");
            return false;
        }
        else if (!password.matches(".*[0-9].*")) {
            showAlert("Password must contain at least one digit.");
            return false;
        }
        return true;
    }

    public boolean confirmPassword(String pass, String CfmPass){
        if (!pass.equals(CfmPass)){
            showAlert("Passwords don't match");
            return false;
        }
        return true;
    }

    @FXML
    public void cancel_button_handler() {
        Main.DashboardSceneSwitch(User.userName);
    }

    public void change_pwd_button_handler() {
        String password = passwordField.getText();
        String ConfirmPassword = confirmPasswordField.getText();

        if (validatePassword(password) && confirmPassword(password,ConfirmPassword)) {
            Queries.updatePassword(User.getUserName(), password);
            showAlert("password changed succesfully.");
            Main.DashboardSceneSwitch(User.userName);
        }
        else{
            showAlert("Password updation failed. try again.");
        }
    }

    public void initialize() {
        String instructions = 
    "Password Creation Steps:\n\n"
    + "   - Your password must be at least 8 characters long and no more than 30 characters.\n"
    + "   - It must include at least one uppercase letter, one lowercase letter, and one digit.\n\n";

        label.setText(instructions);   
    }
}
