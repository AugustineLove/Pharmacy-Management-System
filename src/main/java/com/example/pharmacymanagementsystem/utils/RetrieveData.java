package com.example.pharmacymanagementsystem.utils;

import com.example.pharmacymanagementsystem.controllers.dashboardView;
import com.example.pharmacymanagementsystem.models.Drug;
import databaseConnection.MyDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RetrieveData {

    MyDatabase newsql = new MyDatabase();

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
            System.out.println(e);
        }
        dashboardView.newUpdateListOfDrugs = drugList;
        return drugList;
    };



}
