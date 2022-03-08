package com.example.gzone;
import com.example.entity.Store;
import com.example.util.StoreStat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class HomePageController {
 @FXML
 void initialize (){
     topstore.getItems().setAll(StoreStat.Top3());
 }
    @FXML
    private AnchorPane homepagepane;
    @FXML
    private ListView<Store> topstore;

    @FXML
    void Forum(ActionEvent event) {

    }

    @FXML
    void HomePage(ActionEvent event) throws IOException{

    }

    @FXML
    void Store(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ViewStores.fxml"));
        homepagepane.getChildren().setAll(pane);
    }

    @FXML
    void Team(ActionEvent event) {

    }

    @FXML
    void Tournament(ActionEvent event) {

    }

}
