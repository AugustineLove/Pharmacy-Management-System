package com.example.pharmacymanagementsystem.utils;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

import java.time.LocalDate;
import java.util.Date;

public class AppConstants
{

    /**
     * Resets the input fields for drug records.
     *
     * @param drugId            The TextField for the drug ID to be reset.
     * @param drugName          The TextField for the drug name to be reset.
     * @param drugDescription   The TextField for the drug description to be reset.
     * @param stock             The TextField for the stock quantity to be reset.
     * @param suppliers         The TextField for the supplier information to be reset.
     * @param newDrugID         The new drug ID to set in the drugId TextField.
     */
    public void resetInputRecords(TextField drugId, TextField drugName, TextField drugDescription, TextField stock, TextField suppliers, String newDrugID){
        drugId.setText(newDrugID);
        drugName.setText("");
        drugDescription.setText("");
        stock.setText("");
        suppliers.setText("");
    }

    /**
     * Resets the input fields for purchase records.
     *
     * @param drugName   The TextField for the drug name to be reset.
     * @param quantity   The TextField for the quantity to be reset.
     * @param price      The TextField for the price to be reset.
     * @param datePicker The DatePicker for the date to be reset to the current date.
     */
    public void resetPInputRecords(TextField drugName, TextField quantity, TextField price, DatePicker datePicker){
        drugName.setText("");
        quantity.setText("");
        price.setText("");
        datePicker.setValue(LocalDate.now());
    }

    /**
     * Resets the input fields for supplier records.
     *
     * @param supplierId       The TextField for the supplier ID to be reset.
     * @param supplierName     The TextField for the supplier name to be reset.
     * @param supplierLocation The TextField for the supplier location to be reset.
     * @param supplierPhone    The TextField for the supplier phone number to be reset.
     * @param newSupplierID    The new supplier ID to set in the supplierId TextField.
     */
    public void resetSupplierInputRecords(TextField supplierId, TextField supplierName, TextField supplierLocation, TextField supplierPhone, String newSupplierID){
        supplierId.setText(newSupplierID);
        supplierName.setText("");
        supplierLocation.setText("");
        supplierPhone.setText("");
    }

    /**
     * Resets the input fields for drug and supplier records.
     *
     * @param drugName         The TextField for the drug name to be reset.
     * @param supplierName     The TextField for the supplier name to be reset.
     * @param supplierLocation The TextField for the supplier location to be reset.
     */

    public void resetDrugSupplierRecords(TextField drugName, TextField supplierName, TextField supplierLocation){
        drugName.setText("");
        supplierName.setText("");
        supplierLocation.setText("");
    }
}
