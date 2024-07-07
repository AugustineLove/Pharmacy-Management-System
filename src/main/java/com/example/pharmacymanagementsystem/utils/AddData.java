package com.example.pharmacymanagementsystem.utils;


import com.example.pharmacymanagementsystem.controllers.DashboardController;
import databaseConnection.MyDatabase;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.*;
import java.util.Random;


public class AddData {
    AlertDialogue alertDialogue = new AlertDialogue();
    MyDatabase myDatabase = new MyDatabase();
    ErrorHandler myErrorHandler = new ErrorHandler();
    AppConstants appConstants = new AppConstants();
    RetrieveData retrieveData = new RetrieveData();

    /**
     * Adds a new drug record to the database.
     *
     * @param drugId the text field containing the drug ID
     * @param drugName the text field containing the drug name
     * @param drugDescription the text field containing the drug description
     * @param stock the text field containing the drug stock
     * @param suppliers the text field containing the supplier name
     * @param newDrugID the new drug ID to be assigned
     * @throws SQLException if a database access error occurs
     * @throws ClassNotFoundException if the JDBC driver class is not found
     */
    public void addDrugData(TextField drugId, TextField drugName, TextField drugDescription, TextField stock, TextField suppliers, String newDrugID) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO DRUGS " + "(DrugName, DrugDescription, DrugStock, DrugSupplier, SupplierID)" +"VALUES(?,?,?,?,?)";


        try (Connection con = myDatabase.getConnection()){

            if(drugId.getText().isEmpty() || drugName.getText().isEmpty() || drugDescription.getText().isEmpty()
                    || stock.getText().isEmpty() || suppliers.getText().isEmpty()){
                alertDialogue.showErrorAlert("Please provide value for all fields");
            }
            else{
                String checkQuery = "SELECT DRUGID FROM DRUGS WHERE DRUGID = '"+  drugId.getText()+"'";
                String getSupplierID = "Select supplierID from suppliers where supplierName = '" + suppliers.getText() + "'";
                String supplierID = "";

                Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = st.executeQuery(checkQuery);

                Statement sst = con.createStatement();
                ResultSet rs1 = sst.executeQuery(getSupplierID);
                if(rs1.next()){
                    supplierID = rs1.getString(1);

                    PreparedStatement ps = con.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

                    ps.setString(1, drugName.getText());
                    ps.setString(2, drugDescription.getText());
                    ps.setString(3, stock.getText());
                    ps.setString(4, suppliers.getText());
                    ps.setString(5, supplierID);
                    ps.executeUpdate();

                    AppConstants appConstants = new AppConstants();

                    appConstants.resetInputRecords(drugId, drugName, drugDescription, stock, suppliers, newDrugID);
                    RetrieveData retrieveData = new RetrieveData();
                    DashboardController.newUpdateListOfDrugs = retrieveData.getAllDrugs();
                    retrieveData.getAllDrugs();

                    alertDialogue.showSuccessAlert("Record added successfully!");
                } else{
                    alertDialogue.showErrorAlert("Supplier does not exist");
                }
                if(rs.next()){
                    alertDialogue.showErrorAlert("Drug "+drugName.getText()+ " already exists");
                }

            }

        } catch (SQLException e) {
            myErrorHandler.getSQLException(e);
        }
    }

    /**
     * Checks if a customer exists in the database based on the given customer ID.
     *
     * @param customerID the ID of the customer to check
     * @return {@code true} if the customer exists, {@code false} otherwise
     * @throws SQLException if a database access error occurs
     * @throws ClassNotFoundException if the JDBC driver class is not found
     */

    public boolean isCustomer( int customerID) throws SQLException, ClassNotFoundException {
        String checkCustomer = "Select * from customers where customerID = '" + customerID + "'";
        try (Connection con = myDatabase.getConnection()){
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(checkCustomer);
            while(rs.next()){
                return true;
            }
        }
        return false;
    }

    /**
     * Updates the stock of a specific drug in the database by reducing the stock by the purchased amount.
     *
     * @param drugName the text field containing the name of the drug
     * @param purchasedStock the amount of stock that has been purchased
     * @throws SQLException if a database access error occurs
     * @throws ClassNotFoundException if the JDBC driver class is not found
     */

    public void updateStock(TextField drugName, int purchasedStock) throws SQLException, ClassNotFoundException {
        String stockQuery = "Select drugStock from drugs where drugName = '" + drugName.getText() + "'";
        try(Connection con = myDatabase.getConnection()){
            Statement stmt = con.createStatement();
            String drugStockString = "";
            ResultSet rs = stmt.executeQuery(stockQuery);
            while(rs.next()){
                String stock = rs.getString("drugStock");
                drugStockString = stock;
            }
            int existingStock = Integer.parseInt(drugStockString);
            int newStock = existingStock - purchasedStock;

            String updateStock = "UPDATE drugs SET DrugStock = ? WHERE DrugName = '" + drugName.getText() + "'";
            PreparedStatement pstmt = con.prepareStatement(updateStock);
            pstmt.setInt(1, newStock);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Updating stock error...");
            myErrorHandler.getSQLException(e);
        }
    }


    /**
     * Adds a new drug purchase record to the database. If the customer does not exist, a new customer is created.
     *
     * @param drugName the text field containing the name of the drug
     * @param drugQuantity the text field containing the quantity of the drug purchased
     * @param drugPrice the text field containing the price of the drug
     * @param datePicker the date picker containing the purchase date
     * @throws SQLException if a database access error occurs
     * @throws ClassNotFoundException if the JDBC driver class is not found
     */
    public void addDrugPurchase(TextField drugName, TextField drugQuantity, TextField drugPrice, DatePicker datePicker) throws SQLException, ClassNotFoundException {

        try(Connection con = myDatabase.getConnection()){

            if(drugName.getText().isEmpty() || drugQuantity.getText().isEmpty() || drugPrice.getText().isEmpty() || datePicker.getValue() == null){
                alertDialogue.showErrorAlert("Please provide value for all fields");
            }

            Random rand = new Random();
            int randomCusID = rand.nextInt(10) + 1;

            if (isCustomer(randomCusID)) {
                String addPurchase = "INSERT INTO purchases (drugName, drugQuantity, drugPrice, purchaseDate, customerID) VALUES (?,?,?,?,?)";
                PreparedStatement pds = con.prepareStatement(addPurchase);
                pds.setString(1, drugName.getText());
                pds.setString(2, drugQuantity.getText());
                pds.setString(3, drugPrice.getText());
                pds.setString(4, datePicker.getValue().toString());
                pds.setInt(5, randomCusID);
                pds.executeUpdate();

                updateStock(drugName, Integer.parseInt(drugQuantity.getText()));

                AppConstants appConstants = new AppConstants();

                appConstants.resetPInputRecords(drugName, drugQuantity, drugPrice, datePicker);
                RetrieveData retrieveData = new RetrieveData();
                DashboardController.updateListOfPurchases = retrieveData.getAllDrugPurchases();
                retrieveData.getAllDrugPurchases();
                DashboardController.newUpdateListOfDrugs = retrieveData.getAllDrugs();

                alertDialogue.showSuccessAlert("Record added successfully!");
            } else{

                String createCustomer = "insert into customers (customerID) values (?)";
                PreparedStatement ps = con.prepareStatement(createCustomer);
                ps.setString(1, String.valueOf(randomCusID));
                ps.executeUpdate();

                String addPurchase = "INSERT INTO purchases (drugName, drugQuantity, drugPrice, purchaseDate, customerID) VALUES (?,?,?,?,?)";
                PreparedStatement pds = con.prepareStatement(addPurchase);
                pds.setString(1, drugName.getText());
                pds.setString(2, drugQuantity.getText());
                pds.setString(3, drugPrice.getText());
                pds.setString(4, datePicker.getValue().toString());
                pds.setInt(5, randomCusID);
                pds.executeUpdate();

                updateStock(drugName, Integer.parseInt(drugQuantity.getText()));

                AppConstants appConstants = new AppConstants();

                appConstants.resetPInputRecords(drugName, drugQuantity, drugPrice, datePicker);
                RetrieveData retrieveData = new RetrieveData();
                DashboardController.updateListOfPurchases = retrieveData.getAllDrugPurchases();
                retrieveData.getAllDrugPurchases();
                DashboardController.newUpdateListOfDrugs = retrieveData.getAllDrugs();

                alertDialogue.showSuccessAlert("Record added successfully!");
            }
        } catch (SQLException exception){
            myErrorHandler.getSQLException(exception);
        }
    }

    /**
     * Adds a new supplier record to the database.
     *
     * @param supplierId the text field containing the supplier ID
     * @param supplierName the text field containing the supplier name
     * @param supplierLocation the text field containing the supplier location
     * @param supplierPhone the text field containing the supplier phone number
     * @param newSupplierID the new supplier ID to be assigned
     * @throws SQLException if a database access error occurs
     * @throws ClassNotFoundException if the JDBC driver class is not found
     */

    public void addSupplierData(TextField supplierId, TextField supplierName, TextField supplierLocation, TextField supplierPhone, String newSupplierID) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO SUPPLIERS " + "(Name, Location)" +"VALUES(?,?)";

        try (Connection con = myDatabase.getConnection()){

            if(supplierId.getText().isEmpty() || supplierName.getText().isEmpty() || supplierLocation.getText().isEmpty()
            ){
                alertDialogue.showErrorAlert("Please provide value for all fields");
            }
            else{
                String checkQuery = "SELECT supplierID FROM SUPPLIERS WHERE supplierID = '"+  supplierId.getText()+"'";

                Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = st.executeQuery(checkQuery);

                if(rs.next()){
                    alertDialogue.showErrorAlert("Supplier "+supplierName.getText()+ " already exists");
                }
                PreparedStatement ps = con.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

                ps.setString(1, supplierName.getText());
                ps.setString(2, supplierLocation.getText());
                ps.executeUpdate();

                AppConstants appConstants = new AppConstants();

                appConstants.resetSupplierInputRecords(supplierId, supplierName, supplierLocation, supplierPhone, newSupplierID);
                RetrieveData retrieveData = new RetrieveData();
                DashboardController.updatedListOfSuppliers = retrieveData.getAllSuppliers();
                retrieveData.getAllDrugs();

                alertDialogue.showSuccessAlert("Record added successfully!");
            }
        } catch (SQLException e) {
            myErrorHandler.getSQLException(e);
        }
    }

    /**
     * Retrieves the supplier ID from the database based on the supplier's name and location.
     *
     * @param drugName the text field containing the drug name (not used in the query)
     * @param supplierName the text field containing the supplier name
     * @param supplierLocation the text field containing the supplier location
     * @return the supplier ID if found, otherwise returns 0
     * @throws SQLException if a database access error occurs
     * @throws ClassNotFoundException if the JDBC driver class is not found
     */

    private int getSupplierID(TextField drugName, TextField supplierName, TextField supplierLocation) throws SQLException, ClassNotFoundException {
        String query = "SELECT supplierID from SUPPLIERS WHERE supplierName = '" + supplierName.getText() + "' AND supplierLocation = '" + supplierLocation.getText() + "'";
        int supplierId = 0;
        try (Connection con = myDatabase.getConnection()){
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(query);
            while(rs.next()){
                supplierId = rs.getInt("supplierID");
            }
        }
        return supplierId;
    }

    /**
     * Retrieves the drug ID from the database based on the drug's name.
     *
     * @param drugName the text field containing the name of the drug
     * @return the drug ID if found, otherwise returns 0
     * @throws SQLException if a database access error occurs
     * @throws ClassNotFoundException if the JDBC driver class is not found
     */
    private int getDrugID(TextField drugName) throws SQLException, ClassNotFoundException {
        String drugIDQuery = "SELECT DRUGID from DRUGS WHERE drugName = '"+ drugName.getText() +"'";
        int drugID = 0;
        try (Connection con = myDatabase.getConnection()){
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(drugIDQuery);
            while(rs.next()){
                drugID = rs.getInt("DRUGID");
            }
        }
        System.out.println("This is the drug ID: " + drugID);
        return drugID;
    }

    /**
     * Adds a new record to the DrugSupplier table, linking a drug with a supplier.
     *
     * @param drugName the text field containing the name of the drug
     * @param supplierName the text field containing the name of the supplier
     * @param supplierLocation the text field containing the location of the supplier
     * @throws SQLException if a database access error occurs
     * @throws ClassNotFoundException if the JDBC driver class is not found
     */

    public void addDrugSupplier(TextField drugName, TextField supplierName, TextField supplierLocation) throws SQLException, ClassNotFoundException {


        int supplierId = getSupplierID(drugName, supplierName, supplierLocation);
        int drugID = getDrugID(drugName);

        System.out.println("Supplier ID: " + supplierId);
        System.out.println("Drug ID: " + drugID);

        try (Connection con = myDatabase.getConnection()){

            if(String.valueOf(supplierId).isEmpty() || String.valueOf(drugID).isEmpty()
            ){
                alertDialogue.showErrorAlert("Please provide value for all fields");
            }
            else{
                String drugSupplier = "INSERT INTO DrugSupplier (DrugID, SupplierID) values (?, ?)";

                PreparedStatement pstmt = con.prepareStatement(drugSupplier);
                pstmt.setInt(1, drugID);
                pstmt.setInt(2, supplierId);
                pstmt.executeUpdate();
                appConstants.resetDrugSupplierRecords(drugName, supplierName, supplierLocation);
                DashboardController.updatedListOfDrugsAndSuppliers = retrieveData.getAllDrugSupplier();
                retrieveData.getAllDrugs();
                alertDialogue.showSuccessAlert("Record added successfully!");
            }
        } catch (SQLException e) {
            myErrorHandler.getSQLException(e);
        }
    }
}
