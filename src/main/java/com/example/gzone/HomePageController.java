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
    void Forum(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Forum.fxml"));
        homepagepane.getChildren().setAll(pane);
    }

    @FXML
    void HomePage(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        homepagepane.getChildren().setAll(pane);
    }

    @FXML
    void Store(ActionEvent event) throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("ViewStores.fxml"));
        homepagepane.getChildren().setAll(pane);
    }

    @FXML
    void Team(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Team.fxml"));
        homepagepane.getChildren().setAll(pane);
    }

    @FXML
    void Tournament(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Tournament.fxml"));
        homepagepane.getChildren().setAll(pane);
    }

}
