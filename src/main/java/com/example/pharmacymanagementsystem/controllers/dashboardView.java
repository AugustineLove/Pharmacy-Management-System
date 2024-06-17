package com.example.pharmacymanagementsystem.controllers;

import com.example.pharmacymanagementsystem.models.Admin;
import com.example.pharmacymanagementsystem.models.Drug;
import com.example.pharmacymanagementsystem.utils.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;

public class dashboardView {

    @FXML
    private Button addDrug_addBtn;

    @FXML
    private TableView<Drug> drugsTable;
    @FXML
    private TableColumn<Drug, String> addDrug_col_description;

    @FXML
    private TableColumn<Drug, String> addDrug_col_id;

    @FXML
    private TableColumn<Drug, String> addDrug_col_name;

    @FXML
    private TableColumn<Drug, String> addDrug_col_stock;

    @FXML
    private TableColumn<Drug, String> addDrug_col_suppliers;

    @FXML
    private TextField addDrug_description;

    @FXML
    private TextField addDrug_id;

    @FXML
    private TextField addDrug_name;

    @FXML
    private TextField addDrug_search;

    @FXML
    private TextField addDrug_stock;

    @FXML
    private TextField addDrug_supplier;

    @FXML
    private AnchorPane drug_history_page;

    @FXML
    private Button drugs_btn;

    @FXML
    private AnchorPane drugs_page;

    @FXML
    private AnchorPane purchases_page;

    @FXML
    private Button purchases_btn;

    @FXML
    private TableColumn<?, ?> purchases_col_amount;

    @FXML
    private TableColumn<?, ?> purchases_col_date;

    @FXML
    private TableColumn<?, ?> purchases_col_drugName;

    @FXML
    private TableColumn<?, ?> purchases_col_purchaseID;

    @FXML
    private TableColumn<?, ?> purchases_col_quantity;

    @FXML
    private TableColumn<?, ?> purchases_col_supplierId;

    @FXML
    private Label purchases_date;

    @FXML
    private Label purchases_drugName;

    @FXML
    private Button purchases_searchBtn;

    @FXML
    private TextField purchases_searchName;

    @FXML
    private Label purchases_supplierName;

    @FXML
    private Label username;

    public static ObservableList<Drug> newUpdateListOfDrugs;
    public static ObservableList<Drug> binarySearchResultList;

    public void getUserName(){
        username.setText(Admin.adminUsername);
    }

    public void getDrugRecords() throws SQLException, ClassNotFoundException {
        RetrieveData retrieveData = new RetrieveData();
        newUpdateListOfDrugs = retrieveData.getAllDrugs();

    }

    public void searchByID() throws SQLException, ClassNotFoundException {
        SearchAlgorithms bS = new SearchAlgorithms();

        try{
            Drug searchResult =  bS.binarySearch(newUpdateListOfDrugs, Integer.parseInt(addDrug_search.getText()));
            ObservableList<Drug> searchResultList = FXCollections.observableArrayList(searchResult);
            showTableData(searchResultList);
        }
        catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Wrong input type");
            alert.showAndWait();
        }

    }

    public void searchByNS() throws SQLException, ClassNotFoundException {
        SearchAlgorithms lS = new SearchAlgorithms();
        ObservableList<Drug> searchResult = lS.linearSearch(newUpdateListOfDrugs, addDrug_search.getText());
        System.out.println(searchResult);
        showTableData(searchResult);
    }
    public void clearSearchResult() throws SQLException, ClassNotFoundException {
        addDrug_search.setText("");
        showTableData(newUpdateListOfDrugs);
    }

    public void updateDrugRecord() throws SQLException, ClassNotFoundException, IOException {
        UpdateData updateData = new UpdateData();

        int nextDrugId = newUpdateListOfDrugs.size() + 1;
        String newDrugIdS = String.valueOf(nextDrugId);
        updateData.updateDrugData(addDrug_id, addDrug_name, addDrug_description, addDrug_stock, addDrug_supplier, newDrugIdS);
        showTableData(newUpdateListOfDrugs);
    }

    public void deleteDrugRecord() throws SQLException, ClassNotFoundException {
        DeleteData deleteData = new DeleteData();
        deleteData.deleteDrugData(addDrug_id);
        showTableData(newUpdateListOfDrugs);
        clearFormInput();
    }

    public void showTableData(ObservableList<Drug> list) throws SQLException, ClassNotFoundException {
        RetrieveData retrieveData = new RetrieveData();
        ObservableList<Drug> drugDataList = retrieveData.getAllDrugs();
        int lastIn = Integer.parseInt(drugDataList.getLast().getDrugID());
        int showLastIn = lastIn + 1;
        int nextDrugId = drugDataList.size() + 1;
        String newDrugIdS = String.valueOf(nextDrugId);
        addDrug_id.setText(String.valueOf(showLastIn));

        addDrug_col_id.setCellValueFactory(new PropertyValueFactory<>("drugID"));
        addDrug_col_name.setCellValueFactory(new PropertyValueFactory<>("drugName"));
        addDrug_col_description.setCellValueFactory(new PropertyValueFactory<>("drugDescription"));
        addDrug_col_stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addDrug_col_suppliers.setCellValueFactory(new PropertyValueFactory<>("suppliers"));
        drugsTable.setItems(list);
    }

    public void displaySelectedDrug(){
        Drug newDrugs = drugsTable.getSelectionModel().getSelectedItem();
        int drugIndex = drugsTable.getSelectionModel().getSelectedIndex();

        if((drugIndex -1) < -1 ){return;}

        addDrug_id.setText(String.valueOf(newDrugs.getDrugID()));
        addDrug_name.setText(String.valueOf(newDrugs.getDrugName()));
        addDrug_description.setText(String.valueOf(newDrugs.getDrugDescription()));
        addDrug_stock.setText(String.valueOf(newDrugs.getStock()));
        addDrug_supplier.setText(String.valueOf(newDrugs.getSuppliers()));
    }


    public void showPage(ActionEvent event){
        if(event.getSource() == drugs_btn){
            drugs_page.setVisible(true);
            purchases_page.setVisible(false);

            drugs_btn.setStyle("-fx-background-color: #ffff; -fx-text-fill: #283342");
            purchases_btn.setStyle("-fx-background-color: #283342");
            purchases_btn.setStyle("-fx-background-color: #283342");
            purchases_btn.setStyle("-fx-background-color: #283342");
        } else if(event.getSource() == purchases_btn){
            purchases_page.setVisible(true);
            drugs_page.setVisible(false);

            purchases_btn.setStyle("-fx-background-color: #ffff; -fx-text-fill: #283342");
            drugs_btn.setStyle("-fx-background-color: #283342");
        }
    }

    public void clearFormInput() throws SQLException, ClassNotFoundException{
        RetrieveData retrieveData = new RetrieveData();
        ObservableList<Drug> drugDataList = retrieveData.getAllDrugs();
        int nextDrugId = drugDataList.size() + 1;

        int lastIn = Integer.parseInt(drugDataList.getLast().getDrugID());
        int showLastIn = lastIn + 1;
        String newDrugIdS = String.valueOf(nextDrugId);
        addDrug_id.setText("00" + showLastIn);

        addDrug_id.setText("00" + showLastIn);
        addDrug_id.setText(newDrugIdS);
        AppConstants appConstants = new AppConstants();
        appConstants.resetInputRecords(addDrug_id, addDrug_name, addDrug_description, addDrug_stock, addDrug_supplier, String.valueOf(showLastIn));
    }

    public void addnewDrug() throws SQLException, ClassNotFoundException {
        int nextDrugId = newUpdateListOfDrugs.size() + 1;
        String newDrugIdS = String.valueOf(nextDrugId);

        AddData addData = new AddData();
        addData.addDrugData(addDrug_id, addDrug_name, addDrug_description, addDrug_stock, addDrug_supplier, newDrugIdS);
        showTableData(newUpdateListOfDrugs);
    }


    @FXML
    public void initialize() throws  SQLException, ClassNotFoundException{
        getUserName();
        getDrugRecords();
        showTableData(newUpdateListOfDrugs);
    }


}
