package com.example.pharmacymanagementsystem.models;

import java.util.Date;

public class Purchase {
    private String purchaseId;
    private String drugName;
    private String quantity;
    private String price;
    private String date;
    private String customerID;

    public Purchase(String purhcaseId, String drugName, String quantity, String price, String date, String customerID){
        this.purchaseId = purhcaseId;
        this.drugName = drugName;
        this.quantity = quantity;
        this.price = price;
        this.date = date;
        this.customerID = customerID;
    }

    public void setPurchaseId(String purchaseId){
        this.purchaseId = purchaseId;
    }
    public void setDrugName(String drugName){
        this.drugName = drugName;
    }
    public void setQuantity(String quantity){
        this.quantity = quantity;
    }
    public void setPrice(String price){
        this.price = price;
    }
    public void setDate(String date){
        this.date = date;
    }
    public void setCustomerID(String customerID){
        this.customerID = customerID;
    }

    public String getCustomerID(){
        return customerID;
    }
    public String getPurchaseId(){
        return this.purchaseId;
    }

    public String getDrugName(){
        return this.drugName;
    }
    public String getQuantity(){
        return this.quantity;
    }

    public String getPrice() {
        return price;
    }

    public String getDate() {
        return date;
    }
}
