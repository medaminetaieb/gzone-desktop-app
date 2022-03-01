package com.example.gzone;

import com.example.entity.Game;
import com.example.entity.JoinRequest;
import com.example.entity.Team;
import com.example.entity.Tournament;
import com.example.service.Games;
import com.example.service.JoinRequests;
import com.example.service.Teams;
import com.example.service.Tournaments;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ModifyTournamentController {

    private Tournament tournament;
    private Integer gameId;
    private Integer requiredTeams;
    private Integer teamSize;

    public void setTournament(Tournament t) {
        this.tournament = tournament;
    }

    @FXML
    private Button bCancel;

    @FXML
    private Button bModify;

    @FXML
    private CheckBox cbOpenForRequests;

    @FXML
    private DatePicker dpCloseRequestDate;

    @FXML
    private SplitMenuButton smbGame;

    @FXML
    private SplitMenuButton smbNumberOfTeams;

    @FXML
    private SplitMenuButton smbParticipatingTeams;

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
    void cancelModifyTournament(ActionEvent event) {

    }

    @FXML
    void modifyTournament(ActionEvent event) {
        Tournaments tournaments = new Tournaments();
        LocalDate ld = dpCloseRequestDate.getValue();
        Instant instant = Instant.from(ld.atStartOfDay(ZoneId.systemDefault()));
        tournament.setGameId(gameId);
        tournament.setName(tfTournamentName.getText());
        tournament.setDescription(taTournamentDescription.getText());
        tournament.setRequiredTeamsNumber(requiredTeams);
        tournament.setTeamSize(teamSize);
        tournament.setCloseRequestsDate(new Date(Date.from(instant).getTime()));
        tournament.setApproved(cbOpenForRequests.isSelected());
        tournaments.modify(tournament);
    }

    @FXML
    public void initialize() {
        Tournaments tournaments = new Tournaments();
        tournament = tournaments.findById(2);
        // Initialize Fields from current tournament
        tfTournamentName.setText(tournament.getName());
        taTournamentDescription.setText(tournament.getDescription());
        smbNumberOfTeams.setText(tournament.getRequiredTeams().toString());
        smbTeamSize.setText(tournament.getTeamSize().toString());
        dpCloseRequestDate.setValue(
                Instant.ofEpochMilli(tournament.getCloseRequestsDate().getTime())
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate());
        cbOpenForRequests.setSelected(tournament.isApproved());

        // Modify Tournament Fields
        final Games games = new Games();
        List<Game> gameList = games.findAll();
        Game g = games.findById(tournament.getGameId());
        smbGame.setText((g != null)? g.getName() : "No Game");
        MenuItem smbgmi = new MenuItem("No Game");
        smbGame.getItems().add(smbgmi);
        smbgmi.setOnAction(e -> {
            gameId = null;
            smbGame.setText("No Game");
        });
        for (Game g1 : gameList) {
            MenuItem mi = new MenuItem(g1.getName());
            mi.setOnAction(e -> {
                gameId = g1.getId();
                smbGame.setText(g1.getName());
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
        final Teams teams = new Teams();
        List<JoinRequest> teamJoinRequestList = new JoinRequests().findAll(
                String.format("`tournament_id`=%d AND `accepted`=%b AND `team_id` IS NOT NULL", tournament.getId(), true),
                "`response_date` DESC"
                );
        List<Team> teamList = new ArrayList<>();
        final List<Team> finalTeamList = teamList;
        teamJoinRequestList.stream().forEach(jr -> finalTeamList.add(teams.findById(jr.getTeamId())));
        for (Team t : teamList) {
            RadioMenuItem rmi = new RadioMenuItem(t.getName());
            rmi.setSelected(true);
            smbParticipatingTeams.getItems().add(rmi);
        }

        smbParticipatingTeams.getItems().add(new SeparatorMenuItem());

        teamJoinRequestList = new JoinRequests().findAll(
                String.format("(`tournament_id`=%d) AND (`accepted` IS NULL) AND (`team_id` IS NOT NULL)", tournament.getId()),
                "`request_date` DESC"
        );
        final List<Team> finalTeamList1 = teamList;
        teamJoinRequestList.stream().forEach(jr -> finalTeamList1.add(teams.findById(jr.getTeamId())));
        for (Team t : teamList) {
            RadioMenuItem rmi = new RadioMenuItem(t.getName());
            rmi.setSelected(false);
            smbParticipatingTeams.getItems().add(rmi);
        }

        smbParticipatingTeams.getItems().add(new SeparatorMenuItem());

        teamList = teams.findAll(
                String.format("`invitable`=true AND `game_id`=%d AND `team_size`=%d", tournament.getGameId(), tournament.getTeamSize()),
                "`create_date` DESC"
        );
        for (Team t : teamList) {
            RadioMenuItem rmi = new RadioMenuItem(t.getName());
            rmi.setSelected(false);
            smbParticipatingTeams.getItems().add(rmi);
        }
    }
}
