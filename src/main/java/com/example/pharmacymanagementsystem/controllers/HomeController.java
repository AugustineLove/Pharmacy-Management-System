package com.example.pharmacymanagementsystem.controllers;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.pharmacymanagementsystem.models.Drug;
import com.example.pharmacymanagementsystem.utils.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;

import javafx.event.ActionEvent;

public class HomeController implements Initializable {

    @FXML
    private Button addDrug_addBtn;

    @FXML
    private Button addDrug_clearBtn;

    @FXML
    private Button addDrug_clearBtn1;

    @FXML
    private TableColumn<?, ?> addDrug_col_description;

    @FXML
    private TableColumn<?, ?> addDrug_col_id;

    @FXML
    private TableColumn<?, ?> addDrug_col_name;

    @FXML
    private TableColumn<?, ?> addDrug_col_stock;

    @FXML
    private TableColumn<?, ?> addDrug_col_suppliers;

    @FXML
    private Button addDrug_deleteBtn;

    @FXML
    private TextField addDrug_description;

    @FXML
    private TextField addDrug_id;

    @FXML
    private TextField addDrug_name;

    @FXML
    private Button addDrug_nameSearch;

    @FXML
    private TextField addDrug_search;

    @FXML
    private Button addDrug_searchBtn;

    @FXML
    private Button addDrug_searchBtn1;

    @FXML
    private TextField addDrug_stock;

    @FXML
    private TextField addDrug_supplier;

    @FXML
    private TableView<Drug> drugsTable;

    @FXML
    private Button drugs_btn;

    @FXML
    private AnchorPane drugs_page;

    @FXML
    private Button purchases_btn;

    @FXML
    private Label purchases_date;

    @FXML
    private Label purchases_drugName;

    @FXML
    private AnchorPane purchases_page;

    @FXML
    private Button purchases_searchBtn;

    @FXML
    private TextField purchases_searchName;

    @FXML
    private Label purchases_supplierName;

    @FXML
    private Label username;

    public static ObservableList<Drug> newUpdateListOfDrugs;

    public void getDrugRecords() throws SQLException, ClassNotFoundException {
        RetrieveData retrieveData = new RetrieveData();
        newUpdateListOfDrugs = retrieveData.getAllDrugs();

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        getDrugRecords();
//        showTableData(newUpdateListOfDrugs);
    }

}
