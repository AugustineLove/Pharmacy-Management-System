package com.example.pharmacymanagementsystem.utils;

import com.example.pharmacymanagementsystem.dashboardView;
import com.example.pharmacymanagementsystem.models.Drug;
import com.example.pharmacymanagementsystem.models.Purchase;
import databaseConnection.MyDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
                Drug newDrug = new Drug(rs.getString("DRUGID"),
                        rs.getString("NAME"),
                        rs.getString("DESCRIPTION"),
                        rs.getString("STOCK"),
                        rs.getString("SUPPLIERS"));
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
        String query = "SELECT * FROM PURCHASE";

        try (Connection con = newsql.getConnection()){
            PreparedStatement ps = con.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Purchase newPurchase = new Purchase(
                        rs.getString("Name"),
                        rs.getString("Quantity"),
                        rs.getString("Price"),
                        rs.getString("Date"));
                purchaseList.add(newPurchase);

            }
        } catch (SQLException e){
            myErrorHandler.getSQLException(e);
        }
        dashboardView.updateListOfPurchases = purchaseList;
        return purchaseList;
    };
}
