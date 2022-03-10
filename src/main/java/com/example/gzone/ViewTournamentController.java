package com.example.gzone;

import com.example.entity.*;
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
import java.util.Date;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

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

    @FXML
    private Button bUpdateScore;
    @FXML
    private ListView<Integer> lvRounds;
    @FXML
    private ListView<Match> lvMatches;
    @FXML
    private ListView<Team> lvTeams;
    @FXML
    private Text tRequestable;
    @FXML
    private MenuButton mbSelectTeam;
    @FXML
    private Button report;

    @FXML
    void Forum(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Forumview1.fxml"));
        apViewTournament.getChildren().setAll(pane);
    }

    @FXML
    void HomePage(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        apViewTournament.getChildren().setAll(pane);
    }

    @FXML
    void Store(ActionEvent event) throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("ViewStores.fxml"));
        apViewTournament.getChildren().setAll(pane);
    }

    @FXML
    void Team(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("team-view.fxml"));
        apViewTournament.getChildren().setAll(pane);
    }

    @FXML
    void Tournament(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ListTournaments.fxml"));
        apViewTournament.getChildren().setAll(pane);
    }

    @FXML
    void goToModifyTournament(ActionEvent event) throws IOException {
        if (t.getAdminId().equals(Id.user)) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("ModifyTournament.fxml"));
            apViewTournament.getChildren().setAll(pane);
        } else {
            String title = "Access denied!";
            String message = "You are not the admin of the tournament!";
            NotificationType notification = NotificationType.WARNING;
            TrayNotification tray = new TrayNotification();
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotificationType(notification);
            tray.showAndDismiss(Duration.seconds(6));
        }
    }

    @FXML
    void addMatches(ActionEvent event) throws IOException {
        if (t.getAdminId().equals(Id.user)) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("AddMatches.fxml"));
            apViewTournament.getChildren().setAll(pane);
        } else {
            String title = "Access denied!";
            String message = "You are not the admin of the tournament!";
            NotificationType notification = NotificationType.WARNING;
            TrayNotification tray = new TrayNotification();
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotificationType(notification);
            tray.showAndDismiss(Duration.seconds(6));
        }
    }

    @FXML
    void exitTournament(ActionEvent event) throws IOException {
        bExit.getScene().setRoot(FXMLLoader.load(getClass().getResource("ListTournaments.fxml")));
    }

    @FXML
    void selectWinner(ActionEvent event) throws IOException {
        Integer round = lvRounds.getSelectionModel().getSelectedItem();
        Match match = lvMatches.getSelectionModel().getSelectedItem();
        Team team = lvTeams.getSelectionModel().getSelectedItem();

        if (t.getAdminId().equals(Id.user) && team != null) {
            match.setWinnerTeamId(team.getId());
            new Matches().modify(match);
            if (--round > 0) {
                if (new Matches().findAll("`tournament_id`=" + Id.tournament + " AND `round`=" + round + " AND `team2_id` IS NULL").isEmpty()) {
                    new Matches().insert(new Match(null, Id.tournament, new Date(), round, team.getId(), null, null));
                } else {
                    Match m = new Matches().findAll("`tournament_id`=" + Id.tournament + " AND `round`=" + round + " AND `team2_id` IS NULL").get(0);
                    Id.match = m.getId();
                    m.setTeam2Id(team.getId());
                    m.setWinnerTeamId(null);
                    new Matches().modify(m);
                    bAddMatches.getScene().setRoot(FXMLLoader.load(getClass().getResource("ModifyMatch.fxml")));
                }
            } else {
                for (HappyHour hh : new HappyHours().findAll("now() BETWEEN `start_date` AND `end_date`")) {
                    if ((new Badges().findById(hh.getBadgeId()).getGameId().equals(new Tournaments().findById(Id.tournament)))) {
                        for (JoinRequest jr : new JoinRequests().findAll("`accepted`=true AND `user_id` IS NOT NULL AND `team_id`=" + team.getId())) {
                            new BadgeShips().insert(new BadgeShip(null, hh.getBadgeId(), jr.getUserId()));
                        }
                    }
                }
            }
            handleMatchesMouseClick(null);
        } else {
            String title = "Access denied!";
            String message = "You are not the admin of the tournament!";
            NotificationType notification = NotificationType.WARNING;
            TrayNotification tray = new TrayNotification();
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotificationType(notification);
            tray.showAndDismiss(Duration.seconds(6));
        }
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        t = new Tournaments().findById(Id.tournament);
        if(Id.user.equals(t.getAdminId())){
            report.setVisible(true);
        }
        tTournamentName.setText(t.getName());
        tCreationDate.setText(t.getCreateDate().toString());
        tDescription.setText(t.getDescription());
        tRequestable.setText(t.isRequestable().toString());
        tGameName.setText((t.getGameId() == null) ? "No Game" : new Games().findById(t.getGameId()).getName());
        tJoinedTeams.setText("" + new JoinRequests().findAll(String.format(
                "`tournament_id`=%d AND `accepted`=true", Id.tournament
        )).size());
        tNumberOfTeams.setText(t.getRequiredTeams().toString());
        tTeamSize.setText(t.getTeamSize().toString());

        for (Integer i = TournamentMatches.firstRoundOf(t); i > 0; --i) {
            lvRounds.getItems().add(i);
        }

        if (t.isRequestable()) {
            if (t.getRequiredTeams() > new JoinRequests().findAll("`tournament_id`=" + Id.tournament + " AND `accepted`=true").size()) {
                String c = "";
                if (Id.game == null) {
                    c = " OR `game_id` IS NULL";
                }
                for (Team team : new Teams().findAll("`admin_id`=" + Id.user + " AND (`game_id`=" + t.getGameId() + c + ") AND `team_size`=" + t.getTeamSize() + " AND `invitable`=true")) {
                    if (new JoinRequests().findAll("`tournament_id`=" + Id.tournament + " AND `team_id`=" + team.getId()).size() == 0) {
                        MenuItem mi = new MenuItem(team.getName());
                        mi.setOnAction(e -> {
                            Id.team = team.getId();
                            mbSelectTeam.setText(team.getName());
                            try {
                                mbSelectTeam.getScene().setRoot(FXMLLoader.load(getClass().getResource("joinRequest-Tournament.fxml")));
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        });
                        mbSelectTeam.getItems().add(mi);
                    }
                }
            } else {
                mbSelectTeam.setText("Tournament is full");
            }
        } else {
            mbSelectTeam.setText("Not Requestable");
        }
    }

    @FXML
    private void handleRoundsMouseClick(MouseEvent event) {
        lvMatches.getItems().clear();
        lvTeams.getItems().clear();
        for (Match m : new Matches().findAll("`tournament_id`=" + Id.tournament + " AND `round`=" + lvRounds.getSelectionModel().getSelectedItem())) {
            lvMatches.getItems().add(m);
        }
    }

    @FXML
    void handleMatchesMouseClick(MouseEvent event) {
        lvTeams.getItems().clear();
        Teams teams = new Teams();
        lvTeams.getItems().add(teams.findById(lvMatches.getSelectionModel().getSelectedItem().getTeam1Id()));
        lvTeams.getItems().add(teams.findById(lvMatches.getSelectionModel().getSelectedItem().getTeam2Id()));
        if ((lvMatches.getSelectionModel().getSelectedItem().getWinnerTeamId() != 0) || (lvMatches.getSelectionModel().getSelectedItem().getTeam2Id() == 0)) {
            bUpdateScore.setDisable(true);
        } else {
            bUpdateScore.setDisable(false);
        }
    }

    @FXML
    private void ReportTournament(ActionEvent event) throws IOException {
        Id.type = 0;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Report.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage newWindow = new Stage();
        newWindow.setTitle("Report Tournament");
        newWindow.setScene(scene);
        newWindow.show();
    }

}
