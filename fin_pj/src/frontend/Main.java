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
        Main.stage.setFullScreen(true);

        LoginSceneSwitch();
        primaryStage.setTitle("Project");
        primaryStage.show();
    }

    public static void LoginSceneSwitch() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/frontend/login_page.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setFullScreen(true);
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
    public static void NormUserDashboardSceneSwitch(String name) {
        try {

            Main.userName = name;

            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/frontend/norm_dashboard.fxml"));
            Parent root = loader.load();

            norm_dashboard_controller norm_dashboard_controller = loader.getController();
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

    public static void NormUserAddItemSceneSwitch() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/frontend/norm_add_item.fxml"));
            Parent root = loader.load();

            norm_add_item_controller norm_add_item_controller = loader.getController();
            norm_add_item_controller.setUsername(userName);

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void NormUserUpdateItemSceneSwitch() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/frontend/norm_update_item.fxml"));
            Parent root = loader.load();

            norm_add_item_controller norm_add_item_controller = loader.getController();
            norm_add_item_controller.setUsername(userName);

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void NormUserInsightsSceneSwitch() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/frontend/norm_insights.fxml"));
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
            stage.setFullScreen(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
