package com.example.gzone;

import com.example.entity.Match;
import com.example.entity.Team;
import com.example.entity.Tournament;
import com.example.service.*;
import com.example.util.TournamentMatches;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.List;

public class ViewTournamentController {

    Tournament t;

    @FXML
    private AnchorPane apViewTournament;

    @FXML
    private Button bAddMatches;

    @FXML
    private Button bExit;

    @FXML
    private Button bSelectWinner;

    @FXML
    private Text tCloseRequestDate;

    @FXML
    private Text tCreationDate;

    @FXML
    private Label tDescription;

    @FXML
    private Text tGameName;

    @FXML
    private Label tJoinedTeams;

    @FXML
    private Text tNumberOfTeams;

    @FXML
    private Text tTeamSize;

    @FXML
    private Label tTournamentName;

    @FXML
    private TreeTableView ttvMatches;

    @FXML
    void Forum(MouseEvent event) {

    }

    @FXML
    void HomePage(MouseEvent event) {

    }

    @FXML
    void Store(MouseEvent event) {

    }

    @FXML
    void Team(MouseEvent event) {

    }

    @FXML
    void Tournament(MouseEvent event) {

    }

    @FXML
    void addMatches(ActionEvent event) throws IOException {
        if (t.getAdminId().equals(Id.user)) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("AddMatches.fxml"));
            apViewTournament.getChildren().setAll(pane);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("You cannot access this menu");
            a.setContentText("You are not the admin of this tournament to add matches");
            a.show();
        }
    }

    @FXML
    void exitTournament(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ListTournaments.fxml"));
        apViewTournament.getChildren().setAll(pane);
    }

    @FXML
    void selectWinner(ActionEvent event) {
        if (t.getAdminId().equals(Id.user) && ttvMatches.getSelectionModel().getSelectedItem() != null ) {
            Team winnerTeam = new Teams().find(
                    null, null,
                    String.format(
                            "`name`='%s'",
                            ttvMatches.getSelectionModel().getSelectedItem().toString()
                            ),
                    null).get(0);

        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("You cannot access this action");
            a.setContentText("You are not the admin of this tournament to select winnners");
            a.show();
        }
    }

    @FXML
    void initialize() {
        t = new Tournaments().findById(Id.tournament);
        tTournamentName.setText(t.getName());
        tCreationDate.setText(t.getCreateDate().toString());
        tDescription.setText(t.getDescription());
        tCloseRequestDate.setText(t.getCloseRequestsDate().toString());
        tGameName.setText(new Games().findById(t.getGameId()).getName());
        tJoinedTeams.setText("" + new JoinRequests().findAll(String.format(
                "`tournament_id`=%d AND `accepted`=true", Id.tournament
        )).size());
        tNumberOfTeams.setText(t.getRequiredTeams().toString());
        tTeamSize.setText(t.getTeamSize().toString());
        TreeTableColumn<String, String> ttcTeam = new TreeTableColumn<>("Teams");
        ttcTeam.setCellValueFactory(new TreeItemPropertyValueFactory<>("team"));
        ttvMatches.getColumns().add(ttcTeam);
        TreeItem tiroot = new TreeItem("View Rounds");
        for (int i = TournamentMatches.firstRoundOf(t); i > 0; --i) {
            TreeItem tiRounds = new TreeItem("Round "+i);
            List<Match> matchList = new Matches().findAll(String.format(
                    "`tournament_id`=%d AND `round`=%d", Id.tournament, i
            ));
            for (int j = 0; j < matchList.size(); j++) {
                TreeItem tiMatches = new TreeItem("Match "+j);
                TreeItem tiTeam1 = new TreeItem(new Teams().findById(matchList.get(j).getTeam1Id()).getName());
                TreeItem tiTeam2 = new TreeItem(new Teams().findById(matchList.get(j).getTeam2Id()).getName());
                tiMatches.getChildren().addAll(tiTeam1, tiTeam2);
                tiRounds.getChildren().add(tiMatches);
            }
            tiroot.getChildren().add(tiRounds);
        }
        ttvMatches.setRoot(tiroot);

    }
}
