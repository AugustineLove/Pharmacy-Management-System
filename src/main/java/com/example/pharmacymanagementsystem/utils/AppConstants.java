package com.example.pharmacymanagementsystem.utils;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

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

    public void resetSupplierInputRecords(TextField supplierId, TextField supplierName, TextField supplierLocation, TextField supplierPhone, String newSupplierID){
        supplierId.setText(newSupplierID);
        supplierName.setText("");
        supplierLocation.setText("");
        supplierPhone.setText("");
    }

    public void resetDrugSupplierRecords(TextField drugName, TextField supplierName, TextField supplierLocation){
        drugName.setText("");
        supplierName.setText("");
        supplierLocation.setText("");
    }
}
