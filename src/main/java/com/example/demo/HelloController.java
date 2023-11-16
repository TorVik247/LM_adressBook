package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Button btnAdd;

    @FXML
    void showDialog(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("edit.fxml"));

        try {
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(),400,150);
            stage.setScene(scene);

            stage.setTitle("Редагування");
            stage.setMinHeight(170);
            stage.setMinWidth(400);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(btnAdd.getScene().getWindow());
            stage.show();

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    void new_Alert(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Видалення");

        alert.setHeaderText("Results:");
            alert.setContentText("Видалення може призвести до зміни порядку осіб у Адресній книзі! ");

        alert.showAndWait();
    }
}