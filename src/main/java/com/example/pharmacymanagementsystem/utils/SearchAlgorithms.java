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
    /**
     * Searches for a drug in the provided list using a binary search algorithm.
     * The search is based on the drug's ID.
     *
     * @param array the list of drugs to search through, assumed to be sorted by drug ID
     * @param target the drug ID to search for
     * @return the {@link Drug} object with the specified drug ID, or {@code null} if not found
     */

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

    /**
     * Searches for drugs in the provided list using a linear search algorithm.
     * The search is based on the drug's name or supplier's name.
     *
     * @param array the list of drugs to search through
     * @param target the target string to search for in the drug name or supplier's name
     * @return a list of {@link Drug} objects that match the search criteria
     */
    public ObservableList<Drug> linearSearch(ObservableList<Drug> array, String target) {
        ObservableList<Drug> result = FXCollections.observableArrayList();
        for (Drug drug : array) {
            if (Objects.equals(drug.getDrugName().toLowerCase(), target.toLowerCase()) || Objects.equals(drug.getSuppliers().toLowerCase(), target.toLowerCase())) {
                result.add(drug);
            }
        }
        return result;
    }

    /**
     * Searches for a purchase in the provided list using a binary search algorithm.
     * The search is based on the purchase date.
     *
     * @param array the list of purchases to search through, assumed to be sorted by date
     * @param target the target date to search for in the format YYYY-MM-DD
     * @return the {@link Purchase} object with the specified date, or {@code null} if not found
     * @throws ParseException if the target date cannot be parsed
     */

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

    /**
     * Searches for purchases in the provided list using a linear search algorithm.
     * The search is based on the drug's name.
     *
     * @param array the list of purchases to search through
     * @param target the target drug name to search for
     * @return a list of {@link Purchase} objects that match the search criteria
     */

    public ObservableList<Purchase> linearSearchPurchase(ObservableList<Purchase> array, String target) {
        ObservableList<Purchase> result = FXCollections.observableArrayList();
        for (Purchase purchase : array) {
            if (Objects.equals(purchase.getDrugName().toLowerCase(), target.toLowerCase())) {
                result.add(purchase);
            }
        }
        return result;
    }

    /**
     * Searches for a supplier in the provided list using a binary search algorithm.
     * The search is based on the supplier ID.
     *
     * @param array the list of suppliers to search through, assumed to be sorted by supplier ID
     * @param target the supplier ID to search for
     * @return the {@link Supplier} object with the specified supplier ID, or {@code null} if not found
     */

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

    /**
     * Searches for suppliers in the provided list using a linear search algorithm.
     * The search is based on the supplier's name.
     *
     * @param array the list of suppliers to search through
     * @param target the target supplier name to search for
     * @return a list of {@link Supplier} objects that match the search criteria
     */

    public ObservableList<Supplier> linearSearchSupplier(ObservableList<Supplier> array, String target){
        ObservableList<Supplier> result = FXCollections.observableArrayList();
        for (Supplier supplier : array){
            if (Objects.equals(supplier.getSupplierName().toLowerCase(), target.toLowerCase())){
                result.add(supplier);
            }
        }
        return result;
    }

    /**
     * Searches for drug and supplier records in the provided list using a linear search algorithm.
     * The search is based on the supplier's location.
     *
     * @param array the list of drug and supplier records to search through
     * @param target the target supplier location to search for
     * @return a list of {@link DrugAndSupplier} objects that match the search criteria
     */

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
