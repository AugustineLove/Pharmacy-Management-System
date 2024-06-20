package com.example.pharmacymanagementsystem.models;

import java.util.Date;

public class Purchase {
    private String drugName;
    private String quantity;
    private String price;
    private String date;

    public Purchase(String drugName, String quantity, String price, String date){
        this.drugName = drugName;
        this.quantity = quantity;
        this.price = price;
        this.date = date;
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
