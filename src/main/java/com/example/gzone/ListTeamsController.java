package com.example.gzone;

import com.example.entity.Team;
import com.example.entity.Tournament;
import com.example.service.JoinRequests;
import com.example.service.Teams;
import com.example.service.Tournaments;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.scene.layout.AnchorPane;

public class ListTeamsController implements Initializable {

    @FXML
    private ListView<Tournament> lvtournament;

    @FXML
    private ListView<Team> lvteams;

    @FXML
    private Button bInvite;
    @FXML
    private AnchorPane apInviteTeams;

    @FXML
    void Forum(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Forumview1.fxml"));
        apInviteTeams.getChildren().setAll(pane);
    }

    @FXML
    void HomePage(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        apInviteTeams.getChildren().setAll(pane);
    }

    @FXML
    void Store(ActionEvent event) throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("ViewStores.fxml"));
        apInviteTeams.getChildren().setAll(pane);
    }

    @FXML
    void Team(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("team-view.fxml"));
        apInviteTeams.getChildren().setAll(pane);
    }

    @FXML
    void Tournament(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ListTournaments.fxml"));
        apInviteTeams.getChildren().setAll(pane);
    }


    @FXML
    void choose(MouseEvent event) {
        Teams teams = new Teams();
        List<Team> lteam = teams.findAll("`admin_id`!=" + Id.user + " And `invitable`=1 And `game_id`=" + lvtournament.getSelectionModel().getSelectedItem().getGameId() + " And `team_size`=" + lvtournament.getSelectionModel().getSelectedItem().getTeamSize());
        lvteams.getItems().clear();
        lvteams.getItems().addAll(lteam);

        lvteams.refresh();

    }

    @FXML
    void invitetournament(ActionEvent event) throws IOException {
        Id.temp = lvteams.getSelectionModel().getSelectedItem().getId();
        Id.tournament = lvtournament.getSelectionModel().getSelectedItem().getId();
        bInvite.getScene().setRoot(FXMLLoader.load(getClass().getResource("JoinRequest-inviteTeam.fxml")));
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Tournaments tournaments = new Tournaments();


        List<Tournament> ltournament = tournaments.findAll("`admin_id`=" + Id.user);

        List<Tournament> result = ltournament.stream().filter(
                tournament -> tournament.getRequiredTeams() > new JoinRequests().findAll(String.format("`tournament_id`=%d AND `accepted`=true", tournament.getId())).size()
        ).collect(Collectors.toList());
        lvtournament.getItems().addAll(ltournament);


        bInvite.disableProperty()
                .bind(lvteams.getSelectionModel().selectedItemProperty().isNull());
        bInvite.disableProperty()
                .bind(lvtournament.getSelectionModel().selectedItemProperty().isNull());

    }
}
