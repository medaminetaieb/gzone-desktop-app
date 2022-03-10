package com.example.gzone;
import com.example.entity.Store;
import com.example.service.UserLikesDislikes;
import com.example.util.StoreStat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class HomePageController {

    @FXML
    private PieChart pieChart;
 @FXML
 void initialize (){
     topstore.getItems().setAll(StoreStat.Top3());
     for(Store s: StoreStat.Top3()){
         pieChart.getData().add(new PieChart.Data(s.getName(), new UserLikesDislikes().findAll("`store_id`=" + s.getId() + " and `like`=true").size() ));
     }

 }
    @FXML
    private AnchorPane homepagepane;
    @FXML
    private ListView<Store> topstore;


    @FXML
    void Forum(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Forumview1.fxml"));
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
        AnchorPane pane = FXMLLoader.load(getClass().getResource("team-view.fxml"));
        homepagepane.getChildren().setAll(pane);
    }

    @FXML
    void Tournament(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ListTournaments.fxml"));
        homepagepane.getChildren().setAll(pane);
    }

}
