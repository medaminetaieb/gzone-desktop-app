package com.example.gzone;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ItemForm {

    @FXML
    public Button addbutton;

    @FXML
    public AnchorPane additempane;

    @FXML
    void AddYourItem(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("StoreProfile.fxml"));
        additempane.getChildren().setAll(pane);
    }


}
