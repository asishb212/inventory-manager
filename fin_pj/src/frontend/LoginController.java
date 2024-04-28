package frontend;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;


public class LoginController {
    @FXML
    private Label label;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

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
    public void signin_button_handler() {
        //String userName = usernameField.getText();
        //String password = passwordField.getText();
        //String json_login_info = "{\"password\": \"" + password + "\", \"email\": \"" + userName + "\"}";

        boolean isLoginSuccess = true;
        boolean isAdmin = false;

        // code to connect to server
        // code to get a data response

        if (isLoginSuccess) {
            if (isAdmin) {
                Main.AdminUserDashboardSceneSwitch();
            }
            else{
                Main.NormUserDashboardSceneSwitch("userName");
            }
        }
    }

    public void newUser_button_handler() {
        Main.NewUserSceneSwitch();
    }

}
