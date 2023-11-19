package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;



public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        stage.setTitle("Golova");

        Scene scene = new Scene(fxmlLoader.load(), 350, 380);
        stage.setScene(scene);

        stage.setMinHeight(420);
        stage.setMinWidth(365);

        stage.show();
        testData();
    }

    private void testData(){
        CollectionAddressBook addressBook = new CollectionAddressBook();
        addressBook.fillTestData();
        addressBook.print();
    }



    public static void main(String[] args) {
        launch();
    }
}