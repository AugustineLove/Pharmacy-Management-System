package com.example.pharmacymanagementsystem;


import java.sql.*;

import com.example.pharmacymanagementsystem.models.Admin;
import com.example.pharmacymanagementsystem.utils.AlertDialogue;
import databaseConnection.MyDatabase;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HelloController {


    @FXML
    private Button close;

    @FXML
    private AnchorPane main_page;

    @FXML
    private PasswordField password;

    @FXML
    private Button sign_in;

    @FXML
    private TextField username_field;

    MyDatabase myDatabase = new MyDatabase();

    //this function logs in the admin and displays the dashboard screen
    public void loginAdmin() throws SQLException, ClassNotFoundException {
        AlertDialogue alertDialogue = new AlertDialogue();

        //get a connection to make a statements
        Connection con = myDatabase.getConnection();
        con = myDatabase.getConnection();
        String query = "select * from admin WHERE username = ? and password = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, username_field.getText());
            ps.setString(2, password.getText());
            ResultSet rs = ps.executeQuery();

            if (username_field.getText().isEmpty() || password.getText().isEmpty()) {
            alertDialogue.showErrorAlert("Email and password required");
            } else {
                if(rs.next()) {
                    Admin.adminUsername = username_field.getText();
                    alertDialogue.showSuccessAlert("Login successful");

                    //hide sign in window and load dashboard window
                    sign_in.getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(getClass().getResource("/dashboardView.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setTitle("Home");
                    System.out.println("Login is successful");


                    stage.setScene(scene);
                    stage.show();
                } else {
                    alertDialogue.showErrorAlert("Invalid credentials");
                }

            }

        } catch (Exception e){
            System.out.println("Error message: "+e.getMessage() + "error" + e.getCause());

        }
    }

    public void close(){
        System.exit(0);
    }
    @FXML
    void initialize() {

    }

}
