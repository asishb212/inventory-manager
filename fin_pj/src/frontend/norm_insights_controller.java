package frontend;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.collections.*;
import javafx.scene.control.ComboBox;

import java.util.ArrayList;

public class norm_insights_controller {
    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private PieChart pieChart;

    @FXML
    private LineChart<String, Number> lineChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

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
        Main.NormUserDashboardSceneSwitch(User.userName);
    }

    @FXML
    private void handleInventory() {
        // Handle Save action
        showAlert("Save clicked");
    }

    @FXML
    private void handleInsights() {
        Main.NormUserInsightsSceneSwitch();
    }

    @FXML
    private void handleInfo() {
        // Handle Cut action
        showAlert("info clicked");
    }

    @FXML
    private void handlePwd() {
        // Handle Copy action
        showAlert("Copy clicked");
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Action");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    public void handlePlotButton() {
        String selectedPlot = comboBox.getValue();

        // Sample data in ArrayLists
        ArrayList<String> categories = new ArrayList<>();
        categories.add("Category 1");
        categories.add("Category 2");
        categories.add("Category 3");
        categories.add("Category 4");
        categories.add("Category 5");
        categories.add("Category 6");
        categories.add("Category 7");


        ArrayList<Integer> values = new ArrayList<>();
        values.add(10);
        values.add(20);
        values.add(15);
        values.add(7);
        values.add(36);
        values.add(42);
        values.add(4);


        // Convert ArrayLists to ObservableLists
        ObservableList<String> observableCategories = FXCollections.observableArrayList(categories);
        ObservableList<Integer> observableValues = FXCollections.observableArrayList(values);

        // Create an XYChart.Series using the observable lists
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Data Series");

        for (int i = 0; i < observableCategories.size(); i++) {
            series.getData().add(new XYChart.Data<>(observableCategories.get(i), observableValues.get(i)));
        }

        if (selectedPlot.equals("Line Chart")){
            lineChart.getData().clear();
            lineChart.setAnimated(false);

            lineChart.setVisible(true);
            barChart.setVisible(false);
            pieChart.setVisible(false);

            lineChart.getData().add(series);
        }

        else if (selectedPlot.equals("Bar Chart")){
            barChart.getData().clear();
            barChart.setAnimated(false);

            lineChart.setVisible(false);
            barChart.setVisible(true);
            pieChart.setVisible(false);

            barChart.getData().add(series);
        }

        else if (selectedPlot.equals("Pie Chart")){
            pieChart.getData().clear();
            pieChart.setLabelsVisible(false);
            pieChart.setAnimated(false);

            lineChart.setVisible(false);
            barChart.setVisible(false);
            pieChart.setVisible(true);

            for (XYChart.Data<String, Number> data : series.getData()) {
                pieChart.getData().add(new PieChart.Data(data.getXValue(), data.getYValue().doubleValue()));
            }
        }
    }
    public void initialize() {
        pieChart.getData().clear();        
        barChart.getData().clear();
        lineChart.getData().clear();
    }
}

