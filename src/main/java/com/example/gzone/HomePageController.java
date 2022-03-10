package com.example.gzone;

import com.example.entity.Report;
import com.example.entity.Store;
import com.example.entity.Tournament;
import com.example.entity.User;
import com.example.service.Reports;

import com.example.service.UserLikesDislikes;
import com.example.service.Users;
import com.example.util.StoreStat;
import com.example.util.TournamentStat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class HomePageController {

    ArrayList datausers = new ArrayList();
    @FXML
    private TextField search;
    @FXML
    private PieChart pieChart;
    @FXML
    private ListView<Tournament> tournamentList;
    @FXML
    private ListView<String> userList;
    @FXML
    private Button bGoToTournament;
    @FXML
    private ListView<User> Top5 = new ListView<>();

    @FXML
    void initialize() {
        datausers.clear();
        Top5.getItems().addAll(TournamentStat.TopFive());
        Users us = new Users();
        for (User u : us.findAll()) {
            datausers.add(u.getUsername());
        }
        ObservableList users = FXCollections.observableArrayList(datausers);
        userList.setItems(users);

        userList.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent click) {
                if (click.getClickCount() == 2) {
                    Id.usertemp = new Users().findAll("`username` REGEXP '" + userList.getSelectionModel().getSelectedItem().toString() + "'").get(0).getId();
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("OtherProfile.fxml"));
                    Scene scene = null;
                    try {
                        scene = new Scene(fxmlLoader.load());
                    } catch (IOException ex) {
                        Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Stage newWindow = new Stage();
                    newWindow.setTitle("Profile");
                    newWindow.setScene(scene);
                    newWindow.show();
                }
            }
        });

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

    @FXML
    private void search(KeyEvent event) {
        userList.getItems().clear();
        new Users().findAll().stream().filter(u -> u.getFullName().contains(search.getText())).forEach(u -> userList.getItems().add(u.getUsername()));
        userList.refresh();

    }

    void Games(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("GameView.fxml"));
        homepagepane.getChildren().setAll(pane);
    }

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
