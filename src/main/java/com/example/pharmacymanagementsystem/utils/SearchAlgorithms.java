package com.example.pharmacymanagementsystem.utils;


import com.example.pharmacymanagementsystem.models.Drug;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
}
