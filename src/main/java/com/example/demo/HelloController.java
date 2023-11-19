package com.example.demo;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDel;
    @FXML
    private Button btnSearch;
    @FXML
    private Button btnEdit;
    @FXML
    private TableColumn<Person, String> columnPIP;
    @FXML
    private TableColumn<Person, String> columnPhone;
    @FXML
    private TableView<Person> tableAddressBook;
    @FXML
    private TextField txtSearch;
    @FXML
    private Label labelCount;
    private CollectionAddressBook addressBookImpl = new CollectionAddressBook();




    @FXML
    void new_Alert(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Видалення");

        alert.setContentText("Ви впевнені , що хочете видалити запис? ");

        if (alert.showAndWait().get() == null){
            this.btnDel.setText("No selection!");
        }else if (alert.showAndWait().get() == ButtonType.OK) {
            this.btnDel.setText("Запис виделено! ");
        }else if (alert.showAndWait().get() == ButtonType.CANCEL){
            this.btnDel.setText("Відмінено!");
        }else {
            this.btnDel.setText("_");
        }
    }

    @FXML
    public void initialize(){
        columnPIP.setCellValueFactory(new PropertyValueFactory<Person,String>("PIP"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<Person,String>("Phone"));
        addressBookImpl.getPersonList().addListener(new ListChangeListener<Person>() {
            @Override
            public void onChanged(Change<? extends Person> c) {
                updateCountLabel();
            }
        });

        addressBookImpl.fillTestData();
        tableAddressBook.setItems(addressBookImpl.getPersonList());
    }
    private void updateCountLabel(){
        labelCount.setText("Кількість записів: " + addressBookImpl.getPersonList().size());
    }

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
}