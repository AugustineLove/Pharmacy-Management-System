package com.example.pharmacymanagementsystem.utils;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.util.Date;

public class AppConstants
{

    public void resetInputRecords(TextField drugId, TextField drugName, TextField drugDescription, TextField stock, TextField suppliers, String newDrugID){
        drugId.setText(newDrugID);
        drugName.setText("");
        drugDescription.setText("");
        stock.setText("");
        suppliers.setText("");
    }

    public void resetPInputRecords(TextField drugName, TextField quantity, TextField price, DatePicker datePicker){
        drugName.setText("");
        quantity.setText("");
        price.setText("");
        datePicker.setValue(LocalDate.now());
    }
}
