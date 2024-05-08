package frontend;

import javafx.fxml.FXML;

public class admin_dashboard_controller {


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
    private void handleInfo() {
        // Handle Cut action
        Main.UserInfoSceneSwitch();
    }

    @FXML
    private void handlePwd() {
        Main.UpdatePwdSceneSwitch();
    }

    @FXML
    private void handleInventory() {
        Main.AdminInventorySceneSwitch();
    }

    @FXML
    private void handleDataInsights() {
        Main.AdminInsightsSceneSwitch();
    }

    @FXML
    private void handleShowuserData() {
        Main.AdminUsersDataSceneSwitch();
    }
}
