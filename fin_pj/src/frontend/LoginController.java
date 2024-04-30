package frontend;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.Map;

import Backend.*;

public class LoginController {

    private static Connection conn;

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

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Action");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void signin_button_handler() throws SQLException {
        String userName = usernameField.getText();
        String password = passwordField.getText();
        //String json_login_info = "{\"password\": \"" + password + "\", \"email\": \"" + userName + "\"}";

        conn = DBConnection.getConnection();
        Queries.setConnection(conn);
    	System.out.println("DB Connected is : " + conn); 

        boolean isLoginSuccess = Queries.checkCredentials(userName, password);
        System.out.println(isLoginSuccess);
        
        boolean isAdmin = false;

        // code to connect to server
        // code to get a data response

        if (isLoginSuccess) {

            Map<String,Object> userData = Queries.getUserInfo(userName);

            User.setUserName((String) userData.get("username"));
            User.setUserId((int) userData.get("user_id"));
            User.setUserRole((String) userData.get("user_role"));
            User.setUserType((String) userData.get("user_type"));

            if (isAdmin) {
                Main.AdminUserDashboardSceneSwitch();
            }
            else{
                Main.NormUserDashboardSceneSwitch(userName);
            }
        }
        else {
            conn.close();
            showAlert("Wrong Login Credentials");
        }
    }

    public void newUser_button_handler() {
        Main.NewUserSceneSwitch();
    }

}
