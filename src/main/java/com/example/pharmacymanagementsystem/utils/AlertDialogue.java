package com.example.pharmacymanagementsystem.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class AlertDialogue {

    /**
     * Displays a success alert with the given content message.
     *
     * @param content the message to be displayed in the alert
     */
    public void showSuccessAlert(String content){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Displays an error alert with the given content message.
     *
     * @param content the message to be displayed in the alert
     */
    public void showErrorAlert(String content){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Displays a confirmation alert with the given content message and returns the user's response.
     *
     * @param content the message to be displayed in the alert
     * @return an {@link Optional} containing the {@link ButtonType} representing the user's response
     */

    public Optional<ButtonType> showConfirmationAlert(String content){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText(content);
        Optional<ButtonType> result = alert.showAndWait();
        return result;
    }
}
