package com.example.pharmacymanagementsystem.controllers;

import com.example.pharmacymanagementsystem.models.*;
import com.example.pharmacymanagementsystem.utils.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;

import javafx.event.ActionEvent;

public class DashboardController {

    @FXML
    private Button addDrug_addBtn;

    @FXML
    private TableView<Drug> drugsTable;

    @FXML
    private TableView<Purchase> purchaseHistoryTable;

    @FXML
    private TableView<Supplier> supplier_table;

    @FXML
    private TableView<DrugAndSupplier> DrugSupplier_table;


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
    private TableColumn<Drug, String> addDrug_col_suppliersID;

    @FXML
    private TableColumn<DrugAndSupplier, String> DrugSupplier_DrugID_col;

    @FXML
    private TableColumn<DrugAndSupplier, String> DrugSupplier_SupplierID_col;

    @FXML
    private TableColumn<DrugAndSupplier, String> DrugSupplier_DrugName_col;

    @FXML
    private TableColumn<DrugAndSupplier, String> DrugSupplier_SupplierName_col;

    @FXML
            private TableColumn<DrugAndSupplier, String> DrugSupplier_SupplierLocation_col;

    @FXML
    private TableColumn<DrugAndSupplier, String> DrugSupplier_SupplierPhone_col;

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
    private AnchorPane supplier_page;

    @FXML
    private AnchorPane supplier_drug_page;

    @FXML
    private Button purchases_btn;

    @FXML
    private Button supplierBtn;

    @FXML
    private Button supplierDrugBtn;

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
    private DatePicker purchases_addDrugDate;

    @FXML
    private TextField purchases_addDrugName;

    @FXML
    private TextField purchases_addDrugPrice;

    @FXML
    private TextField purchases_addDrugQuantity;

    @FXML
    private TextField purchases_addDrugID;

    @FXML
    private TextField supplierAdd_ID;

    @FXML
    private TextField supplierAdd_DrugID;

    @FXML
    private TextField supplierAdd_SupplierID;

    @FXML
    private TextField supplierAdd_SearchLocation;

    @FXML
    private TextField supplierAdd_Name;

    @FXML
    private TextField supplierAdd_Location;

    @FXML
    private TextField supplierAdd_Phone;

    @FXML
    private Button purchases_searchBtn;

    @FXML
    private TextField purchases_searchName;

    @FXML
    private Label purchases_supplierName;

    @FXML
    private Label username;


    @FXML
    private TableColumn<Purchase, String> purchaseDrugDate;

    @FXML
    private TableColumn<Purchase, String> purchaseCustomerID;
    @FXML
    private TableColumn<Purchase, String> purchaseDrugName;

    @FXML
    private TableColumn<Purchase, String> purchaseDrugPrice;

    @FXML
    private TableColumn<Purchase, String> purchaseDrugQuantity;

    @FXML
    private TableColumn<Purchase, String> purchaseDrugID;

    @FXML
    private TableColumn<Supplier, String> supplierID_col;

    @FXML
    private TableColumn<Supplier, String> supplierName_col;

    @FXML
    private TableColumn<Supplier, String> supplierLocation_col;

    @FXML
    private TableColumn<Supplier, String> supplierPhone_col;



    public static ObservableList<Purchase> updateListOfPurchases;
public static ObservableList<Supplier> updatedListOfSuppliers;
    public static ObservableList<Drug> newUpdateListOfDrugs;
    public static ObservableList<DrugAndSupplier> updatedListOfDrugsAndSuppliers;
    public static ObservableList<Drug> binarySearchResultList;
    AlertDialogue alertDialogue = new AlertDialogue();
    RetrieveData retrieveData = new RetrieveData();
    SearchAlgorithms bS = new SearchAlgorithms();
    DeleteData deleteData = new DeleteData();
    AppConstants appConstants = new AppConstants();
    AddData addData = new AddData();
    ErrorHandler myErrorHandler = new ErrorHandler();
    SearchAlgorithms searchItem = new SearchAlgorithms();


    public void getUserName(){
        username.setText(Admin.adminUsername);
    }

    public void getDrugRecords() throws SQLException, ClassNotFoundException {

        newUpdateListOfDrugs = retrieveData.getAllDrugs();

    }
    public void getHistoryRecords() throws SQLException, ClassNotFoundException{
        updateListOfPurchases = retrieveData.getAllDrugPurchases();
    }

    public void getSuppliersRecord() throws SQLException, ClassNotFoundException {
        updatedListOfSuppliers = retrieveData.getAllSuppliers();
    }
    public void getDrugsAndSuppliers() throws SQLException, ClassNotFoundException{
        updatedListOfDrugsAndSuppliers = retrieveData.getAllDrugSupplier();
    }

    public void searchByID() throws SQLException, ClassNotFoundException {
        try{
            Drug searchResult =  bS.binarySearch(newUpdateListOfDrugs, Integer.parseInt(addDrug_search.getText()));
            ObservableList<Drug> searchResultList = FXCollections.observableArrayList(searchResult);
            showTableData(searchResultList);
        }
        catch (NumberFormatException e){
            alertDialogue.showErrorAlert("Wrong input type");
        }

    }
    public void searchSupplierById() throws SQLException, ClassNotFoundException {
        try{
            Supplier searchResult =  bS.searchSupplierById(updatedListOfSuppliers, Integer.parseInt(supplierAdd_ID.getText()));
            ObservableList<Supplier> searchResultList = FXCollections.observableArrayList(searchResult);
            showSupplierTableData(searchResultList);
        }
        catch (NumberFormatException e){
            alertDialogue.showErrorAlert("Wrong input type");
        }

    }

    public  void searchPurchaseByDate() throws SQLException, ClassNotFoundException, ParseException {
        try{
            Purchase searchResult = bS.binarySearchPurchase(updateListOfPurchases, purchases_addDrugDate.getValue().toString());
            ObservableList<Purchase> searchResultList = FXCollections.observableArrayList(searchResult);
            showPurchaseTableData(searchResultList);

        } catch (ParseException e){
            System.out.println("Parse exception here: " + e);
        }
    }

    public void searchByNS() throws SQLException, ClassNotFoundException {
        ObservableList<Drug> searchResult = searchItem.linearSearch(newUpdateListOfDrugs, addDrug_search.getText());
        System.out.println(searchResult);
        showTableData(searchResult);
    }

    public void searchDrugSupplierLocation() throws SQLException, ClassNotFoundException {
        ObservableList<DrugAndSupplier> searchResult = searchItem.linearSearchDrugSupplier(updatedListOfDrugsAndSuppliers, supplierAdd_SearchLocation.getText());
        showDrugsAndSupplierTableData(searchResult);
    }
    public void searchSupplier() throws SQLException, ClassNotFoundException{
        ObservableList<Supplier> searchResult = searchItem.linearSearchSupplier(updatedListOfSuppliers, supplierAdd_Name.getText());
        showSupplierTableData(searchResult);
    }

    public void clearSearchResult() throws SQLException, ClassNotFoundException {
        addDrug_search.setText("");
        showTableData(newUpdateListOfDrugs);
    }

    public void clearPurchaseSearchResult() throws SQLException, ClassNotFoundException {
        clearPurchaseFormInput();
        purchases_addDrugDate.setValue(LocalDate.now());
        showPurchaseTableData(updateListOfPurchases);
    }

    public void clearDrugSupplierResult() throws SQLException, ClassNotFoundException {
        clearDrugSupplierFormInput();
        showDrugsAndSupplierTableData(updatedListOfDrugsAndSuppliers);
    }

    public void updateDrugRecord() throws SQLException, ClassNotFoundException, IOException {
        UpdateData updateData = new UpdateData();

        int nextDrugId = newUpdateListOfDrugs.size() + 1;
        String newDrugIdS = String.valueOf(nextDrugId);
        updateData.updateDrugData(addDrug_id, addDrug_name, addDrug_description, addDrug_stock, addDrug_supplier, newDrugIdS);
        showTableData(newUpdateListOfDrugs);
    }

    public void deleteDrugRecord() throws SQLException, ClassNotFoundException {

        deleteData.deleteDrugData(addDrug_id);
        showTableData(newUpdateListOfDrugs);
        clearFormInput();
    }

    public void deleteDrugPurchaseRecord() throws SQLException, ClassNotFoundException {

        deleteData.deleteDrugPurchaseData(purchases_addDrugID);
        showPurchaseTableData(updateListOfPurchases);
        clearPurchaseFormInput();
    }

    public void deleteSupplierRecord() throws SQLException, ClassNotFoundException{
        deleteData.deleteSupplierData(supplierAdd_ID, supplierAdd_Name);
        showSupplierTableData(updatedListOfSuppliers);
        clearSupplierFormInput();
    }

    public void showTableData(ObservableList<Drug> list) throws SQLException, ClassNotFoundException {
        ObservableList<Drug> drugDataList = retrieveData.getAllDrugs();
        int showLastIn = 1;

        if (!drugDataList.isEmpty()) {
            int lastIn = Integer.parseInt(drugDataList.get(drugDataList.size() - 1).getDrugID());
            showLastIn = lastIn + 1;
        }
        addDrug_id.setText(String.valueOf(showLastIn));


        addDrug_col_id.setCellValueFactory(new PropertyValueFactory<>("drugID"));
        addDrug_col_name.setCellValueFactory(new PropertyValueFactory<>("drugName"));
        addDrug_col_description.setCellValueFactory(new PropertyValueFactory<>("drugDescription"));
        addDrug_col_stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addDrug_col_suppliers.setCellValueFactory(new PropertyValueFactory<>("suppliers"));
        addDrug_col_suppliersID.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
        drugsTable.setItems(list);
    }

    public void showPurchaseTableData(ObservableList<Purchase> list) throws SQLException, ClassNotFoundException {
        ObservableList<Purchase> drugDataList = retrieveData.getAllDrugPurchases();
        int showLastIn = 1;
        if (!drugDataList.isEmpty()) {
            int lastIn = Integer.parseInt(drugDataList.get(drugDataList.size() - 1).getPurchaseId());
            showLastIn = lastIn + 1;
        }
        purchases_addDrugID.setText(String.valueOf(showLastIn));


        purchaseDrugID.setCellValueFactory(new PropertyValueFactory<>("purchaseId"));
        purchaseDrugName.setCellValueFactory(new PropertyValueFactory<>("drugName"));
        purchaseDrugQuantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        purchaseDrugPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        purchaseDrugDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        purchaseCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        purchaseHistoryTable.setItems(list);
    }

    public void showSupplierTableData(ObservableList<Supplier> list) throws SQLException, ClassNotFoundException {

        ObservableList<Supplier> supplierDataList = retrieveData.getAllSuppliers();
        int showLastIn = 1;

        if (!supplierDataList.isEmpty()) {
            int lastIn = Integer.parseInt(supplierDataList.get(supplierDataList.size() - 1).getSupplierID());
            showLastIn = lastIn + 1;
        }
        String newSupplierId = String.valueOf(showLastIn);
        supplierAdd_ID.setText(newSupplierId);


        supplierID_col.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
        supplierName_col.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        supplierLocation_col.setCellValueFactory(new PropertyValueFactory<>("supplierLocation"));
        supplierPhone_col.setCellValueFactory(new PropertyValueFactory<>("supplierPhone"));
        supplier_table.setItems(list);
    }

    public void showDrugsAndSupplierTableData(ObservableList<DrugAndSupplier> list) throws  SQLException, ClassNotFoundException {
        ObservableList<DrugAndSupplier> drugAndSuppliersList = retrieveData.getAllDrugSupplier();


        DrugSupplier_DrugID_col.setCellValueFactory(new PropertyValueFactory<>("drugID"));
        DrugSupplier_SupplierID_col.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
        DrugSupplier_DrugName_col.setCellValueFactory(new PropertyValueFactory<>("drugName"));
        DrugSupplier_SupplierName_col.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        DrugSupplier_SupplierLocation_col.setCellValueFactory(new PropertyValueFactory<>("supplierLocation"));
                DrugSupplier_SupplierPhone_col.setCellValueFactory(new PropertyValueFactory<>("supplierPhone"));
        DrugSupplier_table.setItems(list);

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
    public void displaySelectedDrugPurchase(){
        Purchase newDrugPurchase = purchaseHistoryTable.getSelectionModel().getSelectedItem();
        int drugPIndex = purchaseHistoryTable.getSelectionModel().getSelectedIndex();

        if((drugPIndex -1) < -1 ){return;}

        purchases_addDrugName.setText(newDrugPurchase.getDrugName());
        purchases_addDrugQuantity.setText(newDrugPurchase.getQuantity());
        purchases_addDrugPrice.setText(newDrugPurchase.getPrice());
        purchases_addDrugDate.setValue(LocalDate.parse(newDrugPurchase.getDate()));
        purchases_addDrugID.setText(newDrugPurchase.getPurchaseId());

    }


    public void displaySelectedSupplier(){
        Supplier newSupplier = supplier_table.getSelectionModel().getSelectedItem();
        int supplierIndex = supplier_table.getSelectionModel().getSelectedIndex();

        if((supplierIndex -1) < -1 ){return;}
        supplierAdd_ID.setText(String.valueOf(newSupplier.getSupplierID()));
        supplierAdd_Name.setText(String.valueOf(newSupplier.getSupplierName()));
        supplierAdd_Location.setText(String.valueOf(newSupplier.getSupplierLocation()));
        supplierAdd_Phone.setText(String.valueOf(newSupplier.getSupplierPhone()));

    }
    public void displaySelectedDrugSupplier(){
        DrugAndSupplier drugAndSupplier = DrugSupplier_table.getSelectionModel().getSelectedItem();
        int drugSupplierIndex = DrugSupplier_table.getSelectionModel().getSelectedIndex();

        if((drugSupplierIndex -1 ) < -1){return;}
        supplierAdd_DrugID.setText(drugAndSupplier.getDrugName());
        supplierAdd_SupplierID.setText(drugAndSupplier.getSupplierName());
        supplierAdd_SearchLocation.setText(drugAndSupplier.getSupplierLocation());
    }
    public void showPage(ActionEvent event){

        if(event.getSource() == drugs_btn){
            drugs_page.setVisible(true);
            purchases_page.setVisible(false);
            supplier_page.setVisible(false);
            supplier_drug_page.setVisible(false);

            drugs_btn.setStyle("-fx-background-color: #ffff; -fx-text-fill: #283342");
            purchases_btn.setStyle("-fx-background-color: #283342");
            purchases_btn.setStyle("-fx-background-color: #283342");
            purchases_btn.setStyle("-fx-background-color: #283342");
            supplierBtn.setStyle("-fx-background-color: #283342");
            supplierDrugBtn.setStyle("-fx-background-color: #283342");
        } else if(event.getSource() == purchases_btn){
            purchases_page.setVisible(true);
            drugs_page.setVisible(false);
            supplier_page.setVisible(false);
            supplier_drug_page.setVisible(false);

            purchases_btn.setStyle("-fx-background-color: #ffff; -fx-text-fill: #283342");
            drugs_btn.setStyle("-fx-background-color: #283342");
            supplierBtn.setStyle("-fx-background-color: #283342");
            supplierDrugBtn.setStyle("-fx-background-color: #283342");
        } else if (event.getSource() == supplierBtn){
            supplier_page.setVisible(true);
            purchases_page.setVisible(false);
            drugs_page.setVisible(false);
            supplier_drug_page.setVisible(false);

            supplierBtn.setStyle("-fx-background-color: #ffff; -fx-text-fill: #283342");
            drugs_btn.setStyle("-fx-background-color: #283342");
            purchases_btn.setStyle("-fx-background-color: #283342");
            supplierDrugBtn.setStyle("-fx-background-color: #283342");
        } else if (event.getSource() == supplierDrugBtn){
            supplier_drug_page.setVisible(true);
            supplier_page.setVisible(false);
            purchases_page.setVisible(false);
            drugs_page.setVisible(false);

            supplierDrugBtn.setStyle("-fx-background-color: #ffff; -fx-text-fill: #283342");
            supplierBtn.setStyle("-fx-background-color: #283342");
            drugs_btn.setStyle("-fx-background-color: #283342");
            purchases_btn.setStyle("-fx-background-color: #283342");
        }

    }

    public void clearFormInput() throws SQLException, ClassNotFoundException{
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

    public void clearPurchaseFormInput(){
        appConstants.resetPInputRecords(purchases_addDrugName, purchases_addDrugQuantity, purchases_addDrugPrice, purchases_addDrugDate);
    }
    public void clearDrugSupplierFormInput(){
        appConstants.resetDrugSupplierRecords(supplierAdd_DrugID, supplierAdd_SupplierID, supplierAdd_SearchLocation);
    }

    public void clearSupplierFormInput() throws SQLException, ClassNotFoundException{
        ObservableList<Supplier> supplierListData = retrieveData.getAllSuppliers();
        int nextSupplierId = supplierListData.size() + 1;
        int lastIn = Integer.parseInt(supplierListData.getLast().getSupplierID());
        int showLastIn = lastIn + 1;
        String newSupplierId = String.valueOf(showLastIn);
        appConstants.resetSupplierInputRecords(supplierAdd_ID, supplierAdd_Name, supplierAdd_Location, supplierAdd_Phone, newSupplierId);
        showSupplierTableData(updatedListOfSuppliers);
    }

    public void addnewDrug() throws SQLException, ClassNotFoundException {
        int nextDrugId = newUpdateListOfDrugs.size() + 1;
        String newDrugIdS = String.valueOf(nextDrugId);


        addData.addDrugData(addDrug_id, addDrug_name, addDrug_description, addDrug_stock, addDrug_supplier, newDrugIdS);
        showTableData(newUpdateListOfDrugs);
    }

    public void addPurchase() throws SQLException, ClassNotFoundException {
        addData.addDrugPurchase(purchases_addDrugName, purchases_addDrugQuantity, purchases_addDrugPrice, purchases_addDrugDate);
        showPurchaseTableData(updateListOfPurchases);
        showTableData(newUpdateListOfDrugs);
    }

    public void addSupplier() throws SQLException, ClassNotFoundException {
        ObservableList<Supplier> supplierListData = retrieveData.getAllSuppliers();
        int nextSupplierId = supplierListData.size() + 1;
        int lastIn = (supplierListData.isEmpty()) ? 0 : Integer.parseInt(supplierListData.getLast().getSupplierID());
        int showLastIn = lastIn + 1;
        String newSupplierId = String.valueOf(showLastIn);
        addData.addSupplierData(supplierAdd_ID, supplierAdd_Name, supplierAdd_Location, supplierAdd_Phone, newSupplierId);
        showSupplierTableData(updatedListOfSuppliers);
    }

    public void addDrugAndSupplier() throws SQLException, ClassNotFoundException{
        System.out.println("Trying to add data");
        addData.addDrugSupplier(supplierAdd_DrugID, supplierAdd_SupplierID, supplierAdd_SearchLocation);
        showDrugsAndSupplierTableData(updatedListOfDrugsAndSuppliers);
    }
    public void createReportButton() {
        retrieveData.generateReport();
    }

    @FXML
    public void initialize() throws  SQLException, ClassNotFoundException{
        drugs_btn.setStyle("-fx-background-color: #ffff; -fx-text-fill: #283342");
        getUserName();
        getDrugRecords();
        getHistoryRecords();
        getSuppliersRecord();
        getDrugsAndSuppliers();
        showTableData(newUpdateListOfDrugs);
        showPurchaseTableData(updateListOfPurchases);
        showSupplierTableData(updatedListOfSuppliers);
        showDrugsAndSupplierTableData(updatedListOfDrugsAndSuppliers);
    }
}
