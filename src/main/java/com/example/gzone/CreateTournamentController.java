package com.example.gzone;

import com.example.entity.Game;
import com.example.entity.Tournament;
import com.example.service.Games;
import com.example.service.Tournaments;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.util.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

public class CreateTournamentController {

    private Integer gameId;
    private Integer requiredTeams;
    private Integer teamSize;

    @FXML
    private Button bCancel;

    @FXML
    private Button bCreate;

    @FXML
    private CheckBox cbOpenForRequests;

    @FXML
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
    void cancelCreateTournament(ActionEvent event) {

    }

    @FXML
    void createTournament(ActionEvent event) {
        Tournaments tournaments = new Tournaments();
        LocalDate ld = dpCloseRequestDate.getValue();
        Instant instant = Instant.from(ld.atStartOfDay(ZoneId.systemDefault()));
        Date crd = new Date(Date.from(instant).getTime());
        tournaments.insert(new Tournament(
                null,
                2,
                gameId,
                tfTournamentName.getText(),
                taTournamentDescription.getText(),
                requiredTeams,
                teamSize,
                crd,
                cbOpenForRequests.isSelected(),
                new java.util.Date()
        ));
    }

    @FXML
    public void initialize() {
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
