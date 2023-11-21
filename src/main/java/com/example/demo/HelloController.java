package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;


import java.io.IOException;

public class HelloController {

    private CollectionAddressBook addressBookImpl = new CollectionAddressBook();
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDel;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnSearch;
    @FXML
    private Label labelCount;
    @FXML
    private TableColumn<Person, String> columnPIP;
    @FXML
    private TableColumn<Person, String> columnPhone;
    @FXML
    private TableView<Person> tableAddressBook;
    @FXML
    private TextField txtSearch;


    @FXML
    public void openEditDialog(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/edit.fxml"));
            Parent root = loader.load();

            EditController editController = loader.getController();
            editController.setPerson(new Person("", ""));
            Stage stage = new Stage();
            stage.setTitle("Додати контакт");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.showAndWait();
            Person newPerson = editController.getPerson();


            if (newPerson != null) {
                addressBookImpl.add(newPerson);
                tableAddressBook.setItems(addressBookImpl.getPersonList());
                updateCountLabel();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() throws IOException {
        columnPIP.setCellValueFactory(cellData -> cellData.getValue().pipProperty());
        columnPhone.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        addressBookImpl.getPersonList().addListener((ListChangeListener<Person>) c -> updateCountLabel());
        addressBookImpl.fillTestData();
        tableAddressBook.setItems(addressBookImpl.getPersonList());
        updateCountLabel();
    }

    private void updateCountLabel() {
        labelCount.setText("Кількість записів: " + addressBookImpl.getPersonList().size());
    }



    @FXML
    public void openEditDialogForEdit(ActionEvent event) {
        Person selectedPerson = tableAddressBook.getSelectionModel().getSelectedItem();

        if (selectedPerson != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/edit.fxml"));
                Parent root = loader.load();
                EditController editController = loader.getController();
                editController.setPerson(selectedPerson);
                Stage stage = new Stage();
                stage.setTitle("Редагувати контакт");
                stage.setResizable(false);
                stage.setScene(new Scene(root));
                stage.showAndWait();
                tableAddressBook.setItems(addressBookImpl.getPersonList());
                updateCountLabel();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Please select a contact to edit.");
        }
    }

    @FXML
    public void deleteSelectedContact() {
        Person personToDelete = tableAddressBook.getSelectionModel().getSelectedItem();

        if (personToDelete != null) {
            addressBookImpl.delete(personToDelete);
            tableAddressBook.setItems(addressBookImpl.getPersonList());
            updateCountLabel();
        } else {
            System.out.println("Please select a contact to delete.");
        }
    }

    @FXML
    private void search() {
        String query = txtSearch.getText().toLowerCase();
        ObservableList<Person> searchResults = FXCollections.observableArrayList();

        for (Person person : addressBookImpl.getPersonList()) {
            if (person.getPip().toLowerCase().contains(query) || person.getPhone().toLowerCase().contains(query)) {
                searchResults.add(person);
            }
        }

        tableAddressBook.setItems(searchResults);

    }

}