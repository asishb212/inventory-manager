package frontend;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;


public class dashboard_controller {

    @FXML
    private Label welcomeText;

    @FXML
    private Button AddItemButton;

    @FXML
    private Button UpdateItemButton;

    @FXML
    private Button OrderButton;

    @FXML
    private Button ManageOrderButton;

    @FXML
    private Button CartButton;

    @FXML
    private void handleLogout() {
        // Handle New action
        Main.LoginSceneSwitch();
    }

    @FXML
    private void handleExit() {
        System.exit(0);
    }

    @FXML
    private void handleInventory() {
        Main.SearchSceneSwitch();
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

    @FXML
    private void handleAddItem(){
        Main.AddItemSceneSwitch();
    }

    @FXML 
    private void handleUpdateItem(){
        Main.UpdateItemSceneSwitch();
    }

    @FXML
    private void handleOrder(){
        Main.SearchSceneSwitch();
    }

    @FXML 
    private void handleManageOrder(){
        Main.ManageSceneSwitch();
    }

    @FXML 
    private void handleShowCart(){
        Main.CartSceneSwitch();
    }

    public void setText(String text) {
        welcomeText.setText(text);    
    }

    public void initialize(){
        if (User.getUserType().equals("S")){
            AddItemButton.setVisible(true);
            UpdateItemButton.setVisible(true);
            OrderButton.setVisible(true);
            ManageOrderButton.setVisible(true);
            CartButton.setVisible(true);

            AddItemButton.setDisable(false);
            UpdateItemButton.setDisable(false);
            OrderButton.setDisable(false);
            ManageOrderButton.setDisable(false);
            CartButton.setDisable(false);
        }
        else{
            AddItemButton.setVisible(false);
            UpdateItemButton.setVisible(false);
            OrderButton.setVisible(true);
            ManageOrderButton.setVisible(true);
            CartButton.setVisible(true);

            AddItemButton.setDisable(true);
            UpdateItemButton.setDisable(true);
            OrderButton.setDisable(false);
            ManageOrderButton.setDisable(false);
            CartButton.setDisable(false);
        }
    }
}
