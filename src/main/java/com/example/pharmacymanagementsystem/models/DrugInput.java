package com.example.pharmacymanagementsystem.models;

import javafx.scene.control.TextField;

public class DrugInput {
TextField drugId;
TextField drugName;
TextField drugDescription;
TextField stock;
TextField suppliers;

    public DrugInput(TextField drugId, TextField drugName, TextField drugDescription, TextField stock, TextField suppliers) {
        this.drugId = drugId;
        this.drugName = drugName;
        this.drugDescription = drugDescription;
        this.stock = stock;
        this.suppliers = suppliers;
    }
public String getId() {
    return this.drugId.getText();
}
public String getName() {
    return this.drugName.getText();
}
public String getDescription() {
    return this.drugDescription.getText();
}
public String getStock() {
    return this.stock.getText();
}
public String getSuppliers() {
    return this.suppliers.getText();
}

public void setId(String id) {
    this.drugId.setText(id);
}
public void setName(String name) {
    this.drugName.setText(name);
}
public void setDescription(String description) {
    this.drugDescription.setText(description);
}
public void setStock(String stock) {
    this.stock.setText(stock);
}
public void setSuppliers(String suppliers) {
    this.suppliers.setText(suppliers);
}

}
