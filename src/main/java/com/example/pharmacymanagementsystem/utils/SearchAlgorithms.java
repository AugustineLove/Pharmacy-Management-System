package com.example.pharmacymanagementsystem.utils;


import com.example.pharmacymanagementsystem.models.Drug;
import com.example.pharmacymanagementsystem.models.DrugAndSupplier;
import com.example.pharmacymanagementsystem.models.Purchase;
import com.example.pharmacymanagementsystem.models.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class SearchAlgorithms {

    //kind of make sense
    //this part is where you search for a drug based on the drug id with a binary search algorithm
    public Drug binarySearch(ObservableList<Drug> array, int target) {
        int low = 0;
        int high = array.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (Integer.parseInt(array.get(mid).getDrugID()) == target) {
                return array.get(mid);
            }
            else if( Integer.parseInt(array.get(mid).getDrugID()) > target ){
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }
        return null;
    }

    //make a liner search to find a drug in the database either by a drug name or a drug description
    // the search is only working for complete search prompt bro, if you do not provide full drug name, no search result
    public ObservableList<Drug> linearSearch(ObservableList<Drug> array, String target) {
        ObservableList<Drug> result = FXCollections.observableArrayList();
        for (Drug drug : array) {
            if (Objects.equals(drug.getDrugName().toLowerCase(), target.toLowerCase()) || Objects.equals(drug.getSuppliers().toLowerCase(), target.toLowerCase())) {
                result.add(drug);
            }
        }
        return result;
    }

    public Purchase binarySearchPurchase(ObservableList<Purchase> array, String target) throws ParseException {
        System.out.println("Target date: " +target);
        LocalDate date = LocalDate.parse(target);
        System.out.println("Formated date: " + date);
        int low = 0;
        int high = array.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            System.out.println("First side: " + LocalDate.parse(array.get(mid).getDate()));
            System.out.println("Second side: "+ date);
            if (LocalDate.parse(array.get(mid).getDate()).equals(date)) {
                System.out.println(array.get(mid).getDate());
                return array.get(mid);
            }
            else if(LocalDate.parse(array.get(mid).getDate()).isAfter(date)){
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }
        return null;
    }

    //make a liner search to find a drug in the database either by a drug name or a drug description
    // the search is only working for complete search prompt bro, if you do not provide full drug name, no search result
    public ObservableList<Purchase> linearSearchPurchase(ObservableList<Purchase> array, String target) {
        ObservableList<Purchase> result = FXCollections.observableArrayList();
        for (Purchase purchase : array) {
            if (Objects.equals(purchase.getDrugName().toLowerCase(), target.toLowerCase())) {
                result.add(purchase);
            }
        }
        return result;
    }

    public Supplier searchSupplierById(ObservableList<Supplier> array, int target) {
        int low = 0;
        int high = array.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (Integer.parseInt(array.get(mid).getSupplierID()) == target) {
                return array.get(mid);
            }
            else if( Integer.parseInt(array.get(mid).getSupplierID()) > target ){
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }
        return null;
    }

    public ObservableList<Supplier> linearSearchSupplier(ObservableList<Supplier> array, String target){
        ObservableList<Supplier> result = FXCollections.observableArrayList();
        for (Supplier supplier : array){
            if (Objects.equals(supplier.getSupplierName().toLowerCase(), target.toLowerCase())){
                result.add(supplier);
            }
        }
        return result;
    }

    public ObservableList<DrugAndSupplier> linearSearchDrugSupplier(ObservableList<DrugAndSupplier> array, String target){
        ObservableList<DrugAndSupplier> result = FXCollections.observableArrayList();
        for (DrugAndSupplier drugAndSupplier: array){
            if (Objects.equals(drugAndSupplier.getSupplierLocation().toLowerCase(), target.toLowerCase())){
                result.add(drugAndSupplier);
            }
        }
        return result;
    }
}
