package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditController {
    @FXML
    private TextField txtPIP;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnCancel;

    @FXML
    private TextField txtPhone;

    private Person person;


    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
        txtPIP.setText(person.getPip());
        txtPhone.setText(person.getPhone());
    }

    @FXML
    private void handleOkButtonAction() {
        if (!txtPIP.getText().isEmpty() && !txtPhone.getText().isEmpty()) {
            if (person == null) {
                person = new Person(txtPIP.getText(), txtPhone.getText());
            } else {
                person.setPip(txtPIP.getText());
                person.setPhone(txtPhone.getText());
            }
            closeDialog();
        } else {
            System.out.println("Please enter both name and phone.");
        }
    }

    @FXML
    private void handleCancelButtonAction() {
        closeDialog();
    }

    private void closeDialog() {
        ((Stage) btnCancel.getScene().getWindow()).close();
    }

}
