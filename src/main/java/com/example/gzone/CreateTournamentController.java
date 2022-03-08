package com.example.gzone;

import com.example.entity.Game;
import com.example.entity.Tournament;
import com.example.service.Games;
import com.example.service.Tournaments;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CreateTournamentController implements Initializable {

    private Integer gameId;
    private Integer requiredTeams;
    private Integer teamSize;

    @FXML
    private AnchorPane apCreateTournament;

    @FXML
    private Button bCancel;

    @FXML
    private Button bCreate;

    @FXML
    private CheckBox cbRequestable;

    private DatePicker dpCloseRequestDate;

    @FXML
    private SplitMenuButton smbGame;

    @FXML
    private SplitMenuButton smbNumberOfTeams;

    @FXML
    private SplitMenuButton smbTeamSize;

    @FXML
    private TextArea taTournamentDescription;

    @FXML
    private TextField tfTournamentName;

    @FXML
    void Forum(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Forumview1.fxml"));
        apCreateTournament.getChildren().setAll(pane);
    }

    @FXML
    void HomePage(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        apCreateTournament.getChildren().setAll(pane);
    }

    @FXML
    void Store(ActionEvent event) throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("ViewStores.fxml"));
        apCreateTournament.getChildren().setAll(pane);
    }

    @FXML
    void Team(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("team-view.fxml"));
        apCreateTournament.getChildren().setAll(pane);
    }

    @FXML
    void Tournament(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Tournament.fxml"));
        apCreateTournament.getChildren().setAll(pane);
    }

    @FXML
    void cancelCreateTournament(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ListTournaments.fxml"));
        apCreateTournament.getChildren().setAll(pane);
    }

    @FXML
    void createTournament(ActionEvent event) throws IOException {
        Alert a = new Alert(Alert.AlertType.NONE);
        if (
                !tfTournamentName.getText().isBlank()
                        && !taTournamentDescription.getText().isBlank()
                        && requiredTeams != null
                        && teamSize != null
        ) {
            Tournaments tournaments = new Tournaments();
            tournaments.insert(new Tournament(
                    null,
                    Id.user,
                    gameId,
                    tfTournamentName.getText(),
                    taTournamentDescription.getText(),
                    requiredTeams,
                    teamSize,
                    cbRequestable.isSelected(),
                    true,
                    new java.util.Date()
            ));
            a.setAlertType(Alert.AlertType.INFORMATION);
            a.setTitle("Success");
            a.setContentText("Tournament created");
            a.show();
            Id.tournament = tournaments.findAll("`name` REGEXP '"+tfTournamentName.getText()+"'").get(0).getId();
            bCreate.getScene().setRoot(FXMLLoader.load(getClass().getResource("ListTournaments.fxml")));
        } else {
            a.setAlertType(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setContentText("Please Fill the form");
            a.show();
        }
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Game> gameList = new Games().findAll();
        MenuItem smbgmi = new MenuItem("No Game");
        smbGame.getItems().add(smbgmi);
        smbgmi.setOnAction(e -> {
            gameId = null;
            smbGame.setText("No Game");
        });
        for (Game g : gameList) {
            MenuItem mi = new MenuItem(g.getName());
            mi.setOnAction(e -> {
                gameId = g.getId();
                smbGame.setText(g.getName());
            });
            smbGame.getItems().add(mi);
        }
        MenuItem smbnot4 = new MenuItem("4");
        smbnot4.setOnAction(e -> {
            requiredTeams = 4;
            smbNumberOfTeams.setText("4 teams");
        });
        MenuItem smbnot8 = new MenuItem("8");
        smbnot8.setOnAction(e -> {
            requiredTeams = 8;
            smbNumberOfTeams.setText("8 teams");
        });
        MenuItem smbnot16 = new MenuItem("16");
        smbnot16.setOnAction(e -> {
            requiredTeams = 16;
            smbNumberOfTeams.setText("16 teams");
        });
        MenuItem smbnot32 = new MenuItem("32");
        smbnot32.setOnAction(e -> {
            requiredTeams = 32;
            smbNumberOfTeams.setText("32 teams");
        });
        smbNumberOfTeams.getItems().addAll(
                smbnot4,
                smbnot8,
                smbnot16,
                smbnot32
        );

        MenuItem smbts1 = new MenuItem("1");
        smbts1.setOnAction(e -> {
            teamSize = 1;
            smbTeamSize.setText("Solo");
        });
        MenuItem smbts2 = new MenuItem("2");
        smbts2.setOnAction(e -> {
            teamSize = 2;
            smbTeamSize.setText("Double");
        });
        MenuItem smbts3 = new MenuItem("3");
        smbts3.setOnAction(e -> {
            teamSize = 3;
            smbTeamSize.setText("Triple");
        });
        MenuItem smbts4 = new MenuItem("4");
        smbts4.setOnAction(e -> {
            teamSize = 4;
            smbTeamSize.setText("Quadra");
        });
        MenuItem smbts5 = new MenuItem("5");
        smbts5.setOnAction(e -> {
            teamSize = 5;
            smbTeamSize.setText("Penta");
        });
        smbTeamSize.getItems().addAll(
                smbts1,
                smbts2,
                smbts3,
                smbts4,
                smbts5
        );
    }
}
