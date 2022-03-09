package com.example.gzone;

import com.example.entity.*;
import com.example.gzone.Id;
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
import java.util.Date;
import java.util.ResourceBundle;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class ModifyMatchController implements Initializable {
    @FXML
    private AnchorPane apModifyMatches;

    @FXML
    private Button bAddMatch;


    @FXML
    private DatePicker dpStartTime;
    
    @FXML
    private Label tTournamentName;
    @FXML
    private MenuButton mbRound;
    @FXML
    private MenuButton mbTeam1;
    @FXML
    private MenuButton mbTeam2;
    
    Match m;

    @FXML
    void addMatch(ActionEvent event) throws IOException {
        if (dpStartTime.getValue() != null) {
            m.setStartTime(new Date(java.sql.Date.valueOf(dpStartTime.getValue()).getTime()));
            new Matches().modify(m);
            String title = "Success!";
            String message = "Date Updated !";
            NotificationType notification = NotificationType.SUCCESS;
            TrayNotification tray = new TrayNotification();
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotificationType(notification);
            tray.showAndDismiss(Duration.seconds(3));
            bAddMatch.getScene().setRoot(FXMLLoader.load(getClass().getResource("ViewTournament.fxml")));
        } else {
            String title = "Not Modifying !";
            String message = "Select Match start date !";
            NotificationType notification = NotificationType.ERROR;
            TrayNotification tray = new TrayNotification();
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotificationType(notification);
            tray.showAndDismiss(Duration.seconds(3));
        }

    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        tTournamentName.setText(new Tournaments().findById(Id.tournament).getName());
        mbTeam1.setDisable(true);
        mbTeam2.setDisable(true);
        mbRound.setDisable(true);
        m = new Matches().findById(Id.match);
        mbTeam1.setText(new Teams().findById(m.getTeam1Id()).getName());
        mbTeam2.setText(new Teams().findById(m.getTeam2Id()).getName());
        mbRound.setText("Round "+m.getRound().toString());
    }
}
