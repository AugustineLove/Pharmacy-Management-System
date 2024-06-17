package com.example.pharmacymanagementsystem.utils;

import com.example.pharmacymanagementsystem.controllers.dashboardView;
import databaseConnection.MyDatabase;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.*;

public class AddData {

    public void addDrugData(TextField drugId, TextField drugName, TextField drugDescription, TextField stock, TextField suppliers, String newDrugID) throws SQLException, ClassNotFoundException {
        MyDatabase myDatabase = new MyDatabase();
        String query = "INSERT INTO DRUGS " + "(NAME, DESCRIPTION, STOCK, SUPPLIERS)" +"VALUES(?,?,?,?)";

        try (Connection con = myDatabase.getConnection()){

            if(drugId.getText().isEmpty() || drugName.getText().isEmpty() || drugDescription.getText().isEmpty()
            || stock.getText().isEmpty() || suppliers.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please provide value for all fields");
                alert.showAndWait();
            }

          else{

              String checkQuery = "SELECT DRUGID FROM DRUGS WHERE DRUGID = '"+  drugId.getText()+"'";

                Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = st.executeQuery(checkQuery);

                if(rs.next()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Drug "+drugName.getText()+ " already exists");
                    alert.showAndWait();
                }
                PreparedStatement ps = con.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

                ps.setString(1, drugName.getText());
                ps.setString(2, drugDescription.getText());
                ps.setString(3, stock.getText());
                ps.setString(4, suppliers.getText());
                ps.executeUpdate();

                AppConstants appConstants = new AppConstants();

                appConstants.resetInputRecords(drugId, drugName, drugDescription, stock, suppliers, newDrugID);
                RetrieveData retrieveData = new RetrieveData();
                dashboardView.newUpdateListOfDrugs = retrieveData.getAllDrugs();
                retrieveData.getAllDrugs();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Record added successfully!");
                alert.showAndWait();

            }

        }
    }


}
