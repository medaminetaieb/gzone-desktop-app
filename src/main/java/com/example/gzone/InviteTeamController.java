package com.example.gzone;

import com.example.entity.JoinRequest;
import com.example.service.JoinRequests;
import com.example.service.Teams;
import com.example.service.Tournaments;
import com.example.service.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class InviteTeamController implements Initializable {

    @FXML
    private TextArea messagetxt;

    @FXML
    private Button sendinvitation;

    @FXML
    private TextField tfteam;

    @FXML
    private TextField tftournament;

    @FXML
    void Actionsendinvitation(ActionEvent event) throws IOException {
        if (!messagetxt.getText().isBlank()) {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText("Send Invitation");
            a.setContentText("Invite team to this tournament?");
            Optional<ButtonType> buttonType = a.showAndWait();
            if (buttonType.get().equals(ButtonType.OK)) {
                JoinRequest jr = new JoinRequest(null, null, Id.temp, Id.tournament, messagetxt.getText(), new Date(), null, null, true);
                new JoinRequests().insert(jr);
                String title = "Invitation sent!";
                String message = "wait for them to respond !";
                NotificationType notification = NotificationType.SUCCESS;
                TrayNotification tray = new TrayNotification();
                tray.setTitle(title);
                tray.setMessage(message);
                tray.setNotificationType(notification);
                tray.showAndDismiss(Duration.seconds(3));
            }
        } else {
            String title = "Failed!";
            String message = "Fill the form correctly !";
            NotificationType notification = NotificationType.ERROR;
            TrayNotification tray = new TrayNotification();
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotificationType(notification);
            tray.showAndDismiss(Duration.seconds(3));
        }
    }

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tftournament.setText(new Tournaments().findById(Id.tournament).getName());
        tftournament.setEditable(false);
        tfteam.setText(new Teams().findById(Id.temp).getName());
        tfteam.setEditable(false);
    }
}
