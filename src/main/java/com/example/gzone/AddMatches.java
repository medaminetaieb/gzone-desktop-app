package com.example.gzone;

import com.example.entity.*;
import com.example.service.JoinRequests;
import com.example.service.Matches;
import com.example.service.Teams;
import com.example.service.Tournaments;
import com.example.util.TournamentMatches;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddMatches implements Initializable {

    private int round;

    private Integer team1Id;
    private Integer team2Id;

    private Teams teams;

    @FXML
    private AnchorPane apAddMatches;

    @FXML
    private Button bAddMatch;

    @FXML
    private Button bReturn;

    @FXML
    private DatePicker dpStartTime;

    @FXML
    private MenuButton mbRound;

    @FXML
    private MenuButton mbTeam1;

    @FXML
    private MenuButton mbTeam2;

    @FXML
    private Label tTournamentName;

    @FXML
    void addMatch(ActionEvent event) {
        if (dpStartTime.getValue() != null
                && team1Id != null
                && team2Id != null
                && team1Id != team2Id) {
            new Matches().insert(new Match(
                    null,
                    Id.tournament,
                    new java.util.Date(java.sql.Date.valueOf(dpStartTime.getValue()).getTime()),
                    round,
                    team1Id,
                    team2Id,
                    null
            ));
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Match was added successfully");
            a.setContentText("You can add more matches");
            a.show();
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Not Adding");
            a.setContentText("Please fill the form");
            a.show();
        }

    }

    @FXML
    void returnToTournament(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ListTournaments.fxml"));
        apAddMatches.getChildren().setAll(pane);
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        teams = new Teams();
        Tournament t = new Tournaments().findById(Id.tournament);
        tTournamentName.setText(t.getName());
        for (int i = TournamentMatches.firstRoundOf(t); i > 0; --i) {
            MenuItem mi = new MenuItem("Round " + i);
            int finalI = i;
            mi.setOnAction(actionEvent -> {
                round = finalI;
                mbRound.setText(mi.getText());
            });
            mbRound.getItems().add(mi);
        }

        List<JoinRequest> jrl = new JoinRequests().findAll("`tournament_id`=" + Id.tournament + " AND `accepted`=true");

        refresh1(jrl);
        refresh2(jrl);
    }

    private void refresh1(List<JoinRequest> jrl) {
        jrl.stream()
                .filter(jr -> !jr.getTeamId().equals(team2Id))
                .forEach(jr -> {
                    Team team = teams.findById(jr.getTeamId());
                    MenuItem mi = new MenuItem((team.getName()));
                    mi.setOnAction(actionEvent -> {
                        team1Id = jr.getTeamId();
                        mbTeam1.setText(mi.getText());
                        refresh2(jrl);
                    });
                    mbTeam1.getItems().add(mi);
                });
    }

    private void refresh2(List<JoinRequest> jrl) {
        jrl.stream()
                .filter(jr -> !jr.getTeamId().equals(team1Id)).peek(System.out::println)
                .forEach(jr -> {
                    Team team = teams.findById(jr.getTeamId());
                    MenuItem mi = new MenuItem((team.getName()));
                    mi.setOnAction(actionEvent -> {
                        team2Id = jr.getTeamId();
                        mbTeam2.setText(mi.getText());
                        refresh1(jrl);
                    });
                    mbTeam2.getItems().add(mi);
                });
    }
}
