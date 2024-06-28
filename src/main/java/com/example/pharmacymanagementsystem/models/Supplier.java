package com.example.pharmacymanagementsystem.models;

public class Supplier {
    private String supplierID;
    private String supplierName;
    private String supplierLocation;
    private String supplierPhone;

    public Supplier(String supplierID, String supplierName, String supplierLocation, String supplierPhone) {
        this.supplierID = supplierID;
        this.supplierName = supplierName;
        this.supplierLocation = supplierLocation;
        this.supplierPhone = supplierPhone;
    }

    public String getSupplierID() {
        return supplierID;
    }
    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }
    public String getSupplierName() {
        return supplierName;
    }
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
    public String getSupplierLocation() {
        return supplierLocation;
    }

    public String getSupplierPhone() {
        return supplierPhone;
    }
    public void setSupplierLocation(String supplierLocation) {
        this.supplierLocation = supplierLocation;
    }
    public void setSupplierPhone(String supplierPhone) {
        this.supplierPhone = supplierPhone;
    }

}
