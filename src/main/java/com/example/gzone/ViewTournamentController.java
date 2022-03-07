package com.example.gzone;

import com.example.entity.Match;
import com.example.entity.Team;
import com.example.entity.Tournament;
import com.example.service.*;
import com.example.util.TournamentMatches;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewTournamentController implements Initializable {

    Tournament t;

    @FXML
    private AnchorPane apViewTournament;

    @FXML
    private Button bModifyTournament;

    @FXML
    private Button bAddMatches;

    @FXML
    private Button bExit;

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

    private TreeTableView ttvMatches;
    @FXML
    private Button bUpdateScore;
    @FXML
    private ListView<Integer> lvRounds;
    @FXML
    private ListView<Match> lvMatches;
    @FXML
    private Text tRequestable;
    @FXML
    private MenuButton mbSelectTeam;

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
    void goToModifyTournament(ActionEvent event) throws IOException {
        if (t.getAdminId().equals(Id.user)) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("ModifyTournament.fxml"));
            apViewTournament.getChildren().setAll(pane);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("You cannot access this page");
            a.setContentText("You are not the admin of this tournament to modify it");
            a.show();
        }
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
        bExit.getScene().setRoot(FXMLLoader.load(getClass().getResource("ListTournaments.fxml")));
    }

    @FXML
    void selectWinner(ActionEvent event) {
        if (t.getAdminId().equals(Id.user) && ttvMatches.getSelectionModel().getSelectedItem() != null) {
            Team winnerTeam = new Teams().findAll(
                    String.format(
                            "`name`='%s'",
                            ttvMatches.getSelectionModel().getSelectedItem().toString()
                    )
            ).get(0);

        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("You cannot access this action");
            a.setContentText("You are not the admin of this tournament to select winnners");
            a.show();
        }
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        t = new Tournaments().findById(Id.tournament);
        tTournamentName.setText(t.getName());
        tCreationDate.setText(t.getCreateDate().toString());
        tDescription.setText(t.getDescription());
        tRequestable.setText(t.isRequestable().toString());
        tGameName.setText(new Games().findById(t.getGameId()).getName());
        tJoinedTeams.setText("" + new JoinRequests().findAll(String.format(
                "`tournament_id`=%d AND `accepted`=true", Id.tournament
        )).size());
        tNumberOfTeams.setText(t.getRequiredTeams().toString());
        tTeamSize.setText(t.getTeamSize().toString());

        lvRounds = new ListView<>();
        lvMatches = new ListView<>();
        for (int i = TournamentMatches.firstRoundOf(t); i > 0; --i) {
            lvRounds.getItems().add(i);
        }
        
        if (t.getRequiredTeams() > new JoinRequests().findAll("`tournament_id`=" + Id.tournament + " AND `accepted`=true").size()) {
            for (Team t : new Teams().findAll("`admin_id`=" + Id.user + " AND `game_id`=" + t.getGameId() + " AND `team_size`=" + t.getTeamSize())) {
                MenuItem mi = new MenuItem(t.getName());
                mi.setOnAction(e -> {
                    Id.team = t.getId();
                    mbSelectTeam.setText(t.getName());
                    try {
                    mbSelectTeam.getScene().setRoot(FXMLLoader.load(getClass().getResource("joinRequest-Tournament.fxml")));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
                mbSelectTeam.getItems().add(mi);
            }
        }
    }

    @FXML
    private void handleRoundsMouseClick(MouseEvent event) {
        for (Match m : new Matches().findAll("`round`=" + lvRounds.getSelectionModel().getSelectedItem())) {
            lvMatches.getItems().add(m);
        }
    }
}
