package com.example.gzone;

import com.example.entity.MarketItem;
import com.example.service.MarketItems;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Date;

public class AddItemForm {

    @FXML
    public Button additembutton;

    @FXML
    public TextArea description;

    @FXML
    public AnchorPane itemformpane;

    @FXML
    public TextField title;

    @FXML
    void AddItem(ActionEvent event) throws IOException {
        if (!title.getText().equals("")) {
            new MarketItems().insert(new MarketItem(null, Id.store, title.getText(), description.getText(), false, new Date()));

            AnchorPane pane = FXMLLoader.load(getClass().getResource("StoreProfile.fxml"));
            itemformpane.getChildren().setAll(pane);
            new StoreProfileController().ViewItem();

        }
    }

    @FXML
    void HomePage(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        itemformpane.getChildren().setAll(pane);
    }

    @FXML
    void Store(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ViewStores.fxml"));
        itemformpane.getChildren().setAll(pane);
    }


}


