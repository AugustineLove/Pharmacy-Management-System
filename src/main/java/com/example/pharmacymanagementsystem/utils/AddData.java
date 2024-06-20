package com.example.pharmacymanagementsystem.utils;

import com.example.pharmacymanagementsystem.dashboardView;
import databaseConnection.MyDatabase;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.*;

public class AddData {
    AlertDialogue alertDialogue = new AlertDialogue();
    MyDatabase myDatabase = new MyDatabase();
    ErrorHandler myErrorHandler = new ErrorHandler();

    public void addDrugData(TextField drugId, TextField drugName, TextField drugDescription, TextField stock, TextField suppliers, String newDrugID) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO DRUGS " + "(NAME, DESCRIPTION, STOCK, SUPPLIERS)" +"VALUES(?,?,?,?)";

        try (Connection con = myDatabase.getConnection()){

            if(drugId.getText().isEmpty() || drugName.getText().isEmpty() || drugDescription.getText().isEmpty()
            || stock.getText().isEmpty() || suppliers.getText().isEmpty()){
                alertDialogue.showErrorAlert("Please provide value for all fields");
            }
          else{
              String checkQuery = "SELECT DRUGID FROM DRUGS WHERE DRUGID = '"+  drugId.getText()+"'";

                Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = st.executeQuery(checkQuery);

                if(rs.next()){
                    alertDialogue.showErrorAlert("Drug "+drugName.getText()+ " already exists");
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

                alertDialogue.showSuccessAlert("Record added successfully!");
            }

        } catch (SQLException e) {
            myErrorHandler.getSQLException(e);
        }
    }

    public void addDrugPurchase(TextField drugName, TextField drugQuantity, TextField drugPrice, DatePicker datePicker) throws SQLException, ClassNotFoundException {

        try(Connection con = myDatabase.getConnection()){

            if(drugName.getText().isEmpty() || drugQuantity.getText().isEmpty() || drugPrice.getText().isEmpty() || datePicker.getValue() == null){
                alertDialogue.showErrorAlert("Please provide value for all fields");
            }

            String addPurchase = "INSERT INTO PURCHASE (NAME, QUANTITY, PRICE, DATE) VALUES (?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(addPurchase);
            ps.setString(1, drugName.getText());
            ps.setString(2, drugQuantity.getText());
            ps.setString(3, drugPrice.getText());
            ps.setString(4, datePicker.getValue().toString());
            ps.executeUpdate();

            AppConstants appConstants = new AppConstants();

            appConstants.resetPInputRecords(drugName, drugQuantity, drugPrice, datePicker);
            RetrieveData retrieveData = new RetrieveData();
            dashboardView.updateListOfPurchases = retrieveData.getAllDrugPurchases();
            retrieveData.getAllDrugPurchases();

            alertDialogue.showSuccessAlert("Record added successfully!");

        } catch (SQLException exception){
            myErrorHandler.getSQLException(exception);
        }
    }
}
