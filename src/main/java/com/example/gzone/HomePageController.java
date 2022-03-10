package com.example.gzone;

import com.example.entity.Store;
import com.example.entity.Tournament;
import com.example.service.UserLikesDislikes;
import com.example.util.StoreStat;
import com.example.util.TournamentStat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import javafx.scene.control.Button;

public class HomePageController {

    @FXML
    private PieChart pieChart;
    @FXML
    private ListView<Tournament> tournamentList;
    @FXML
    private Button bGoToTournament;

    @FXML
    void initialize() {
        topstore.getItems().setAll(StoreStat.Top3());
        for (Store s : StoreStat.Top3()) {
            pieChart.getData().add(new PieChart.Data(s.getName(), new UserLikesDislikes().findAll("`store_id`=" + s.getId() + " and `like`=true").size()));
        }

        tournamentList.getItems().addAll(TournamentStat.suggestedTournaments(Id.user));
    }
    @FXML
    private AnchorPane homepagepane;
    @FXML
    private ListView<Store> topstore;

    void Games(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("GameView.fxml"));
        homepagepane.getChildren().setAll(pane);
    }

    @FXML
    void HappyHour(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("HappyHour.fxml"));
        homepagepane.getChildren().setAll(pane);
    }

    @FXML
    void Forum(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Forumview1.fxml"));
        homepagepane.getChildren().setAll(pane);
    }

    @FXML
    void Profile(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Profile.fxml"));
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
    void Tournament(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ListTournaments.fxml"));
        homepagepane.getChildren().setAll(pane);
    }

    @FXML
    private void goToTournament(ActionEvent event) throws IOException {
        Id.tournament = tournamentList.getSelectionModel().getSelectedItem().getId();
        bGoToTournament.getScene().setRoot(FXMLLoader.load(getClass().getResource("ViewTournament.fxml")));
    }

}
