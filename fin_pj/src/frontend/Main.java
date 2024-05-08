package frontend;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage stage;
    private static String userName;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Main.stage = primaryStage;
        LoginSceneSwitch();
        primaryStage.setTitle("Inventory Management");
        primaryStage.show();
    }

    public static void LoginSceneSwitch() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/frontend/login_page.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void NewUserSceneSwitch() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/frontend/newUser.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void DashboardSceneSwitch(String name) {
        try {

            Main.userName = name;

            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/frontend/dashboard.fxml"));
            Parent root = loader.load();

            dashboard_controller norm_dashboard_controller = loader.getController();
            norm_dashboard_controller.setText("Hello "+name);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void AdminUserDashboardSceneSwitch() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/frontend/admin_dashboard.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void AdminUsersDataSceneSwitch() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/frontend/admin_users_data.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void AdminInventorySceneSwitch() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/frontend/admin_inventory.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void AdminInsightsSceneSwitch() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/frontend/admin_insights.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void AddItemSceneSwitch() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/frontend/add_item.fxml"));
            Parent root = loader.load();

            add_item_controller add_item_controller = loader.getController();
            add_item_controller.setUsername(userName);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void UpdateItemSceneSwitch() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/frontend/update_item.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void InsightsSceneSwitch() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/frontend/insights.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void UserInfoSceneSwitch() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("handle_info.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void UpdatePwdSceneSwitch() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/frontend/update_password.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void SearchSceneSwitch() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/frontend/add_order.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void CartSceneSwitch() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/frontend/Cart.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void ManageSceneSwitch() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/frontend/manage_order.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
