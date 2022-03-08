package com.example.gzone;

import com.example.entity.JoinRequest;
import com.example.entity.Team;
import com.example.entity.Tournament;
import com.example.service.JoinRequests;
import com.example.service.Teams;
import com.example.service.Tournaments;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AcceptDecline implements Initializable {

    @FXML
    private Button decline;

    @FXML
    private Button accept;

    @FXML
    private ListView<JoinRequest> listinvitation;

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
    void actionaccept(ActionEvent event) {

    }

    @FXML
    void actiondeccline(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        JoinRequests jrs = new JoinRequests();
        Teams teams = new Teams();
        Tournaments tournaments = new Tournaments();
        List<Team> lteam = teams.findAll("`admin_id`=" + Id.user);
        List<JoinRequest> ljrinvitations = jrs.findAll("`accepted` IS NULL AND `invitation`=true").stream()
                .filter(jr -> lteam.indexOf(jr.getTeamId()) == -1 && jr.getUserId().equals(Id.user))
                .collect(Collectors.toList());

        List<Tournament> ltournaments = tournaments.findAll("`admin_id`=" + Id.user);
        List<JoinRequest> ljrRequests = jrs.findAll("`accepted` IS NULL AND `invitation`=false").stream()
                .filter(jr -> )
                .collect(Collectors.toList());
        List<JoinRequest> ljr = jrs.findAll("`accepted` IS NULL").stream()
                .filter(joinRequest -> joinRequest.getUserId() != Id.user && lteam.indexOf(joinRequest.getTeamId()) == -1 && ltournaments.indexOf(joinRequest.getTournamentId()) == -1
                ).peek(System.out::println).collect(Collectors.toList());

        listinvitation.getItems().addAll(ljr);

    }
}
