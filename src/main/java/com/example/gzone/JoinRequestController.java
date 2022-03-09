package com.example.gzone;

import com.example.entity.JoinRequest;
import com.example.entity.User;
import com.example.service.JoinRequests;
import com.example.service.Teams;
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

import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class JoinRequestController implements Initializable {

    final JoinRequests joinRequests = new JoinRequests();
    @FXML
    private AnchorPane teamviewanchor;
    @FXML
    private TextArea messagetxt;

    @FXML
    private Button sendrequest;

    @FXML
    private TextField tfuser;

    @FXML
    private TextField tfteam;

    @FXML
    void Forum(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Forumview1.fxml"));
        teamviewanchor.getChildren().setAll(pane);
    }

    @FXML
    void HomePage(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        teamviewanchor.getChildren().setAll(pane);
    }

    @FXML
    void Store(ActionEvent event) throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("ViewStores.fxml"));
        teamviewanchor.getChildren().setAll(pane);
    }

    @FXML
    void Team(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("team-view.fxml"));
        teamviewanchor.getChildren().setAll(pane);
    }

    @FXML
    void Tournament(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ListTournaments.fxml"));
        teamviewanchor.getChildren().setAll(pane);
    }


    @FXML
    void sendrequest(ActionEvent event) throws IOException {
        if (!messagetxt.getText().isBlank()) {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText("Send Request");
            a.setContentText("Send request to join this tournament");
            Optional<ButtonType> buttonType = a.showAndWait();

            if (buttonType.get().equals(ButtonType.OK)) {
                JoinRequest jr = new JoinRequest(null, Id.user, Id.team, null, messagetxt.getText(), new Date(), null, null, false);
                joinRequests.insert(jr);
                String title = "Request sent!";
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tfuser.setText(new Users().findById(Id.user).getFullName());
        tfuser.setEditable(false);
        tfteam.setText(new Teams().findById(Id.team).getName());
        tfteam.setEditable(false);
    }
}
