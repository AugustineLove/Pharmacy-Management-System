package com.example.pharmacymanagementsystem.utils;

import javafx.scene.control.TextField;

public class AppConstants
{

    public void resetInputRecords(TextField drugId, TextField drugName, TextField drugDescription, TextField stock, TextField suppliers, String newDrugID){
        drugId.setText(newDrugID);
        drugName.setText("");
        drugDescription.setText("");
        stock.setText("");
        suppliers.setText("");
    }
}
