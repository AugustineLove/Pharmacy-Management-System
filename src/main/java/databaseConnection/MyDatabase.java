package databaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MyDatabase {
    public String driverClassName = "com.mysql.cj.jdbc.Driver";
    public String url = "jdbc:mysql://localhost:3306/pharmacymsystem";
    public String username = "root";
    public String password = "@Password12345";

    public Statement getStatement() throws SQLException, ClassNotFoundException {
        // Load driver class
        Class.forName(driverClassName);
        // Obtain a connection
        Connection con = DriverManager.getConnection(url, username, password);
        // Obtain a statement
        Statement st = con.createStatement();
        return st;
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        // Load driver class
        Class.forName(driverClassName);
        // Obtain a connection
        Connection con = DriverManager.getConnection(url, username, password);
        return con;
    }
}
