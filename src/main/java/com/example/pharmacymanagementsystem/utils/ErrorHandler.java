package com.example.pharmacymanagementsystem.utils;

import java.sql.SQLException;

public class ErrorHandler {

    /**
     * Handles and prints details of an SQL exception.
     *
     * @param exception The SQLException that occurred.
     */

    public void getSQLException(SQLException exception) {

        for(Throwable e: exception) {
            if(!ignoreSQLException(((SQLException) e).getMessage())){
                e.printStackTrace(System.err);

                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());

                Throwable t = exception.getCause();
                while (t != null) {
                    System.err.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    /**
     * Determines whether an SQL exception should be ignored based on its SQL state.
     *
     * @param sqlState The SQL state of the exception.
     * @return true if the SQL state indicates that the exception should be ignored, false otherwise.
     */

    public static boolean ignoreSQLException(String sqlState) {
        if(sqlState.equals("")){
            return true;
        }

        //when jar file already exists in the schema
        else if(sqlState.equalsIgnoreCase("XOY32")){
            return true;
        }

        //when the table already exists
        else if (sqlState.equalsIgnoreCase("42Y55")){
            return true;
        }
    return false;
    }
}
