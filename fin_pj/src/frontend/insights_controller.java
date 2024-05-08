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
import javafx.scene.layout.StackPane;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import Backend.Queries;

public class insights_controller {
    @FXML
    private ComboBox<String> comboBox,comboBoxGraph;

    @FXML
    private StackPane stackpane;

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
        Main.DashboardSceneSwitch(User.userName);
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

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Action");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    public void handlePlotButton() {

        stackpane.setVisible(true);

        String selectedPlot = comboBox.getValue();
        String selectedGraph = comboBoxGraph.getValue();

        if (selectedPlot == null){
            showAlert("Choose a chart type");
            return;
        }

        if (selectedGraph == null){
            showAlert("Choose required insight");
            return;
        }

        ArrayList<Map<String,Object>> ordersCurUser = Queries.getOrders_by_uID(User.userId);
        ArrayList<Map<String,Object>> itemsInEachOrder = new ArrayList<>();

        for (Map<String,Object> order : ordersCurUser){

            String item_ids_str = (String) order.get("items");
            long[] item_ids = Arrays.stream(item_ids_str.split(";")).mapToLong(Long::parseLong).toArray();

            String quantity_str = (String) order.get("quantities");
            long[] quantities = Arrays.stream(quantity_str.split(";")).mapToLong(Long::parseLong).toArray();

            int i = 0;

            for(long item_id : item_ids){
                Map<String,Object> item = Queries.getItems_by_uID_orderID(item_id);
                item.put("quantity", quantities[i]);
                itemsInEachOrder.add(item);
                i += 1;
            }
        }

        ArrayList<String> monthsList = new ArrayList<>();
        ArrayList<Double> spendingPerMonthList = new ArrayList<>();
        
        // Initialize ArrayLists to store categories and spending on each category
        ArrayList<String> categoriesList = new ArrayList<>();
        ArrayList<Double> spendingPerCategoryList = new ArrayList<>();
        
        // Process orders to calculate spending
        for (Map<String, Object> order : ordersCurUser) {
            String dateCreated = order.get("date_created").toString();
            double totalOrderAmount = 0.0;
        
            // Calculate total amount for each order
            for (Map<String, Object> item : itemsInEachOrder) {
                BigDecimal unitPriceBD = (BigDecimal) item.get("item_unit_price");
                double unitPrice = unitPriceBD.doubleValue();
                long quantity = (long) item.get("quantity");
                totalOrderAmount += (unitPrice * quantity);
            }
        
            // Extract month from the order date
            String month = dateCreated.substring(5,7);
        
            // Update monthsList and spendingPerMonthList
            if (!monthsList.contains(month)) {
                monthsList.add(month);
                spendingPerMonthList.add(totalOrderAmount);
            } else {
                int index = monthsList.indexOf(month);
                double currentSpending = spendingPerMonthList.get(index);
                spendingPerMonthList.set(index, currentSpending + totalOrderAmount);
            }

        }
        
        // Process items to calculate category spending
        for (Map<String, Object> item : itemsInEachOrder) {
            String category = (String) item.get("Category");
            BigDecimal unitPriceBD = (BigDecimal) item.get("item_unit_price");
            double unitPrice = unitPriceBD.doubleValue();
            long totalQtySold = (long) item.get("quantity");
            double itemSpending = unitPrice * totalQtySold;
        
            // Update categoriesList and spendingPerCategoryList
            if (!categoriesList.contains(category)) {
                categoriesList.add(category);
                spendingPerCategoryList.add(itemSpending);
            } else {
                int index = categoriesList.indexOf(category);
                double currentSpending = spendingPerCategoryList.get(index);
                spendingPerCategoryList.set(index, currentSpending + itemSpending);
            }
        }

        // Convert ArrayLists to ObservableLists
        ObservableList<String> observableCategories = FXCollections.observableArrayList(categoriesList);
        ObservableList<Double> observableSpendingCategories = FXCollections.observableArrayList(spendingPerCategoryList);

        ObservableList<String> observableMonth = FXCollections.observableArrayList(monthsList);
        ObservableList<Double> observableSpendingMonth = FXCollections.observableArrayList(spendingPerMonthList);

        // Create an XYChart.Series using the observable lists
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        if (selectedGraph.equals("Spending by month")){
            for (int i = 0; i < observableMonth.size() && i < observableSpendingMonth.size(); i++) {
                series.getData().add(new XYChart.Data<>(observableMonth.get(i),observableSpendingMonth.get(i)));
            }
            series.setName("Spending by month");
        }

        else if (selectedGraph.equals("Spending by category")){
            for (int i = 0; i < observableCategories.size() && i < observableSpendingCategories.size(); i++) {
                series.getData().add(new XYChart.Data<>(observableCategories.get(i),observableSpendingCategories.get(i)));
            }
            series.setName("Spending by month");
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
            //pieChart.setLabelsVisible(false);
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
        
        stackpane.setVisible(false);
        pieChart.getData().clear();        
        barChart.getData().clear();
        lineChart.getData().clear();
    }

    public void handleCancelButton() {
        Main.DashboardSceneSwitch(User.userName);
    }
}

