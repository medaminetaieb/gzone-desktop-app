package com.example.gzone;

import com.example.entity.Game;
import com.example.entity.Tournament;
import com.example.service.Games;
import com.example.service.Tournaments;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.List;

public class ListTournaments {

    Tournaments tournaments;

    @FXML
    private AnchorPane apListTournaments;

    @FXML
    private Button bRefresh;

    @FXML
    private Button bViewTournament;

    @FXML
    private Button bCreateNewTournament;

    @FXML
    private MenuButton mbGame;

    @FXML
    private TextField tfSearchName;

    @FXML
    private TableView tvList;

    @FXML
    private TableColumn<Tournament, String> tcAdmin;

    @FXML
    private TableColumn<Tournament, String> tcGame;

    @FXML
    private TableColumn<Tournament, String> tcName;

    @FXML
    private TableColumn<Tournament, String> tcDescription;

    @FXML
    private TableColumn<Tournament, String> tcRequiredTeams;

    @FXML
    private TableColumn<Tournament, String> tcTeamSize;

    @FXML
    private TableColumn<Tournament, String> tcCloseRequestDate;

    @FXML
    void refreshList(ActionEvent event) {
        tvList.getItems().clear();

        if (Id.game == null) {
            for (Tournament t : tournaments.findAll("`name` REGEXP '" + tfSearchName.getText() + "' AND `game_id` IS NULL")) {
                tvList.getItems().add(t);
                System.out.println(t);
            }
        } else if (Id.game == 0) {
            for (Tournament t : tournaments.findAll("`name` REGEXP '" + tfSearchName.getText() + "'")) {
                tvList.getItems().add(t);
                System.out.println(t);
            }
        } else {
            for (Tournament t : tournaments.findAll("`name` REGEXP '" + tfSearchName.getText() + "' AND `game_id`=" + Id.game)) {
                tvList.getItems().add(t);
            }
        }
        tvList.refresh();
    }


    @FXML
    void viewTournament(ActionEvent event) throws IOException {
        if (tvList.getSelectionModel().getSelectedItem() != null) {
            Id.tournament = ((Tournament) tvList.getSelectionModel().getSelectedItem()).getId();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("ModifyTournament.fxml"));
            apListTournaments.getChildren().setAll(pane);
        }
    }

    @FXML
    void goToCreateTournament(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("CreateTournament.fxml"));
        apListTournaments.getChildren().setAll(pane);
    }

    @FXML
    void initialize() {
        tournaments = new Tournaments();
        List<Game> gameList = new Games().findAll();
        MenuItem mbgall = new MenuItem("Any Game");
        mbGame.getItems().add(mbgall);
        mbgall.setOnAction(e -> {
            Id.game = 0;
            refreshList(null);
            mbGame.setText("Any Game");
        });
        MenuItem mbgnone = new MenuItem("No Game");
        mbGame.getItems().add(mbgnone);
        mbgnone.setOnAction(e -> {
            Id.game = null;
            refreshList(null);
            mbGame.setText("No Game");
        });
        for (Game g : gameList) {
            MenuItem mi = new MenuItem(g.getName());
            mi.setOnAction(e -> {
                Id.game = g.getId();
                refreshList(null);
                mbGame.setText(g.getName());
            });
            mbGame.getItems().add(mi);
        }

        tcAdmin = new TableColumn<>();
        tcAdmin.setCellValueFactory(new PropertyValueFactory<>("Admin"));
        tcGame = new TableColumn<>();
        tcGame.setCellValueFactory(new PropertyValueFactory<>("Game"));
        tcName = new TableColumn<>();
        tcName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        tcDescription = new TableColumn<>();
        tcDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        tcRequiredTeams = new TableColumn<>();
        tcRequiredTeams.setCellValueFactory(new PropertyValueFactory<>("Required Teams"));
        tcTeamSize = new TableColumn<>();
        tcTeamSize.setCellValueFactory(new PropertyValueFactory<>("Team Size"));
        tcCloseRequestDate = new TableColumn<>();
        tcCloseRequestDate.setCellValueFactory(new PropertyValueFactory<>("Close Date"));
        tvList.getColumns().addAll(tcAdmin, tcGame, tcName, tcDescription, tcRequiredTeams, tcRequiredTeams, tcTeamSize, tcCloseRequestDate);
        refreshList(null);
    }
}
