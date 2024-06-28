package com.example.pharmacymanagementsystem;

import com.mysql.cj.x.protobuf.MysqlxPrepare;
import databaseConnection.MyDatabase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.*;

public class HelloApplication extends Application {
    MyDatabase myDatabase = new MyDatabase();
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/signIn.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/style/loginStyle.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("/style/dashboardView.css").toExternalForm());
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Pharmacy Management System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)  throws SQLException, ClassNotFoundException {
        MyDatabase myDatabase = new MyDatabase();
        try {
            //creating an instance of my database configurations

            //printing admin username to test code
            String query = "SELECT * FROM Admin";
            Statement st = myDatabase.getStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String userName = rs.getString("Username");
                System.out.println("Username: " + userName);
            }
        } catch (SQLException e){
            System.out.println(e);
        }
        launch();
    }


}