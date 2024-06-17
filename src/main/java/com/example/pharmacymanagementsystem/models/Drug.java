package com.example.pharmacymanagementsystem.models;

public class Drug {
    private String drugID;
    private String drugName;
    private String drugDescription;
    private String stock;
    private String suppliers;

    public Drug(String drugID, String drugName, String drugDescription, String stock, String suppliers){
        this.drugID = drugID;
        this.drugName = drugName;
        this.drugDescription = drugDescription;
        this.stock = stock;
        this.suppliers = suppliers;
    }

    public String getDrugID(){
        return this.drugID;
    }
    public String getDrugName(){
        return this.drugName;
    }
    public String getDrugDescription(){
        return this.drugDescription;
    }
    public String getStock(){
        return this.stock;
    }
    public String getSuppliers(){
        return this.suppliers;
    }

    public void setDrugID(String ID){
        this.drugID = ID;
    }
    public void setDrugName(String name){
        this.drugName = name;
    }
    public void setDrugDescription(String drugDescription){
        this.drugDescription = drugDescription;
    }
    public void setStock(String stock){
        this.stock = stock;
    }
    public void setSuppliers(String suppliers){
        this.suppliers = suppliers;
    }

}
