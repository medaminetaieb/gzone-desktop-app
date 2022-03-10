package com.example.gzone;

import com.example.entity.JoinRequest;
import com.example.entity.Team;
import com.example.entity.Tournament;
import com.example.service.JoinRequests;
import com.example.service.Teams;
import com.example.service.Tournaments;
import com.example.service.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AcceptDecline implements Initializable {
    @FXML
    private AnchorPane homepagepane;
    @FXML
    private Button decline;

    @FXML
    private Button accept;

    @FXML
    private ListView<JoinRequest> listinvitation;


    @FXML
    void Forum(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Forumview1.fxml"));
        homepagepane.getChildren().setAll(pane);
    }

    @FXML
    void Profile(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Profile.fxml"));
        homepagepane.getChildren().setAll(pane);
    }

    @FXML
    void HomePage(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        homepagepane.getChildren().setAll(pane);
    }

    @FXML
    void Store(ActionEvent event) throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("ViewStores.fxml"));
        homepagepane.getChildren().setAll(pane);
    }

    @FXML
    void Team(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("team-view.fxml"));
        homepagepane.getChildren().setAll(pane);
    }

    @FXML
    void Tournament(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ListTournaments.fxml"));
        homepagepane.getChildren().setAll(pane);
    }
    @FXML
    void actionaccept(ActionEvent event) {
        JoinRequest joinRequest = listinvitation.getSelectionModel().getSelectedItem();
        JoinRequests joinRequests = new JoinRequests();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Accept confirmation Alert");
        alert.setContentText("Do you really want to accept this ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            joinRequests.respondToJoinRequestById(joinRequest.getId(), true);
            Integer userId;
            if (joinRequest.getUserId() != null) {
                if (!joinRequest.isInvitation()) {
                    userId = joinRequest.getUserId();
                    joinRequest.setMessage(
                            new Teams().findById(joinRequest.getTeamId()).getName()
                            + " accepted your join request"
                    );
                } else {
                    userId = new Teams().findById(joinRequest.getTeamId()).getAdminId();
                    joinRequest.setMessage(
                            new Users().findById(joinRequest.getUserId()).getFullName()
                            + " accepted your invitation"
                    );
                }
            } else {
                if (!joinRequest.isInvitation()) {
                    Tournament t = new Tournaments().findById(joinRequest.getTournamentId());
                    userId = t.getAdminId();
                    joinRequest.setMessage(
                            t.getName() + " accepted your team (" + new Teams().findById(joinRequest.getTeamId()).getName() + ") join request"
                    );
                } else {
                    Team t = new Teams().findById(joinRequest.getTeamId());
                    userId = t.getAdminId();
                    joinRequest.setMessage(
                            t.getName() + " accepted your tournament (" + new Tournaments().findById(joinRequest.getTournamentId()).getName()
                            + ") invitation"
                    );
                }
            }
            try {
                JoinRequestNotification.notifMail(new Users().findById(userId).getEmail(), joinRequest);
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
        refresh();
    }

    @FXML
    void actiondeccline(ActionEvent event) {
        JoinRequest joinRequest = listinvitation.getSelectionModel().getSelectedItem();
        JoinRequests joinRequests = new JoinRequests();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Decline confirmation Alert");
        alert.setContentText("Do you really want to decline this ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            joinRequests.respondToJoinRequestById(joinRequest.getId(), false);
        }
        refresh();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refresh();
    }

    private void refresh() {
        listinvitation.getItems().clear();
        JoinRequests jrs = new JoinRequests();
        Teams teams = new Teams();
        Tournaments tournaments = new Tournaments();
        List<JoinRequest> ljr = new ArrayList<>();
        List<Integer> myTeamIds = teams.findAll("`admin_id`=" + Id.user).stream().map(t -> t.getId()).collect(Collectors.toList());
        List<Integer> myTournamentIds = tournaments.findAll("`admin_id`=" + Id.user).stream().map(t -> t.getId()).collect(Collectors.toList());

        // Add User Join Requests to My Teams
        ljr.addAll(
                jrs.findAll("`accepted` IS NULL AND `user_id` IS NOT NULL AND `invitation`=false").stream()
                        .filter(jr -> myTeamIds.indexOf(jr.getTeamId()) != -1)
                        .collect(Collectors.toList())
        );

        // Add Team Join Requests To My Tournaments
        ljr.addAll(
                jrs.findAll("`accepted` IS NULL AND `team_id` IS NOT NULL AND `invitation`=false").stream()
                        .filter(jr -> myTournamentIds.indexOf(jr.getTournamentId()) != -1)
                        .collect(Collectors.toList())
        );

        // Add Team Invitations
        ljr.addAll(
                jrs.findAll("`accepted` IS NULL AND `invitation`=true AND `user_id`=" + Id.user)
        );

        // Add Tournament Invitations To My Teams
        ljr.addAll(
                jrs.findAll("`accepted` IS NULL AND `tournament_id` IS NOT NULL AND `invitation`=true").stream()
                        .filter(jr -> myTeamIds.indexOf(jr.getTeamId()) != -1)
                        .collect(Collectors.toList())
        );

        listinvitation.getItems().addAll(ljr);
    }
}

