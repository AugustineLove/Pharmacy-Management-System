package com.example.pharmacymanagementsystem.utils;

import com.example.pharmacymanagementsystem.dashboardView;
import com.example.pharmacymanagementsystem.models.Drug;
import com.example.pharmacymanagementsystem.models.DrugAndSupplier;
import com.example.pharmacymanagementsystem.models.Purchase;
import com.example.pharmacymanagementsystem.models.Supplier;
import databaseConnection.MyDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class RetrieveData {

    MyDatabase newsql = new MyDatabase();
    ErrorHandler myErrorHandler = new ErrorHandler();

    public ObservableList<Drug> getAllDrugs() throws SQLException, ClassNotFoundException {
        ObservableList<Drug> drugList = FXCollections.observableArrayList();
        String query = "SELECT * FROM DRUGS";

        try (Connection con = newsql.getConnection()){
            PreparedStatement ps = con.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Drug newDrug = new Drug(
                        rs.getString("drugID"),
                        rs.getString("drugName"),
                        rs.getString("drugDescription"),
                        rs.getString("drugStock"),
                        rs.getString("drugSupplier"),
                        rs.getString("supplierID")
                        );
                drugList.add(newDrug);

            }
        } catch (SQLException e){
            myErrorHandler.getSQLException(e);
        }
        dashboardView.newUpdateListOfDrugs = drugList;
        return drugList;
    };

    public ObservableList<Purchase> getAllDrugPurchases() throws SQLException, ClassNotFoundException {
        ObservableList<Purchase> purchaseList = FXCollections.observableArrayList();
        String query = "SELECT c.customerID, p.purchaseId, p.drugName, p.drugQuantity, p.drugPrice, p.purchaseDate FROM purchases p JOIN customers c ON p.customerID = c.customerID";

        try (Connection con = newsql.getConnection()){
                PreparedStatement pds = con.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = pds.executeQuery();

                while (rs.next()){
                    Purchase newPurchase = new Purchase(
                            rs.getString("purchaseID"),
                            rs.getString("drugName"),
                            rs.getString("drugQuantity"),
                            rs.getString("drugPrice"),
                            rs.getString("purchaseDate"),
                            rs.getString("customerID")
                    );
                    purchaseList.add(newPurchase);



}
        } catch (SQLException e){
            myErrorHandler.getSQLException(e);
        }

        dashboardView.updateListOfPurchases = purchaseList;
        return purchaseList;
    };


    public ObservableList<Supplier> getAllSuppliers() throws SQLException, ClassNotFoundException {
        ObservableList<Supplier> supplierList = FXCollections.observableArrayList();
        String query = "SELECT * FROM SUPPLIERS";

        try (Connection con = newsql.getConnection()){
            PreparedStatement ps = con.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Supplier newSupplier = new Supplier(
                        rs.getString("supplierID"),
                        rs.getString("supplierName"),
                        rs.getString("supplierLocation"),
                        rs.getString("supplierPhone")
                        );
                supplierList.add(newSupplier);

            }
        } catch (SQLException e){
            myErrorHandler.getSQLException(e);
        }
        dashboardView.updatedListOfSuppliers = supplierList;
        return supplierList;
    };


    public ObservableList<DrugAndSupplier> getAllDrugSupplier() throws SQLException, ClassNotFoundException {
        ObservableList<DrugAndSupplier> drugAndSupplierList = null;
        try (Connection con = newsql.getConnection()) {
            drugAndSupplierList = FXCollections.observableArrayList();
            String query = "SELECT d.drugID, s.supplierID, d.drugName, s.supplierName, s.supplierLocation, d.drugDescription, s.supplierPhone FROM DrugSupplier ds JOIN Drugs d ON ds.DrugID = d.DRUGID JOIN Suppliers s ON ds.SupplierID = s.supplierID";

            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DrugAndSupplier newDrugAndSupplier = new DrugAndSupplier(
                        rs.getString("drugID"),
                        rs.getString("supplierID"),
                        rs.getString("drugName"),
                        rs.getString("supplierName"),
                        rs.getString("supplierLocation"),
                        rs.getString("supplierPhone")
                );
                drugAndSupplierList.add(newDrugAndSupplier);
            }
        } catch (SQLException e){
            myErrorHandler.getSQLException(e);
        }
        return drugAndSupplierList;
    }

    public void generateReport() {
        Stage reportStage = new Stage();
        reportStage.setTitle("Drug Stock Report");

        PieChart pieChart = new PieChart();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        try (Connection conn = newsql.getConnection()) {
            String query = "SELECT d.DrugName, d.drugStock FROM Drugs d";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String drugName = rs.getString("DrugName");
                String stockQuantity = rs.getString("drugStock");

                // Extract numeric part from the stockQuantity string
                String numericStockQuantity = stockQuantity.replaceAll("[^\\d]", "");

                // Parse the numeric part to an integer and add it to the pie chart data
                pieChartData.add(new PieChart.Data(drugName, Integer.parseInt(numericStockQuantity)));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        pieChart.setData(pieChartData);

        VBox vbox = new VBox();
        vbox.getChildren().add(pieChart);

        Scene scene = new Scene(vbox, 800, 600);
        reportStage.setScene(scene);
        reportStage.show();
    }

}
