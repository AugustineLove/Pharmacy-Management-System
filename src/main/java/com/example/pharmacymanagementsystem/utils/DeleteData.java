package com.example.pharmacymanagementsystem.utils;

import com.example.pharmacymanagementsystem.controllers.DashboardController;
import databaseConnection.MyDatabase;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class DeleteData {

    MyDatabase myDb = new MyDatabase();
    AlertDialogue alertDialogue = new AlertDialogue();

    /**
     * Deletes the data of a drug from the database based on the given drug ID.
     *
     * @param drugId The TextField containing the drug ID to be deleted.
     * @throws SQLException           If a database access error occurs.
     * @throws ClassNotFoundException If the JDBC driver class is not found.
     */

    public void deleteDrugData(TextField drugId) throws SQLException, ClassNotFoundException {


        String deleteQuery = "DELETE FROM DRUGS WHERE DRUGID = '"+ drugId.getText()+"'";

        if(drugId.getText().equals("")){
            alertDialogue.showErrorAlert("Input a drug ID");
        }
        Statement st = myDb.getStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM DRUGS WHERE DRUGID = '"+drugId.getText()+"'");
        if(rs.next()){
            Optional<ButtonType> result = alertDialogue.showConfirmationAlert("Are you sure you want to delete "+drugId.getText() +" drug record?" );
            if(result.get() == ButtonType.OK){
                st.executeUpdate(deleteQuery);
                RetrieveData retrieveData = new RetrieveData();
                DashboardController.newUpdateListOfDrugs = retrieveData.getAllDrugs();
                retrieveData.getAllDrugs();
                alertDialogue.showSuccessAlert("Record deleted successfully!");
            }
        }
        else{
            alertDialogue.showErrorAlert("Drug not in records");
        }

    }

    /**
     * Deletes the purchase data of a drug from the database based on the given purchase ID.
     *
     * @param purchaseId The TextField containing the purchase ID to be deleted.
     * @throws SQLException           If a database access error occurs.
     * @throws ClassNotFoundException If the JDBC driver class is not found.
     */

    public void deleteDrugPurchaseData(TextField purchaseId) throws SQLException, ClassNotFoundException {


        String deleteQuery = "DELETE FROM purchases WHERE purchaseID = '"+ purchaseId.getText()+"'";

        if(purchaseId.getText().equals("")){
            alertDialogue.showErrorAlert("Input a drug ID");
        }
        Statement st = myDb.getStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM purchases WHERE purchaseID = '"+purchaseId.getText()+"'");
        if(rs.next()){
            Optional<ButtonType> result = alertDialogue.showConfirmationAlert("Are you sure you want to delete "+purchaseId.getText() +" drug record?" );
            if(result.get() == ButtonType.OK){
                st.executeUpdate(deleteQuery);
                RetrieveData retrieveData = new RetrieveData();
                DashboardController.updateListOfPurchases = retrieveData.getAllDrugPurchases();
                retrieveData.getAllDrugPurchases();
                alertDialogue.showSuccessAlert("Record deleted successfully!");


            }
        }
        else{
            alertDialogue.showErrorAlert("Drug not in records");
        }
    }

    /**
     * Deletes the data of a supplier from the database based on the given supplier ID.
     *
     * @param supplierID   The TextField containing the supplier ID to be deleted.
     * @param supplierName The TextField containing the supplier name.
     * @throws SQLException           If a database access error occurs.
     * @throws ClassNotFoundException If the JDBC driver class is not found.
     */

    public void deleteSupplierData(TextField supplierID, TextField supplierName) throws SQLException, ClassNotFoundException {

        String deleteQuery = "DELETE FROM SUPPLIERS WHERE supplierID = '"+ supplierID.getText()+"'";

        if(supplierID.getText().equals("")){
            alertDialogue.showErrorAlert("Input a drug ID");
        }
        Statement st = myDb.getStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM SUPPLIERS WHERE supplierID = '"+supplierID.getText()+"'");
        if(rs.next()){
            Optional<ButtonType> result = alertDialogue.showConfirmationAlert("Are you sure you want to delete "+supplierName.getText() +" record?" );
            if(result.get() == ButtonType.OK){
                st.executeUpdate(deleteQuery);
                RetrieveData retrieveData = new RetrieveData();
                DashboardController.updatedListOfSuppliers = retrieveData.getAllSuppliers();
                retrieveData.getAllSuppliers();
                alertDialogue.showSuccessAlert("Record deleted successfully!");
            }
        }
        else{
            alertDialogue.showErrorAlert("Drug not in records");
        }

    }
}
