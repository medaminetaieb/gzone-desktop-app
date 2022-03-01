package com.example.gzone;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class StoreProfileController {

    @FXML
    public Button additembutton;

    @FXML
    public AnchorPane credentialspane;

    @FXML
    public AnchorPane itemspane;

    @FXML
    public AnchorPane profilepane;

    @FXML
    void AddItem(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ItemForm.fxml"));
        profilepane.getChildren().setAll(pane);
    }

    @FXML
    void Forum(ActionEvent event) {

    }

    @FXML
    void HomePage(ActionEvent event) {

    }

    @FXML
    void Store(ActionEvent event) {

    }

    @FXML
    void Team(ActionEvent event) {

    }

    @FXML
    void Tournament(ActionEvent event) {

    }

}
