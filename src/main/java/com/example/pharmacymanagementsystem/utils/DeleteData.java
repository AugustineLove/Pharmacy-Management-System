package com.example.pharmacymanagementsystem.utils;

import com.example.pharmacymanagementsystem.dashboardView;
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
                dashboardView.newUpdateListOfDrugs = retrieveData.getAllDrugs();
                retrieveData.getAllDrugs();
                alertDialogue.showSuccessAlert("Record deleted successfully!");
            }
        }
        else{
            alertDialogue.showErrorAlert("Drug not in records");
        }

    }

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
                dashboardView.updateListOfPurchases = retrieveData.getAllDrugPurchases();
                retrieveData.getAllDrugPurchases();
                alertDialogue.showSuccessAlert("Record deleted successfully!");


            }
        }
        else{
            alertDialogue.showErrorAlert("Drug not in records");
        }
    }


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
                dashboardView.updatedListOfSuppliers = retrieveData.getAllSuppliers();
                retrieveData.getAllSuppliers();
                alertDialogue.showSuccessAlert("Record deleted successfully!");
            }
        }
        else{
            alertDialogue.showErrorAlert("Drug not in records");
        }

    }
}
