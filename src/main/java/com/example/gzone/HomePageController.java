package com.example.gzone;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class HomePageController {

    @FXML
    private AnchorPane homepagepane;

    @FXML
    void Forum(ActionEvent event) {

    }

    @FXML
    void HomePage(ActionEvent event) throws IOException{

    }

    @FXML
    void Store(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Store.fxml"));
        homepagepane.getChildren().setAll(pane);
    }

    @FXML
    void Team(ActionEvent event) {

    }

    @FXML
    void Tournament(ActionEvent event) {

    }

}
