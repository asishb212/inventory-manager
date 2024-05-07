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

        conn = DBConnection.getConnection();
        Queries.setConnection(conn);
    	//System.out.println("DB Connected is : " + conn); 

        boolean isLoginSuccess = Queries.checkCredentials(userName, password);
        
        if (isLoginSuccess) {

            Map<String,Object> userData = Queries.getUserInfo(userName);

            User.setUserName((String) userData.get("username"));
            User.setUserId((int) userData.get("user_id"));
            User.setUserRole((String) userData.get("user_role"));
            User.setUserType((String) userData.get("user_type"));

            if (User.getUserRole().equals("Admin")) {
                //Main.AdminUserDashboardSceneSwitch();
                Main.AdminUserDashboardSceneSwitch();
            }
            else {
                if (User.getUserType().equals("S")){
                    Map<String,Object> Info = Queries.getSupplier(User.getUserId()) ;

                    Supplier.setSupplierID((long) Info.get("SUPPLIER_ID"));
                    Supplier.setSupplierName((String) Info.get("SUPPLIER_NAME"));
                    Supplier.setContactFirstname((String) Info.get("CONTACT_FIRSTNAME"));
                    Supplier.setContactLastname((String) Info.get("CONTACT_LASTNAME"));
                    Supplier.setContactPhone((String) Info.get("CONTACT_PHONE"));

                    Map<String,Object> Address = Queries.getSupplierAddress(Supplier.getSupplierID());
                    
                    address.setSupplierStreet((String) Address.get("ADDR_STREET"));
                    address.setSupplierCity((String) Address.get("ADDR_CITY"));
                    address.setSupplierState((String) Address.get("ADDR_STATE"));
                    address.setSupplierCountry((String) Address.get("ADDR_COUNTRY"));
                    address.setSupplierZipcode((String) Address.get("ADDR_ZIPCODE"));

                    Main.DashboardSceneSwitch(userName);
                }
                else{
                    Map<String,Object> Info = Queries.getCustomer(User.getUserId()) ;

                    Customer.setCustomerID((long) Info.get("CUST_ID"));
                    Customer.setFirstname((String) Info.get("FIRST_NAME"));
                    Customer.setLastname((String) Info.get("LAST_NAME"));
                    Customer.setPhoneNumber((String) Info.get("PHONE_NUMBER"));
                    Customer.setEmail((String) Info.get("EMAIL"));

                    Map<String,Object> Address = Queries.getCustomerAddress(Customer.getPhoneNumber());
                    
                    address.setSupplierStreet((String) Address.get("ADDR_STREET"));
                    address.setSupplierCity((String) Address.get("ADDR_CITY"));
                    address.setSupplierState((String) Address.get("ADDR_STATE"));
                    address.setSupplierCountry((String) Address.get("ADDR_COUNTRY"));
                    address.setSupplierZipcode((String) Address.get("ADDR_ZIPCODE"));

                    Main.DashboardSceneSwitch(userName);
                }
            }
        }
        else {
            showAlert("Wrong Login Credentials");
        }
    }

    public void newUser_button_handler() {
        Main.NewUserSceneSwitch();
    }

}
