package frontend;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.event.ActionEvent;


import java.sql.Connection;
import java.sql.SQLException;

import Backend.*;

public class newUser_controller {

    private static Connection conn;

    @FXML
    private Label label;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField email;

    @FXML
    private TextField AddStreet;

    @FXML
    private TextField Addcity;

    @FXML
    private TextField Addstate;

    @FXML
    private TextField Addcountry;

    @FXML
    private TextField Addzip;

    @FXML
    private ComboBox<String> userType;

    @FXML
    private TextField supp_name;
    
    @FXML
    public void initialize() {
        String instructions = 
    "Account Creation Steps:\n\n"
    + "1. Username:\n"
    + "   - Ensure your username is between 5 and 30 characters in length.\n\n"
    + "2. Password:\n"
    + "   - Your password must be at least 8 characters long and no more than 30 characters.\n"
    + "   - It must include at least one uppercase letter, one lowercase letter, and one digit.\n\n"
    + "3. Confirm Password:\n"
    + "   - Re-enter your password to confirm it.\n"
    + "   - Ensure both passwords match.\n\n"
    + "4. First Name:\n"
    + "   - The first name must not exceed 30 characters and cannot be empty.\n\n"
    + "5. Last Name:\n"
    + "   - The last name must not exceed 30 characters and cannot be empty.\n\n"
    + "6. Phone Number:\n"
    + "   - The phone number must consist exactly of 10 digits and cannot be empty.\n\n"
    + "7. Email:\n"
    + "   - Ensure the email is valid and does not exceed 30 characters in length.\n\n"
    + "8. Address:\n"
    + "   - Enter your full address, including street, city, state, country, and zip code.\n"
    + "   - Ensure each part does not exceed 30 characters and is not empty.\n"
    + "   - The zip code must not exceed 10 characters.\n\n"
    + "9. User Type:\n"
    + "   - Ensure that a user type is selected.";

        label.setText(instructions);   
    }

    @FXML
    private void handleExit() {
        System.exit(0);
    }

    public boolean validateUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            showAlert("Username cannot be empty.");
            return false;
        }
        else if (username.length() > 30) {
            showAlert("Username cannot be longer than 30 characters.");
            return false;
        }
        else if (username.length() < 5) {
            showAlert("Username should be longer than 5 characters.");
            return false;
        }
        return true;
    }

    // Validates password
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

    public boolean validateFirstName(String firstName) {
        if (firstName == null || firstName.trim().isEmpty()) {
            showAlert("First name cannot be empty.");
            return false;
        }
        else if (firstName.length() > 30) {
            showAlert("First name cannot be longer than 30 characters.");
            return false;
        }
        return true;
    }

    // Validates last_name
    public boolean validateLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty()) {
            showAlert("Last name cannot be empty.");
            return false;
        }
        else if (lastName.length() > 30) {
            showAlert("Last name cannot be longer than 30 characters.");
            return false;
        }
        return true;
    }

    // Validates phone_number
    public boolean validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            showAlert("Phone number cannot be empty.");
            return false;
        }
        else if (!phoneNumber.matches("\\d{10}")) {
            showAlert("Phone number must be exactly 10 digits.");
            return false;
        }
        return true;
    }

    // Validates email
    public boolean validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            showAlert("Email cannot be empty.");
            return false;
        }
        else if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            showAlert("Email is not valid.");
            return false;
        }
        else if (email.length() > 30) {
            showAlert("Email cannot be longer than 30 characters.");
            return false;
        }
        return true;
    }

    // Validates addr_street
    public boolean validateStreet(String street) {
        if (street == null || street.trim().isEmpty()) {
            showAlert("Street cannot be empty.");
            return false;
        }
        else if (street.length() > 30) {
            showAlert("Street cannot be longer than 30 characters.");
            return false;
        }
        return true;
    }

    // Validates addr_city
    public boolean validateCity(String city) {
        if (city == null || city.trim().isEmpty()) {
            showAlert("City cannot be empty.");
            return false;
        }
        else if (city.length() > 30) {
            showAlert("City cannot be longer than 30 characters.");
            return false;
        }
        return true;
    }

    // Validates addr_state
    public boolean validateState(String state) {
        if (state == null || state.trim().isEmpty()) {
            showAlert("State cannot be empty.");
            return false;
        }
        else if (state.length() > 30) {
            showAlert("State cannot be longer than 30 characters.");
            return false;
        }
        return true;
    }

    // Validates addr_country
    public boolean validateCountry(String country) {
        if (country == null || country.trim().isEmpty()) {
            showAlert("Country cannot be empty.");
            return false;
        }
        else if (country.length() > 30) {
            showAlert("Country cannot be longer than 30 characters.");
            return false;
        }
        return true;
    }

    // Validates addr_zipcode
    public boolean validateZipCode(String zipCode) {
        if (zipCode == null || zipCode.trim().isEmpty()) {
            showAlert("Zip code cannot be empty.");
            return false;
        }
        else if (zipCode.length() > 10){
            showAlert("Zip code cannot be longer than 10 charecters");
            return false;
        }
        return true;
    }

    public boolean validateUsertype(String userType){
        if (userType == null){
            showAlert("Choose user type");
            return false;
        }
        return true;
    }

    public boolean validateSuppName(String supplierName){
        if (supplierName == null || supplierName.trim().isEmpty()) {
            showAlert("Supplier name cannot be empty.");
            return false;
        }
        else if (supplierName.length() > 30) {
            showAlert("Supplier name cannot be longer than 30 characters.");
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
    void comboBoxChanged(ActionEvent event) {
        String selectedOption = userType.getValue();
        if (selectedOption != null) {
            if (selectedOption.equals("Supplier")) {
                supp_name.setVisible(true);
            } else {
                supp_name.setVisible(false);
            }
        }
    }

    @FXML
    public void create_account_button_handler() throws SQLException{

        String userName = usernameField.getText();
        String password = passwordField.getText();
        String ConfirmPassword = confirmPasswordField.getText();

        String firstNameText = firstName.getText();
        String lastNameText = lastName.getText();
        String phoneNumberText = phoneNumber.getText();
        String emailText = email.getText();
        String addStreetText = AddStreet.getText();
        String addCityText = Addcity.getText();
        String addStateText = Addstate.getText();
        String addCountryText = Addcountry.getText();
        String addZipText = Addzip.getText();
        String user_type_value = userType.getValue();
        String suppName = supp_name.getText();

        String user_type;

        if (validateUsername(userName) && 
            validatePassword(password) && 
            validateFirstName(firstNameText) && 
            validateLastName(lastNameText) &&
            validatePhoneNumber(phoneNumberText) && 
            validateEmail(emailText) && 
            validateStreet(addStreetText) && 
            validateCity(addCityText) &&
            validateState(addStateText) && 
            validateCountry(addCountryText) && 
            validateZipCode(addZipText) &&
            validateUsertype(user_type_value) &&
            confirmPassword(password,ConfirmPassword)) {
        // All fields are valid, proceed with creating the account
        // You can add your account creation logic here
                
                if (user_type_value.equals("Customer")){
                    user_type = "C";

                    conn = DBConnection.getConnection();
                    Queries.setConnection(conn);
    	            System.out.println("DB Connected is : " + conn);

                    if (Queries.CheckUsername(userName)){
                        showAlert("User name is already taken.");
                        return;
                    }

                    if (Queries.checkCustomerPhone(phoneNumberText)){
                        showAlert("Phone number already exists.");
                        return;
                    }
                    else{
                    Queries.addUser(userName, user_type, password);
                    Queries.addCustomer(firstNameText, lastNameText, phoneNumberText, emailText, Queries.getUserId(userName));
                    Queries.addCustomerAddress(phoneNumberText, addStreetText, addCityText, addStateText, addCountryText, addZipText);
                    }
                }
                else{
                    user_type = "S";
                    System.out.println("hi");

                    if (!validateSuppName(suppName)){
                        return;
                    }

                    conn = DBConnection.getConnection();
                    Queries.setConnection(conn);
    	            System.out.println("DB Connected is : " + conn);

                    if (Queries.CheckUsername(userName)){
                        showAlert("User name is already taken.");
                        return;
                    }

                    if (Queries.checkSupplierName(suppName)){
                        showAlert("Supplier name already exists.");
                        return;
                    }
                    
                    else{
                        Queries.addUser(userName, user_type, password);
                        Queries.addSupplier(userName,suppName, firstNameText, lastNameText, phoneNumberText);
                        Queries.addSupplierAddress(suppName, addStreetText, addCityText, addStateText, addCountryText, addZipText);
                        }
                    
                }
            showAlert("Account successfully created!");
            Main.LoginSceneSwitch();
            return;
        }

        //showAlert("Account creation failed. Try again");
        //Main.NewUserSceneSwitch();
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