package com.example.pharmacymanagementsystem.utils;

import com.example.pharmacymanagementsystem.controllers.DashboardController;
import databaseConnection.MyDatabase;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class UpdateData {

    MyDatabase myDb = new MyDatabase();
    AlertDialogue alertDialog = new AlertDialogue();
    ErrorHandler myErrorHandler = new ErrorHandler();

    /**
     * Updates the data of an existing drug in the database.
     *
     * @param drugId            The TextField containing the current drug ID.
     * @param drugName          The TextField containing the drug name.
     * @param drugDescription   The TextField containing the drug description.
     * @param stock             The TextField containing the stock quantity.
     * @param suppliers         The TextField containing the supplier information.
     * @param newDrugID         The new drug ID to be updated.
     * @throws SQLException           If a database access error occurs.
     * @throws ClassNotFoundException If the JDBC driver class is not found.
     * @throws IOException            If an input or output exception occurred.
     */

    public void updateDrugData(TextField drugId, TextField drugName, TextField drugDescription, TextField stock, TextField suppliers, String newDrugID) throws SQLException, ClassNotFoundException, IOException {
    try{
        if(drugId.getText().isEmpty() || drugDescription.getText().isEmpty() || stock.getText().isEmpty() || suppliers.getText().isEmpty() ){

            alertDialog.showErrorAlert("Fill in blank spaces");
        } else{

            Statement st = myDb.getStatement();

            ResultSet rs = st.executeQuery("SELECT DRUGID FROM DRUGS WHERE drugName = '"+ drugName.getText() +"'");

            if (rs.next()) {
                int Id = Integer.parseInt(rs.getString(1));

                String updateQuery = "UPDATE DRUGS SET drugName = '"+drugName.getText()+"', drugDescription = '" +drugDescription.getText() +"', drugStock = '"+ stock.getText() +"', drugSupplier = '"+ suppliers.getText() +"' WHERE DRUGID = '"+Id+"'";
                Optional<ButtonType> result = alertDialog.showConfirmationAlert("Are you sure you want to update "+ drugName.getText()+" record?");
                if(result.get() == ButtonType.OK){
                    st.executeUpdate(updateQuery);
                    RetrieveData retrieveData = new RetrieveData();
                    DashboardController.newUpdateListOfDrugs = retrieveData.getAllDrugs();
                    retrieveData.getAllDrugs();
                    alertDialog.showSuccessAlert("Record updated successfully!");

                    AppConstants appConstants = new AppConstants();
                    appConstants.resetInputRecords(drugId, drugName, drugDescription, stock, suppliers, newDrugID);
                }
            }
        }
    } catch (SQLException errors) {
        myErrorHandler.getSQLException(errors);
        }
    }

    /**
     * Updates the purchase data of an existing drug in the database.
     *
     * @param drugId            The TextField containing the current drug ID.
     * @param drugName          The TextField containing the drug name.
     * @param drugDescription   The TextField containing the drug description.
     * @param stock             The TextField containing the stock quantity.
     * @param suppliers         The TextField containing the supplier information.
     * @param newDrugID         The new drug ID to be updated.
     * @throws SQLException           If a database access error occurs.
     * @throws ClassNotFoundException If the JDBC driver class is not found.
     * @throws IOException            If an input or output exception occurred.
     */
    public void updateDrugPurchaseData(TextField drugId, TextField drugName, TextField drugDescription, TextField stock, TextField suppliers, String newDrugID) throws SQLException, ClassNotFoundException, IOException {
        try{
            if(drugId.getText().isEmpty() || drugDescription.getText().isEmpty() || stock.getText().isEmpty() || suppliers.getText().isEmpty() ){

                alertDialog.showErrorAlert("Fill in blank spaces");
            } else{

                Statement st = myDb.getStatement();

                ResultSet rs = st.executeQuery("SELECT DRUGID FROM DRUGS WHERE NAME = '"+ drugName.getText() +"'");

                if (rs.next()) {
                    int Id = Integer.parseInt(rs.getString(1));

                    String updateQuery = "UPDATE DRUGS SET NAME = '"+drugName.getText()+"', DESCRIPTION = '" +drugDescription.getText() +"', STOCK = '"+ stock.getText() +"', SUPPLIERS = '"+ suppliers.getText() +"' WHERE DRUGID = '"+Id+"'";
                    Optional<ButtonType> result = alertDialog.showConfirmationAlert("Are you sure you want to update "+ drugName.getText()+" record?");
                    if(result.get() == ButtonType.OK){
                        st.executeUpdate(updateQuery);
                        RetrieveData retrieveData = new RetrieveData();
                        DashboardController.newUpdateListOfDrugs = retrieveData.getAllDrugs();
                        retrieveData.getAllDrugs();
                        alertDialog.showSuccessAlert("Record updated successfully!");

                        AppConstants appConstants = new AppConstants();
                        appConstants.resetInputRecords(drugId, drugName, drugDescription, stock, suppliers, newDrugID);
                    }
                }
            }
        } catch (SQLException errors) {
            myErrorHandler.getSQLException(errors);
        }
    }
}
