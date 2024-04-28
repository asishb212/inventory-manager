package frontend;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;

public class newUser_controller {

    @FXML
    private Label label;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;
    
    @FXML
    public void initialize() {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        label.setText("Hello, JavaFX " + javafxVersion + "\nRunning on Java " + javaVersion + ".");    
    }

    @FXML
    private void handleExit() {
        System.exit(0);
    }

    @FXML
    public void create_account_button_handler() {
        String userName = usernameField.getText();
        String password = passwordField.getText();
        String ConfirmPassword = confirmPasswordField.getText();
        //String json_login_info = "{\"password\": \"" + password + "\", \"email\": \"" + userName + "\"}";

        if (password.equals(ConfirmPassword) && password.length()>=8 && userName.length()>=5){
            showAlert(userName+"'s Account Created");
            Main.LoginSceneSwitch();
        }
        else if (userName.length()<5){
            showAlert("Username should be atleast 5 charecters long");
        }
        else if (!password.equals(ConfirmPassword)){
            showAlert("Passwords did not match");
        }
        else if (password.length()<8){
            showAlert("Password should be atleast 8 charecters long");
        }
        else{
            showAlert("Account creation failed");
        }
    }

    @FXML
    public void cancel_button_handler() {
        Main.LoginSceneSwitch();
    }

    public void newUser_button_handler() {
        Main.NewUserSceneSwitch();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Action");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}