package com.example.pharmacymanagementsystem.models;

import java.util.Optional;

public class DrugAndSupplier {
    private String drugID;
private String supplierID;
    private String drugName;
    private String supplierName;
    private String supplierLocation;
    private String supplierPhone;

    public DrugAndSupplier(String drugID, String supplierID, String drugName, String supplierName, String supplierLocation, String supplierPhone) {
        this.drugID = drugID;
        this.supplierID = supplierID;
        this.drugName = drugName;
        this.supplierName = supplierName;
        this.supplierLocation = supplierLocation;
        this.supplierPhone = supplierPhone;
    }
    public String getDrugID(){
        return drugID;
    }

    public String getDrugName() {
        return drugName;
    }
    public String getSupplierName() {
        return supplierName;
    }
    public String getSupplierLocation() {
        return supplierLocation;
    }
    public String getSupplierID(){
        return supplierID;
    }
    public String getSupplierPhone() {
        return supplierPhone;
    }
    public void setSupplierID(){
        this.supplierID = supplierID;
    }
    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
    public void setSupplierLocation(String supplierLocation) {
        this.supplierLocation = supplierLocation;
    }

    public void setSupplierPhone(String supplierPhone) {
        this.supplierPhone = supplierPhone;
    }
}
